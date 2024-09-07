package org.example.hellospring.exrate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.stream.Collectors;
import org.example.hellospring.payment.ExRateProvider;

public class WebApiExRateProvider implements ExRateProvider {

  @Override
  public BigDecimal getExRate(String currency) {
    String url = "https://open.er-api.com/v6/latest/" + currency;
    URI uri;
    try {
      uri = new URI(url);
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }

    String response;
    try {
      HttpURLConnection connection = (HttpURLConnection) uri.toURL().openConnection();
      // finally 를 사용하지 않고도 br.close() 실행
      try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream())) ) {
        response = br.lines().collect(Collectors.joining());
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    ObjectMapper mapper = new ObjectMapper();
    try {
      ExRateData data = mapper.readValue(response, ExRateData.class);
      return data.rates().get("KRW");
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}

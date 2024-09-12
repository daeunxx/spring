package org.example.hellospring.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.stream.Collectors;

public class SimpleApiExecutor implements ApiExecutor {

  @Override
  public String execute(URI uri) throws IOException {
    String response;
    HttpURLConnection connection = (HttpURLConnection) uri.toURL().openConnection();
    // finally 를 사용하지 않고도 br.close() 실행
    try (BufferedReader br = new BufferedReader(
        new InputStreamReader(connection.getInputStream()))) {
      response = br.lines().collect(Collectors.joining());
    }
    return response;
  }
}

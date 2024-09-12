package org.example.hellospring;

import org.example.hellospring.payment.Payment;
import org.example.hellospring.payment.PaymentService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Client {

  public static void main(String[] args) {
    BeanFactory beanFactory = new AnnotationConfigApplicationContext(PaymentConfig.class);
    PaymentService paymentService = beanFactory.getBean(PaymentService.class);

    Payment payment = paymentService.prepare(100L, "USD", java.math.BigDecimal.valueOf(50.7));
    System.out.println("payment = " + payment);
  }
}

package core.springbasic.beandefinition;

import core.springbasic.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanDefinitionTest {

  AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig .class);
  //GenericXmlApplicationContext context = new GenericXmlApplicationContext("appConfig.xml");

  @Test
  @DisplayName("빈 설정 메타 정보 확인")
  void findApplicationBean() {
    String[] beanDefinitionNames = context.getBeanDefinitionNames();
    for (String beanDefinitionName : beanDefinitionNames) {
      BeanDefinition beanDefinition = context.getBeanDefinition(beanDefinitionName);

      if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
        System.out.println("beanDefinitionName = " + beanDefinitionName + ", beanDefinition = " + beanDefinition);
      }
    }
  }

}

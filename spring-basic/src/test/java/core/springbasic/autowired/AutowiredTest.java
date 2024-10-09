package core.springbasic.autowired;

import core.springbasic.member.Member;
import jakarta.annotation.Nullable;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutowiredTest {

  @Test
  void autowiredOption() {
    ApplicationContext context = new AnnotationConfigApplicationContext(TestBean.class);
  }

  static class TestBean {

    // 자동 주입할 대상이 없으면 수정자 메서드 자체가 호출되지 않음
    @Autowired(required = false)
    public void setNoBean1(Member noBean1) {
      System.out.println("noBean1 = " + noBean1);
    }

    // 자동 주입할 대상이 없으면 null 입력
    @Autowired
    public void setNoBean2(@Nullable Member noBean2) {
      System.out.println("noBean2 = " + noBean2);
    }

    // 자동 주입할 대상이 없으면 Optional.empty 입력
    @Autowired
    public void setNoBean3(Optional<Member> noBean3) {
      System.out.println("noBean3 = " + noBean3);
    }
  }

}

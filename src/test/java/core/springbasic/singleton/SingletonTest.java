package core.springbasic.singleton;

import static org.assertj.core.api.Assertions.assertThat;

import core.AppConfig;
import core.springbasic.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SingletonTest {

  @Test
  @DisplayName("스프링 없는 순수한 DI 컨테이너")
  void pureContainer() {
    AppConfig appConfig = new AppConfig();

    // 호출할 때마다 객체 생성
    MemberService memberService1 = appConfig.memberService();
    MemberService memberService2 = appConfig.memberService();

    System.out.println("memberService1 = " + memberService1);
    System.out.println("memberService2 = " + memberService2);
    assertThat(memberService1).isNotSameAs(memberService2);
  }
}

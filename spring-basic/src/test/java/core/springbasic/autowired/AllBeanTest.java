package core.springbasic.autowired;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import core.springbasic.AutoAppConfig;
import core.springbasic.discount.DiscountPolicy;
import core.springbasic.member.Grade;
import core.springbasic.member.Member;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AllBeanTest {

  @Test
  void findAllBean() {
    ApplicationContext context = new AnnotationConfigApplicationContext(
        AutoAppConfig.class, DiscountService.class);

    DiscountService discountService = context.getBean(DiscountService.class);
    Member member = new Member(1L, "userA", Grade.VIP);
    int fixDiscountPolicy = discountService.discount(member, 10000, "fixDiscountPolicy");
    int rateDiscountPolicy = discountService.discount(member, 20000, "rateDiscountPolicy");

    assertThat(discountService).isInstanceOf(DiscountService.class);
    assertThat(fixDiscountPolicy).isEqualTo(1000);
    assertThat(rateDiscountPolicy).isEqualTo(2000);
  }

  static class DiscountService {

    private final Map<String, DiscountPolicy> policyMap;
    private final List<DiscountPolicy> policyList;

    public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policyList) {
      this.policyMap = policyMap;
      this.policyList = policyList;
      System.out.println("policyMap = " + policyMap);
      System.out.println("policyList = " + policyList);
    }

    public int discount(Member member, int price, String discountCode) {
      DiscountPolicy discountPolicy = policyMap.get(discountCode);
      return discountPolicy.discount(member, price);
    }
  }
}

package hello.core;

import hello.core.discount.DiscountPolish;
import hello.core.discount.FixDiscountPolish;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    // @Bean memberService --> new memoryMemberRepository()
    // @Bean orderService --> new MemoryMemberRepository()
    // ----> 각각 new MemoryMemberRepository()를 호출하기 때문에 다른 인스턴스가 생성되어야 하는게 아닌가 ? 싱글톤 규칙을 어기는게 아닌가 ??

    // 메소드 호출 순서 (순서대로)
    // call AppConfig.memberService
    // call AppConfig.memberRepository
    // call AppConfig.memberRepository
    // call AppConfig.orderService
    // call AppConfig.memberRepository

    // 메소드 호출 결과
    // call AppConfig.memberService
    // call AppConfig.memberRepository
    // call AppConfig.orderService

    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }
    @Bean
    public MemoryMemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }
    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolish());
    }
    @Bean
    public DiscountPolish discountPolish() {
//        return new FixDiscountPolish();
        return new RateDiscountPolicy();
    }


}

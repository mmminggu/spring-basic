package hello.core;

import hello.core.discount.DiscountPolish;
import hello.core.discount.FixDiscountPolish;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {
    public MemberService memberService() {

        return new MemberServiceImpl(memberRepository());
    }

    private static MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolish());
    }

    public DiscountPolish discountPolish() {
//        return new FixDiscountPolish();
        return new RateDiscountPolicy();
    }


}
package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

public class FixDiscountPolish implements DiscountPolish {

    private int discountFixAmount = 1000;   // 고정 할인 금액
    @Override
    public int discount(Member member, int price) {
        if(Grade.VIP == member.getGrade()){
            return discountFixAmount;
        }else {
            return 0;
        }
    }
}

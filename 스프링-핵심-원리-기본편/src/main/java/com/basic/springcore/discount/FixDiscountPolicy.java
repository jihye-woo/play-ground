package com.basic.springcore.discount;

import com.basic.springcore.member.Grade;
import com.basic.springcore.member.Member;

public class FixDiscountPolicy implements DiscountPolicy {

    // 1000원 할인
    private int discountFixAmount = 1000;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        }
        return 0;
    }
}

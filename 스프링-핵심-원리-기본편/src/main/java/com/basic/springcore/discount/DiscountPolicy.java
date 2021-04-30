package com.basic.springcore.discount;

import com.basic.springcore.member.Member;

public interface DiscountPolicy {

    /**
    * @return 할인 대상 금액
    *
     **/

    int discount(Member member, int price);
}

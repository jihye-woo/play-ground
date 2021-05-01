package com.basic.springcore;

import com.basic.springcore.discount.DiscountPolicy;
import com.basic.springcore.discount.FixDiscountPolicy;
import com.basic.springcore.member.MemberRepository;
import com.basic.springcore.member.MemberService;
import com.basic.springcore.member.MemberServiceImpl;
import com.basic.springcore.member.MemoryMemberRepository;
import com.basic.springcore.order.OrderService;
import com.basic.springcore.order.OrderServiceImpl;

public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(
                memberRepository(),
                discountPolicy());
    }

    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    private DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }
}

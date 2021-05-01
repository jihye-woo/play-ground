package com.basic.springcore;

import com.basic.springcore.discount.FixDiscountPolicy;
import com.basic.springcore.member.MemberService;
import com.basic.springcore.member.MemberServiceImpl;
import com.basic.springcore.member.MemoryMemberRepository;
import com.basic.springcore.order.OrderService;
import com.basic.springcore.order.OrderServiceImpl;

public class AppConfig {

    public OrderService orderService() {
        return new OrderServiceImpl(
                new MemoryMemberRepository(),
                new FixDiscountPolicy());
    }

    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }
}

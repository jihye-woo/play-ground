package com.basic.springcore;

import com.basic.springcore.member.Grade;
import com.basic.springcore.member.Member;
import com.basic.springcore.member.MemberService;
import com.basic.springcore.member.MemberServiceImpl;
import com.basic.springcore.order.OrderService;
import com.basic.springcore.order.OrderServiceImpl;
import com.basic.springcore.order.Order;

public class OrderApp {

    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);

        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println("order = " + order);
    }
}

package com.basic.springcore;

import com.basic.springcore.member.Grade;
import com.basic.springcore.member.Member;
import com.basic.springcore.member.MemberService;
import com.basic.springcore.order.OrderService;
import com.basic.springcore.order.Order;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {

    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

//        MemberService memberService = appConfig.memberService();
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

//        OrderService orderService = appConfig.orderService();
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);

        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println("order = " + order);
    }
}

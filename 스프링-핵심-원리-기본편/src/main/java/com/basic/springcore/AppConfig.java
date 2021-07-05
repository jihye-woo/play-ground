package com.basic.springcore;

import com.basic.springcore.discount.DiscountPolicy;
import com.basic.springcore.discount.RateDiscountPolicy;
import com.basic.springcore.member.MemberRepository;
import com.basic.springcore.member.MemberService;
import com.basic.springcore.member.MemberServiceImpl;
import com.basic.springcore.member.MemoryMemberRepository;
import com.basic.springcore.order.OrderService;
import com.basic.springcore.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
}

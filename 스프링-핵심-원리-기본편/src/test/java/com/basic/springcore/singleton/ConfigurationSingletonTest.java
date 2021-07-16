package com.basic.springcore.singleton;

import com.basic.springcore.AppConfig;
import com.basic.springcore.member.MemberRepository;
import com.basic.springcore.member.MemberServiceImpl;
import com.basic.springcore.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = applicationContext.getBean("memberSAuervice", MemberServiceImpl.class);
        OrderServiceImpl orderService = applicationContext.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = applicationContext.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("memberService -> memberRepository1 = " + memberRepository1);
        System.out.println("orderService -> memberRepository2 = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);

        Assertions.assertThat(memberRepository1).isSameAs(memberRepository2);
    }


    @Test
    void configurationDeep(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = applicationContext.getBean(AppConfig.class);

        System.out.println("bean = " + bean);
    }
}

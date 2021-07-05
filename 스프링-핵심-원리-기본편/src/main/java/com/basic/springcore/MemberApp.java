package com.basic.springcore;

import com.basic.springcore.member.Grade;
import com.basic.springcore.member.Member;
import com.basic.springcore.member.MemberService;
import com.basic.springcore.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        // 어노테이션 기반으로 configuration을 등록했기 때문에 AnnotationConfigApplicationContext 사용
        // 스프링이 AppConfig에 있는 환경설정 정보를 확인해서 선언한 빈들을 모두 스프링 컨테이너에다가 넣어서 관리해준다
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        // ApplicationContext를 통해 이미 등록 및 생성된 빈을 가지고 온다.
        // "memberService" 빈으로 등록한 메소드 명과 동일해야 함
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.BASIC);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new findMember = " + findMember.getName());
        System.out.println("find Member = " + member.getName());

    }
}

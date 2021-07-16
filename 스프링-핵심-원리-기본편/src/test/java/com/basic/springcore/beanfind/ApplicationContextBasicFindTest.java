package com.basic.springcore.beanfind;

import com.basic.springcore.AppConfig;
import com.basic.springcore.member.MemberService;
import com.basic.springcore.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName(){
        // getBean(빈 이름, 타입)
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("이름 없이 타입으로 빈 조회")
    void findBeanByType(){
        // getBean(타입)
        // 편하긴 한데 같은 타입의 빈이 여러 개 있을 경우 문제가 생길 수 있다.
        MemberService memberService = applicationContext.getBean(MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구체 타입으로 빈 조회")
    void findBeanByConcreteType(){
        // 이전까지는 인터페이스인 MemberService로 조회했다.
        // 이 경우에는 실제 조회 대상이 해당 인터페이스의 구현이 조회 대상이 된다.
        // 하! 지! 만! 구체 클래스의 타입으로 조회를 해도 상관없다!
        // AppConfig에서 빈으로 등록된 메소드 memberService()의 리턴 타입이 인터페이스 MemberService 이더라도 상관 없다!
        // WHY? 어차피 빈을 찾을 때는 인스턴스 타입(new 를 통해서 선언된 타입)으로 찾기 때문!

        // BUT! 역할과 구현을 구분해야 하고 우리의 코드는 역할에 의존해야 한다.
        // 이런 맥락에서 구체 타입으로 빈을 조회하는 것은 구현에 의존하는 코드가 된다..ㅠ
        // 좋은 코드는 아니다..ㅠ 구체 타입으로도 빈이 조회가 가능하다는 점만 알고 넘어가자
        MemberService memberService = applicationContext.getBean("memberService", MemberServiceImpl.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    // 처음 테스트를 만들 때는 실패하는 테스트를 만들어야 한다
    @Test
    @DisplayName("빈 이름으로 조회")
    void findByNameX(){
        assertThrows(NoSuchBeanDefinitionException.class,
                () -> applicationContext.getBean("XXX", MemberService.class));
    }
}

package com.basic.springcore.beanfind;

import com.basic.springcore.member.MemberRepository;
import com.basic.springcore.member.MemoryMemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextSameBeanFindTest {

    // SameBeanConfig 안에 있는 memberRepository1, memberRepository2을 빈으로 등록한다.
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 중복 오류가 발생한다.")
    void findBeanByTypeDuplicate(){
        assertThrows(NoUniqueBeanDefinitionException.class,
                () -> applicationContext.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 빈 이름을 지정하면 된다.")
    void findBeanByName() {
        // getBean() 메소드 호출 시, 타입과 더불어 빈 이름 "memberRepository1"을 통해 특정한 빈을 찾는다.
        MemberRepository memberRepository = applicationContext.getBean("memberRepository1", MemberRepository.class);
        assertThat(memberRepository).isInstanceOf(MemberRepository.class);
    }

    @Test
    @DisplayName("특정 타입을 모두 조회하기")
    void findBeanByType() {
        // 왜 이런걸 알아야 하나요?
        // @Autowired 와 같은 자동 주입을 할 때 이런 기능이 사용되기 때문에 알아놓는 것이 좋다.
        Map<String, MemberRepository> beans = applicationContext.getBeansOfType(MemberRepository.class);
        for(String key : beans.keySet()){
            System.out.println("ket = " + key + " value = " + beans.get(key));
        }
        System.out.println("beans = " + beans);
        assertThat(beans.size()).isEqualTo(2);
    }


    @Configuration
    static class SameBeanConfig {

        @Bean
        public MemberRepository memberRepository1(){
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2(){
            return new MemoryMemberRepository();
        }
    }
}

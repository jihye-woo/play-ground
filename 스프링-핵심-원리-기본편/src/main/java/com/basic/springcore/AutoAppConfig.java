package com.basic.springcore;

import com.basic.springcore.member.MemberRepository;
import com.basic.springcore.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class))
// 기존에 @Configuration 어노테이션을 붙인 설정 정보 클래스도 자동으로 등록된다
// 때문에 앞에서 만든 AppConfig 혹은 TestConfig 등 앞에서 만들어놓았던 설정 정보가 한번에 실행되어 버리기 때문에 excludeFilters를 사용해서 설정정보는 컴포넌트 스캔 대상에서 제외했다.
// 보통은 이런 식으로 @Configuration 설정 클래스를 컴포넌트 스캔 대상에서 제외하지 않는다. -> 한 마디로 이전 예제 코드를 살리기 위한 방법

public class AutoAppConfig {

    // 수동으로 등록하는 빈과 같은 이름을 가지도록 빈을 설정
    // 해당 빈은 자동으로 등록
    // 그래도 에러가 발생하지 않는다.
    @Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}

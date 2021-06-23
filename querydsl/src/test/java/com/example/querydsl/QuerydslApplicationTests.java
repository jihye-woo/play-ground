package com.example.querydsl;

import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class QuerydslApplicationTests {

    @Autowired
    private AcademyRepository academyRepository;

    @Autowired
    private AcademyRepositorySupport academyRepositorySupport;

    @Autowired
    private AcademyQueryRepository academyQueryRepository;

    @AfterEach
    public void tearDown() throws Exception {
        academyRepository.deleteAllInBatch();
    }

    @Test
    public void querydsl_기본_기능_확인() {
        //given
        String name = "jojoldu";
        String address = "jojoldu@gmail.com";
        academyRepository.save(new Academy(name, address));

        //when
        List<Academy> result = academyRepositorySupport.findByName(name);

        //then
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getAddress()).isEqualTo(address);
    }

//    @Test
//    public void querydsl_기본_기능_확인2() {
//        //given
//        String name = "jojoldu";
//        String address = "jojoldu@gmail.com";
//        academyRepository.save(new Academy(name, address));
//
//        //when
//        List<Academy> result = academyQueryRepository.findByName(name);
//
//        //then
//
//        assertThat(result.size(), is(1));
//        assertThat(result.get(0).getAddress(), is(address));
//    }
}

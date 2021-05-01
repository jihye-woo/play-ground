package com.basic.springcore.discount;

import com.basic.springcore.member.Grade;
import com.basic.springcore.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    // 성공 테스트
    @Test
    @DisplayName("VIP는 10% 할인이 적용 되어야 한다")
    void vip_can_get_discount(){
        // given
        Member vip = new Member(1L, "vip", Grade.VIP);
        // when
        int discount = discountPolicy.discount(vip, 10000);
        // then
        assertThat(discount).isEqualTo(1000);
    }

    // 실패 테스트
    @Test
    void basic_cannot_get_discount(){
        // given
        Member basic = new Member(2L, "basic", Grade.BASIC);
        // when
        int discount = discountPolicy.discount(basic, 10000);
        // then
        assertThat(discount).isEqualTo(1000);
    }
}
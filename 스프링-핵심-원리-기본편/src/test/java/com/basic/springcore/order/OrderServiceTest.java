package com.basic.springcore.order;

import com.basic.springcore.member.Grade;
import com.basic.springcore.member.Member;
import com.basic.springcore.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    MemberServiceImpl memberService = new MemberServiceImpl();
    OrderService orderService = new OrderServiceImpl();

    @Test
    void createOrder() {
        // given
        long memberId = 1L;
        String name = "memberA";
        Member member = new Member(memberId, name, Grade.VIP);

        // when
        memberService.join(member);
        Order order = orderService.createOrder(memberId, name, 10000);

        // then
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}

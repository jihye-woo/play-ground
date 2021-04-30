package com.basic.springcore.order;

import com.basic.springcore.discount.DiscountPolicy;
import com.basic.springcore.discount.FixDiscountPolicy;
import com.basic.springcore.member.Member;
import com.basic.springcore.member.MemberRepository;
import com.basic.springcore.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);

        /** < 단일 책임 원칙이 잘 지켜짐 >
         * WHY?
         * - discountPolicy에서 할인에 대한 책임을 맡고 있기 때문에,
         * - OrderService에서는 온리 주문에 대한 처리만 할 수 있게 되었다.
         **/
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}

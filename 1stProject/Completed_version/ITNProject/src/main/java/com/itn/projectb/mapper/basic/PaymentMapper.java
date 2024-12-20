package com.itn.projectb.mapper.basic;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.itn.projectb.vo.basic.MemberVO;
import com.itn.projectb.vo.basic.PaymentVO;

@Mapper
public interface PaymentMapper {
	public PaymentVO selectPaymentByBoid(String boid);		//특정 결제 정보를 조회
	public int insertPayment(PaymentVO paymentVO);
	public List<?> selectPaymentList(MemberVO memberVO);
	public void updatePaymentDate(String boid);
}

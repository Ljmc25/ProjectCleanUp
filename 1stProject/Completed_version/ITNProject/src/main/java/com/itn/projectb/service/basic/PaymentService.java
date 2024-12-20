package com.itn.projectb.service.basic;

import java.util.List;

import com.itn.projectb.vo.basic.MemberVO;
import com.itn.projectb.vo.basic.PaymentVO;

public interface PaymentService {
	public int insertPayment(PaymentVO paymentVO);
	public List<?> selectPaymentList(MemberVO memberVO);
	public void updatePaymentDate(String boid);
}

package com.itn.projectb.service.impl.basic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itn.projectb.mapper.basic.PaymentMapper;
import com.itn.projectb.service.basic.PaymentService;
import com.itn.projectb.vo.basic.MemberVO;
import com.itn.projectb.vo.basic.PaymentVO;

@Service
public class PaymentServiceImpl implements PaymentService{
	
	@Autowired
	PaymentMapper paymentMapper;
	
	@Override
	public int insertPayment(PaymentVO paymentVO) {
		paymentMapper.insertPayment(paymentVO);
		return 0;
	}

	@Override
	public List<?> selectPaymentList(MemberVO memberVO) {
		// TODO Auto-generated method stub
		return paymentMapper.selectPaymentList(memberVO);
	}

	@Override
	public void updatePaymentDate(String boid) {
		paymentMapper.updatePaymentDate(boid);	
	}
	
}

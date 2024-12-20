package com.itn.projectb.controller.basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itn.projectb.service.basic.PaymentService;
import com.itn.projectb.vo.basic.PaymentVO;

@Controller
public class PaymentController {
	
	@Autowired
	PaymentService paymentService;
	
	@ResponseBody
	@PostMapping("/savePayment")
	public String savePayment(@RequestBody PaymentVO paymentVO) {
		int result = paymentService.insertPayment(paymentVO);
		String str = Integer.toString(result);
		return str;
	}
}

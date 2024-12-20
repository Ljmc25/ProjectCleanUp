package com.itn.projectb.vo.basic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PaymentVO {
	private String paid;		
	private String methodType;	
	private int totalPrice;		
	private String email; 
	private String boid;  
	private String bookNum;
	private String insertTime;
	private String updateTime;
}

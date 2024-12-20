package com.itn.projectb.vo.basic;

import com.itn.projectb.vo.common.Criteria;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@SuppressWarnings("serial")
public class MemberVO extends Criteria {
	private String email;
	private String password;
	private String phone;
	private String grade;
	private String insertTime;
	private String updateTime;
}

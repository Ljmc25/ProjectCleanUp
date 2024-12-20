package com.itn.projectb.vo.advanced;

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
public class InquiryAnswerVO extends Criteria {
	private String anid;
	private String contents;
	private String inid;
	private String writer;
	private String insertTime;
	private String updateTime;
}

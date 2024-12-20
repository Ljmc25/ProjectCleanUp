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
public class NoticeVO extends Criteria {
	private String noid;
	private String title;
	private String contents;
	private String writer;
	private String fileUrl;
	private String insertTime;
	private String updateTime;
}
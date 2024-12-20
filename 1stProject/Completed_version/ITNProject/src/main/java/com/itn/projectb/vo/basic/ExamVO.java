package com.itn.projectb.vo.basic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ExamVO {
	private String uuid, title, contents, insertTime, updateTime;
	
	public ExamVO(String title, String contents) {
		this.title = title;
		this.contents = contents;
	}
}

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
public class ProductReviewVO {
	// 영화 실관람평 리뷰 관련 VO
	private String prid;
	private String pdid;
	private String review;
	private String writer;
	private String insertTime;
	private String updateTime;
	
}

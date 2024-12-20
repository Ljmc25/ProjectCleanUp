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
public class BookVO {
	private String boid;
	private String bookNum;
	private String email;
	private int selectNum;
	private String selectSeat;
	private int childrenNum; 
	private int teenagerNum;
	private int adultNum;
	private int agedNum; 
	private String scid;
	private String title;
	private int totalprice;
	private String city;
	private String cinema;
	private String branch;
	private int theater;
	private String address;
	private String showingTime;
	private String startTime;
	private String endTime;
	private int runningTime;
	private String imageSmallUrl;
	private int childrenPrice;
	private int teenagerPrice;
	private int adultPrice;
	private int agedPrice;
	private String insertTime;
	private String updateTime;
}

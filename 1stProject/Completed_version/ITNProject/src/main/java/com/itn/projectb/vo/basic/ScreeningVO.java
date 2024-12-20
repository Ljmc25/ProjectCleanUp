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
public class ScreeningVO {
	private String scid;
    private String title;
    private String cinema;
    private String branch; 
    private int theater;    
    private int seats;
    private int remainSeats;
    private String showingTime;
    private String insertTime;
    private String updateTime;
    private String city;
    private String startTime;
    private String endTime;
    private String address;
    private String reservedSeats;
}

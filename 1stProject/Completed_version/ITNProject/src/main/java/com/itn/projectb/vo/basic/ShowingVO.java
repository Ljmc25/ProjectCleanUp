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
public class ShowingVO {
	public int theater, seats, remainSeats;
	public String title, city, cinema, branch, address,
				showingTime, startTime, endTime;
}

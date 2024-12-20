package com.itn.projectb.service.basic;

import java.util.List;
import java.util.Map;

import com.itn.projectb.vo.basic.ScreeningVO;

public interface ScreeningService {
	
	 public List<ScreeningVO> getProductsByDate(String date); // 날짜에 맞는 영화 목록 가져오기

	 public List<ScreeningVO> getScreeningInfo(Map<String, Object> params);
    
	 public ScreeningVO getScreeningById(String scid);
	 
	 public void updateReservedSeat(ScreeningVO screeningVO);
	 
	 public void updateRemainSeats(ScreeningVO screeningVO);
	 
	 public ScreeningVO selectScreeningScid(String scid);
	 
	 public void updateCencalData(ScreeningVO screeningVO);
}
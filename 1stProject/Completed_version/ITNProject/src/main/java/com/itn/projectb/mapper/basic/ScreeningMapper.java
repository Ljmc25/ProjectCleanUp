package com.itn.projectb.mapper.basic;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import com.itn.projectb.vo.basic.ScreeningVO;

@Mapper
public interface ScreeningMapper {
	 public List<ScreeningVO>getProductsByDate(String date);
		
	 public List<ScreeningVO> getScreeningInfo(Map<String, Object> params);
	 
	 // SCID로 상영 정보 조회
	 public ScreeningVO getScreeningById(String scid);
	 
	 public void updateReservedSeat(ScreeningVO screeningVO);
	 
	 public void updateRemainSeats(ScreeningVO screeningVO);
	 
	 public ScreeningVO selectScreeningScid(String scid);
	 
	 public void updateCencalData(ScreeningVO screeningVO);
}

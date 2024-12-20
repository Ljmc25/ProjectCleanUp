package com.itn.projectb.service.impl.basic;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.itn.projectb.mapper.basic.ScreeningMapper;
import com.itn.projectb.service.basic.ScreeningService;
import com.itn.projectb.vo.basic.ScreeningVO;
@Service
public class ScreeningServiceImpl implements ScreeningService {
	
    @Autowired
    ScreeningMapper screeningMapper;

//    극장지역 
    @Override
    public List<ScreeningVO> getProductsByDate(String date) {
        return screeningMapper.getProductsByDate(date);
    }

    @Override
    public List<ScreeningVO> getScreeningInfo(Map<String, Object> params) {
        return screeningMapper.getScreeningInfo(params);
    }

	@Override
	public ScreeningVO getScreeningById(String scid) {
		
		return screeningMapper.getScreeningById(scid);
	}

	@Override
	public void updateReservedSeat(ScreeningVO screeningVO) {
		
		screeningMapper.updateReservedSeat(screeningVO);
	}

	@Override
	public void updateRemainSeats(ScreeningVO screeningVO) {
		
		screeningMapper.updateRemainSeats(screeningVO);
	}

	@Override
	public ScreeningVO selectScreeningScid(String scid) {
		// TODO Auto-generated method stub
		return screeningMapper.selectScreeningScid(scid);
	}

	@Override
	public void updateCencalData(ScreeningVO screeningVO) {
		// TODO Auto-generated method stub
		screeningMapper.updateCencalData(screeningVO);
	}


	
	

	
    
    
    
    
    
    
}

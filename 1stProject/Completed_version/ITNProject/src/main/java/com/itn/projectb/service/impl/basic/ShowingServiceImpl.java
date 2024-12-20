package com.itn.projectb.service.impl.basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itn.projectb.mapper.basic.ShowingMapper;
import com.itn.projectb.service.basic.ShowingService;
import com.itn.projectb.vo.basic.ShowingVO;

@Service
public class ShowingServiceImpl implements ShowingService{
	
	@Autowired
	ShowingMapper showingMapper;

	@Override
	public void createShowing(ShowingVO showingVO) {
		showingMapper.createShowing(showingVO);	
	}

}

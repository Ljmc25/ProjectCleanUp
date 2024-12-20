package com.itn.projectb.service.impl.advanced;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itn.projectb.mapper.advanced.InquiryAnswerMapper;
import com.itn.projectb.service.advanced.InquiryAnswerService;
import com.itn.projectb.vo.advanced.InquiryAnswerVO;
import com.itn.projectb.vo.common.Criteria;

@Service
public class InquiryAnswerServiceImpl implements InquiryAnswerService {

	@Autowired
	InquiryAnswerMapper inquiryAnswerMapper;

	@Override
	public List<?> selectCt4List(Criteria searchVO) throws Exception {
		// TODO Auto-generated method stub
		return inquiryAnswerMapper.selectCt4List(searchVO);
	}

	@Override
	public int selectCt4ListTotCnt(Criteria searchVO) {
		// TODO Auto-generated method stub
		return inquiryAnswerMapper.selectCt4ListTotCnt(searchVO);
	}

	@Override
	public int insertCt4(InquiryAnswerVO inquiryAnswerVO) throws Exception {
		// TODO Auto-generated method stub
		return inquiryAnswerMapper.insert(inquiryAnswerVO);
	}

	@Override
	public InquiryAnswerVO selectCt4(String anid) throws Exception {
		// TODO Auto-generated method stub
		InquiryAnswerVO inquiryAnswerVO = inquiryAnswerMapper.selectCt4(anid);
		return inquiryAnswerVO;
	}
}

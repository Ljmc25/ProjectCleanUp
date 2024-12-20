package com.itn.projectb.service.impl.advanced;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itn.projectb.mapper.advanced.InquiryMapper;
import com.itn.projectb.service.advanced.InquiryService;
import com.itn.projectb.vo.advanced.InquiryAnswerVO;
import com.itn.projectb.vo.advanced.InquiryVO;
import com.itn.projectb.vo.common.Criteria;

@Service
public class InquiryServiceImpl implements InquiryService {

	@Autowired
	InquiryMapper inquiryMapper;

	@Override
	public void insertCt(InquiryVO inquiryVO) throws Exception {
		// TODO Auto-generated method stub 인서트
		inquiryMapper.insert(inquiryVO);
	}

	@Override
	public List<?> selectCtList(Criteria searchVO) throws Exception {
		// TODO Auto-generated method stub 전체조회
		return inquiryMapper.selectCtList(searchVO);
	}

	@Override
	public int selectCtListTotCnt(Criteria searchVO) {
		// TODO Auto-generated method stub 페이징
		return inquiryMapper.selectCtListTotCnt(searchVO);
	}

	@Override
	public void deleteCt(InquiryVO inquiryVO) throws Exception {
		// TODO Auto-generated method stub 삭제
		inquiryMapper.delete(inquiryVO);
		
	}

	@Override
	public InquiryVO selectCt(String inid) throws Exception {
		// TODO Auto-generated method stub 상세보기
	       InquiryVO inquiryVO = inquiryMapper.selectCt(inid);
		return inquiryVO;
	}

	@Override
	public void updateCt(InquiryVO inquiryVO) throws Exception {
		// TODO Auto-generated method stub 수정
		inquiryMapper.update(inquiryVO);
		
	}

	@Override
	public InquiryAnswerVO selectAn(String inid) {
		// TODO Auto-generated method stub
		return inquiryMapper.selectAn(inid);
	}
	
}

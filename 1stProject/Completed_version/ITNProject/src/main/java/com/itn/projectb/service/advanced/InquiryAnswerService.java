package com.itn.projectb.service.advanced;

import java.util.List;

import com.itn.projectb.vo.advanced.InquiryAnswerVO;

import com.itn.projectb.vo.common.Criteria;

public interface InquiryAnswerService {
	public List<?> selectCt4List(Criteria searchVO)  throws Exception; // 전체조회 함수
	public int selectCt4ListTotCnt(Criteria searchVO); // 페이징
	public int insertCt4(InquiryAnswerVO inquiryAnswerVO) throws Exception;
	public InquiryAnswerVO selectCt4(String anid) throws Exception; // 상세보기	
}

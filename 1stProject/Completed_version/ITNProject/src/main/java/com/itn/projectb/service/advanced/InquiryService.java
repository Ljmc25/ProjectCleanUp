package com.itn.projectb.service.advanced;

import java.util.List;

import com.itn.projectb.vo.advanced.InquiryAnswerVO;
import com.itn.projectb.vo.advanced.InquiryVO;
import com.itn.projectb.vo.common.Criteria;

public interface InquiryService {
	public void insertCt(InquiryVO inquiryVO) throws Exception; // insert 함수
	public List<?> selectCtList(Criteria searchVO)  throws Exception; // 전체조회 함수
	public int selectCtListTotCnt(Criteria searchVO); // 페이징
	public void deleteCt(InquiryVO inquiryVO) throws Exception; //삭제
	public  InquiryVO selectCt(String inid) throws Exception; // 상세보기
	public InquiryAnswerVO selectAn(String inid);
	public void updateCt(InquiryVO inquiryVO) throws Exception; // 수정 함수
}

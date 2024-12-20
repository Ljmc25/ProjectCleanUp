package com.itn.projectb.service.advanced;

import java.util.List;

import com.itn.projectb.vo.advanced.FaqVO;
import com.itn.projectb.vo.common.Criteria;

public interface FaqService {
	public List<?> selectCt2List(Criteria searchVO)  throws Exception; // 전체조회 함수
	public int selectCt2ListTotCnt(Criteria searchVO); // 페이징
	public FaqVO selectCt2(String faid) throws Exception; // 상세보기
	 
	public List<?> selectQnaList(Criteria searchVO) throws Exception;
	public int selectQnaListTotCnt(Criteria searchVO);
	public void insertQna(FaqVO qnaVO) throws Exception;
	public FaqVO selectQna(String faid) throws Exception;
	public void updateQnaVO(FaqVO qnaVO) throws Exception;
	public void deleteQnaVO(FaqVO qnaVO) throws Exception;
}

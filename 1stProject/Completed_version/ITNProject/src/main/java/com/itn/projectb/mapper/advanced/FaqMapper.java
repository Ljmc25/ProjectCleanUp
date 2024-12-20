package com.itn.projectb.mapper.advanced;

import java.util.List;


import org.apache.ibatis.annotations.Mapper;

import com.itn.projectb.vo.advanced.FaqVO;

import com.itn.projectb.vo.common.Criteria;

@Mapper
public interface FaqMapper {
	public List<?> selectCt2List(Criteria searchVO);  //전체조회
	public int selectCt2ListTotCnt(Criteria searchVO); //페이징
	public FaqVO selectCt2(String faid);  //상세조회
	
	// 관리자 페이지
	public List<?> selectQnaList(Criteria searchVO);
	public int selectQnaListTotCnt(Criteria searchVO);
	public int insert(FaqVO faqVO); 						// insert 함수
	public FaqVO selectQna(String faid);						// 상세조회함수
	public int update(FaqVO faqVO); 						// update함수 
	public int delete(FaqVO faqVO);
}

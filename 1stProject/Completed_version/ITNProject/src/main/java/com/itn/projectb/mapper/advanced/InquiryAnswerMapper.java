package com.itn.projectb.mapper.advanced;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.itn.projectb.vo.advanced.InquiryAnswerVO;

import com.itn.projectb.vo.common.Criteria;

@Mapper
public interface InquiryAnswerMapper {
	public List<?> selectCt4List(Criteria searchVO);  //전체조회
	public int selectCt4ListTotCnt(Criteria searchVO); //페이징
	public int insert(InquiryAnswerVO inquiryAnswerVO); // insert 함수
	public InquiryAnswerVO selectCt4(String inid); // 상세조회 함수
}

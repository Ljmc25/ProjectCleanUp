package com.itn.projectb.mapper.advanced;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.itn.projectb.mapper.advanced.InquiryMapper;
import com.itn.projectb.vo.advanced.InquiryAnswerVO;
import com.itn.projectb.vo.advanced.InquiryVO;
import com.itn.projectb.vo.common.Criteria;

@Mapper
public interface InquiryMapper {
	public int insert(InquiryVO inquiryVO); // insert 함수
	public List<?> selectCtList(Criteria searchVO); //전체조회
	public int selectCtListTotCnt(Criteria searchVO); 
	public int delete(InquiryVO inquiryVO); // 삭제 
	public InquiryVO selectCt(String inid); // 상세조회 함수
	public InquiryAnswerVO selectAn(String inid);
	public int update(InquiryVO inquiryVO); // update 함수
}

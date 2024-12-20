package com.itn.projectb.mapper.basic;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.itn.projectb.vo.basic.ExamVO;
import com.itn.projectb.vo.common.Criteria;

@Mapper
public interface ExamMapper {
	public List<?> selectExamList(Criteria searchVO);
	public int selectExamListTotCnt(Criteria searchVO);
	public int insertExam(ExamVO examVO);
	public ExamVO selectExam(String uuid);
	public int update(ExamVO examVO);
	public int delete(ExamVO examVO);
	
}

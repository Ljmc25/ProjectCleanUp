package com.itn.projectb.service.basic;

import java.util.List;

import com.itn.projectb.vo.basic.ExamVO;
import com.itn.projectb.vo.common.Criteria;

public interface ExamService {
	public List<?> selectExamList(Criteria searchVO); 
	public int selectExamListTotCnt(Criteria searchVO);
	public void insertExam(ExamVO examVO);
	public ExamVO selectExam(String uuid); 
	public void updateExam(ExamVO examVO);
	public void deleteExam(ExamVO examVO);
}

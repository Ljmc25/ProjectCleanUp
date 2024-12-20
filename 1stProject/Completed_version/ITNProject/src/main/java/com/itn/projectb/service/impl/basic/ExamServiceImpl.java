package com.itn.projectb.service.impl.basic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itn.projectb.mapper.basic.ExamMapper;
import com.itn.projectb.service.basic.ExamService;
import com.itn.projectb.vo.basic.ExamVO;
import com.itn.projectb.vo.common.Criteria;

@Service
public class ExamServiceImpl implements ExamService{
	
	@Autowired
	ExamMapper examMapper;

// 전체 조회
	@Override
	public List<?> selectExamList(Criteria searchVO) {
		return examMapper.selectExamList(searchVO);
	}
	
//	개수 세기 함수
	@Override
	public int selectExamListTotCnt(Criteria searchVO) {
		return examMapper.selectExamListTotCnt(searchVO);
	}
	
//	insert 함수 
	@Override
	public void insertExam(ExamVO examVO) {
		examMapper.insertExam(examVO);
	}
	
//	상세조회
	@Override
	public ExamVO selectExam(String uuid) {
		ExamVO examVO = examMapper.selectExam(uuid);
		return examVO;
	}

	@Override
	public void updateExam(ExamVO examVO) {
		examMapper.update(examVO);
	}

	@Override
	public void deleteExam(ExamVO examVO) {
		examMapper.delete(examVO);
	}

}

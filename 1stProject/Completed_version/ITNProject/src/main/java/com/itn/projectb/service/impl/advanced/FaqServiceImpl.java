package com.itn.projectb.service.impl.advanced;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itn.projectb.mapper.advanced.FaqMapper;
import com.itn.projectb.service.advanced.FaqService;
import com.itn.projectb.vo.advanced.FaqVO;
import com.itn.projectb.vo.common.Criteria;

@Service
public class FaqServiceImpl implements FaqService {

	@Autowired
	FaqMapper faqMapper;

	@Override
	public List<?> selectCt2List(Criteria searchVO) throws Exception {
		// TODO Auto-generated method stub
		return faqMapper.selectCt2List(searchVO);
                 //전체조회 검색
	}

	@Override
	public int selectCt2ListTotCnt(Criteria searchVO) {
		// TODO Auto-generated method stub
		return faqMapper.selectCt2ListTotCnt(searchVO);
	}                // 페이징

	@Override
	public FaqVO selectCt2(String faid) throws Exception {
		// TODO Auto-generated method stub
		FaqVO faqVO = faqMapper.selectCt2(faid);
		return faqVO;
	}        //상세보기
	
	@Override
	public List<?> selectQnaList(Criteria searchVO) throws Exception {
		// TODO Auto-generated method stub
		return faqMapper.selectQnaList(searchVO);
	}

	@Override
	public int selectQnaListTotCnt(Criteria searchVO) {
		// TODO Auto-generated method stub
		return faqMapper.selectQnaListTotCnt(searchVO);
	}

	@Override
	public FaqVO selectQna(String faid) throws Exception {
		FaqVO qnaVO = faqMapper.selectQna(faid);
		return qnaVO;
	}

	@Override
	public void updateQnaVO(FaqVO qnaVO) throws Exception {
		faqMapper.update(qnaVO);
		
	}

	@Override
	public void deleteQnaVO(FaqVO qnaVO) throws Exception {
		faqMapper.delete(qnaVO);
	}

	@Override
	public void insertQna(FaqVO qnaVO) throws Exception {
		faqMapper.insert(qnaVO);
	}

}

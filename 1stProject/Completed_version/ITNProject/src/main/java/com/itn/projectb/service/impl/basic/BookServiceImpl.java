package com.itn.projectb.service.impl.basic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itn.projectb.mapper.basic.BookMapper;
import com.itn.projectb.service.basic.BookService;
import com.itn.projectb.vo.basic.BookVO;
import com.itn.projectb.vo.basic.MemberVO;
import com.itn.projectb.vo.common.Criteria;

@Service
public class BookServiceImpl implements BookService{
	
	@Autowired
	BookMapper bookMapper;

	@Override
	public void insertBook(BookVO bookVO) {
		bookMapper.insertBook(bookVO);
		
	}

	@Override
	public BookVO selectBookId(String boid) {
		// TODO Auto-generated method stub
		return bookMapper.selectBookId(boid);
	}

	@Override
	public List<?> selectBookList(Criteria searchVO) {
		// TODO Auto-generated method stub
		return bookMapper.selectBookList(searchVO);
	}

	@Override
	public int selectBookListTotCnt(Criteria searchVO) {
		// TODO Auto-generated method stub
		return bookMapper.selectBookListTotCnt(searchVO);
	}

	@Override
	public void updateBookDate(String boid) {
		// TODO Auto-generated method stub
		bookMapper.updateBookDate(boid);
	}

}

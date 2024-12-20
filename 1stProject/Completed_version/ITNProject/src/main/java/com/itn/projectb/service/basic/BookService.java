package com.itn.projectb.service.basic;

import java.util.List;

import com.itn.projectb.vo.basic.BookVO;
import com.itn.projectb.vo.basic.MemberVO;
import com.itn.projectb.vo.common.Criteria;

public interface BookService {
	 public void insertBook(BookVO bookVO);
	 public BookVO selectBookId(String boid);
	 public List<?> selectBookList(Criteria searchVO);
	 public int selectBookListTotCnt(Criteria searchVO);
	 public void updateBookDate(String boid);
}

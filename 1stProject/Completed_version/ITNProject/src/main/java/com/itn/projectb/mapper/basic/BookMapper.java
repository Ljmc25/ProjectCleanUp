package com.itn.projectb.mapper.basic;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.itn.projectb.vo.basic.BookVO;
import com.itn.projectb.vo.basic.MemberVO;
import com.itn.projectb.vo.common.Criteria;

@Mapper
public interface BookMapper {
	 public void insertBook(BookVO bookVO);
	 public BookVO selectBookId(String boid);
	 public List<?> selectBookList(Criteria searchVO);
	 public int selectBookListTotCnt(Criteria searchVO);
	 public void updateBookDate(String boid);
}

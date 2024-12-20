package com.itn.projectb.mapper.basic;

import org.apache.ibatis.annotations.Mapper;

import com.itn.projectb.vo.basic.ShowingVO;

@Mapper
public interface ShowingMapper {
	public int createShowing(ShowingVO showingVO);
}

package com.itn2024.projectb.mapper.basic;

import com.itn2024.projectb.vo.common.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExampleMapper {
    public List<?> selectExampleList(Criteria searchVO);
    public int selectExampleListTotCnt(Criteria searchVO);
}

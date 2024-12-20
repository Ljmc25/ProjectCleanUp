package com.itn2024.projectb.service.basic;

import com.itn2024.projectb.mapper.basic.ExampleMapper;
import com.itn2024.projectb.vo.common.Criteria;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExampleService {
    private final ExampleMapper exampleMapper;

    public List<?> selectExampleList(Criteria searchVO) {
        List<?> page = exampleMapper.selectExampleList(searchVO);

        int count = exampleMapper.selectExampleListTotCnt(searchVO);
        searchVO.setTotalItems(count);

        return page;
    }
}

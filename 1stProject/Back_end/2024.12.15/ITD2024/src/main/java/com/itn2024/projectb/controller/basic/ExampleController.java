package com.itn2024.projectb.controller.basic;

import com.itn2024.projectb.service.basic.ExampleService;
import com.itn2024.projectb.vo.common.Criteria;
import com.itn2024.projectb.vo.dto.ResultDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ExampleController {

    private final ExampleService exampleService;

    @GetMapping("/api/basic/example")
    public ResponseEntity<?> selectExampleList(Criteria searchVO){
        List<?> examples = exampleService.selectExampleList(searchVO);
        ResultDto resultDto = new ResultDto(examples, searchVO.getTotalItems());
        return new ResponseEntity<>(resultDto, HttpStatus.OK);
    }
}

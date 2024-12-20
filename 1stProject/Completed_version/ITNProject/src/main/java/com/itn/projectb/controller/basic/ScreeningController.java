package com.itn.projectb.controller.basic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itn.projectb.service.basic.ScreeningService;
import com.itn.projectb.vo.basic.ScreeningVO;

@RestController
public class ScreeningController {
	
	@Autowired
	ScreeningService screeningService;
	
    // 선택한 날짜에 맞는 영화 리스트 가져오기
    @GetMapping(value = "/screening/getProductsByDate", produces = "application/json")
    public List<ScreeningVO> getProductsByDate(@RequestParam("date") String date) {
        return screeningService.getProductsByDate(date);
    }   
	
    //상영정보 받아오기
    @GetMapping("/screening/getScreeningInfo")
    public List<ScreeningVO> getScreeningInfo(@RequestParam String movie,
                                               @RequestParam String city,
                                               @RequestParam String branch,
                                               @RequestParam String date) {
        Map<String, Object> params = new HashMap<>();
        params.put("movie", movie);
        params.put("city", city);
        params.put("branch", branch);
        params.put("date", date);

        return screeningService.getScreeningInfo(params);
    }
	
	
}

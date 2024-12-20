package com.itn2024.projectb.controller.basic;

import com.itn2024.projectb.service.basic.InquiryService;
import com.itn2024.projectb.vo.basic.Inquiry;
import com.itn2024.projectb.vo.common.Criteria;
import com.itn2024.projectb.vo.dto.InquiryDto;
import com.itn2024.projectb.vo.dto.ResultDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author : KTE
 * @fileName : FaqController
 * @since : 24. 11. 27.
 * description :
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class InquiryController {

    private final InquiryService inquiryService;

//전체조회
    @GetMapping("/api/basic/inquiry/admin")
    public ResponseEntity<?> selectInquiryList(Criteria searchVO){
        List<?> inquiries = inquiryService.selectInquiryList(searchVO);
        ResultDto resultDto = new ResultDto(inquiries , searchVO.getTotalItems());
        return new ResponseEntity<>(resultDto , HttpStatus.OK);
    }

//전체조회(특정사용자_)
    @GetMapping("/api/basic/inquiry")
    public ResponseEntity<?> selectInquiryListByEmail(Criteria searchVO){
        List<?> inquiries = inquiryService.selectInquiryListByEmail(searchVO);
        ResultDto resultDto = new ResultDto(inquiries , searchVO.getTotalItems());
        return new ResponseEntity<>(resultDto , HttpStatus.OK);
    }

//상세조회
    @GetMapping("/api/basic/inquiry/{iqid}")
    public ResponseEntity<?>selectInquiry(@PathVariable String iqid){
        Optional<InquiryDto> inquiryDto = inquiryService.selectInquiry(iqid);
        if (inquiryDto.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(inquiryDto.get() , HttpStatus.OK);
    }

//관리자 INSERT
@PostMapping("/api/basic/inquiry/admin")
public ResponseEntity<?> answerInquiry(@RequestBody Inquiry inquiry) {
    inquiryService.insertAnswerAndUpdateQuestion(inquiry);
    return new ResponseEntity<>(HttpStatus.OK);
}


//INSERT
    @PostMapping("/api/basic/inquiry")
    public  ResponseEntity<?>insert(@RequestBody Inquiry inquiry) {
        inquiryService.insert(inquiry);
        return new ResponseEntity<>(inquiry, HttpStatus.OK);
    }

//DELETE
    @DeleteMapping("/api/basic/inquiry/delection/{iqid}")
    public ResponseEntity<?> delete(@PathVariable String iqid) {
        inquiryService.delete(iqid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
//UPDATE
    @PutMapping("/api/basic/inquiry/{iqid}")
    public ResponseEntity<?> update(
            @RequestBody Inquiry inquiry,
            @PathVariable String iqid) {
        inquiryService.update(inquiry);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

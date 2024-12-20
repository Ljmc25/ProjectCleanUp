package com.itn2024.projectb.service.basic;

import com.itn2024.projectb.mapper.basic.InquiryMapper;
import com.itn2024.projectb.vo.basic.Inquiry;
import com.itn2024.projectb.vo.common.Criteria;
import com.itn2024.projectb.vo.dto.InquiryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author : KTE
 * @fileName : FaqService
 * @since : 24. 11. 27.
 * description :
 */
@Service
@RequiredArgsConstructor
public class InquiryService {
    private final InquiryMapper inquiryMapper;
//전체조회 (총개수) - 관리자님
    public List<?> selectInquiryList(Criteria searchVO){
        List<?>page = inquiryMapper.selectInquiryList(searchVO);
        int count = inquiryMapper.selectInquiryListToCnt(searchVO);
        searchVO.setTotalItems(count);
        return page;
    }

//특정 작성자가 작성한 질문글 조회(전체조회)
    public List<?> selectInquiryListByEmail(Criteria searchVO){
        List<?>page = inquiryMapper.selectInquiryListByEmail(searchVO);
        int count = inquiryMapper.selectInquiryListToCntByEmail(searchVO);
        searchVO.setTotalItems(count);
        searchVO.setTotalItems(count);
        return page;
    }

//관리자 INSERT
    @Transactional
    public void insertAnswerAndUpdateQuestion(Inquiry inquiry){
        inquiryMapper.insertAnswer(inquiry);
        inquiryMapper.updateAnswerStatus(inquiry.getReid());
    }


//상세조회
    public Optional<InquiryDto> selectInquiry(String iqid){
        return inquiryMapper.selectInquiry(iqid);
    }
//    INSERT
    public void insert(Inquiry inquiry){
        inquiryMapper.insert(inquiry);
    }
//delete
public void delete(String iqid){
    inquiryMapper.delete(iqid);
}
//update
    public void update(Inquiry inquiry){
        inquiryMapper.update(inquiry);
    }
}

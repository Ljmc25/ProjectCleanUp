package com.itn2024.projectb.mapper.basic;

import com.itn2024.projectb.vo.basic.Inquiry;
import com.itn2024.projectb.vo.common.Criteria;
import com.itn2024.projectb.vo.dto.InquiryDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

/**
 * @author : KTE
 * @fileName : FaqMapper
 * @since : 24. 11. 27.
 * description :
 */
@Mapper
public interface InquiryMapper {
    public List<?>selectInquiryList(Criteria searchVO);    //전체조회
    public int selectInquiryListToCnt(Criteria searchVO); //총건수구하기
    public List<?>selectInquiryListByEmail(Criteria searchVO); //--> 특정한사람이 작성한 1:1 문의 글 전체조회
    public Optional<InquiryDto>selectInquiry(String iqid);//상세조회
    public int insert(Inquiry inquiry);//INSERT(질문글)

    public void insertAnswer(Inquiry inquiry); //INSERT(답글)
    public void updateAnswerStatus(String reid); //UPDATE

    public int update(Inquiry inquiry);//UPDATE
    public int delete(String Inquiry); //DELETE

    public int selectInquiryListToCntByEmail(Criteria searchVO);
}

package com.itn2024.projectb.vo.dto;

import lombok.*;

/**
 * @author : KTE
 * @fileName : InquiryDto
 * @since : 24. 11. 28.
 * description :
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class InquiryDto {
    // 질문 정보
    private String iqid; // 질문 ID
    private String title; // 질문 제목
    private String content; // 질문 내용
    private String writer; // 작성자
    private String insertTime; // 작성일자
    private String anwser; //상태

    // 답변 정보 (상세 조회 시에만 포함)
    private String answerId; // 답변 ID REID
    private String answerContent; // 답변 내용
    private String answerWriter; // 답변 작성자
    private String answerInsertTime; // 답변 작성일자
}

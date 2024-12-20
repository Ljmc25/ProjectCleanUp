package com.itn2024.projectb.vo.dto;

import lombok.*;

/**
 * @author : KTE
 * @fileName : ResultDto
 * @since : 24. 10. 25.
 * description : 박스포장용 객체(DTO 객체)
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CartDto {
    // 질문 정보
    private int price; // 단일가격
    private String gender; // 착용구분
    private String fileUrl; // 메인 이미지
    private String name; // 상품명
    private String siid; // 사이즈이름
    private String caid; // 고유ID
    private String prid;
    private int quantity; // 개수
    private String karegorie;
    private String contents;
    private int sales;
//    private String email; //

}

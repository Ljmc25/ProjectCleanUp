package com.itn2024.projectb.vo.dto;

import lombok.*;

import java.util.List;

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
public class ResultDto {
    private List<?> results;      // 배열
    private Integer totalCount;   // 총건수
}

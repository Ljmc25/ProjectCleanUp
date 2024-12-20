package com.itn2024.projectb.vo.dto;

import lombok.*;

/**
 * @author : KTE
 * @fileName : UserReqDto
 * @since : 24. 11. 13.
 * description : 유저 택배상자(프론트 -> 백엔드)
 *  TODO: 로그인 진행시 보안때문에 (조회) POST 방식 사용 : 객체(유저택배상자:DTO)
 *    => Get 방식 : (단점) : url 에 정보가 노출
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserReqDto {
    private String email;    // 로그인 ID
    private String password; // 암호
}

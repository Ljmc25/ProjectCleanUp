package com.itn2024.projectb.vo.dto;

import lombok.*;

/**
 * @author : KTE
 * @fileName : UserResDto
 * @since : 24. 11. 13.
 * description : 유저 택배상자(프론트 결과 전송)
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserResDto {
    private String accessToken;          // 웹토큰
    private String tokenType = "Bearer"; // 토큰종류 : 고정

    private String email;                // 유저 이메일
    private String codeName;             // 권한명

//    생성자 : accessToken, email, codeName
    public UserResDto(String accessToken, String email, String codeName) {
        this.accessToken = accessToken;
        this.email = email;
        this.codeName = codeName;
    }
}

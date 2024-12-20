package com.itn2024.projectb.vo.auth;

import lombok.*;

/**
 * @author : KTE
 * @fileName : Member
 * @since : 24. 11. 11.
 * description :
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    private String email;
    private String password;
    private String name;
    private String phoneNumber;
    private String codeName;
    private String insertTime;
    private String updateTime;
}

package com.itn2024.projectb.vo.advanced;

import lombok.*;

/**
 * @author : KTE
 * @fileName : payment
 * @since : 24. 12. 3.
 * description :
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    private String paid;
    private String code;
    private String payType;
    private int totalPrice;
    private String email;
    private String insertTime;
    private String deleteTime;
}

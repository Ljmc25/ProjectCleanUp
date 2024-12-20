package com.itn2024.projectb.vo.advanced;

import lombok.*;

/**
 * @author : KTE
 * @fileName : Delivery
 * @since : 24. 11. 28.
 * description :
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Delivery {
    private String deid; // 고유ID
    private String code; // 배송코드
    private String courier; // 택배사
    private String state; // 배송상태
    private String email; // 이메일ID
    private String orderAddress; // 주문자주소
    private String orderZipCode; // 주문자우편번호
    private String consigneeName; // 수취인이름
    private String consigneePhoneNumber; // 수취인전화번호
    private String consigneeAddress; // 수취인주소
    private String consigneeZipCode; // 수취인우편번호
    private String faid; // 결제ID
    private String insertTime; // 등록날짜
    private String deleteTime; // 취소날짜
}

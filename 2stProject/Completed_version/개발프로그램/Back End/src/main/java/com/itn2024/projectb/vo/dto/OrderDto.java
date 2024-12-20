package com.itn2024.projectb.vo.dto;

import lombok.*;

/**
 * @author : KTE
 * @fileName : OrderDto
 * @since : 24. 12. 4.
 * description :
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDto {
    private String orid;
    private String prid;
    private String siid;
    private String color;
    private int quantity;
    private String code;
    private String name;
    private String gender;
    private int price;
    private String imageUrl;
}

package com.itn2024.projectb.vo.advanced;

import lombok.*;

/**
 * @author : dlfdl
 * @fileName : Gallery
 * @since : 24. 11. 1.
 * description :
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    private String caid;
    private String prid;
    private String siid;
    private String color;
    private int quantity;
    private String email;
    private String insertTime;
    private String updateTime;
}







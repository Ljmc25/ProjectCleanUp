package com.itn2024.projectb.vo.basic;

import lombok.*;

/**
* @fileName   : product 
* @author     : KTE 
* @since      : 24. 11. 28. 
* description :
*/
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private String prid;
    private String code;
    private String name;
    private String karegorie;
    private String gender;
    private int price;
    private String contents;
    private int sales;
    private String insertTime;
    private String updateTime;

    public Product(String prid, String name, String karegorie, String gender, int price, String contents, int sales) {
        this.prid = prid;
        this.name = name;
        this.karegorie = karegorie;
        this.gender = gender;
        this.price = price;
        this.contents = contents;
        this.sales = sales;

    }

    public Product(String name, String karegorie, String gender, int price, String contents, int slaes) {
        this.name = name;
        this.karegorie = karegorie;
        this.gender = gender;
        this.price = price;
        this.contents = contents;
        this.sales = slaes;
    }
}

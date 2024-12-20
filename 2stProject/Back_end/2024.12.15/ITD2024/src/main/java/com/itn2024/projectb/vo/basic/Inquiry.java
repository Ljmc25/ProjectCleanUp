package com.itn2024.projectb.vo.basic;

import lombok.*;

/**
 * @author : KTE
 * @fileName : Faq
 * @since : 24. 11. 27.
 * description :
 */
//IQID	VARCHAR2(20 BYTE)
//CONTENTS	VARCHAR2(2000 BYTE)
//WRITER	VARCHAR2(50 BYTE)
//REID	VARCHAR2(20 BYTE)
//INSERT_TIME	VARCHAR2(30 BYTE)
//UPDATE_TIME	VARCHAR2(30 BYTE)
//TITLE	VARCHAR2(300 BYTE)
//ANWSER	VARCHAR2(5 BYTE)

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Inquiry {
    private String iqid,title,contents, writer, reid, insertTime, updateTime;


}

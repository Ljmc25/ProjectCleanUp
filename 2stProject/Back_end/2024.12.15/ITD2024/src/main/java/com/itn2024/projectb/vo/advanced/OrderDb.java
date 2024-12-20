package com.itn2024.projectb.vo.advanced;

import lombok.*;

/**
 * @author : NoteBook
 * @fileName : OrderDb
 * @since : 2024-11-28
 * description :
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderDb {
        private String orid;
        private String prid;
        private String siid;
        private String color;
        private int quantity;
        private String paid;
        private String email;
        private String insertTime;
        private String deleteTime;

}

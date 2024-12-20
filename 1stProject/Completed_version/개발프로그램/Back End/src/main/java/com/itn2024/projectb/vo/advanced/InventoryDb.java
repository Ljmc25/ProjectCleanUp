package com.itn2024.projectb.vo.advanced;

import lombok.*;

/**
 * @author : NoteBook
 * @fileName : InventoryDb
 * @since : 2024-11-29
 * description :
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class InventoryDb {
        private String inid;
        private String prid;
        private String siid;
        private String color;
        private int quantity;
        private String insertTime;
        private String updateTime;
}

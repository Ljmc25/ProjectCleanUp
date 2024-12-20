package com.itn2024.projectb.vo.advanced;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FileDb {

    private String uuid;        // 복합키 1
    private String prid;        // 복합키 2
    private byte[] fileData;
    private String fileUrl;
    private String insertTime;
    private String updateTime;
}

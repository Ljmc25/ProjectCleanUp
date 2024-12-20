package com.itn2024.projectb.vo.basic;

import lombok.*;

/**
 * @author : KTE
 * @fileName : Dept
 * @since : 24. 10. 23.
 * description : 부서 vo
 *  TODO: DB 테이블 : 컬럼(속성), 테이블명(클래스명)
 *    DB   : 언더바 표기법 (단어_단어)
 *    Java : 낙타표기법(소문자대문자)
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Notice {
    private String noid; // 고유ID(기본키)
    private String title;// 제목
    private String contents;  // 내용
    private String imageUrl;  // 이미지
    private byte[] fileData;        // 이미지파일
    private String writer;  // 작성자
    private String insertTime;  // 등록시간
    private String updateTime;  // 수정시간

    // 생성자 (기본키, 제목, 내용, 이미지파일)
    public Notice(String noid, String title, String contents, byte[] fileData) {
        this.noid = noid;
        this.title = title;
        this.contents = contents;
        this.fileData = fileData;
    }


    // 생성자 (제목, 내용, 이미지파일)

    public Notice(String title, String contents, byte[] fileData) {
        this.title = title;
        this.contents = contents;
        this.fileData = fileData;
    }


//    noid
//title
//contents
//image_url
//writer
}

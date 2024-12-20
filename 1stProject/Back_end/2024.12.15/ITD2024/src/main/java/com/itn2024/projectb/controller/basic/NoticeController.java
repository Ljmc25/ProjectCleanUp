package com.itn2024.projectb.controller.basic;

import com.itn2024.projectb.service.basic.NoticeService;
import com.itn2024.projectb.vo.basic.Notice;
import com.itn2024.projectb.vo.common.Criteria;
import com.itn2024.projectb.vo.dto.ResultDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

/**
 * @author : KTE
 * @fileName : DeptController
 * @since : 24. 10. 24.
 * description : TODO: spring (@Controller)
 *                 springboot (@RestController)
 *  TODO: (복습) @Controller 기능 : url + jsp 결과 내보내기
 *        (신규) @RestController  : url + json 으로 결과 내보내기
 *          json 데이터 : js 객체배열 : [{},{}...]
 *  TODO: @RestController 코딩된 컨트롤러 테스트 도구
 *             (REST API 테스트 도구(프로그램))
 *     => 1) 컨트롤러 함수 앞에 아이콘 클릭 : api
 *     => 2) GET http://localhost:8000/url 앞에 실행버튼 클릭
 *     => 결과 : json 데이터(객체배열)
 *  TODO: ResponseEntity 객체
 *    => json 데이터 전송할때 , 상태코드도 같이 전송하는 객체
 *    => 목적 : 프로그램 품질 향상
 *    => new ResponseEntity<>(전송데이터, HttpStatus.상태코드)
 *    => 상태코드 예시) OK(200), NO_CONTENT(204) 등
 *    => 200번대 숫자 : 성공을 의미
 *    => 400번대 숫자 : 실패를 의미
 * TODO: spring :객체를 매개변수로 받는 어노테이션 : @ModelAttribute
 * TODO: springboot :객체를 매개변수로 받는 어노테이션 : @RequestBody
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService; // 서비스 객체

    //    전체 조회
    @GetMapping("/api/basic/notice")
    public ResponseEntity<?> selectNoticeList(Criteria searchVO) {
        List<?> notices = noticeService.selectNoticeList(searchVO);
        log.debug("test"); //디버깅

//      TODO: depts(배열) 1개 + 총건수 1개 -> 박스포장용 객체
//          객체생성(택배박스: DTO 객체) : 속성필드 (배열), 속성필드(총건수)
        ResultDto resultDto
                = new ResultDto(notices, searchVO.getTotalItems());
//      TODO: 상태코드 : HttpStatus.OK, NO_CONTENT ...
        return new ResponseEntity<>(resultDto, HttpStatus.OK);
    }
    //    Notice 생성 : fileTitle, fileContent, image(업로드)
//    TODO: 객체 : @RequestBody, @ModelAttribute(jsp 용) (x)
//          변수 : @RequestParam(파일업로드, O), @PathVariable(x)
    @PostMapping("/api/basic/notice/add")
    public ResponseEntity<?> insert(
            @RequestParam(defaultValue = "") String title,
            @RequestParam(defaultValue = "") String content,
            @RequestParam(required = false) MultipartFile image,
            @RequestParam String writer
    ) throws Exception {

        byte[] insertPy = null;

        if(image != null) {
            insertPy = image.getBytes();
        }

        Notice notice = new Notice(title, content, insertPy);
        notice.setWriter(writer);
        noticeService.insert(notice);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //    TODO: 이미지 다운로드 함수
    //      springboot : json 데이터 => body 쪽에 넣어서 전송
    @GetMapping("/api/basic/notice/{noid}")
    public ResponseEntity<byte[]> findDownload(
            @PathVariable String noid) throws Exception {
//      1) 상세조회 : 객체받기(첨부파일)
        Notice notice
                = noticeService.selectNotice(noid)
                .orElseThrow(()-> new FileNotFoundException("데이터 없습니다."));

//      2) 첨부파일 jsp 전송 : 규격대로 전송(코딩 보냄)
        // 우편물 보낼때 규칙과 유사 :
        HttpHeaders headers = new HttpHeaders();                  // html 문서 객체(머리말)
        headers.setContentDispositionFormData("attachment", notice.getNoid()); // 첨부파일(문서형태)
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);              // 첨부파일2(문서형태)

//       첨부파일 전송 + OK 신호 보냄
        return new ResponseEntity<byte[]>(notice.getFileData(), headers, HttpStatus.OK);
    }

    //    상세조회
    @GetMapping("/api/basic/notice/get/{noid}")
    public ResponseEntity<?> selectNotice(
            @PathVariable String noid
    ) {
        Optional<Notice> notice
                = noticeService.selectNotice(noid);
        if (notice.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(notice.get(), HttpStatus.OK);
    }


    //    TODO: spring : CUD => @PostMapping("/url")
//    TODO: springboot : C(생성) => @PostMapping("/url")
//                       U(수정) => @PutMapping("/url")
//                       D(삭제) => @DeleteMapping("/url")
// 수정

    @PutMapping("/api/basic/notice/update/{noid}")
    public ResponseEntity<?> update(
            @PathVariable String noid,
            @RequestParam(defaultValue = "") String title,
            @RequestParam(defaultValue = "") String content,
            @RequestParam (required = false) MultipartFile image,
            @RequestParam String writer
    ) throws Exception {

        byte[] insertPy = null;

        if(image != null){
            insertPy = image.getBytes();
        }

        Notice notice = new Notice(noid,title, content, insertPy);
        notice.setWriter(writer);
        noticeService.update(notice);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //    부서 삭제
    @DeleteMapping("/api/basic/notice/{noid}")
    public ResponseEntity<?> delete(
            @PathVariable String noid
    ) {
        noticeService.delete(noid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}










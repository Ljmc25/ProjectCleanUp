package com.itn2024.projectb.service.basic;

import com.itn2024.projectb.mapper.basic.NoticeMapper;
import com.itn2024.projectb.vo.basic.Notice;
import com.itn2024.projectb.vo.common.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author : KTE
 * @fileName : DeptService
 * @since : 24. 10. 23.
 * description : 부서 서비스
 *   TODO: 1) DI 방법 : @Autowired 사용 (변수 DI)
 *         2) final 상수 + @RequiredArgsConstructor
 *            (생성자 DI) : springboot 추천
 */
@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeMapper noticeMapper; // DI(객체 받아오기)

    //    TODO: 전체조회 + 총건수(Criteria 의 totalItems 변수 저장)
    public List<?> selectNoticeList(Criteria searchVO) {
        List<?> page = noticeMapper.selectNoticeList(searchVO);

//        TODO: 총건수 저장 : Criteria 의 totalItems
        int count = noticeMapper.selectNoticeListTotCnt(searchVO);
        searchVO.setTotalItems(count);

        return page;
    }


    //    TODO: 부서 상세조회  NOID,
    public Optional<Notice> selectNotice(String noid) {
        return noticeMapper.selectNotice(noid);
    }


    //    FileDb 생성 : 이미지 업로드(DB/클라우드(현대)/PC)
    public void insert(Notice notice) {

//        1) uuid 생성
        String noid = UUID.randomUUID().toString();

//        2) 다운로드 url 생성 :
        String url = generateDownloadUrl(noid);
//        3) VO 에 uuid/url 저장(setter)
        notice.setNoid(noid);
        notice.setImageUrl(url);
//        4) insert(VO)
        noticeMapper.insert(notice);
    }


    //  download url 만들기 함수
    public String generateDownloadUrl(String noid) {
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()                         // spring 기본주소 : http://localhost:8000
                .path("/api/basic/notice/")// 추가 경로 넣기 : /advanced/fileDb/
                .path(noid)                                       // uuid 넣기     : xxxxxx
                .toUriString();                                // 합치기 : http://localhost:8000/advanced/fileDb/xxxxxx

    }


    //    FileDB 수정
    public void update(Notice notice) {
//        1) uuid 가져오기(*)
        String noid = notice.getNoid();
//        2) 다운로드 url 생성 :
        String url = generateDownloadUrl(noid);
//        3) VO 에 uuid/url 저장(setter)
        notice.setNoid(noid);
        notice.setImageUrl(url);
//        4) update(VO)(*)
        noticeMapper.update(notice);
    }

    //    TODO: 부서 삭제 (기본키:noid)
    public void delete(String noid) {
        noticeMapper.delete(noid);
    }
}

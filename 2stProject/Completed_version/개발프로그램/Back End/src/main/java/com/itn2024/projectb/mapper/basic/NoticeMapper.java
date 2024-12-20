package com.itn2024.projectb.mapper.basic;

import com.itn2024.projectb.vo.basic.Notice;
import com.itn2024.projectb.vo.common.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

/**
 * @author : KTE
 * @fileName : DeptMapper
 * @since : 24. 10. 23.
 * description : 부서 매퍼
 */
@Mapper
public interface NoticeMapper {
    public List<?> selectNoticeList(Criteria searchVO);   // 전체조회
    public int selectNoticeListTotCnt(Criteria searchVO); // 총건수구하기
    public int insert(Notice notice);                       // 생성
    public Optional<Notice> selectNotice(String noid);          // 상세조회
    public int update(Notice notice);                           // 업데이트
    public int delete(String noid);                         // 삭제
}

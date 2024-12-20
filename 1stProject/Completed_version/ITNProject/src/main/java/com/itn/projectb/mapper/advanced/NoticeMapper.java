package com.itn.projectb.mapper.advanced;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.itn.projectb.vo.advanced.NoticeVO;
import com.itn.projectb.vo.common.Criteria;

@Mapper
public interface NoticeMapper {
	public List<?> selectCt3List(Criteria searchVO);  //전체조회
	public int selectCt3ListTotCnt(Criteria searchVO); //페이징
	public NoticeVO selectCt3(String noid);  //상세조회
	
	// 관리자 페이지
	public List<NoticeVO> selectNotiList(Criteria searchVO); 									// 공지사항 목록 조회
	public List<NoticeVO> getNotiList(@Param("offset") int offset, @Param("limit") int limit);	// 공지사항 목록을 반환하는 메서드	
	public int getTotalRecords();																// 전체 레코드 수를 반환하는 메서드
	public int getTotalNotiCount();															// 전체 공지사항 수를 반환하는 메서드
	public void insert(NoticeVO noticeVO);															// 공지사항 추가
	public String getMaxNoid();	 															// 현재 최대 noid 값을 조회하는 메서드
	public void updateNoti(NoticeVO noticeVO);														// 공지사항 업데이트
	public NoticeVO selectNotiById(String noticeVO);      											// 특정 ID로 공지사항 조회
	public void deleteNoti(String noticeVO);														// 공지사항 삭제
}

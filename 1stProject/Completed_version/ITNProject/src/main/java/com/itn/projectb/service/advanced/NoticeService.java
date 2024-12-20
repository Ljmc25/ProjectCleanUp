package com.itn.projectb.service.advanced;

import java.util.List;

import com.itn.projectb.vo.advanced.NoticeVO;
import com.itn.projectb.vo.common.Criteria;

public interface NoticeService {
	public List<?> selectCt3List(Criteria searchVO)  throws Exception; // 전체조회 함수
	public int selectCt3ListTotCnt(Criteria searchVO); // 페이징
	public NoticeVO selectCt3(String noid) throws Exception; // 상세보기
	
	// 관리자 페이지
	public List<NoticeVO> selectNotiList(Criteria searchVO) throws Exception;
	public List<NoticeVO> getNotiList(int offset, int limit);
	public int getTotalRecords();
	public int getTotalNotiCount(); // 전체 사용자 수를 반환하는 메서드
	public void insertNoti(NoticeVO notiVO) throws Exception;
	public void updateNoti(NoticeVO noti);
	public NoticeVO findById(String noid);              // 특정 ID로 공지사항 조회
	public void deleteNoti(String noid);
}

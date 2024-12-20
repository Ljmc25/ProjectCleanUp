package com.itn.projectb.service.impl.advanced;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itn.projectb.mapper.advanced.NoticeMapper;
import com.itn.projectb.service.advanced.NoticeService;
import com.itn.projectb.vo.advanced.NoticeVO;
import com.itn.projectb.vo.common.Criteria;

@Service
public class NoticeServiceImpl implements NoticeService {
	@Autowired
	NoticeMapper noticeMapper;
	
	@Override
	public List<?> selectCt3List(Criteria searchVO) throws Exception {
		// TODO Auto-generated method stub
		return noticeMapper.selectCt3List(searchVO);
	}
	@Override
	public int selectCt3ListTotCnt(Criteria searchVO) {
		// TODO Auto-generated method stub
		return noticeMapper.selectCt3ListTotCnt(searchVO);
	}
	@Override
	public NoticeVO selectCt3(String noid) throws Exception {
		// TODO Auto-generated method stub
		NoticeVO noticeVO = noticeMapper.selectCt3(noid);
		return noticeVO;
	}
	
	//관리자 페이지
	@Override
	public List<NoticeVO> selectNotiList(Criteria searchVO) throws Exception {
		// TODO Auto-generated method stub
		return noticeMapper.selectNotiList(searchVO);
	}

	@Override
	public List<NoticeVO> getNotiList(int offset, int limit) {
		// TODO Auto-generated method stub
		return noticeMapper.getNotiList(offset, limit);
	}

	@Override
	public int getTotalRecords() {
		// TODO Auto-generated method stub
		return noticeMapper.getTotalRecords();
	}

	@Override
	public int getTotalNotiCount() {
		// TODO Auto-generated method stub
		return noticeMapper.getTotalRecords();
	}
	@Override
	public void insertNoti(NoticeVO noti) {
	    noticeMapper.insert(noti); // 기존 메서드 호출
	}
	
	@Override
	@Transactional
	public void updateNoti(NoticeVO noti) {
	    noticeMapper.updateNoti(noti); // 수정 로직
	}
	    
	@Override
	@Transactional
	public NoticeVO findById(String noid) {
	    return noticeMapper.selectNotiById(noid); // 특정 ID로 공지사항 조회
	}
	    
	@Override
	@Transactional
	public void deleteNoti(String noid) {
	    noticeMapper.deleteNoti(noid); // 삭제 로직
	}
}

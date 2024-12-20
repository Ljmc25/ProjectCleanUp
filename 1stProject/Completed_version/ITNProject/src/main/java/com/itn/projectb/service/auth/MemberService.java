package com.itn.projectb.service.auth;

import java.util.List;

import com.itn.projectb.vo.basic.MemberVO;
import com.itn.projectb.vo.common.Criteria;

public interface MemberService {
	public MemberVO authenticateMember(MemberVO loginVO) throws Exception;
	public void registerMember(MemberVO memberVO) throws Exception;	
	public boolean checkEmailDuplicate(MemberVO memberVO) throws Exception; // 아이디 중복 확인
	
	//관리자 페이지
	
	public List<MemberVO> selectUserList(Criteria searchVO) throws Exception; // 제품 목록 조회
	public List<MemberVO> getUserList(int offset, int limit);
	public int getTotalRecords();
	public int getTotalUserCount(); // 전체 사용자 수를 반환하는 메서드
	
	public MemberVO selectMember(MemberVO memberVO); 
	public void updateMember(MemberVO memberVO);
}

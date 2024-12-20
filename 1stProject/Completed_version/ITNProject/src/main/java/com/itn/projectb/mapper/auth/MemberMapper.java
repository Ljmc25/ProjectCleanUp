package com.itn.projectb.mapper.auth;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.itn.projectb.vo.basic.MemberVO;
import com.itn.projectb.vo.common.Criteria;

@Mapper
public interface MemberMapper {
	public MemberVO authenticate(MemberVO memberVO); //로그인 인증
	public void register(MemberVO memberVO); // 회원가입
	public int checkEmail(MemberVO memberVO); // 이메일 중복 체크
	
	public List<MemberVO> selectUserList(Criteria searchVO); // 제품 목록 조회

	public List<MemberVO> getUserList(@Param("offset") int offset, @Param("limit") int limit);
	public int getTotalRecords();
	public int getTotalUserCount();
	
	public MemberVO selectMember(MemberVO memberVO); 
	
	public void updateMember(MemberVO memberVO);
}

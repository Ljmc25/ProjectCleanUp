package com.itn.projectb.service.impl.auth;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itn.projectb.mapper.auth.MemberMapper;
import com.itn.projectb.service.auth.MemberService;
import com.itn.projectb.vo.basic.MemberVO;
import com.itn.projectb.vo.common.Criteria;
@Service

public class MemberServiceImpl implements MemberService {
	// 인증 : 상세조회
	@Autowired
	MemberMapper memberMapper;

	@Override
	public MemberVO authenticateMember(MemberVO loginVO) throws Exception {

		MemberVO memberVO = memberMapper.authenticate(loginVO);

		if (memberVO != null) {
			boolean isMatchedPassword = BCrypt.checkpw(loginVO.getPassword(), memberVO.getPassword());
			if (isMatchedPassword == false) {
				throw new Exception("비밀번호가 틀렸습니다.");
			}
		}

		return memberVO;

	}

	// register(insert) : source
	@Override
	public void registerMember(MemberVO memberVO) throws Exception {
		String hashedPassword = BCrypt.hashpw(memberVO.getPassword(), BCrypt.gensalt());
		memberVO.setPassword(hashedPassword);
		memberMapper.register(memberVO);
	}

	// 아이디 중복 확인
	@Override
	public boolean checkEmailDuplicate(MemberVO memberVO) throws Exception {		
		int count = memberMapper.checkEmail(memberVO);
		return count > 0;
	}
	
	@Override
	public List<MemberVO> selectUserList(Criteria searchVO) throws Exception {
		// TODO 전체 조회 실행
		return memberMapper.selectUserList(searchVO);
	}

	@Override
	public List<MemberVO> getUserList(int offset, int limit) {
		// Mapper를 통해 페이징 처리된 데이터 가져오기
		return memberMapper.getUserList(offset, limit);
	}

	@Override
	public int getTotalRecords() {
		  // 전체 레코드 수 계산
		return memberMapper.getTotalRecords();
	}
	
	@Override
	public int getTotalUserCount() {
	    return memberMapper.getTotalUserCount(); // Mapper 호출
	}

	@Override
	public MemberVO selectMember(MemberVO memberVO) {
		// TODO Auto-generated method stub
		return memberMapper.selectMember(memberVO);
	}

	@Override
	public void updateMember(MemberVO memberVO) {
		String hashedPassword = BCrypt.hashpw(memberVO.getPassword(), BCrypt.gensalt());
		memberVO.setPassword(hashedPassword);
		memberMapper.updateMember(memberVO);
	}
}

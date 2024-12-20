package com.itn2024.projectb.mapper.auth;

import com.itn2024.projectb.vo.auth.Member;
import org.apache.ibatis.annotations.Mapper;
import com.itn2024.projectb.vo.common.Criteria;

import java.util.List;
import java.util.Optional;


@Mapper
public interface MemberMapper {

    public List<?> selectMemberList(Criteria searchVO); // 전체조회

    public int selectMemberListTotCnt(Criteria searchVO); // 총개수

    public Optional<Member> selectMember(String email); //상세조회
    
    public int insert(Member member);     // 회원가입
    
    public long existsById(String email); // 우리회원인지 확인

    public int update(Member member); // 회원 수정

    public int delete(String email); //회원 삭제
}



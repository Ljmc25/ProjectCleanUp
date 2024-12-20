package com.itn2024.projectb.mapper.advanced;

import com.itn2024.projectb.vo.advanced.Cart;
import com.itn2024.projectb.vo.common.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author : dlfdl
 * @fileName : GalleryMapper
 * @since : 24. 11. 1.
 * description :
 */
@Mapper
public interface CartMapper {

    public List<?> selectCartList(Criteria searchVO);  //전체조회

    public int selectCartListTotCnt(Criteria searchVO); //총개수

    public int insert(Cart cart); // 부서생성
//    public Optional<Cart> selectCart(String caid);   // 상세조회
    public int update(Cart cart); // 수정
    public int delete(String caid);                         // 삭제
}

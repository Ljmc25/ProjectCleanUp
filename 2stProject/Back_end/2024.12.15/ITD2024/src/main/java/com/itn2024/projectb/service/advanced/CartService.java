package com.itn2024.projectb.service.advanced;

import com.itn2024.projectb.mapper.advanced.CartMapper;
import com.itn2024.projectb.vo.advanced.Cart;
import com.itn2024.projectb.vo.common.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : dlfdl
 * @fileName : GalleryService
 * @since : 24. 11. 1.
 * description :
 */
@Service
@RequiredArgsConstructor
public class CartService {
    private final CartMapper cartMapper;
    public List<?> selectCartList(Criteria searchVO) {
       List<?> page = cartMapper.selectCartList(searchVO);
        //        TODO: 총건수 저장 : Criteria 의 totalItems
        int count = cartMapper.selectCartListTotCnt(searchVO);
        searchVO.setTotalItems(count);
        return page;

    }


    //    TODO: 부서생성
//     좋은코딩 : 남이 봤는데 가장 간단한 코딩
    public void insert(Cart cart) {
        cartMapper.insert(cart);
    }




//    //    상세조회
//
//    public Optional<Cart> selectCart(String caid) {
//        return cartMapper.selectCart(caid);
//    }



    // 수정
// TODO: 업데이트
public void update(Cart cart){
    cartMapper.update(cart);
}

//    삭제
    public void delete(String caid) {
        cartMapper.delete(caid);
    }

}
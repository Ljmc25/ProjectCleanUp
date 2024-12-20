package com.itn2024.projectb.controller.advanced;

import com.itn2024.projectb.service.advanced.CartService;
import com.itn2024.projectb.vo.advanced.Cart;
import com.itn2024.projectb.vo.common.Criteria;
import com.itn2024.projectb.vo.dto.ResultDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : dlfdl
 * @fileName : GalleryMapper
 * @since : 24. 11. 1.
 * description :
 */


@Slf4j
@RestController
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;  // 서비스 객체

    // 전체조회
    @GetMapping("/api/advanced/cart")
    public ResponseEntity<?> selectCartList(Criteria searchVO) {
        List<?> carts = cartService.selectCartList(searchVO);
        log.info(searchVO.getEmail());
//      TODO: depts(배열) 1개 + 총건수 1개 -> 박스포장용 객체
//          객체생성(택배박스: DTO 객체) : 속성필드 (배열), 속성필드(총건수)
        ResultDto resultDto
                = new ResultDto(carts, searchVO.getTotalItems());

//      TODO: 상태코드 : HttpStatus.OK, NO_CONTENT ...
        return new ResponseEntity<>(resultDto, HttpStatus.OK);
    }


    // 삭제
    @DeleteMapping("/api/advanced/cart/delete/{caid}")
    public ResponseEntity<?> delete(
            @PathVariable String caid
    ) {
        cartService.delete(caid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
   

    //    부서 생성
//    TODO: spring :객체를 매개변수로 받는 어노테이션 : @ModelAttribute
//    TODO: springboot :객체를 매개변수로 받는 어노테이션 : @RequestBody
    @PostMapping("/api/advanced/cart")
    public ResponseEntity<?> insert(
            @RequestBody Cart cart
    ) {
        cartService.insert(cart);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //    부서 수정
    @PutMapping("/api/advanced/cart/{caid}")
    public ResponseEntity<?> update(
            @PathVariable String caid,
            @RequestBody Cart cart
    ) {
        cartService.update(cart);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}




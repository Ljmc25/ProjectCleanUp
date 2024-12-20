package com.itn2024.projectb.controller.advanced;

import com.itn2024.projectb.service.advanced.OrderDbService;
import com.itn2024.projectb.vo.advanced.OrderDb;
import com.itn2024.projectb.vo.common.Criteria;
import com.itn2024.projectb.vo.dto.OrderDto;
import com.itn2024.projectb.vo.dto.ResultDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author : NoteBook
 * @fileName : OrderDbController
 * @since : 2024-11-28
 * description :
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class



OrderDbController {
    private final OrderDbService orderDbService;

    // Order 전체 조회
    @GetMapping("/api/advanced/order/list")
    public ResponseEntity<?> selectOrderDbList(Criteria searchVO) {
        List<?> OrderDbList = orderDbService.selectOrderList(searchVO);
        ResultDto resultDto = new ResultDto(OrderDbList, searchVO.getTotalItems());
        return new ResponseEntity<>(resultDto, HttpStatus.OK);
    }

    // Order insert

    @PostMapping("/api/advanced/order/insert")
    public ResponseEntity<?> insertOrderDb(@RequestBody OrderDb orderDb) {
        orderDbService.insertOrderDb(orderDb);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Order 상세 조회

    @GetMapping("/api/advanced/order/select/{orid}")
    public ResponseEntity<?> selectOrderDb(@RequestBody String orid) {
        Optional<OrderDb> orderDb = orderDbService.selectOrderDb(orid);
        if (orderDb.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orderDb.get(), HttpStatus.OK);
    }

    // Order update
    @PutMapping("/api/advanced/order/update/{orid}")
    public ResponseEntity<?> updateOrderDb(@PathVariable String orid, @RequestBody OrderDb orderDb) {
        orderDbService.updateOrderDb(orderDb);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Order delete
    @DeleteMapping("/api/advanced/order/delete/{orid}")
    public ResponseEntity<?> deleteOrderDb(@PathVariable String orid) {
        orderDbService.deleteOrderDb(orid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/api/advanced/order/list/{paid}")
    public ResponseEntity<?> selectOrderDtoListByPaid(Criteria searchVO,@PathVariable String paid) {
        searchVO.setPaid(paid);
        List<?> orderDtoList = orderDbService.selectOrderDtoListByPaid(searchVO,paid);
        ResultDto resultDto = new ResultDto(orderDtoList, searchVO.getTotalItems());
        return new ResponseEntity<>(resultDto, HttpStatus.OK);
    }
}

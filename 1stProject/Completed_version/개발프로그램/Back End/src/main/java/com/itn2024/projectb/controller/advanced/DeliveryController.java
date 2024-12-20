package com.itn2024.projectb.controller.advanced;

import com.itn2024.projectb.service.advanced.DeliveryService;
import com.itn2024.projectb.vo.advanced.Delivery;
import com.itn2024.projectb.vo.common.Criteria;
import com.itn2024.projectb.vo.dto.ResultDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author : KTE
 * @fileName : DeliveryController
 * @since : 24. 11. 28.
 * description :
 */
@RestController
@RequiredArgsConstructor
public class DeliveryController {
    private final DeliveryService deliveryService;

    // 전체조회
    @GetMapping("/api/advanced/delivery")
    public ResponseEntity<?> selectDeliveryList(Criteria searchVO)
    {
        List<?> deliverys = deliveryService.selectDeliveryList(searchVO);
        ResultDto resultDto = new ResultDto(deliverys,searchVO.getTotalItems());
        return new ResponseEntity<>(resultDto, HttpStatus.OK);
    }

    // 기본키을 이용한 상세조회
    @GetMapping("/api/advanced/delivery/{deid}")
    public ResponseEntity<?> selectDeliveryById( @PathVariable String deid){
        Optional<Delivery> delivery = deliveryService.selectDeliveryById(deid);

        if(delivery.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(delivery.get(), HttpStatus.OK);
    }

    //결제 ID를 이용한 상세조회
    @GetMapping("/api/advanced/delivery/payment/{faid}")
    public ResponseEntity<?> selectDeliveryByPayment(@PathVariable String faid){
        Optional<Delivery> delivery = deliveryService.selectDeliveryByPayment(faid);
        if(delivery.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(delivery.get(), HttpStatus.OK);
    }

    // INSERT
    @PostMapping("/api/advanced/delivery")
    public ResponseEntity<?> insertDelivery(
            @RequestBody Delivery delivery
    ) {
        deliveryService.insertDelivery(delivery);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    // UPDATE
    @PutMapping("/api/advanced/delivery/{deid}")
    public ResponseEntity<?> updateDelivery(
            @PathVariable String deid, @RequestBody Delivery delivery
    ){
        deliveryService.updateDelivery(delivery);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

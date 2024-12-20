package com.itn2024.projectb.controller.advanced;

import com.itn2024.projectb.service.advanced.PaymentService;
import com.itn2024.projectb.vo.advanced.Payment;
import com.itn2024.projectb.vo.common.Criteria;
import com.itn2024.projectb.vo.dto.ResultDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author : KTE
 * @fileName : PaymentController
 * @since : 24. 12. 3.
 * description :
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class PaymentController {

    private final PaymentService paymentService;

//    전체조회
    @GetMapping("/api/advanced/payment")
    public ResponseEntity<?> selectPaymentList(Criteria searchVO) {
        log.info("테스트");
        List<?> payments = paymentService.selectPaymentList(searchVO);
        ResultDto resultDto
                = new ResultDto(payments, searchVO.getTotalItems());
        return new ResponseEntity<>(resultDto, HttpStatus.OK);
    }

    //    결제 상세조회
    @GetMapping("/api/advanced/payment/{paid}")
    public ResponseEntity<?> selectPayment(@PathVariable String paid) {
        Optional<Payment> payments = paymentService.selectPayment(paid);

        // 제품이 없는 경우
        if (!payments.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // 제품을 찾을 수 없으면 404 반환
        }

        // 제품이 있는 경우
        return new ResponseEntity<>(payments.get(), HttpStatus.OK);  // 제품 정보를 반환하며 200 상태 코드
    }
    //    insert
    @PostMapping("/api/advanced/payment/insert")
    public ResponseEntity<String> insert(@RequestBody Payment payment) {
        String paid= paymentService.insert(payment);
        return new ResponseEntity<>(paid, HttpStatus.OK);
    }

    //    update
    @PutMapping("/api/advanced/payment/update/{paid}")
    public ResponseEntity<?> update(@PathVariable String paid, @RequestBody Payment payment) {
        payment.setPaid(paid); // URL에서 받은 `paid`를 객체에 설정
        paymentService.update(payment);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

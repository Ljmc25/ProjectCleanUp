package com.itn2024.projectb.service.advanced;

import com.itn2024.projectb.mapper.advanced.PaymentMapper;
import com.itn2024.projectb.vo.advanced.Payment;
import com.itn2024.projectb.vo.basic.Product;
import com.itn2024.projectb.vo.common.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author : KTE
 * @fileName : PaymentService
 * @since : 24. 12. 3.
 * description :
 */
@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentMapper paymentMapper;

    //    TODO: 전체조회 + 총건수(Criteria 의 totalItems 변수 저장)
    public List<?> selectPaymentList(Criteria searchVO) {
        List<?> page = paymentMapper.selectPaymentList(searchVO);

//        TODO: 총건수 저장 : Criteria 의 totalItems
        int count = paymentMapper.selectPaymentListTotCnt(searchVO);
        searchVO.setTotalItems(count);

        return page;
    }

    public Optional<Payment> selectPayment(String paid) {
        return paymentMapper.selectPayment(paid);
    }

    public String insert(Payment payment) {
        paymentMapper.insert(payment);
        return payment.getPaid();
    }

    public void update(Payment payment) {
        paymentMapper.update(payment);
    }

}

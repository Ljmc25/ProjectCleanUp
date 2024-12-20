package com.itn2024.projectb.mapper.advanced;

import com.itn2024.projectb.vo.advanced.Payment;
import com.itn2024.projectb.vo.common.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

/**
 * @author : KTE
 * @fileName : PaymentMapper
 * @since : 24. 12. 3.
 * description :
 */
@Mapper
public interface PaymentMapper {
    public List<?> selectPaymentList(Criteria searchVO);                                                    // 전체 조회
    public int selectPaymentListTotCnt(Criteria searchVO);                                                  // 총 건수 구하기
    public Optional<Payment> selectPayment(String paid);                                                   // 상세 조회
    public int insert(Payment payment);                                                                    // insert
    public int update(Payment payment);                                                                    // update
}

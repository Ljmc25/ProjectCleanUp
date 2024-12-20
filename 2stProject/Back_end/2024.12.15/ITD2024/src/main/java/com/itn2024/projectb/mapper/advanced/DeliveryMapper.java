package com.itn2024.projectb.mapper.advanced;

import com.itn2024.projectb.vo.advanced.Delivery;
import com.itn2024.projectb.vo.common.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

/**
 * @author : KTE
 * @fileName : DeliveryMapper
 * @since : 24. 11. 28.
 * description :
 */

@Mapper
public interface DeliveryMapper {
    public List<?> selectDeliveryList(Criteria searchVO); // 전체조회
    public int selectDeliveryListTotCnt(Criteria searchVO); // 총개수
    public Optional<Delivery> selectDeliveryById(String deid); // 기본키을 이용한 상세조회
    public Optional<Delivery> selectDeliveryByPayment(String faid); //결제 ID를 이용한 상세조회
    public int insertDelivery(Delivery delivery); // INSERT
    public int updateDelivery(Delivery delivery); // UPDATE
    public int updateDeliveryByPayment(Delivery delivery);
}

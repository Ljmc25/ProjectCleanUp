package com.itn2024.projectb.service.advanced;

import com.itn2024.projectb.mapper.advanced.DeliveryMapper;
import com.itn2024.projectb.vo.advanced.Delivery;
import com.itn2024.projectb.vo.common.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author : KTE
 * @fileName : DeliveryService
 * @since : 24. 11. 28.
 * description :
 */

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryMapper deliveryMapper;

    // 전체조회
    public List<?> selectDeliveryList(Criteria searchVO){
        List<?> page = deliveryMapper.selectDeliveryList(searchVO);
        int count = deliveryMapper.selectDeliveryListTotCnt(searchVO);
        searchVO.setTotalItems(count);

        return page;
    }

    // 기본키을 이용한 상세조회
    public Optional<Delivery> selectDeliveryById(String deid){
        return deliveryMapper.selectDeliveryById(deid);
    }

    //결제 ID를 이용한 상세조회
    public Optional<Delivery> selectDeliveryByPayment(String faid){
        return deliveryMapper.selectDeliveryByPayment(faid);
    }

    // INSERT
    public void insertDelivery(Delivery delivery){deliveryMapper.insertDelivery(delivery);}

    // UPDATE
    public void updateDelivery(Delivery delivery){deliveryMapper.updateDelivery(delivery);}

    public void updateDeliveryByPayment(Delivery delivery){deliveryMapper.updateDeliveryByPayment(delivery);}

}

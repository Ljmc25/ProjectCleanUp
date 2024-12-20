package com.itn2024.projectb.service.advanced;

import com.itn2024.projectb.mapper.advanced.OrderDbMapper;
import com.itn2024.projectb.vo.advanced.OrderDb;
import com.itn2024.projectb.vo.common.Criteria;
import com.itn2024.projectb.vo.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author : NoteBook
 * @fileName : OrderDbService
 * @since : 2024-11-28
 * description :
 */
@Service
@RequiredArgsConstructor
public class OrderDbService {
    private final OrderDbMapper orderDbMapper;

    //전체조회 + 총건수 추가
    public List<?>selectOrderList(Criteria searchVO){
        List<?> page = orderDbMapper.selectOrderList(searchVO);
        int count = orderDbMapper.selectOrderDbTotCnt(searchVO);
        searchVO.setTotalItems(count);
        return page;
    }

    public void insertOrderDb(OrderDb orderDb) {
         orderDbMapper.insertOrderDb(orderDb);
    }

    public Optional<OrderDb> selectOrderDb(String orid) {
        return orderDbMapper.selectOrderDb(orid);
    }

    public void updateOrderDb(OrderDb orderDb) {
        orderDbMapper.updateOrderDb(orderDb);
    }

    public void deleteOrderDb(String orid) {
        orderDbMapper.deleteOrderDb(orid);
    }

    public List<?> selectOrderDtoListByPaid(Criteria searchVO, String paid){
        List<?> page = orderDbMapper.selectOrderDtoListByPaid(searchVO);
        int count = orderDbMapper.selectOrderDbTotCntByPaid(paid);
        searchVO.setTotalItems(count);
        return page;
    }

}

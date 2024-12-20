package com.itn2024.projectb.mapper.advanced;

import com.itn2024.projectb.vo.advanced.OrderDb;
import com.itn2024.projectb.vo.common.Criteria;
import com.itn2024.projectb.vo.dto.OrderDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

/**
 * @author : NoteBook
 * @fileName : OrderDbMapper
 * @since : 2024-11-28
 * description :
 */
@Mapper
public interface OrderDbMapper {
    public List<?> selectOrderList(Criteria searchVO); // 전체 조회
    public int selectOrderDbTotCnt(Criteria searchVO); // 페이징

    public int insertOrderDb(OrderDb orderDb);

    public Optional <OrderDb> selectOrderDb(String orid); // 상세조회
    public int updateOrderDb(OrderDb orderDb); // 수정
    public int deleteOrderDb(String orid); // 삭제


    public List<OrderDto> selectOrderDtoListByPaid(Criteria searchVO); // 결제 상세 조회
    public int selectOrderDbTotCntByPaid(String paid);
}

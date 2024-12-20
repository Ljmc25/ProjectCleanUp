package com.itn2024.projectb.mapper.advanced;

import com.itn2024.projectb.vo.advanced.InventoryDb;
import com.itn2024.projectb.vo.common.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

/**
 * @author : NoteBook
 * @fileName : InventoryDbMapper
 * @since : 2024-11-29
 * description :
 */
@Mapper
public interface InventoryDbMapper {
    public List<?> selectInventoryDbList(Criteria searchVO);
    public int selectInventoryDbTotCnt(Criteria searchVO);
    public int insertInventoryDb(InventoryDb inventoryDb);
    public Optional<InventoryDb> selectInventoryDb(String inid);
    public int updateInventoryDb(InventoryDb inventoryDb);
    public int deleteInventoryDb(String inid);

}

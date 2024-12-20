package com.itn2024.projectb.service.advanced;

import com.itn2024.projectb.mapper.advanced.InventoryDbMapper;
import com.itn2024.projectb.vo.advanced.InventoryDb;
import com.itn2024.projectb.vo.common.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author : NoteBook
 * @fileName : InventoryDbService
 * @since : 2024-11-29
 * description :
 */
@Service
@RequiredArgsConstructor
public class InventoryDbService {
    private final InventoryDbMapper inventoryDbMapper;

    public List<?> selectInventoryDbList(Criteria searchVO) {
        List<?> page = inventoryDbMapper.selectInventoryDbList(searchVO);
        int count = inventoryDbMapper.selectInventoryDbTotCnt(searchVO);
        searchVO.setTotalItems(count);
        return page;
    };

    public void insertInventoryDb(InventoryDb inventoryDb) {
        inventoryDbMapper.insertInventoryDb(inventoryDb);
    };

    public Optional<InventoryDb> selectInventoryDb(String inid) {
        return inventoryDbMapper.selectInventoryDb(inid);
    };

    public void updateInventoryDb(InventoryDb inventoryDb) {
        inventoryDbMapper.updateInventoryDb(inventoryDb);
    };

    public void deleteInventoryDb(String inid) {
        inventoryDbMapper.deleteInventoryDb(inid);
    };
}

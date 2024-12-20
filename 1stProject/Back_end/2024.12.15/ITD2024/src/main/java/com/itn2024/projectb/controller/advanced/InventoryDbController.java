package com.itn2024.projectb.controller.advanced;

import com.itn2024.projectb.service.advanced.InventoryDbService;
import com.itn2024.projectb.vo.advanced.InventoryDb;
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
 * @author : NoteBook
 * @fileName : InventoryDbController
 * @since : 2024-11-29
 * description :
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class InventoryDbController {
    private final InventoryDbService inventoryDbService;

    // 전체 조회
    @GetMapping("/api/advanced/inventory/list")
    public ResponseEntity<?> selectInventoryDbList(Criteria searchVO) {
        List<?> inventoryDbs = inventoryDbService.selectInventoryDbList(searchVO);
        ResultDto resultDto = new ResultDto(inventoryDbs, searchVO.getTotalItems());
        return new ResponseEntity<>(resultDto, HttpStatus.OK);
    }

    //inventory 생성
    @GetMapping("/api/advanced/inventory/insert")
    public ResponseEntity<?> insertInventoryDb(@RequestBody InventoryDb inventoryDb) {
        inventoryDbService.insertInventoryDb(inventoryDb);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // inventory 상세조회
    @GetMapping("/api/advanced/inventory/select/{inid}")
    public ResponseEntity<?> selectInventoryDb(@RequestBody String inid) {
        Optional<InventoryDb> inventoryDb = inventoryDbService.selectInventoryDb(inid);
        if (inventoryDb.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(inventoryDb.get(), HttpStatus.OK);
    }

    // inventory 수정
    @PutMapping("/api/advanced/inventory/update/{inid}")
    public ResponseEntity<?> updateInventoryDb(@PathVariable String inid, @RequestBody InventoryDb inventoryDb) {
        inventoryDbService.updateInventoryDb(inventoryDb);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // inventory 상세조회
    @DeleteMapping("/api/advanced/inventory/delete/{inid}")
    public ResponseEntity<?> deleteInventoryDb(@PathVariable String inid) {
        inventoryDbService.deleteInventoryDb(inid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

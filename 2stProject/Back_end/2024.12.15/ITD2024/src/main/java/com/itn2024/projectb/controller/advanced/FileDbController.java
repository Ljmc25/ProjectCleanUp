package com.itn2024.projectb.controller.advanced;

import com.itn2024.projectb.service.advanced.FileDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequiredArgsConstructor
public class FileDbController
{

    private final FileDbService fileDbService;

    @GetMapping("/product/{prid}/image")
    public ResponseEntity<String> getProductImage(@PathVariable String prid) {
        Optional<String> imageUrl = fileDbService.selectProductImage(prid);

        // 이미지가 존재하면 반환하고, 없으면 404 상태를 반환
        return imageUrl.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("이미지가 없습니다."));
    }


}

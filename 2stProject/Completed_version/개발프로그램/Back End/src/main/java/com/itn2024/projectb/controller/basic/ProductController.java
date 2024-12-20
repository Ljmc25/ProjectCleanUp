package com.itn2024.projectb.controller.basic;

import com.itn2024.projectb.service.advanced.FileDbService;
import com.itn2024.projectb.service.basic.ProductService;
import com.itn2024.projectb.vo.advanced.Payment;
import com.itn2024.projectb.vo.basic.Product;
import com.itn2024.projectb.vo.common.Criteria;
import com.itn2024.projectb.vo.dto.ResultDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private final ProductService productService;
    private final FileDbService fileDbService;

    // 제품 전체 조회(고객)
    @GetMapping("/api/basic/product")
    public ResponseEntity<?> selectProductList(Criteria searchVO) {
        List<Product> products = productService.selectProductList(searchVO);  // 명시적으로 Product 리스트로 정의

        // 각 상품에 대해 첫 번째 이미지만 함께 포함시켜 반환
        List<Map<String, Object>> productsWithImages = products.stream().map(product -> {
            // 첫 번째 이미지만 가져오기
            Optional<String> images = fileDbService.selectProductImage(product.getPrid());
            String firstImage = images.orElse("");  // Optional이 비어있으면 빈 문자열을 반환

            return Map.of("product", product, "image", firstImage);  // 첫 번째 이미지만 반환
        }).collect(Collectors.toList());

        ResultDto resultDto = new ResultDto(productsWithImages, searchVO.getTotalItems());
        return new ResponseEntity<>(resultDto, HttpStatus.OK);
    }



    @GetMapping("/api/basic/product/{prid}")
    public ResponseEntity<?> selectProduct(@PathVariable("prid") String prid) {
        log.info("Received prid: " + prid);

        // 제품 정보를 조회
        Optional<Product> productOptional = productService.selectProductAdmin(prid);

        // 제품이 존재하지 않으면 404 반환
        if (productOptional.isEmpty()) {
            log.warn("Product with prid: {} not found", prid);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // 404 Not Found
        }

        Product product = productOptional.get();

        // 제품에 해당하는 이미지 목록을 조회
        List<String> images = fileDbService.selectProductImages(prid);

        // 응답 객체 생성
        Map<String, Object> response = new HashMap<>();
        response.put("product", product);   // 제품 정보
        response.put("images", images);     // 해당 제품의 이미지 UUID 목록

        log.info("Returning product and images for prid: {}", prid);

        // 200 OK 상태로 응답
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/api/basic/product/category")
    public ResponseEntity<?> selectProductCategoryList(@RequestParam("karegorie") String karegorie, Criteria searchVO) {
        List<Product> products = productService.selectProductCategoryList(karegorie);  // 카테고리별 조회

        // 각 상품에 대해 첫 번째 이미지만 함께 포함시켜 반환
        List<Map<String, Object>> productsWithImages = products.stream().map(product -> {
            Optional<String> images = fileDbService.selectProductImage(product.getPrid());
            String firstImage = images.orElse("");  // Optional이 비어있으면 빈 문자열을 반환
            return Map.of("product", product, "image", firstImage);
        }).collect(Collectors.toList());

        ResultDto resultDto = new ResultDto(productsWithImages, searchVO.getTotalItems());
        return new ResponseEntity<>(resultDto, HttpStatus.OK);
    }




    @GetMapping("/api/basic/product/admin")
    public ResponseEntity<?> selectProductListAdmin(Criteria searchVO) {
        List<Product> products = productService.selectProductListAdmin(searchVO);

        // 각 상품에 대해 첫 번째 이미지만 함께 포함시켜 반환
        List<Map<String, Object>> productsWithImages = products.stream().map(product -> {
            Optional<String> images = fileDbService.selectProductImage(product.getPrid());
            String firstImage = images.orElse("");  // Optional이 비어있으면 빈 문자열을 반환
            return Map.of("product", product, "image", firstImage);
        }).collect(Collectors.toList());

        ResultDto resultDto = new ResultDto(productsWithImages, searchVO.getTotalItems());
        return new ResponseEntity<>(resultDto, HttpStatus.OK);
    }



    @GetMapping("/api/basic/product/admin/{prid}")
    public ResponseEntity<?> selectProductAdmin(@PathVariable("prid") String prid) {
        log.info("Received prid: " + prid);

        // 제품 정보를 조회
        Optional<Product> productOptional = productService.selectProductAdmin(prid);

        // 제품이 존재하지 않으면 404 반환
        if (productOptional.isEmpty()) {
            log.warn("Product with prid: {} not found", prid);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // 404 Not Found
        }

        Product product = productOptional.get();

        // 제품에 해당하는 이미지 목록을 조회
        List<String> images = fileDbService.selectProductImages(prid);

        // 응답 객체 생성
        Map<String, Object> response = new HashMap<>();
        response.put("product", product);   // 제품 정보
        response.put("images", images);     // 해당 제품의 이미지 목록

        log.info("Returning product and images for prid: {}", prid);

        // 200 OK 상태로 응답
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    // 상품 등록
    @PostMapping("/api/basic/product/insert")
    public ResponseEntity<?> insert(
            @RequestParam String name,
            @RequestParam String karegorie,
            @RequestParam String gender,
            @RequestParam int price,
            @RequestParam String contents,
            @RequestParam int sales,
            @RequestParam(required = false) MultipartFile[] images  // 이미지들
    ) throws Exception {
        // 상품 객체 생성 (prid는 서비스에서 자동 생성)
        Product product = new Product(name, karegorie, gender, price, contents, sales);

        // 상품 정보 DB에 저장 후 prid 값 생성
        productService.insert(product, images);

        // prid 값이 정상적으로 생성되었는지 확인
        if (product.getPrid() == null) {
            log.error("상품 ID(prid)가 null입니다.");
            return new ResponseEntity<>("상품 등록에 실패했습니다. prid를 생성할 수 없습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // 상품 등록 후 prid 값을 포함하여 응답
        return ResponseEntity.ok(Map.of("message", "상품이 성공적으로 저장되었습니다.", "prid", product.getPrid()));
    }

    // 상품 삭제
    @DeleteMapping("/api/basic/product/delete/{prid}")
    public ResponseEntity<?> delete(@PathVariable String prid) {
        productService.delete(prid);
        fileDbService.deleteFiles(prid);  // 해당 상품의 이미지 삭제 처리
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //    update
    @PutMapping("/api/basic/product/update/sales/{prid}")
    public ResponseEntity<?> updateSales(@PathVariable String prid, @RequestBody Product product,
                                         @RequestParam(defaultValue = "") int num) {
        product.setSales(num);
        productService.updateSales(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

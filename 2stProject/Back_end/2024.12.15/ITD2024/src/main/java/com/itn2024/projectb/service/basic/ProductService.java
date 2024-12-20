package com.itn2024.projectb.service.basic;

import com.itn2024.projectb.mapper.basic.ProductMapper;
import com.itn2024.projectb.service.advanced.FileDbService;
import com.itn2024.projectb.vo.advanced.FileDb;
import com.itn2024.projectb.vo.basic.Product;
import com.itn2024.projectb.vo.common.Criteria;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductMapper productMapper;
    private final FileDbService fileDbService;

    // 제품 전체 조회(고객용)
    public List<Product> selectProductList(Criteria searchVO) {
        List<Product> page = productMapper.selectProductList(searchVO);
        int count = productMapper.selectProductTotCnt(searchVO);
        searchVO.setTotalItems(count);
        return page;
    }

    // 제품 카테고리별 조회
    public List<Product> selectProductCategoryList(String karegorie) {
        // Criteria 객체를 생성하여 karegorie를 설정하고, 조회를 호출
        Criteria criteria = new Criteria();
        criteria.setKaregorie(karegorie);  // karegorie를 설정
        return productMapper.selectProductCategoryList(criteria); // Mapper 호출
    }

    // 제품 상세조회
    public Optional<Product> selectProduct(String prid) {
        return productMapper.selectProduct(prid);
    }

    // 제품 전체 조회(관리자용)
    public List<Product> selectProductListAdmin(Criteria searchVO) {
        // Product 조회 (검색 조건 포함)
        List<Product> page = productMapper.selectProductListAdmin(searchVO);

        // 전체 상품 개수 조회 (검색 조건 포함)
        int count = productMapper.selectProductListAdminTotCnt(searchVO);
        searchVO.setTotalItems(count);

        return page;
    }

    // 제품 상세 조회(관리자용)
    public Optional<Product> selectProductAdmin(String prid) {
        return productMapper.selectProductAdmin(prid);
    }

    public void insert(Product product, MultipartFile[] images) throws Exception {
        // 상품 정보 DB에 저장 (prid 자동 생성)
        productMapper.insert(product);

        // prid 값이 정상적으로 생성되었는지 확인
        if (product.getPrid() == null) {
            throw new RuntimeException("상품 ID(prid)가 null입니다.");
        }

        // prid 값 디버깅 로그 추가
        log.info("상품 등록 후 prid: {}", product.getPrid());

        // 이미지가 있을 경우 처리
        if (images != null && images.length > 0) {
            for (int i = 0; i < images.length; i++) {
                // FileDb 객체 생성 (각 이미지에 대한 처리)
                FileDb fileDb = new FileDb();
                fileDb.setPrid(product.getPrid()); // 생성된 prid 설정
                String fname = images[i].getOriginalFilename();
                System.out.println(fname);
                log.info("이미지 삽입 시 prid: {}", product.getPrid()); // 이미지 삽입 시 prid 확인
                fileDb.setFileData(images[i].getBytes()); // 이미지 데이터 저장

                // 이미지 파일 DB에 삽입
                fileDbService.insertFile(fileDb,fname);
            }
        }
    }

    // Update 상품 (이미지 처리 제외)
    public void update(Product product) {
        // 상품 정보 업데이트
        productMapper.update(product);
    }

    // Delete 상품
    public void delete(String prid) {
        productMapper.delete(prid);
    }
    public void updateSales(Product product){productMapper.updateSales(product);}
}


package com.itn2024.projectb.mapper.basic;

import com.itn2024.projectb.vo.basic.Product;
import com.itn2024.projectb.vo.common.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

/**
 * @author : KTE
 * @fileName : ProductMapper
 * @since : 24. 11. 28.
 * description :
 */
@Mapper
public interface ProductMapper {
    public List<Product> selectProductList(Criteria searchVO); // 전체 조회(고객용)
    public int selectProductTotCnt(Criteria searchVO);
    public List<Product> selectProductListWithPaging(@Param("offset") int offset, @Param("size") int size); // 상품 목록 조회(스크롤)
    public List<Product> selectProductCategoryList(Criteria searchVO); // 제품 카테고리별 조회
    public Optional<Product> selectProduct(String prid); // 제품 상세 조회

    public List<Product> selectProductListAdmin(Criteria searchVO); // 전체 조회(관리자용)
    public int selectProductListAdminTotCnt(Criteria searchVO); // 총 건수 구하기(관리자용)
    public Optional<Product> selectProductAdmin(String prid); // 제품 상세 조회(관리자용)

    public int insert(Product product); // 제품 생성
    public int countProducts(); // 제품 개수 구하기
    public int update(Product product); // 제품 수정

    public int delete(String prid); // 제품 삭제
    public int updateSales(Product product);
}
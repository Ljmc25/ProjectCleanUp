package com.itn.projectb.mapper.basic;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.itn.projectb.vo.basic.ProductVO;
import com.itn.projectb.vo.basic.ScreeningVO;
import com.itn.projectb.vo.common.Criteria;

@Mapper
public interface ProductMapper {
	public List<?> selectMovie_allList(Criteria searchVO);   // 전체 조회 : 페이징 기능 추가
	public int selectMovie_allListTotCnt(Criteria searchVO); // 개수 세기 
	public List<?> selectLimitList();
	public int insert(ProductVO productVO);                   // insert 함수
	public ProductVO selectMovie_all(String pdid);                  // 상세조회 함수
	public void review_register(ProductVO productVO); // 영화 실관람평 리뷰 등록

	public int selectProductCount(String title);// 영화 실관람평 리뷰 등록
	
	public List<?> selectMovie_reviews(String pdid); // 관람평 전체 조회 : 페이징 기능 추가
	public int selectMovie_reviewsTotCnt(Criteria search_reaviewsVO); // 관람평 개수 세기
	
	public ProductVO selectProduct(ScreeningVO screeningVO);
	
	List<ProductVO> selectProductList(Criteria searchVO) throws Exception;									// 영화 목록 조회
	List<ProductVO> getProductList(@Param("offset") int offset, @Param("limit") int limit);		// 영화 목록을 반환하는 메서드	
	int getTotalProductCount(Criteria criteria);												// 전체 레코드 수를 반환하는 메서드														// 영화 추가																	
    void update(ProductVO product); 															// 영화 업데이트
    ProductVO selectProductById(String pdid); 													// 특정 ID로 영화 조회
    void delete(String pdid); 
}

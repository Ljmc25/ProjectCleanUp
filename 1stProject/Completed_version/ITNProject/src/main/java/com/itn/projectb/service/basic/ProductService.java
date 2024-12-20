package com.itn.projectb.service.basic;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.itn.projectb.vo.basic.ProductVO;
import com.itn.projectb.vo.basic.ScreeningVO;
import com.itn.projectb.vo.common.Criteria;

public interface ProductService {
	public ProductVO selectMovie_all(String pdid) throws Exception;     // 상세 조회
	public List<?> selectMovie_allList(Criteria searchVO) throws Exception;
	public int selectMovie_allListTotCnt(Criteria searchVO);     // 개수 세기 
	public void insertMovie_all(ProductVO productVO) throws Exception; // insert 함수
	public void review_register(ProductVO productVO) throws Exception; // 영화 실관람평 리뷰 등록
	public List<?> selectMovie_reviews(String pdid) throws Exception; // 영화 실관람평 리뷰 상세 조회
	public int selectMovie_reviewsTotCnt(Criteria search_reaviewsVO); // 영화 실관람평 리뷰 개수세기
	public List<?> selectLimitList();
	public ProductVO selectProduct(ScreeningVO screeningVO);
	// 관리자 페이지
	public List<ProductVO> selectProductList(Criteria searchVO) throws Exception; 						// 전체 조회
	public List<ProductVO> getProductList(@Param("offset") int offset, @Param("limit") int limit);		// 영화 목록을 반환하는 메서드	
	public int getTotalProductCount(Criteria criteria) throws Exception;																		// 전체 레코드 수를 반환하는 메서드
	public void insert(ProductVO productVO) throws Exception; 											// 영화 추가
	public void update(ProductVO product); 																// 영화 업데이트
	public ProductVO selectProductById(String pdid); 														// 특정 ID로 영화 조회
	public void delete(String pdid); 																		// 영화 삭제  
}

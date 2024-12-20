package com.itn.projectb.service.impl.basic;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itn.projectb.mapper.basic.ProductMapper;
import com.itn.projectb.service.basic.ProductService;
import com.itn.projectb.vo.basic.ProductVO;
import com.itn.projectb.vo.basic.ScreeningVO;
import com.itn.projectb.vo.common.Criteria;

@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	ProductMapper productMapper;

	
	//	함수 재정의 : source
	@Override
	public List<?> selectMovie_allList(Criteria searchVO) throws Exception {
		// TODO : 전체 조회
		return productMapper.selectMovie_allList(searchVO);
	}
	
//	개수 세기 함수 : source 
	@Override
	public int selectMovie_allListTotCnt(Criteria searchVO) {
		// TODO deptMapper.개수세기함수 실행
		return productMapper.selectMovie_allListTotCnt(searchVO);
	}
//	insert 함수 : source 
	@Override
	public void insertMovie_all(ProductVO productVO) throws Exception {
		// TODO deptMapper 의 insert 함수 실행 : 내부 sql 문 실행
		productMapper.insert(productVO);
	}
	
	
//	상세조회 : source
	@Override
	public ProductVO selectMovie_all(String pdid) throws Exception {
		// TODO deptMapper 의 상세조회 함수 실행
		
		ProductVO productVO = productMapper.selectMovie_all(pdid);
		return productVO;
	}

	@Override // 영화 실관람평 리뷰 등록
	public void review_register(ProductVO productVO) throws Exception {
		// title가 PRODUCT_TB에 존재하는지 확인
		String title = productVO.getTitle();
		System.out.println("Title: " + title); // 디버깅을 위한 출력

	    int productCount = productMapper.selectProductCount(productVO.getTitle());
	    
	    if (productCount > 0) {
	        
	    	productMapper.review_register(productVO);
	    } else {
	       
	        throw new Exception("Invalid title: " + productVO.getTitle());
	    }
	}
	
	@Override
	public List<?> selectMovie_reviews(String pdid) throws Exception {
		// TODO Auto-generated method stub
		return productMapper.selectMovie_reviews(pdid);
		
	}

	@Override
	public int selectMovie_reviewsTotCnt(Criteria search_reaviewsVO) {
		// TODO Auto-generated method stub
		return productMapper.selectMovie_reviewsTotCnt(search_reaviewsVO);
	}

	@Override
	public List<?> selectLimitList() {
		// TODO Auto-generated method stub
		return productMapper.selectLimitList();
	}

	@Override
	public ProductVO selectProduct(ScreeningVO screeningVO) {
		// TODO Auto-generated method stub
		return productMapper.selectProduct(screeningVO);
	}
	
	@Override
	public List<ProductVO> selectProductList(Criteria searchVO) throws Exception {
	    return productMapper.selectProductList(searchVO);
	}

	@Override
	public List<ProductVO> getProductList(int offset, int limit) {
		// TODO Auto-generated method stub
		return productMapper.getProductList(offset, limit);
	}

	@Override
	public int getTotalProductCount(Criteria criteria) throws Exception {
		return productMapper.getTotalProductCount(criteria); // Criteria 객체를 전달
	}


	@Override
	public void insert(ProductVO product) {
		// TODO Auto-generated method stub
		productMapper.insert(product);
	}
		
	@Override
	@Transactional
	public void update(ProductVO product) {
		// TODO Auto-generated method stub
		productMapper.update(product);
	}

	@Override
	@Transactional
	public ProductVO selectProductById(String pdid) {
		// TODO Auto-generated method stub
		return productMapper.selectProductById(pdid);
	}

	@Override
	@Transactional
	public void delete(String pdid) {
		// TODO Auto-generated method stub
		productMapper.delete(pdid);
	}
}

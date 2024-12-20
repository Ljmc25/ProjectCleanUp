package com.itn2024.projectb.vo.common;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author : GGG
 * @fileName : Criteria
 * @since : 2024-04-02 description : 공통 클래스 페이징처리 = 전자결재 프레임워크에서 가져옴
 */
@Getter
@Setter
public class Criteria implements Serializable {
	/**
	 * serialVersion UID
	 */
	private static final long serialVersionUID = -858838578081269359L;

	/** 검색조건 */
	private String searchCondition = "";

	/** 검색Keyword */
	private String searchKeyword = "";

	/** 검색사용여부 */
	private String searchUseYn = "";

	/** 현재페이지 */
	private int pageIndex = 0;

	/** 페이지갯수 */
	private int pageUnit = 10;

	/** 페이지사이즈 */
	private int pageSize = 10;

	/** firstIndex */
	private int firstIndex = 1;

	/** lastIndex */
	private int lastIndex = 1;

	/** recordCountPerPage */
	private int recordCountPerPage = 10; // 화면에 보일 개수
	private int totalItems = 0;          // 총건수

	private String category;  // 카테고리
	private String name;      // 제품명
	private Integer minPrice; // 최소 가격
	private Integer maxPrice; // 최대 가격
	private Integer pageNum = 1;  // 페이지 번호, 기본값은 1

	private int page = 1;  // 페이지 번호 (기본값은 1)
	private int size = 8;  // 한 페이지에 표시할 데이터 개수 (기본값은 8)

	private String insertTime;

	private String updateTime;
	private String email;
	public int getOffset() {
		return (page - 1) * size;
	}

	private String paid;
	private String searchCriteria = "all";
	private String karegorie;  // 제품 카테고리 (karegorie)
}

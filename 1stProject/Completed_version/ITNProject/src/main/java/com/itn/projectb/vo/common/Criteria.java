package com.itn.projectb.vo.common;

import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : GGG
 * @fileName : Criteria
 * @since : 2024-04-02 
 * description : 공통 클래스 페이징 처리
 */
@Getter
@Setter
public class Criteria implements Serializable {
    private static final long serialVersionUID = -858838578081269359L;

    /** 검색 조건 */
    private String searchCondition = "";

    /** 검색 Keyword */
    private String searchKeyword = "";

    /** 검색 사용 여부 */
    private String searchUseYn = "";
    
    private String searchValue;

    /** 현재 페이지 인덱스 */
    private int pageIndex = 1;

    
    /** 페이지 갯수 */
    private int pageUnit = 10;

    /** 페이지 사이즈 */
    private int pageSize = 10;

    /** firstIndex */
    private int firstIndex = 1;

    /** lastIndex */
    private int lastIndex = 1;

    /** recordCountPerPage : 1페이지당 화면에 보일 개수 */
    private int recordCountPerPage = 10;

    private String insertTime;
    private String updateTime;

    private int pageNum;  // 페이지 번호를 저장할 변수
    
    private int pageNum2;  // 페이지 번호를 저장할 변수
    private int recordCountPerPage2 = 6; // 페이지당 레코드 수
    
	private String email;
	
	public String getEmail() {
	    return email;
	}

	public void setEmail(String email) {
	    this.email = email;
	}
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    // 기본 생성자
    public Criteria() {
        this.pageIndex = 1; // 기본 페이지는 1
    }

    // 페이지 인덱스를 받는 생성자
    public Criteria(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getPageNum2() {
        return pageNum2;
    }

    public void setPageNum2(int pageNum2) {
        this.pageNum2 = pageNum2;
    }

    public int getRecordCountPerPage() {
        return recordCountPerPage;
    }

    public void setRecordCountPerPage(int recordCountPerPage) {
        this.recordCountPerPage = recordCountPerPage;
    }
    private int totalPages; // 추가

    public int getStartIndex() {
        return (this.pageIndex - 1) * this.recordCountPerPage; // 시작 인덱스 계산
    }
    // 총 페이지 수 계산 메서드
    public void calculateTotalPages(int totalRecords) {
        if (totalRecords > 0) {
            this.totalPages = (int) Math.ceil((double) totalRecords / this.recordCountPerPage);
        } else {
            this.totalPages = 0;
        }
    }
}

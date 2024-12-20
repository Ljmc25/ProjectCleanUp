// 		페이지 번호 클릭시 실행될 함수
function fn_egov_link_page(pageNo) {
	// 			1) 새로운 페이지번호를 받기 : 0 -> 1,2(클릭), pageNo(클릭한 번호)
	document.listForm.pageIndex.value = pageNo; // 컨트롤러로 전송됨
	// 			2) 재조회(다시 전체조회) : /basic/dept
	document.listForm.action = ContextPath + "/movie_all";
	// 			3) submit() 실행
	document.listForm.submit();
}
function fn_egov_selectList() {
	// 			1) 페이지번호 : 1 번으로 전송(컨트롤러)
	document.listForm.pageIndex.value = 1;
	// 			2) action 속성 : /basic/dept (다시 전체조회)
	document.listForm.action = ContextPath + "/movie_all";
	// 			3) submit() 실행
	document.listForm.submit();
	// 			4) (참고) 검색어(input) 밑에서 자동으로 전송됨(컨트롤러)
}

// 		부서번호 클릭시 상세조회 페이지 열기 함수
function fn_select(pdid) {
	// 			1) 부서번호(contents)[input 태그] -> 상세조회로 전달
	document.listForm.pdid.value = pdid;
	// 			2) action = "/view_details/view_details"
	document.listForm.action = ContextPath+"/view_details";
	// 			3) submit()
	document.listForm.submit();
}

function reservePage(title) {
	location.href = ContextPath + "/reserve?title="+title;
}
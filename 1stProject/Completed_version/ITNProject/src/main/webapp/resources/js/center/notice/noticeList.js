// 페이지 번호 클릭시 실행될 함수
function fn_egov_link_page(pageNo) {
// 	1) 새로운 페이지번호를 받기 : 0 -> 1,2(클릭), pageNo(클릭한 번호)
	document.searchForm.pageIndex.value = pageNo; // 컨트롤러로 전송됨
// 	2) 재조회(다시 전체조회) : /basic/dept
	document.searchForm.action = ContextPath + "/noticeAll";
// 	3) submit() 실행
	document.searchForm.submit();
}
    
// 	검색 버튼 클릭시 실행될 함수
function fn_egov_selectList() {
// 	1) 페이지번호 : 1 번으로 전송(컨트롤러)
	document.searchForm.pageIndex.value = 1;
// 	2) action 속성 : /basic/dept (다시 전체조회)
	documentsearchForm.action = ContextPath + "/noticeAll";
// 	3) submit() 실행
	document.searchForm.submit();
// 	4) (참고) 검색어(input) 밑에서 자동으로 전송됨(컨트롤러)
}

function detailPage(noid){
	location.href = ContextPath+"/noticeDetail?noid="+noid;
}
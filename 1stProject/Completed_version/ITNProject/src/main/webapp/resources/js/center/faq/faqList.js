// 검색
function fn_egov_selectList() {
  document.faqForm.pageIndex.value = 1;
  document.faqForm.action = ContextPath + "/faqList";
  document.faqForm.submit();
}

//	페이지 번호 클릭시 실행될 함수
function fn_egov_link_page(pageNo) {
  // 	1) 새로운 페이지번호를 받기 : 0 -> 1,2(클릭), pageNo(클릭한 번호)
  document.faqForm.pageIndex.value = pageNo; // 컨트롤러로 전송됨
  // 	2) 재조회(다시 전체조회) : /basic/dept
  document.faqForm.action = ContextPath + "/faqList";
  // 	3) submit() 실행
  document.faqForm.submit();
}

function faqDetailPage(faid) {
	location.href = ContextPath + "/faqDetail?faid="+faid;
}

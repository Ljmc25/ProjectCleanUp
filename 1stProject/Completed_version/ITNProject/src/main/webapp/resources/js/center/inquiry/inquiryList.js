function submitWrite() {
  if (!writeTitle.value) {
    alert("제목을 입력해주세요");
    return false;
  }

  if (!writeContent.value) {
    alert("내용을 입력해주세요");
    return false;
  }

  writeForm.action = ContextPath + "/inquiry/write";
  writeForm.submit();
}

function fn_egov_link_page(pageNo) {
  document.listForm.pageIndex.value = pageNo;
  document.listForm.method = "get";
  document.listForm.action = ContextPath + "/inquiryList";
  document.listForm.submit();
}

function fn_egov_selectList() {
  document.listForm.pageIndex.value = 1;
  document.listForm.action = ContextPath + "/inquiryList";
  document.listForm.submit();
}

function fn_delete(inid) {
  const id = document.getElementById("inid");
  id.value = inid;

  document.listForm.method = "post";
  document.listForm.action = ContextPath + "/inquiry/delete";

  document.listForm.submit();
}

function detailPage(inid){
	location.href = ContextPath + "/inquiryDetail?inid="+inid;
}

function UpdatePage(inid) {
	location.href = ContextPath + "/inquiryUpdate?inid="+inid;
}




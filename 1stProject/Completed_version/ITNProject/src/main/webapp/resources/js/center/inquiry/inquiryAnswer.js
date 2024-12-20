
function fn_save() {
  document.addForm.action = ContextPath + "/inquiryAnswer/write";
  document.addForm.submit();
}

function InquiryPage(){
	location.href =  ContextPath + "/inquiryList";
}
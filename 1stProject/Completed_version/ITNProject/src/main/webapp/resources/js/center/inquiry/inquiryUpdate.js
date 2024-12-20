const updateForm = document.getElementById("updateForm");
const submit = document.getElementById("update-bn");
const back = document.getElementById("back-bn");

submit.addEventListener("click",()=>{
	updateForm.action =  ContextPath + "/inquiry/update";
	updateForm.submit();
});

back.addEventListener("click",()=>{
	location.href =  ContextPath + "/inquiryList";
});
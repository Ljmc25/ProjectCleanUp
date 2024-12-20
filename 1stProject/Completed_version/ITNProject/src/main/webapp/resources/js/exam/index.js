const listForm = document.getElementById("listForm");
const hidden = document.getElementById("uuid");

const addition = document.getElementById("addition");
const showing = document.getElementById("showing");

const fn_egov_link_page = (pageNo) => {
		listForm.pageIndex.value = pageNo;
		listForm.action = ContextPath + "/exam";
		listForm.submit();
}

const view_page = (uuid) => {
	hidden.value = uuid;
	listForm.action = ContextPath + "/exam/view";
	listForm.submit();
}

const update_page = (uuid) => {
	hidden.value = uuid;
	listForm.action = ContextPath + "/exam/view/update";
	listForm.submit();
}

const delete_contents = (uuid) => {
	hidden.value = uuid;
	listForm.method = "post";
	listForm.action = ContextPath + "/exam/delete";
	listForm.submit();
}

addition.addEventListener("click",()=>{
	location.href = ContextPath + "/exam/view/add";
});

showing.addEventListener("click",()=>{
	location.href = ContextPath + "/showing/view/add";
});
	
	
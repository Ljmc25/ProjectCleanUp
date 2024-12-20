const updateForm = document.getElementById("updateForm");

const hidden = document.getElementById("uuid");
const title = document.getElementById("title");
const contents = document.getElementById("contents");

const cancel = document.getElementById("cancel");

const update = (uuid) => {
	if(!title.value){
		alert("수정할 제목을 입력하세요.");
		return false;
	}
	
	if(!contents.value){
		alert("수정할 내용을 입력하세요.");
		return false;
	}
	
	hidden.value = uuid;
	updateForm.action = ContextPath+"/exam/update";
	updateForm.method="post";
	updateForm.submit();
}

cancel.addEventListener("click",()=>{
	location.href = ContextPath + "/exam";
});
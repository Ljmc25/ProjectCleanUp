const addForm = document.getElementById("addForm");

const title = document.getElementById("title");
const contents = document.getElementById("contents");

const add = document.getElementById("add");
const cancel = document.getElementById("cancel");

cancel.addEventListener("click",()=>{
	location.href = ContextPath + "/exam";
});

add.addEventListener("click",()=>{
	if(!title.value){
		alert("제목을 입력하세요.");
		return false;
	}
	
	if(!contents.value){
		alert("내용을 입력하세요.");
		return false;
	}
	
	addForm.action = ContextPath+"/exam/add";
	addForm.method="post";
	addForm.submit();
});
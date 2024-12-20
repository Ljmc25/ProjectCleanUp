const detailForm = document.getElementById("detailForm");
const reviewBn = document.getElementById("review-bn");
const box1 = document.getElementById("box1");
const box2 = document.getElementById("box2");
const box3 = document.getElementById("box3");
const bn1 = document.getElementById("bn1");
const bn2 = document.getElementById("bn2");
const bn3 = document.getElementById("bn3");

function changeTrailer(url) {
    document.getElementById('mainTrailer').src = url;
}

//   페이지 번호 클릭시 실행될 함수
function fn_egov_link_page(pageNo) {
//      1) 새로운 페이지번호를 받기 : 0 -> 1,2(클릭), pageNo(클릭한 번호)
   document.listForm.pageIndex.value = pageNo; // 컨트롤러로 전송됨
//      2) 재조회(다시 전체조회) : /basic/dept
   document.listForm.action = ContextPath + "/view_details";
//      3) submit() 실행
   document.listForm.submit();
}

function fn_egov_selectList() {
//  1) 페이지번호 : 1 번으로 전송(컨트롤러)
 document.listForm.pageIndex.value = 1;
//  2) action 속성 : /basic/dept (다시 전체조회)
 document.listForm.action = ContextPath + "/view_details";
//  3) submit() 실행
 document.listForm.submit();
//  4) (참고) 검색어(input) 밑에서 자동으로 전송됨(컨트롤러)
}


//       페이지 번호 클릭시 실행될 함수
function fn_egov_link_page(pageNo) {
//    1) 새로운 페이지번호를 받기 : 0 -> 1,2(클릭), pageNo(클릭한 번호)
   document.listForm.pageIndex.value = pageNo; // 컨트롤러로 전송됨
//    2) 재조회(다시 전체조회) : /basic/dept
   document.listForm.action = ContextPath + "/movie_all";
//    3) submit() 실행
   document.listForm.submit();
} 

bn1.addEventListener("click",()=>{
	box1.style.display = "block";
    box2.style.display = "none";
    box3.style.display = "block";
});
    
bn2.addEventListener("click",()=>{
    box1.style.display = "none";
    box2.style.display = "block";
    box3.style.display = "none";
});
    
bn3.addEventListener("click",()=>{
     box1.style.display = "none";
     box2.style.display = "none";
     box3.style.display = "block";
});

reviewBn.addEventListener("click",()=>{
	detailForm.action = ContextPath + "/view_details_review_register";
	detailForm.submit();
});

function reservePage(title) {
	location.href = ContextPath + "/reserve?title="+title;
}
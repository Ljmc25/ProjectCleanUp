const email = document.getElementById("email");
const password = document.getElementById("password");
const phone = document.getElementById("phone");


$(document).ready(function () {
  $(".cancel-button").each(function () {
    var updateTime = $(this).data("update-time");
    if (updateTime) {
      $(this).prop("disabled", true); // 버튼 비활성화
      $(this)
        .removeClass("btn-primary")
        .addClass("btn-secondary")
        .text("취소완료");
    }
  });
  $(".btn-primary").click(function () {
    // 버튼 비활성화 및 스타일 변경
    $(this).prop("disabled", true);
    $(this)
      .removeClass("btn-primary")
      .addClass("btn-secondary")
      .text("취소완료");
  });
});

//  수정 버튼 클릭시 실행함수
function fn_saveBook(boid) {
	const jsonData = {"boid":boid};
	const jsonString = JSON.stringify(jsonData);
		// ITN 프로젝트용 특별 Ajax 실행 메소드
	$.ajax({
		// 전송할 주소
        url: ContextPath + "/productCencal", 
		// 전송 타입 (get,post)
        type: "post", 
		// 송신 테이터 타입
		contentType: "application/json;charset=UTF-8",
		// 수신 데이터 타입
		dataType: "text",
        // 송신 테이터
        data: jsonString,
		// 성공
        success: function(result) {
			alert("결제 취소가 완료되었습니다.");		
			location.href = ContextPath + "/mypage";	
        },
		// 실패
        error: function(request, status, error) {
			//에러 메세지 출력
        	alert("시스템 오류로 인해 결제 취소가 되지않았습니다.");
        }
    });
}

function fn_saveMember() {
	if(!password.value){
		alert("수정할 비밀번호를 입력하세요");
		return false;
	}
	if(!phone.value){
		alert("휴대전화 번호를 입력하세요");
		return false;
	}
	
	const jsonData = {"email":email.value,"password":password.value,"phone":phone.value};
	const jsonString = JSON.stringify(jsonData);
		// ITN 프로젝트용 특별 Ajax 실행 메소드
	$.ajax({
		// 전송할 주소
        url: ContextPath + "/updateMember", 
		// 전송 타입 (get,post)
        type: "post", 
		// 송신 테이터 타입
		contentType: "application/json;charset=UTF-8",
		// 수신 데이터 타입
		dataType: "text",
        // 송신 테이터
        data: jsonString,
		// 성공
        success: function(result) {
			alert("회원정보수정이 완료되었습니다.");		
        },
		// 실패
        error: function(request, status, error) {
			//에러 메세지 출력
        	alert("시스템 오류로 인해 회원정보수정이 중단되었습니다.");
        }
    });
}

// 		페이지 번호 클릭시 실행될 함수
function fn_egov_link_page(pageNo) {
	document.bookForm.pageIndex.value = pageNo; 
	document.bookForm.method = "get";
	document.bookForm.action = ContextPath + "/mypage";
	document.bookForm.submit();
}

const paymentSaveButton = document.querySelector('#paymentSaveButton');
  
// 모든 버튼 요소를 가져옵니다.
const buttons = document.querySelectorAll('.toggle-btn');

// 각 버튼에 클릭 이벤트 리스너를 추가합니다.
buttons.forEach(button => {
    button.addEventListener('click', function() {
        // 현재 클릭한 버튼이 이미 활성화된 상태인지 확인합니다.
        if (this.classList.contains('active')) {
            // 이미 활성화된 상태면 'active' 클래스를 제거하여 비활성화합니다.
            this.classList.remove('active');
        } else {
            // 다른 버튼들의 'active' 클래스를 제거합니다.
            buttons.forEach(btn => btn.classList.remove('active'));

            // 클릭된 버튼에 'active' 클래스를 추가하여 활성화합니다.
            this.classList.add('active');
        }
    });
});

// 결제 버튼 클릭 시 이벤트     
paymentSaveButton.addEventListener("click",()=>{
	const methodInput = document.querySelector('#methodInput');
	
	if(!methodInput.value){
		alert("결제 수단을 선택해주세요");
		return false;
	}
	
	const jsonData = {
		"boid" : boid,
		"bookNum" : bookNum,
		"email" : email,
		"totalPrice" : parseInt(totalprice),
		"methodType" : methodInput.value
	} 
	
	const jsonString = JSON.stringify(jsonData);
	
	// ITN 프로젝트용 특별 Ajax 실행 메소드
	$.ajax({
		// 전송할 주소
        url: ContextPath + "/savePayment", 
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
			alert("결제가 완료되었습니다.");		
			location.href = ContextPath + "/main";	
        },
		// 실패
        error: function(request, status, error) {
			//에러 메세지 출력
        	alert("시스템 오류로 인해 결제 취소되었습니다.");
        }
    });
	
});   
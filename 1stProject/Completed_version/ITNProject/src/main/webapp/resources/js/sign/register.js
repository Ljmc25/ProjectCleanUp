const registerForm = document.getElementById("register-form");
const signupBn = document.getElementById("signup-bn"); // 화면 입력받는 email 값
const emailIdInput = document.getElementById("emailIdInput"); // 화면 입력받는 email 값
const domainSelect = document.getElementById("domainSelect");
const customDomainInput = document.getElementById("customDomainInput1"); // 변경된 ID 사용
const email = document.getElementById("email"); // hidden email 값
const resultMessage = document.getElementById("resultMessage"); // 결과 메시지 표시할 요소

// 도메인 선택 시 동작하는 이벤트 리스너
domainSelect.addEventListener("change", () => {
    const selectedDomain = domainSelect.value;
    if (selectedDomain === "direct") {
        customDomainInput.disabled = false; // 직접 입력 활성화
        customDomainInput.value = ""; // 초기화
    } else {
        customDomainInput.disabled = true; // 선택한 도메인 사용
        customDomainInput.value = selectedDomain;
    }
    updateEmailField(); // 이메일 필드 업데이트
});

// 아이디 입력 시 동작하는 이벤트 리스너
emailIdInput.addEventListener("input", updateEmailField);

// 이메일 필드 업데이트 함수
function updateEmailField() {
    const emailId = emailIdInput.value; // 이메일 아이디 부분
    const domain = customDomainInput.value; // 도메인 부분

    // 이메일 전체 길이 체크
    if (emailId && domain) {
    /* alert(emailId)*/
        const fullEmail = emailId + "@" + domain;
        if (fullEmail.length <= 30) {
      //  alert(fullEmail)
            email.value = fullEmail; // 숨겨진 email 필드에 저장
        } else {
            // 오류 처리: 최대 길이 초과 시
            console.warn("이메일이 너무 깁니다. 최대 30자 이내로 입력해 주세요.");
            email.value = ""; // 필드 초기화
        }
    } else {
        email.value = ""; // 필드 초기화
    }
    console.log("Updated email field:", email.value);
}

/*password 중복확인*/
function checkPasswordMatch() {
	var password = document.getElementById("password").value;
	var confirmPassword = document.getElementById("inputPasswordConfirm").value;
	var message = document.getElementById("passwordMismatch");

	if (password !== confirmPassword) {
		message.style.display = "block"; // 메시지 표시
	} else {
		message.style.display = "none"; // 메시지 숨기기
	}
}

// 영문자와 숫자만 허용
function validateInput(input) {
	// 영문자와 숫자만 허용
	const regex = /[^a-zA-Z0-9]/g;

	// 입력값이 regex에 매치될 경우 제거
	if (regex.test(input.value)) {
		input.value = input.value.replace(regex, '');
	}

	// 최대 20자 제한
	if (input.value.length > 20) {
		input.value = input.value.slice(0, 20);
	}

	// 입력값이 없을 경우 경고 메시지 표시
	const emailWarning = document.getElementById('emailWarning');
	if (input.value === '') {
		emailWarning.style.display = 'block';
	} else {
		emailWarning.style.display = 'none';
	}
}

window.onload = function() {
	function getQueryParam(param) {
		const urlParams = new URLSearchParams(window.location.search);
		return urlParams.get(param);
	}

	const emailMessage = getQueryParam('emailMessage');
	if (emailMessage) {
		alert(emailMessage);
	}
}


// 중복 확인 버튼 클릭 시의 이벤트 리스너
document.getElementById("checkDuplicateBtn").onclick = function() {
    console.log("중복 확인 버튼 클릭됨"); // 로그 추가
    const emailInput = email.value; // 숨겨진 email 필드의 값을 사용
    const memberVO = {
        email: emailInput
    };

    fetch(ContextPath+'/checkEmail', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json; charset=UTF-8'
        },
        body: JSON.stringify(memberVO)
    })
    .then(response => response.text())
    .then(data => {
        resultMessage.innerText = data; // 결과 메시지 표시
    })
    .catch(error => {
        console.error('Error:', error);
        resultMessage.innerText = "서버 오류가 발생했습니다.";
    });
};

signupBn.addEventListener("click",()=>{
	 registerForm.action=ContextPath+"/register/addition";
	 registerForm.submit();
});
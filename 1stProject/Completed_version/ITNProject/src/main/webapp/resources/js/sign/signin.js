const signinBn =  document.getElementById("siginin-bn"); // 로그인 버튼
const signupBn =  document.getElementById("signup-bn"); // 회원가입 버튼
const sigininForm =  document.getElementById("siginin-form"); // 로그인 폼

const emailIdInput = document.getElementById("emailIdInput");  // 아이디 입력 필드
const domainSelect = document.getElementById("domainSelect");  // 도메인 선택 필드
const customDomainInput = document.getElementById("customDomainInput");  // 직접 입력 도메인 필드
const emailField = document.getElementById("email");  // 숨겨진 이메일 필드

// 이메일 필드 업데이트 함수
function updateEmailField() {
	const emailId = emailIdInput.value;  // 이메일 아이디 부분
	const domain = customDomainInput.value;  // 도메인 부분
	if (emailId && domain) {
		emailField.value = emailId + "@" + domain;  // 숨겨진 email 필드에 저장
	} else {
		emailField.value = "";  // 필드 초기화
	}
	console.log("Updated email field:", emailField.value); 
	
}

// 도메인 선택 시 동작하는 이벤트 리스너
domainSelect.addEventListener("change", () => {
	const selectedDomain = domainSelect.value;
	if (selectedDomain === "direct") {
		customDomainInput.disabled = false;  // 직접 입력 활성화
		customDomainInput.value = "";  // 초기화
	} else {
		customDomainInput.disabled = true;  // 선택한 도메인 사용
		customDomainInput.value = selectedDomain;
	}
	updateEmailField();  // 이메일 필드 업데이트
});

// 아이디 입력 시 동작하는 이벤트 리스너
emailIdInput.addEventListener("input", updateEmailField);

// 직접 입력 필드가 활성화된 경우 값을 변경할 때마다 이메일 필드 업데이트
customDomainInput.addEventListener("input", updateEmailField);

// 로그인 클릭 시 발생 이벤트
signinBn.addEventListener("click",()=>{
	sigininForm.action = ContextPath + "/loginProcess";
	sigininForm.submit();
});

signupBn.addEventListener("click",()=>{
	location.href=ContextPath + "/register";
});
// /register






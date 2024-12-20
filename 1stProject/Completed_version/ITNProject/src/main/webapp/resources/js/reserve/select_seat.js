const selectNum = document.getElementById("selectNum");
const selectSeat = document.getElementById("selectSeat");
const childrenNum = document.getElementById("childrenNum");
const teenagerNum = document.getElementById("teenagerNum");
const adultNum = document.getElementById("adultNum");
const agedNum = document.getElementById("agedNum");
const totalprice = document.getElementById("totalprice");
const childrenPrice = document.getElementById("childrenPrice");
const teenagerPrice = document.getElementById("teenagerPrice");
const adultPrice = document.getElementById("adultPrice");
const agedPrice = document.getElementById("agedPrice");

const payment = document.getElementById("payment");
const reserveForm = document.getElementById("reserveForm");

// 좌석 ID 배열 정의
const seatsToHide = {
  1: [
    "A2",
    "A7",
    "B2",
    "B7",
    "C2",
    "C7",
    "D2",
    "D7",
    "E1",
    "E2",
    "E3",
    "E4",
    "E5",
    "E6",
    "E7",
    "E8",
  ],
  2: [
    "A2",
    "A7",
    "B2",
    "B7",
    "C2",
    "C7",
    "D1",
    "D2",
    "D7",
    "D8",
    "E1",
    "E2",
    "E3",
    "E4",
    "E5",
    "E6",
    "E7",
    "E8",
    "F1",
    "F2",
    "F3",
    "F4",
    "F5",
    "F6",
    "F7",
    "F8",
  ],
  3: [
    "A1",
    "A2",
    "A7",
    "A8",
    "B1",
    "B2",
    "B7",
    "B8",
    "C1",
    "C2",
    "C7",
    "C8",
    "D1",
    "D2",
    "D3",
    "D4",
    "D5",
    "D6",
    "D7",
    "D8",
  ],
};
// 해당 관에 따라 좌석 숨기기
if (seatsToHide[theater]) {
  const hiddenSeats = seatsToHide[theater];
  hiddenSeats.forEach(function (seat) {
    const seatButton = document.getElementById(seat);
    if (seatButton) {
      seatButton.style.visibility = "hidden"; // 좌석 숨기기
    }
  });
}

function disableReservedSeats(reservedSeats) {
  // 예약된 좌석을 비활성화하는 로직
  reservedSeats.forEach(function (seat) {
    const seatButton = document.getElementById(seat);
    if (seatButton) {
      seatButton.disabled = true; // 좌석 버튼 비활성화
      seatButton.classList.add("disabled"); // 비활성화된 스타일 추가
    }
  });
}

// 인원 수 초기화
let totalCount = 0; // 총 인원 수
const maxCount = 8; // 최대 인원 수 (예: 8명)
// 각 그룹의 인원 수 초기화
let adultCount = 0;
let youthCount = 0;
let childCount = 0;
let seniorCount = 0;
let adultPriceValue = 0;
let youthPriceValue = 0;
let childPriceValue = 0;
let seniorPriceValue = 0;

// 총 인원 수 업데이트 함수
function updateTotalCount() {
  totalCount = adultCount + youthCount + childCount + seniorCount;
  selectNum.value = parseInt(totalCount);
}
// 가격 정의
const prices = {
  adult: 12000,
  youth: 8000,
  child: 6000,
  senior: 10000,
};
// 총 가격 초기화
let totalPrice = 0;
// 총 가격 계산 함수
function calculateTotalPrice() {
  totalPrice =
    adultCount * prices.adult +
    youthCount * prices.youth +
    childCount * prices.child +
    seniorCount * prices.senior;
  totalprice.value = parseInt(totalPrice);
  document.getElementById("total-price").innerText = `가격 : ${totalPrice}원`; // 총 가격 표시
}
// 각 그룹의 인원 수 업데이트 함수 수정
function updateCount(group, currentCount, operation) {
  let newCount = currentCount; // 새로운 카운트 변수
  if (operation === "increase") {
    if (totalCount < maxCount) {
      // 최대 인원 수 초과 방지
      newCount += 1;
    }
  } else if (operation === "decrease") {
    if (currentCount > 0) {
      // 최소 인원 수 0 방지
      newCount -= 1;
    }
  }
  // 업데이트된 인원 수 저장
  if (group === "adult") adultCount = newCount;
  else if (group === "youth") youthCount = newCount;
  else if (group === "child") childCount = newCount;
  else if (group === "senior") seniorCount = newCount;
  // 총 인원 수 및 가격 계산
  updateTotalCount();
  calculateTotalPrice(); // 가격 계산 호출
  return newCount; // 변경된 인원 수 반환
}

// 각 버튼 클릭 시 이벤트 처리 수정
document.getElementById("adult-minus").onclick = function () {
  adultCount = updateCount("adult", adultCount, "decrease");
  document.getElementById("adult-zero").innerText = adultCount;
  adultNum.value = parseInt(adultCount);
  adultPrice.value = parseInt(adultCount) * prices.adult;
};
document.getElementById("adult-plus").onclick = function () {
  adultCount = updateCount("adult", adultCount, "increase");
  document.getElementById("adult-zero").innerText = adultCount;
  adultNum.value = parseInt(adultCount);
  adultPrice.value = parseInt(adultCount) * prices.adult;
};

document.getElementById("youth-minus").onclick = function () {
  youthCount = updateCount("youth", youthCount, "decrease");
  document.getElementById("youth-zero").innerText = youthCount;
  teenagerNum.value = parseInt(youthCount);
  teenagerPrice.value = parseInt(youthCount) * prices.youth;
};
document.getElementById("youth-plus").onclick = function () {
  youthCount = updateCount("youth", youthCount, "increase");
  document.getElementById("youth-zero").innerText = youthCount;
  teenagerNum.value = parseInt(youthCount);
  teenagerPrice.value = parseInt(youthCount) * prices.youth;
};

document.getElementById("child-minus").onclick = function () {
  childCount = updateCount("child", childCount, "decrease");
  document.getElementById("child-zero").innerText = childCount;
  childrenNum.value = parseInt(childCount);
  childrenPrice.value = parseInt(childCount) * prices.child;
};
document.getElementById("child-plus").onclick = function () {
  childCount = updateCount("child", childCount, "increase");
  document.getElementById("child-zero").innerText = childCount;
  childrenNum.value = parseInt(childCount);
  childrenPrice.value = parseInt(childCount) * prices.child;
};

document.getElementById("senior-minus").onclick = function () {
  seniorCount = updateCount("senior", seniorCount, "decrease");
  document.getElementById("senior-zero").innerText = seniorCount;
  agedNum.value = parseInt(seniorCount);
  agedPrice.value = parseInt(seniorCount) * prices.senior;
};
document.getElementById("senior-plus").onclick = function () {
  seniorCount = updateCount("senior", seniorCount, "increase");
  document.getElementById("senior-zero").innerText = seniorCount;
  agedNum.value = parseInt(seniorCount);
  agedPrice.value = parseInt(seniorCount) * prices.senior;
};

// 초기 상태 설정
document.getElementById("adult-zero").innerText = adultCount;
document.getElementById("youth-zero").innerText = youthCount;
document.getElementById("child-zero").innerText = childCount;
document.getElementById("senior-zero").innerText = seniorCount;
document.getElementById("total-price").innerText = `가격 : ${totalPrice}원`; // 초기 총 가격 표시

let selectedSeats = []; // 선택한 좌석 ID를 저장할 배열
// 좌석 클릭 이벤트 추가
document.querySelectorAll(".btn-outline-success").forEach((seat) => {
  seat.onclick = function () {
    const seatId = this.id; // 선택된 좌석의 ID (예: A1, B3 등)
    console.log(seatId);
    // 이미 선택된 좌석인 경우
    if (selectedSeats.includes(seatId)) {
      // 선택 해제
      selectedSeats = selectedSeats.filter((seat) => seat !== seatId);
      this.classList.remove("selected"); // 선택 해제 스타일 제거
      // 선택 해제 시 select-button-X의 텍스트와 스타일을 초기화
      const selectButtonIndex = selectedSeats.length + 1; // 현재 선택된 좌석 수에 따라 버튼 인덱스 결정
      const selectButton = document.getElementById(
        `select-button-${selectButtonIndex}`
      );
      selectButton.textContent = "-"; // 버튼 텍스트를 "-"로 변경
      selectButton.style.backgroundColor = ""; // 배경색 초기화
      selectButton.style.color = ""; // 글자색 초기화
    } else {
      // 선택되지 않은 좌석인 경우
      if (selectedSeats.length < totalCount) {
        // 총 인원 수에 따라 선택 제한
        selectedSeats.push(seatId);
        this.classList.add("selected"); // 선택 스타일 추가

        // 선택된 좌석을 select-button-X에 표시
        const selectButtonIndex = selectedSeats.length; // 현재 선택된 좌석 수에 따라 버튼 인덱스 결정
        const selectButton = document.getElementById(
          `select-button-${selectButtonIndex}`
        );

        // 선택된 좌석 값을 update
        if (selectedSeats.length === 1) {
          selectSeat.value = seatId; // 첫 번째 좌석 선택 시
        } else {
          selectSeat.value += "," + seatId; // 그 이후의 좌석 선택 시
        }
        // 버튼 텍스트를 좌석 ID로 변경하고 스타일 적용
        selectButton.textContent = seatId; // 버튼 텍스트를 좌석 ID로 변경
        selectButton.style.backgroundColor = "black"; // 배경색을 검정으로 설정
        selectButton.style.color = "white"; // 글자색을 흰색으로 설정
      } else {
        alert(`선택할 수 있는 좌석 수는 ${totalCount}개 입니다.`);
      }
    }
  };
});

// 리셋 버튼 클릭 시 초기화 기능
document.querySelector(".reset").onclick = function () {
  // 인원 수 초기화
  totalCount = 0; // 총 인원 수 초기화
  adultCount = 0;
  youthCount = 0;
  childCount = 0;
  seniorCount = 0;
  // 인원 수 표시 업데이트
  document.getElementById("adult-zero").innerText = adultCount;
  document.getElementById("youth-zero").innerText = youthCount;
  document.getElementById("child-zero").innerText = childCount;
  document.getElementById("senior-zero").innerText = seniorCount;
  // 선택된 좌석 초기화
  selectedSeats = []; // 선택된 좌석 ID 배열 초기화
  clearSelectedSeatButtons(); // 선택 버튼 초기화
};

// 선택 버튼의 상태를 초기화하는 함수
function clearSelectedSeatButtons() {
  for (let i = 1; i <= maxCount; i++) {
    // 최대 인원 수 만큼 반복
    const selectButton = document.getElementById(`select-button-${i}`);
    if (selectButton) {
      selectButton.textContent = "-"; // 버튼 텍스트를 "-"로 변경
      selectButton.style.backgroundColor = ""; // 배경색 초기화
      selectButton.style.color = ""; // 글자색 초기화
    }
  }
  // 선택된 좌석 스타일 초기화
  document.querySelectorAll(".btn-outline-success.selected").forEach((seat) => {
    seat.classList.remove("selected"); // 선택 해제 스타일 제거
  });
  // 총 가격 초기화
  totalPrice = 0; // 총 가격 초기화
  document.getElementById("total-price").innerText = `가격 : ${totalPrice}원`; // 가격 표시 업데이트
}

// 뒤로가기 버튼 클릭 시 이벤트 처리
document.querySelector(".info-btn1").onclick = function () {
  // 선택된 좌석 초기화
  selectedSeats = []; // 선택된 좌석 ID 배열 초기화
  clearSelectedSeatButtons(); // 선택 버튼 초기화
  // 인원 수 초기화
  totalCount = 0; // 총 인원 수 초기화
  adultCount = 0;
  youthCount = 0;
  childCount = 0;
  seniorCount = 0;
  // 인원 수 표시 업데이트
  document.getElementById("adult-zero").innerText = adultCount;
  document.getElementById("youth-zero").innerText = youthCount;
  document.getElementById("child-zero").innerText = childCount;
  document.getElementById("senior-zero").innerText = seniorCount;
  // 총 가격 초기화
  totalPrice = 0;
  document.getElementById("total-price").innerText = `가격 : ${totalPrice}원`; // 초기 총 가격 표시
  // 예매 사이트로 돌아가기 (원하는 URL로 변경)
  window.location.href = ContextPath + "/reserve"; // 예매 사이트 URL로 변경
};

payment.addEventListener("click", () => {
  if (selectedSeats.length !== totalCount) {
    event.preventDefault(); // 기본 이벤트 방지 (예: 폼 제출)
    alert(
      `인원 수와 선택한 좌석 수가 일치하지 않습니다. ${totalCount}개의 좌석을 선택해야 합니다.`
    );
    return; // 함수 종료
  }
  reserveForm.action = ContextPath + "/reserve/payment";
  reserveForm.submit();
});

// 페이지 로드 시 호출되는 함수
window.onload = function () {
  disableReservedSeats(reservedSeats);
  // 초기 상태 설정
  totalprice.value = 0; // 총 가격 초기화
  selectNum.value = 0; // 총 인원 수 초기화

  // 각 인원 수와 가격을 0으로 초기화
  document.getElementById("adult-zero").innerText = adultCount;
  document.getElementById("youth-zero").innerText = youthCount;
  document.getElementById("child-zero").innerText = childCount;
  document.getElementById("senior-zero").innerText = seniorCount;

  adultNum.value = adultCount; // 성인 수 초기화
  teenagerNum.value = youthCount; // 청소년 수 초기화
  childrenNum.value = childCount; // 어린이 수 초기화
  agedNum.value = seniorCount; // 노인 수 초기화

  adultPrice.value = adultPriceValue; // 성인 가격 초기화
  teenagerPrice.value = youthPriceValue; // 청소년 가격 초기화
  childrenPrice.value = childPriceValue; // 어린이 가격 초기화
  agedPrice.value = seniorPriceValue; // 노인 가격 초기화
};

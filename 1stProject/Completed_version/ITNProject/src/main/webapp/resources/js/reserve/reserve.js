let currentDate = new Date(); // 현재 날짜를 저장하는 변수
// 선택된 영화 제목을 저장할 변수
let selectedMovieTitle = "";
// 선택된 극장 지역을 저장할 변수
let selectedCity = "";
// 선택된 극장 브랜치를 저장할 변수
let selectedBranch = "";
// 선택한 날짜를 저장할 변수
let selectedDate = "";

// 달력을 토글하는 함수
function toggleCalendar() {
  const calendar = document.getElementById("calendar");
  calendar.classList.toggle("show"); // 달력의 표시 상태를 전환
  updateCalendar(); // 달력 업데이트
}
// 이전 달로 이동하는 함수
function prevMonth() {
  currentDate.setMonth(currentDate.getMonth() - 1); // 현재 월 -1
  updateCalendar(); // 달력 업데이트
}
// 다음 달로 이동하는 함수
function nextMonth() {
  currentDate.setMonth(currentDate.getMonth() + 1); // 현재 월 +1
  updateCalendar(); // 달력 업데이트
}

// 현재 월과 연도를 업데이트하고 날짜를 생성
function updateCalendar() {
  const month = currentDate.getMonth(); // 현재 월
  const year = currentDate.getFullYear(); // 현재 연도
  const currentMonthDisplay = document.getElementById("currentMonth");
  currentMonthDisplay.innerText = `${year}년 ${month + 1}월`; // 현재 월 표시

  const daysContainer = document.getElementById("days");
  daysContainer.innerHTML = ""; // 기존 내용 삭제

  // 첫째 날의 요일과 마지막 날의 날짜 계산
  const firstDay = new Date(year, month, 1).getDay();
  const lastDate = new Date(year, month + 1, 0).getDate();

  // 빈 공간 추가
  for (let i = 0; i < firstDay; i++) {
    const emptyElement = document.createElement("div");
    emptyElement.className = "day"; // 빈 공간을 위한 div 생성
    daysContainer.appendChild(emptyElement); // 빈 공간 추가
  }

  // 날짜 추가
  for (let day = 1; day <= lastDate; day++) {
    const dayElement = document.createElement("div");
    dayElement.className = "day";
    dayElement.innerText = day; // 날짜 텍스트 설정
    dayElement.onclick = () => selectDate(day); // 날짜 선택 시 함수 호출
    daysContainer.appendChild(dayElement); // 날짜 추가
  }
}

// 페이지가 로드되면 현재 날짜로 설정하고 영화 리스트를 가져오는 함수 호출
window.onload = function () {
  const today = new Date();
  const year = today.getFullYear();
  const month = String(today.getMonth() + 1).padStart(2, "0");
  const day = String(today.getDate()).padStart(2, "0");

  selectedDate = `${year}/${month}/${day}`;
  document.getElementById("dateSelect").value = selectedDate;

  fetchMoviesByDate(); // 해당 날짜에 맞는 영화 리스트 가져오기
};

// 날짜 선택 시 입력 필드에 날짜 추가 (YYYY/MM/DD 형식으로 변환)
function selectDate(day) {
  const year = currentDate.getFullYear(); // 선택한 연도
  const month = String(currentDate.getMonth() + 1).padStart(2, "0"); // 선택한 월 (2자리)
  selectedDate = `${year}/${month}/${String(day).padStart(2, "0")}`; // 선택한 날짜

  document.getElementById("dateSelect").value = selectedDate; // 날짜 필드에 값 추가
  document.getElementById("calendar").classList.remove("show"); // 달력 닫기

  // 날짜가 선택될 때마다 극장 및 시간 리스트 초기화
  resetSelection(); // 극장 및 시간 선택 초기화 함수 호출

  // 선택된 날짜에 따라 영화 리스트를 가져옴
  fetchMoviesByDate(); // 영화 리스트 가져오는 함수 호출
}

// 영화 리스트를 서버에서 가져오는 함수
function fetchMoviesByDate() {
  const selectedDate = document.getElementById("dateSelect").value;

  fetch(ContextPath + `/screening/getProductsByDate?date=${selectedDate}`, {
    method: "GET",
    headers: {
      Accept: "application/json",
    },
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error("Network response was not ok");
      }
      return response.json();
    })
    .then((data) => {
      const movieList = document.getElementById("movieList");
      movieList.innerHTML = "";

      data.forEach((movie) => {
        const li = document.createElement("li");

        li.className = "movie-item";
        li.innerText = movie.title;

        // 서버에서 전달된 제목과 일치하는 영화일 경우 강조 효과
        if (movie.title === serverTitle) {
          //li.style.fontWeight = "bold"; // 강조 효과 (굵게 표시)
          //li.style.backgroundColor = "#f0f0f0"; // 선택 효과 (배경색 변경)
          li.classList.add("selected");
        }

        li.onclick = () => selectMovie(movie.title);
        movieList.appendChild(li);


      });

      initialize();
    })
    .catch((error) => console.error("Error fetching movies:", error)); // 에러 로그
}

// 영화 선택 시 호출되는 함수
function selectMovie(movieTitle) {
  // 선택된 영화의 UI를 업데이트
  const items = document.querySelectorAll(".movie-item");
  items.forEach((item) => item.classList.remove("selected")); // 모든 영화 항목에서 selected 클래스 제거

  // 현재 선택된 영화 항목에 selected 클래스 추가
  const selectedItem = Array.from(items).find(
    (item) => item.innerText === movieTitle
  );
  if (selectedItem) {
    selectedItem.classList.add("selected");
  }

  selectedMovieTitle = movieTitle; // 선택된 영화 제목 저장

  // 극장 및 시간 리스트 초기화
  resetSelection(); // 극장 및 시간 선택 초기화 함수 호출

  // 상영정보를 가져오는 함수 호출
  checkAndFetchScreeningInfo();
}

// 초기화 함수
function initialize() {
  // 극장 선택 비활성화
  document
    .querySelectorAll(".theater-itemS, .theater-itemI, .theater-itemB")
    .forEach((item) => {
      item.classList.add("disabled"); // 비활성화 클래스 추가
    });

  // 영화 선택 리스너
  const movieItems = document.querySelectorAll(".movie-item");
  movieItems.forEach((item) => {
    item.addEventListener("click", function () {
      // 강조 효과 추가
      movieItems.forEach((i) => {
			i.classList.remove("selected");
			i.style.fontWeight = "nomal"; // 강조 효과 (굵게 표시)
          	i.style.backgroundColor = "#fff"; // 선택 효과 (배경색 변경)
		})
      this.classList.add("selected"); // 선택 추가

      // 극장 선택 활성화
      document
        .querySelectorAll(".theater-itemS, .theater-itemI, .theater-itemB")
        .forEach((item) => {
          item.classList.remove("disabled"); // 비활성화 해제
        });

      // 선택된 영화 제목 추출
      selectedMovieTitle = this.innerText; // 선택한 영화 제목 저장

      // 상영정보를 가져오는 함수 호출
      checkAndFetchScreeningInfo();
    });
  });

  // 극장지역 선택 리스너
  const theaterItems = document.querySelectorAll(
    ".theater-itemS, .theater-itemI, .theater-itemB"
  );
  theaterItems.forEach((item) => {
    item.addEventListener("click", function () {
      // 강조 효과 추가
      theaterItems.forEach((i) => i.classList.remove("selected")); // 기존 선택 해제
      this.classList.add("selected"); // 선택 추가

      // 모든 리스트 숨기기
      document.querySelector(".theater-list2").style.display = "none";
      document.querySelector(".theater-list3").style.display = "none";
      document.querySelector(".theater-list4").style.display = "none";

      // 선택된 극장에 맞는 리스트만 표시
      const theaterType = this.classList.contains("theater-itemS")
        ? 2
        : this.classList.contains("theater-itemI")
        ? 3
        : 4;

      // 해당 리스트 활성화
      document.querySelector(".theater-list" + theaterType).style.display =
        "block";

      // 시간 리스트 비활성화
      document.querySelector(".time-select").style.display = "none";

      // 선택된 극장 지역 저장
      selectedCity = this.innerText; // 선택된 극장 지역 저장

      // 상영정보를 가져오는 함수 호출
      checkAndFetchScreeningInfo();
    });
  });

  // 극장 리스트 클릭 시 상영정보 선택 활성화
  const theaterLists = document.querySelectorAll(
    ".theater-list2, .theater-list3, .theater-list4"
  );
  theaterLists.forEach((list) => {
    list.querySelectorAll(".theater-item2").forEach((item) => {
      item.addEventListener("click", function () {
        // 극장 리스트에서 선택된 아이템 강조
        theaterLists.forEach((l) =>
          l
            .querySelectorAll(".theater-item2")
            .forEach((i) => i.classList.remove("selected"))
        );
        this.classList.add("selected"); // 선택 추가

        // 선택된 극장 브랜치 저장
        selectedBranch = this.innerText; // 선택된 극장 브랜치 저장

        // 상영정보를 가져오는 함수 호출
        checkAndFetchScreeningInfo();
      });
    });
  });

  // 극장 지역 비활성화 처리
  resetSelection(); // 초기화 시 극장 지역 비활성화
}

// 선택된 정보 리셋 함수
function resetSelection() {
  const movieItems = document.querySelectorAll(".movie-item");
  movieItems.forEach((item) => item.classList.remove("selected")); // 영화 선택 해제

  const theaterItems = document.querySelectorAll(
    ".theater-itemS, .theater-itemI, .theater-itemB"
  );
  theaterItems.forEach((item) => {
    item.classList.add("disabled"); // 극장 비활성화
    item.classList.remove("selected"); // 극장 선택 해제
  });

  // 상영정보 리스트 비활성화
  const timeSelect = document.querySelector(".time-select");
  timeSelect.style.display = "none"; // 상영정보 숨기기

  // 극장 리스트에서 선택된 항목 해제
  const selectedTheaterItems = document.querySelectorAll(
    ".theater-item2.selected"
  );
  selectedTheaterItems.forEach((item) => {
    item.classList.remove("selected"); // 극장 선택 해제
  });

  // 극장 리스트 숨기기
  document.querySelector(".theater-list2").style.display = "none";
  document.querySelector(".theater-list3").style.display = "none";
  document.querySelector(".theater-list4").style.display = "none";

  // 선택된 값 초기화
  selectedMovieTitle = "";
  selectedCity = "";
  selectedBranch = "";
}

// 상영정보를 가져오는 함수
function checkAndFetchScreeningInfo() {
  // 모든 선택이 완료되었는지 체크
  if (selectedMovieTitle && selectedCity && selectedBranch && selectedDate) {
    fetchScreeningInfo(); // 모든 선택이 완료되면 상영정보를 가져옴
  }
}

// 상영정보를 가져오는 함수
function fetchScreeningInfo() {
  // 상영정보를 서버에서 가져오는 로직 구현

  // 서버로 요청 보내기
  fetch(
    ContextPath + `/screening/getScreeningInfo?movie=${selectedMovieTitle}&city=${selectedCity}&branch=${selectedBranch}&date=${selectedDate}`
  )
    .then((response) => {
      if (!response.ok) {
        throw new Error("Network response was not ok");
      }
      return response.json();
    })
    .then((data) => {
      displayScreeningInfo(data); // 상영정보 표시 함수 호출
    })
    .catch((error) => console.error("Error fetching screening info:", error));
}

function displayScreeningInfo(screeningData) {
  const screeningContainer = document.getElementById("screeningInfoContainer");
  if (!screeningContainer) {
    console.error("screeningInfoContainer not found!");
    return;
  }

  // 화면 정보를 출력하는 코드
  screeningContainer.innerHTML = ""; // 이전 내용 삭제

  // 상영 정보가 없으면 메시지 출력
  if (screeningData.length === 0) {
    screeningContainer.innerHTML = "<div>상영 정보가 없습니다.</div>";
    return;
  }

  // 상영 정보 출력 코드
  screeningData.forEach((info) => {
    screeningContainer.innerHTML += `
            <li class="list-group-item d-flex justify-content-between align-items-start"
            onclick="location.href='${ContextPath}/reserve/select1?scid=${info.scid}'">
                <div class="ms-2 me-auto">
                    <div class="fw-bold time-start">${info.startTime}</div>
                    <div class="time-end">~${info.endTime}</div>
                </div>
                <div class="extra-info">
                    <div class="movie-title">${info.title}</div>
                    <div class="movie-theater">${info.theater}관</div>
                </div>
                <span class="badge bg-secondary rounded-pill">${info.remainSeats}/${info.seats}</span>
            </li>`;
  });

  // 상영 정보가 보이도록 설정
  screeningContainer.style.display = "block";
}

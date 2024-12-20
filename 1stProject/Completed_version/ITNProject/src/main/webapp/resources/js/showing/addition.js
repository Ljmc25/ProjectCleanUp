"use script";

const title = document.getElementById("title");
const cinema = document.getElementById("cinema");
const city = document.getElementById("city");
const branch = document.getElementById("branch");
const theater = document.getElementById("theater");
const seats = document.getElementById("seats");
const remainSeats = document.getElementById("remainSeats");
const address = document.getElementById("address");
const showingTime = document.getElementById("showingTime");
const startTime = document.getElementById("startTime");
const endTime = document.getElementById("endTime");
const submit_bn = document.getElementById("submit-bn");
const back_bn = document.getElementById("back-bn");

const branch_option = branch.getElementsByTagName("option");


const hour = 60 * 60 * 1000;
const seats_number = [0,32,22,38];
const branch_address=[
    "서울특별시 강남구 강남대로 438 스타플렉스 4층~8층",
    "서울특별시 서대문구 신촌로 129",
    "경기도 성남시 분당구 판교역로146번길 20 까르띠에 현대백화점 판교점 5층",
    "인천광역시 연수구 A동 하모니로 158 지하 1층",
    "인천광역시 중구 영종대로 184 예스타워 7층",
    "인천광역시 남동구 논현로26번길 34",
    "장전로12번길 55 라퓨타아일랜드 4층",
    "부산광역시 부산진구 동천로 4 지오플레이스 6F",
    "부산광역시 동래구 중앙대로 1523"
];

const jsonFormat = () => {
	const jsonData = {
		"title" : title.value,
		"city" : city.value,
		"cinema" : cinema.value,
		"branch" : branch.value,
		"theater" : parseInt(theater.value),
		"seats" : parseInt(seats.value),
		"remainSeats" : parseInt(remainSeats.value),
		"address" : address.value,
		"showingTime" : showingTime.value,
		"startTime" : startTime.value,
		"endTime" : endTime.value
	}
	
	const jsonString = JSON.stringify(jsonData);
	return jsonString;
}

city.addEventListener("change",()=>{
    const val = city.value;

    for(let i=0;i<=8;i++){
        branch_option[i].style.display="none";
    }

    switch (val) {
        case "서울":
            branch_option[0].selected = true;
            for(let i=0;i<=2;i++){
                branch_option[i].style.display="block";
            }
            break;
        case "인천":
            branch_option[3].selected = true;
            for(let i=3;i<=5;i++){
                branch_option[i].style.display="block";
            }
            break;
        case "부산":
            branch_option[6].selected = true;
            for(let i=6;i<=8;i++){
                branch_option[i].style.display="block";
            }
            break;
        default:
            break;
    }

    for(let i=0;i<branch_option.length;i++){
        if(branch_option[i].selected){
            address.value = branch_address[i];
        }
        
    }
});

branch.addEventListener("change",()=>{
    for(let i=0;i<branch_option.length;i++){
        if(branch_option[i].selected){
            address.value = branch_address[i];
        }
        
    }
});

theater.addEventListener("change",()=>{
    const val = theater.value;
    seats.value = seats_number[val];
    remainSeats.value = seats_number[val];
});

submit_bn.addEventListener("click",()=>{
    if(!showingTime.value){
        alert("상영날짜를 설정 해주세요.");
        return false;
    }
    
    const sTime = new Date(showingTime.value+" "+startTime.value);
    const eTime = new Date(showingTime.value+" "+endTime.value);

    const diffMSec = eTime.getTime() - sTime.getTime();
    //const diffDate = diffMSec / (24 * 60 * 60 * 1000); //날짜 차이
    // const diffHour = diffMSec / (60 * 60 * 1000); // 시간차이
    // const diffMin = diffMSec / (60 * 1000); // 분차이
    // alert(`${diffMSec}`);

    if(diffMSec < hour){
        alert("상영종료시간을 상영시작시간보다\n1시간 이상 더 높게 설정 해주세요.");
        return false;
    }

	const jsonString = jsonFormat();

    $.ajax({
        url: ContextPath + "/showing/add",
        type: "post",
        data: jsonString,
        dataType: "text",
        contentType: "application/json;charset=UTF-8",
        success: function(data) {
            alert("등록 완료");
        },
        error: function(errorThrown) {
            alert("등록 실패\n다시 시도헤 주세요.");
        }
    });

});

back_bn.addEventListener("click",()=>{
    location.href = ContextPath + "/exam";
});
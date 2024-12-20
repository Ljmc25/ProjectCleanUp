document.addEventListener('DOMContentLoaded', function() {
    var defaultImages = {
        menu1: '../resources/images/admin/default_home.png',
        menu2: '../resources/images/admin/default_announcement.png',
        menu3: '../resources/images/admin/default_person.png',
        menu4: '../resources/images/admin/default_movie.png',
        menu5: '../resources/images/admin/default_theater.png',
        menu6: '../resources/images/admin/default_inquiry.png',
        menu7: '../resources/images/admin/default_qna.png'
    };

    var clickImages = {
        menu1: '../resources/images/admin/click_home.png',
        menu2: '../resources/images/admin/click_announcement.png',
        menu3: '../resources/images/admin/click_person.png',
        menu4: '../resources/images/admin/click_movie.png',
        menu5: '../resources/images/admin/click_theater.png',
        menu6: '../resources/images/admin/click_inquiry.png',
        menu7: '../resources/images/admin/click_qna.png'
    };

    var menuLinks = document.querySelectorAll('.nav-link');
    var dropdownToggle = document.getElementById('menu4'); // 드롭다운 버튼
    const dropdownContent = document.querySelector('.dropdown-content'); // 드롭다운 메뉴

    // 드롭다운 항목의 페이지 매핑
    const pageMapping = {
        menu4_1: '/product/addition',
        menu4_2: '/product/add_screening_list',
        menu4_3: '/admin/product/list'
    };

    // 페이지 로드 시 활성화된 탭과 드롭다운 항목 상태 복원
    var activeTab = localStorage.getItem('activeTab');
    var activeDropdownItem = localStorage.getItem('activeDropdownItem');
    var isDropdownOpen = localStorage.getItem('isDropdownOpen') === 'true'; // 문자열을 boolean으로 변환

    // 활성화된 탭 복원
    if (activeTab) {
        var activeMenu = document.getElementById(activeTab);
        if (activeMenu) {
            activeMenu.style.backgroundColor = 'yellow';
            activeMenu.style.color = 'black';
            activeMenu.classList.add('active');
            var img = activeMenu.querySelector('img');
            img.src = clickImages[activeTab];
        }
    }

    // 드롭다운 활성화 상태 복원
    if (isDropdownOpen) {
        dropdownContent.style.display = 'block'; // 드롭다운 열기
    }

    // 드롭다운 내 보조 항목 상태 복원
    if (activeDropdownItem) {
        const activeDropdown = document.getElementById(activeDropdownItem);
        if (activeDropdown) {
            activeDropdown.style.backgroundColor = 'yellow';
            activeDropdown.style.color = 'black';
            activeDropdown.classList.add('active');
        }
    }

 // 드롭다운 메뉴 클릭 시 페이지 이동 및 상태 저장
dropdownContent.addEventListener('click', function(event) {
    const target = event.target.closest('.dropdown-item'); // 실제 클릭된 항목을 찾음

    if (target) {
        const targetId = target.id; // 클릭된 항목의 ID 가져오기
        const newPage = pageMapping[targetId]; // 해당 ID에 매핑된 페이지 가져오기

        if (newPage) { // 매핑된 페이지가 존재하는 경우
            // 모든 드롭다운 항목 초기화
            resetDropdownStyles();

            // 클릭된 보조 항목 스타일 변경
            target.classList.add('active'); // 활성화 상태 추가
            localStorage.setItem('activeDropdownItem', targetId); // 드롭다운 클릭 상태 저장
            window.location.href = newPage; // 페이지 이동
        } else {
            console.warn('Invalid target ID:', targetId); // 경고 메시지
        }
    }
});

  // 메뉴 클릭 이벤트 (탭)
menuLinks.forEach(function(menu) {
    menu.addEventListener('click', function(event) {
        // 모든 메뉴 초기화
        menuLinks.forEach(function(link) {
            link.classList.remove('active');
            link.style.backgroundColor = ''; // 스타일 초기화
            link.style.color = ''; // 스타일 초기화
            var img = link.querySelector('img');
            img.src = defaultImages[link.id]; // 기본 이미지로 복원
        });

        // 클릭한 메뉴 스타일 변경 및 상태 저장
        this.classList.add('active'); // 클릭한 메뉴에 active 클래스 추가
        this.style.backgroundColor = 'yellow'; // 배경색 변경
        this.style.color = 'black'; // 글자색 변경
        var menuId = this.id;
        var img = this.querySelector('img');
        img.src = clickImages[menuId];

        localStorage.setItem('activeTab', menuId); // 활성화된 탭 상태 저장

        // 드롭다운 보조 항목 초기화
        const dropdownItems = document.querySelectorAll('.dropdown-item');
        dropdownItems.forEach(function(item) {
            item.classList.remove('active'); // 모든 드롭다운 항목의 active 클래스 제거
        });

        // 드롭다운 닫기
        dropdownContent.style.display = 'none';
        isDropdownOpen = false;
        localStorage.setItem('isDropdownOpen', isDropdownOpen); // 드롭다운 열림 상태 저장
        localStorage.removeItem('activeDropdownItem'); // 드롭다운 보조 항목 상태 제거
    });
});

    // 드롭다운 토글: 버튼 클릭 시 드롭다운 열기/닫기
    dropdownToggle.addEventListener('click', function(event) {
        event.preventDefault();
        isDropdownOpen = !isDropdownOpen;
        dropdownContent.style.display = isDropdownOpen ? 'block' : 'none';
        localStorage.setItem('isDropdownOpen', isDropdownOpen); // 드롭다운 열림 상태 저장

        // 드롭다운 열릴 때 영화 관리 탭을 클릭 상태로 유지
        if (isDropdownOpen) {
            dropdownToggle.style.backgroundColor = 'yellow';
            dropdownToggle.style.color = 'black';
            dropdownToggle.classList.add('active');
            var img = dropdownToggle.querySelector('img');
            img.src = clickImages['menu4']; // 영화 관리 탭을 클릭 상태로 설정
        } else {
            resetDropdownStyles(); // 닫힐 때 원래 상태로 복원
        }
    });

    // 드롭다운 상태 초기화 함수
    function resetDropdownStyles() {
        dropdownToggle.style.backgroundColor = '';
        dropdownToggle.style.color = '';
        dropdownToggle.classList.remove('active');
        var dropdownImg = dropdownToggle.querySelector('img');
        dropdownImg.src = defaultImages['menu4']; // 주 항목 디폴트 이미지로 복원
    }

    // 페이지 외부 클릭 시 드롭다운 닫기
    document.addEventListener('click', function(event) {
        // 드롭다운 주 항목 및 보조 항목을 클릭하지 않았을 경우에만 닫기
        if (!dropdownToggle.contains(event.target) && !dropdownContent.contains(event.target)) {
            // 다른 탭 클릭 시 드롭다운 닫히지 않도록
            if (!Array.from(menuLinks).some(menu => menu.contains(event.target))) {
                dropdownContent.style.display = 'none'; // 드롭다운 닫기
                isDropdownOpen = false; // 드롭다운 상태 업데이트
                localStorage.setItem('isDropdownOpen', isDropdownOpen); // 드롭다운 상태 저장
            }
        }
    });
});

<template>
  <div class="container-list">
    <div class="list-all">

      <!--왼쪽 리스트-->
      <ul class="list1">
        <li>이용약관</li>
        <li>개인정보처리방침</li>
        <li>brand 그룹</li>
        <li><br></li>
        <li>brand 닷컴</li>
        <li>brand 닷컴</li>
      </ul>

      <!--오른쪽 리스트-->
      <ul class="list2">
        <li>주식회사:123456789 대표이사:123456789 주소123456789</li>
        <li>사업자등록번호:123456789</li>
        <li>고객센터:1234-56789:(주)brand그룹</li>
        <li>고객님의 안전거래를 위해 은행과 채무지급보증계약을 체결하여 안전거래를 보장하고 있습니다.</li>
        <li><br></li>
        <li>Copyright@ Abcd Efghijk Store. All rights reserved.</li>
      </ul>

    </div>
  </div>
</template>

<script>
</script>

<style scoped>
/* 전체 배경 설정 */

* {
  margin: 0;
  padding: 0;
  background-color: dimgray;
  color: white;
}

.container-list {
  display: flex;
  justify-content: center; /* 리스트들을 수평으로 가운데 정렬 */
  align-items: flex-end; /* 세로로 하단 정렬 */
  height: 200px;
  width: 100%; /* 전체 화면을 차지하도록 설정 */
}

.list-all {
  display: flex; /* ul 요소들을 수평으로 정렬 */
  margin-bottom: 50px;
}

ul {
  list-style-type: none; /* 리스트 점 제거 */
  font-size: 13px;
  padding: 0;
  margin: 0;
}

li {
  margin: 0 50px; /* li 항목들 간의 간격 */
}
</style>

// MainPage
// vueInit
<template>
  <div class="main">

    <!-- 메인 이미지 -->
    <div class="main-img-box">
      <img class="main-img" src="@/assets/images/mainpage/mainPageImg5.jpg"/>
    </div>

    <!-- 슬라이드 -->
    <div id="carouselExampleControls" class="carousel slide slide-box" data-bs-ride="carousel">
      <div class="carousel-inner carousel-img">
        <div class="carousel-item active">
          <!-- 1번 이미지 url -->
          <img src="@/assets/images/mainpage/mainPageImg2.jpg" class="d-block w-100" alt="...">
        </div>
        <div class="carousel-item">
          <!-- 2번 이미지 url -->
          <img src="@/assets/images/mainpage/mainPageImg3.jpg" class="d-block w-100" alt="...">
        </div>
        <div class="carousel-item">
          <!-- 3번 이미지 url -->
          <img src="@/assets/images/mainpage/mainPageImg4.jpg" class="d-block w-100" alt="...">
        </div>
      </div>
      <!-- 슬라이드 버튼 -->
      <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls"
              data-bs-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Previous</span>
      </button>
      <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls"
              data-bs-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Next</span>
      </button>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>

.main {
  margin: 0;
  padding: 0;
  width: 1600px;
  height: 1200px; /* 메인 사이즈 1600 * 1200 */
}

.main-img {
  margin: 0;
  padding: 0;
  position: absolute;
  top: 0;
  left: 0; /* 0,0 에서 사진 시작 */
  width: 100%;
  height: 1000px; /* 메인 이미지 사이즈 1600 * 1000 */
  object-fit: cover;
}

/* 슬라이드 */
.slide-box {
  margin: 0;
  padding: 0;
  position: absolute; /* 부모 요소의 좌상단에 위치하도록 */
  top: 1000px;
  left: 0;
  width: 100%;
  height: 250px; /* 슬라이드 사이즈 1600 * 250 */
}

/* 슬라이드내 이미지 */
.carousel-img {
  width: 100%;
  height: 250px; /* 슬라이드 이미지 사이즈 */
}

</style>
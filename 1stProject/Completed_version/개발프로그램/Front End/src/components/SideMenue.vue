<template>
  <!-- 첫 번째 열 (2단 크기) -->
  <div class="col-md-2 mt-5">
    <!-- 회사 로고 자리 -->
    <img
      src="https://cdn.pixabay.com/photo/2015/05/20/14/27/traditional-775512_1280.jpg"
      alt=""
      width="300"
      height=""
    />
    <!-- 사이드바 -->
    <div class="card mt-3">
      <div class="card-header .bg-light text-black text-center">
        <h4 class="card-title"><a href="/admin/AdminMember">Admin System </a></h4>
      </div>
      <div class="list-group text-center">
        <a href="/admin/AdminMember" class="list-group-item list-group-item-action"
          >회원 관리</a
        >
        <a
          href="/admin/AdminMerchandise"
          class="list-group-item list-group-item-action"
          >상품 관리</a
        >
        <a
          href="#customerSupportMenu"
          class="list-group-item list-group-item-action"
          data-bs-toggle="collapse"
        >
          고객 지원
        </a>
        <div id="customerSupportMenu" class="collapse text-end">
          <a
            href="/admin/AdminAnouncement"
            class="list-group-item list-group-item-action"
            >공지사항</a
          >
          <a href="/admin/AdminFaq" class="list-group-item list-group-item-action"
            >1:1 문의</a
          >
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {};
</script>
<style></style>

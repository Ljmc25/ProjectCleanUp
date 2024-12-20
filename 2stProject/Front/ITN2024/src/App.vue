<template>
  <div class="container-fluid mt-3">
    <div class="row">
      <!-- TODO: 머리말 : HeaderCom -->
      <HeaderCom />
      <SideMenue v-if="isAdminPage" />
      <!-- TODO: 아래 태그(본문) -->
      <div class="col-md-10 mt-5 container">
        <router-view />
      </div>
    </div>
  </div>
  <!-- <FooterCom /> -->
</template>
<script>
import HeaderCom from "./components/HeaderCom.vue";
import SideMenue from "./components/SideMenue.vue";
export default {
  components: {
    SideMenue,
    HeaderCom,
  },
  computed: {
    // 현재 페이지가 관리자 페이지인지 확인
    isAdminPage() {
      return this.$route.path.startsWith("/admin/Admin");
    },
  },
};
</script>
<style>
@import "@/assets/css/main.css";
</style>

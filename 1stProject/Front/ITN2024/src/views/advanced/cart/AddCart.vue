// AddGallery.vue // 연습) 메뉴 등록 및 디자인 // vueInit
<template>
  <div>
    <!-- basketTitle -->
    <div class="form-floating mb-3">
      <input
        type="text"
        class="form-control"
        id="basketTitle"
        placeholder="basketTitle"
        v-model="cart.basketTitle"
      />
      <label for="basketTitle">basketTitle</label>
    </div>
    <!-- TODO: 파일선택상자 -->
    <div class="input-group">
      <input type="file" class="form-control" ref="file" @change="select" />
      <button class="btn btn-outline-secondary" type="button" @click="save">
        Button
      </button>
    </div>
  </div>
</template>
<script>
import CartService from "@/services/advanced/CartService";
export default {
  data() {
    return {
      cart: {
        basketTitle: "",
        basketFileUrl: "", // 다운로드 url
        image: undefined, // 선택이미지
      },
    };
  },
  methods: {
    select() {
      this.cart.image = this.$refs.file.files[0];
    },
    async save() {
      try {
        let response = await CartService.insert(this.cart);
        console.log(response.data); // 디버깅
        // insert 성공 -> 전체조회 강제 이동
        this.$router.push("/advanced/cart");
      } catch (error) {
        this.cart.image = undefined;
        console.log(error);
      }
    },
  },
};
</script>
<style></style>

// FileDbDetail.vue // vueInit
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
    <!-- TODO: 현재이미지보기 -->
    <div class="mb-3 col-12">
      <img :src="cart.basketFileUrl" alt="이미지" />
    </div>

    <!-- TODO: 파일선택상자 -->
    <div class="input-group">
      <input type="file" class="form-control" ref="file" @change="select" />
    </div>
    <div class="mt-3">
      <button class="btn btn-warning me-2" type="button" @click="update">
        수정
      </button>
      <button class="btn btn-danger" type="button" @click="remove">삭제</button>
    </div>
  </div>
</template>
<script>
import CartService from "@/services/advanced/CartService";
export default {
  data() {
    return {
      cart: {
        uuid: this.$route.params.uuid,
        basketTitle: "",
        basketFileUrl: "", // 다운로드 url
        image: undefined, // 선택이미지
      },
    };
  },
  methods: {
    async getDetail(uuid) {
      try {
        let response = await CartService.get(uuid);
        console.log(response.data);
        this.cart = response.data;
      } catch (error) {
        console.log(error);
      }
    },

    select() {
      this.cart.image = this.$refs.file.files[0];
    },
    async update() {
      try {
        let response = await CartService.update(this.cart.uuid, this.cart);
        console.log(response.data); // 디버깅
        this.$router.push("/advanced/cart"); // 전체조회 강제이동: /dept
      } catch (error) {
        this.cart.image = undefined;
        console.log(error);
      }
    },
    async remove() {
      try {
        let response = await CartService.remove(this.cart.uuid, this.cart);
        console.log(response.data);
        this.$router.push("/advanced/cart");
      } catch (error) {
        console.log(error);
      }
    },
  },
  mounted() {
    this.getDetail(this.$route.params.uuid);
  },
};
</script>
<style></style>

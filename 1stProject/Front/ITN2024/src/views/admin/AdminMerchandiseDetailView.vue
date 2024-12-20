<template>
  <h3 class="text-center">상품 관리</h3>
  <hr />

  <br />
  <br />

  <h3 class="text-center">상품 상세조회</h3>
  <hr style="width:70vw; margin: 0 auto;" />

  <div class="text-center" style="width:70vw; margin: 0 auto;">
    
    <!-- 상품명 -->
    <div class="row fs-4 justify-content-center">
      <div class="col-auto">
        <label class="form-label">상품명</label>
      </div>
    </div>
    <div class="row fs-4 justify-content-center">
      <div class="col-auto">
        <div class="fw-bold fs-5">{{ products.name }}</div> <!-- 굵은 폰트 및 약간 작은 폰트 크기 -->
      </div>
    </div>
    <hr />
    <br />

    <!-- 카테고리 -->
    <div class="row fs-4 justify-content-center">
      <div class="col-auto">
        <label class="form-label">카테고리</label>
      </div>
    </div>
    <div class="row fs-4 justify-content-center">
      <div class="col-auto">
        <div class="fw-bold fs-5">{{ products.karegorie }}</div> <!-- 굵은 폰트 및 약간 작은 폰트 크기 -->
      </div>
    </div>
    <hr />
    <br />

    <!-- 제품 성별 -->
    <div class="row fs-4 justify-content-center">
      <div class="col-auto">
        <label class="form-label">제품 성별</label>
      </div>
    </div>
    <div class="row fs-4 justify-content-center">
      <div class="col-auto">
        <div class="fw-bold fs-5">{{ products.gender }}</div> <!-- 굵은 폰트 및 약간 작은 폰트 크기 -->
      </div>
    </div>
    <hr />
    <br />

    <!-- 제품 단일 가격 -->
    <div class="row fs-4 justify-content-center">
      <div class="col-auto">
        <label class="form-label">제품 단일 가격</label>
      </div>
    </div>
    <div class="row fs-4 justify-content-center">
      <div class="col-auto">
        <div class="fw-bold fs-5">{{ products.price }}</div> <!-- 굵은 폰트 및 약간 작은 폰트 크기 -->
      </div>
    </div>
    <hr />
    <br />

    <!-- 제품 상세 내용 -->
    <div class="row fs-4 justify-content-center">
      <div class="col-auto">
        <label class="form-label">제품 상세내용</label>
      </div>
    </div>
    <div class="row fs-4 justify-content-center">
      <div class="col-auto">
        <div class="fw-bold fs-5">{{ products.contents }}</div> <!-- 굵은 폰트 및 약간 작은 폰트 크기 -->
      </div>
    </div>
    <hr />
    <br />

    <!-- 사진 파일 입력 -->
    <h3>메인 사진 (페이지 상단)</h3>
    <hr />

    <div class="text-center">
      <br />
      <div v-if="images.length">
        <div class="text-center">
          <img :src="images[0]" alt="첫 번째 이미지" class="img-fluid" />
        </div>
      </div>
      <div v-else>이미지가 없습니다.</div>
    </div>
    <br />
    <div class="text-center">
      <br />
      <div v-if="images.length">
        <div class="text-center">
          <img :src="images[1]" alt="두 번째 이미지" class="img-fluid" />
        </div>
      </div>
      <div v-else>이미지가 없습니다.</div>

      <br />

      <div v-if="images.length">
        <div class="text-center">
          <img :src="images[2]" alt="세 번째 이미지" class="img-fluid" />
        </div>
      </div>
      <div v-else>이미지가 없습니다.</div>
    </div>

    <br />
    <br />

    <h3>상세 사진 (이미지 하단)</h3>
    <hr />
    <div class="text-center">
      <div v-if="images.length > 3">
        <div
          v-for="(image, index) in images.slice(3)"
          :key="index"
          class="text-center mt-5"
        >
          <img :src="image" :alt="`이미지 ${index + 4}`" class="img-fluid" />
        </div>
        <br />
      </div>
      <div v-else>이미지가 충분하지 않습니다.</div>
    </div>
  </div>
  <br />
  <br />

  <div class="text-center">    
    <button type="button" class="btn btn-danger me-2" @click="remove">
      삭제
    </button>
    <a href="/admin/AdminMerchandise"
      ><button type="button" class="btn btn-warning">뒤로가기</button></a
    >
  </div>
  <br />
  <br />
  <br />
</template>


<script>
import ProductService from "@/services/basic/ProductService";
export default {
  data() {
    return {
      products: {
        prid: this.$route.params.prid,
        code: "",
        name: "",
        karegorie: "",
        gender: "",
        price: "",
        contents: "",
        inserTime: "",
        updateTime: "",
      },
      images: [],
    };
  },
  methods: {
    async getDetail(prid) {
      try {
        let response = await ProductService.getAdmin(prid);
        console.log(response.data);
        this.products = response.data.product;
        this.images = response.data.images || [];
      } catch (error) {
        console.log(error);
      }
    },

    async remove() {
      try {
        let response = await ProductService.remove(this.products.prid);
        console.log(response.data);
        this.$router.push("/admin/AdminMerchandise");
      } catch (error) {
        console.log(error);
      }
    },
  },
  mounted() {
    this.getDetail(this.$route.params.prid);
  },
};
</script>
<style></style>

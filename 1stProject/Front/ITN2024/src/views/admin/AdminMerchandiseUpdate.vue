<template>
  <h3>상품 관리</h3>
  <hr />

  <br />
  <br />

  <h3>상품 등록 수정</h3>
  <hr />

  <div class="mb-3">
    <label class="form-label"> 상품명</label>
    <input
      type="text"
      class="form-control"
      placeholder="상품명을 적어주세요"
      v-model="products.name"
    />
  </div>

  <br />

  <!-- 데이터베이스에서 어떻게 불러올지 의논하기 -->
  <div class="mb-3">
    <label for="exampleFormControlTextarea1" class="form-label">카테고리</label>
    <select
      class="form-select"
      aria-label="Default select example"
      v-model="products.karegorie"
    >
      <option selected>제품 카테고리를 선택하세요</option>
      <option value="두루마기">두루마기</option>
      <option value="저고리">저고리</option>
      <option value="바지">바지</option>
      <option value="치마">치마</option>
    </select>
  </div>

  <br />

  <!-- 데이터베이스에서 어떻게 불러올지 의논하기 -->
  <div class="mb-3">
    <label for="exampleFormControlTextarea1" class="form-label"
      >성별(Sex)</label
    >
    <select
      class="form-select"
      aria-label="Default select example"
      v-model="products.gender"
    >
      <option selected>제품 성별 카테고리를 선택하세요</option>
      <option value="남성">남성</option>
      <option value="여성">여성</option>
      <option value="남녀공용">남녀공용</option>
    </select>
  </div>

  <br />

  <div class="mb-3">
    <label for="exampleFormControlTextarea1" class="form-label"
      >단일 가격 지정</label
    >
    <input
      class="form-control"
      type="number"
      placeholder="가격 지정을 해주세요"
      aria-label="default input example"
      v-model="products.price"
    />
  </div>

  <div class="mb-3">
    <label for="exampleFormControlTextarea1" class="form-label">상세내용</label>
    <textarea
      class="form-control"
      id="exampleFormControlTextarea1"
      rows="3"
      v-model="products.contents"
    ></textarea>
  </div>

  <br />
  <!-- 사진 파일 입력 -->
  <h3>이미지 업로드</h3>
  <hr />
  <div class="mb-3">
    <label class="form-label">이미지 업로드</label>
    <input
      type="file"
      class="form-control"
      multiple
      @change="handleImageUpload"
    />
  </div>
  <br />
  <br />

  <div class="text-center">
    <button type="button" class="btn btn-primary me-2" @click="update">
      수정
    </button>
    <a href="/admin/AdminMerchandise"
      ><button type="button" class="btn btn-warning">취소</button></a
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
    handleImageUpload(event) {
      this.images = Array.from(event.target.files); // 선택한 파일들을 배열로 저장
    },
    async update() {
      try {
        let response = await ProductService.update({
          ...this.products,
          images: this.images,
        });
        console.log(response.data);
        this.products = response.data;
        alert("수정되었습니다");
        this.$router.push("/admin/AdminAnouncement");
      } catch (error) {
        this.products.image = undefined;
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

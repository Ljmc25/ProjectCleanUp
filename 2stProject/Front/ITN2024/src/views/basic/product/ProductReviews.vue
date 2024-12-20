<template>
  <div class="container my-5">
    <h1 class="text-center">상품 사용후기</h1>
    <div class="row">
      <form>
        <div class="mb-3">
          <label for="reviewTitle" class="form-label">제목</label>
          <input
            type="text"
            class="form-control"
            id="reviewTitle"
            placeholder="제목을 입력하세요"
          />
        </div>
        <div class="mb-3">
          <label for="reviewContent" class="form-label">내용</label>
          <textarea
            class="form-control"
            id="reviewContent"
            rows="5"
            placeholder="내용을 입력하세요"
          ></textarea>
        </div>
        <div class="input-group" style="width: 40%">
          <input
            type="file"
            class="form-control"
            id="inputGroupFile04"
            aria-describedby="inputGroupFileAddon04"
            aria-label="Upload"
          />
        </div>
        <div class="text-end container d-flex" style="justify-content: center; align-items: center; margin-top: 5vh;">
          <button type="submit" class="btn btn-primary" style="margin-right: 2vw; width: 10%">등록</button>
          <button type="reset" class="btn btn-secondary" style="margin-right: 2vw; width: 10%">취소</button>
        </div>
      </form>
    </div>
  </div>
</template>
<script>
export default {};
</script>
<style>
</style>
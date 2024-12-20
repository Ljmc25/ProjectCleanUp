// views/basic/dept/AddDept.vue // vueInit
<template>
  <div>
    <!-- title -->
    <div class="form-floating mb-3">
      <input
        type="text"
        class="form-control"
        id="title"
        placeholder="title"
        v-model="notice.title"
      />
      <label for="title">title</label>
    </div>

    <!-- contents -->
    <div class="form-floating mb-3">
      <input
        type="text"
        class="form-control"
        id="contents"
        placeholder="contents"
        v-model="notice.contents"
      />
      <label for="contents">contents</label>
    </div>

<!-- TODO: 파일선택상자 -->
<div class="input-group">
        <input
          type="file"
          class="form-control"
          ref="file"
          @change="select"
        />
        <button
          class="btn btn-outline-secondary"
          type="button"
          @click="save"
        >
          Button
        </button>
      </div>

<br>

    <!-- 버튼 -->
    <button type="button" class="btn btn-primary" @click="save">저장</button>
  </div>
</template>
<script>
import NoticeService from "@/services/basic/NoticeService";
export default {
  data() {
    return {
      notice: {
        
        title: "",
        contents: "",
        imageUrl: "",
        writer: "ITN2024@gmail.com",
        image: undefined, // 선택이미지
      },
    };
  },
  methods: {




    
    select(){
        // 파일선택상자에서 선택한 이미지를 저장
        // TODO: 파일선택상자 태그 : input type="file"
        // 특징 : 첫번째 선택하면(이미지) files[0] 배열에 저장 
        // 사용법 : this.$refs.태그변수 : html 태그 접근
        this.notice.image = this.$refs.file.files[0];
      },




      async save(){
        try {
          let response = await NoticeService.insert(this.notice);
          console.log(response.data); // 디버깅
          // insert 성공 -> 전체조회 강제 이동
          this.$router.push("/basic/notice");
        } catch (error) {
          this.notice.image = undefined;
          console.log(error);
        }
      }
    },
  };



  </script>
  <style></style>
  
    <!-- async save() {
      try {
        // 임시 객체 : 저장할 객체
        let temp = {
          title: this.notice.title,
          contents: this.notice.contents,
          imageUrl: this.notice.imageUrl,
          writer: this.notice.writer,
          image: this.notice.image
        };
        let response = await NoticeService.insert(temp);
        console.log(response.data); // 디버깅
        // TODO: 저장후 강제이동: 전체조회(/dept)
        this.$router.push("/notice");
      } catch (error) {
        console.log(error);
      }
    },
  },
};
</script>
<style></style> -->

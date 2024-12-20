// views-basic-dept/DeptView.vue // vueInit
<template>
    <div>
        <!-- TODO: 검색어 입력상자 -->
        <div class="input-group mb-3">
            <input type="text" class="form-control" placeholder="검색어" v-model="searchKeyword" />
            <button class="btn btn-outline-secondary" type="button" @click="getDept">
                검색
            </button>
        </div>
        <!-- TODO: 부트스트랩 테이블 -->
        <table class="table">
            <thead>
                <tr>
                    <th scope="col">EXID</th>
                    <th scope="col">TITLE</th>
                    <th scope="col">CONTENTS</th>
                    <th scope="col">INSERT_TIME</th>
                    <th scope="col">UPDATE_TIME</th>
                </tr>
            </thead>
            <tbody>
                <!-- TODO: 반복문 : depts -->
                <tr v-for="(data, index) in examples" :key="index">
                    <td>{{ data.exid }}</td>
                    <td>{{ data.title }}</td>
                    <td>{{ data.contents }}</td>
                    <td>{{ data.insertTime }}</td>
                    <td>{{ data.updateTime }}</td>
                </tr>
            </tbody>
        </table>
        <!-- TODO: 페이지 번호 : 부트스트랩뷰(페이지)  -->
        <div>
            <b-pagination v-model="pageIndex" :total-rows="totalCount" :per-page="recodeCountPerPage"
                @click="getExample"></b-pagination>
        </div>
    </div>
</template>
<script>
// 프론트 코딩 : 벡엔드(변수들 보내고) -> 벡엔드(sql 결과 리턴)
//              -> 프론트(json 반복문으로 화면출력)
// @ == src 폴더위치(절대경로)
import ExampleService from "@/services/basic/ExampleService";
export default {
    data() {
        return {
            pageIndex: 1, // 현재페이지번호
            totalCount: 0, // 전체개수
            recodeCountPerPage: 3, // 화면에보일개수
            searchKeyword: "", // 검색어
            examples: [], // 빈배열(json)
        };
    },
    methods: {
        // 함수 작성 : (비동기 코딩: async/await)
        // (복습) : 뷰함수 앞에 : async, axios 함수 앞에 : await
        async getExample() {
            try {
                let response = await ExampleService.getAll(
                    this.searchKeyword,
                    this.pageIndex - 1,
                    this.recodeCountPerPage
                );
                // TODO: 벡엔드 전송되는것: results(배열), totalCount(총개수)
                const { results, totalCount } = response.data;
                console.log(response.data); // 디버깅
                this.examples = results;
                this.totalCount = totalCount;
            } catch (error) {
                console.log(error);
            }
        },
    },
    // 화면이 뜰때 실행하는 함수
    mounted() {
        this.getExample();
    },
};
</script>
<style></style>
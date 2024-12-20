// services - DeptService.js
import axios from "axios";
// 벡엔드 주소 : springboot 주소(컨트롤러주소)
const baseURL = "http://localhost:8000/api";

// TODO: 전체조회 : pageIndex(현재페이지번호), recordCountPerPage(화면에보일개수)
// TODO:           벡엔드로 요청 -> 벡엔드 sql 실행
// TODO: 사용법) axios.get(url);
const getAll = (searchKeyword,pageIndex, recordCountPerPage) => {
    return axios
       .get(baseURL+`/basic/notice?searchKeyword=${searchKeyword}&pageIndex=${pageIndex}&recordCountPerPage=${recordCountPerPage}`);
};


// TODO: 상세조회(dno)
const get =(noid)=>{
return axios.get(baseURL+`/basic/notice/get/${noid}`);
}


// // TODO:부서수정
// const update = (noid, data) => {
//     return axios.put(baseURL + `/basic/notice/${noid}`, data);
// }

// TODO : 부서 삭제 : /api/basic/dept/{dno}
const remove = (noid, data) => {
    return axios.delete(baseURL + `/basic/notice/${noid}`, data);
  };

// // TODO: 부서생성 : "/api/basic/dept"
// // TODO:           json data(db에 생성될 객체)
// // TODO: 사용법) axios.post(baseURL+`/basic/dept`,data);
// const insert = (data) => {
//   return axios.post(baseURL+`/basic/notice`,data);
// };





 // insert(upload)
// form 태그 : input 양식, multipart 전송
// TODO: form 태그 == FormData 객체
const insertForm = (data)=>{
  // FormData 객체 생성
  let formData = new FormData();
  // FormData 객체에 넣기 : .append(키(벡엔드변수명), 값)
  formData.append("title", data.title);
  formData.append("content", data.contents);
  formData.append("image", data.image);
  formData.append("writer", data.writer);
  

  return formData;
}


/// insert 함수
// 다운로드 함수 : 첨부파일입니다.(헤더명시)
const insert = (data) => {
  let form = insertForm(data);
  return axios.post(baseURL+"/basic/notice/add", form,
    {
      headers: {"Content-Type": "multipart/form-data",}
    }
  );
}



// TODO:부서수정
const update = (noid,data) => {
  let form = insertForm(data);
  return axios.put(baseURL+`/basic/notice/update/${noid}`, form,
    {
      headers: {"Content-Type": "multipart/form-data",}
    }
  );
}

const NoticeService = {
     getAll ,
     insert,
     get,
     update,
     remove
    };
export default NoticeService;


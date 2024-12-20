import axios from "axios";

const baseURL = "http://localhost:8000/api";

// 전체조회 /api/basic/notice
const getAll = (
  searchCriteria,
  searchKeyword,
  pageIndex,
  recordCountPerPage
) => {
  return axios.get(baseURL + `/basic/notice`, {
    params: {
      searchCriteria, // 검색 기준 
      searchKeyword, // 검색어
      pageIndex, // 페이지 인덱스
      recordCountPerPage, // 페이지당 레코드 수
    },
  });
};

// 상세 조회 /api/basic/notice/{noid}
const get = (noid) => {
  return axios.get(baseURL + `/basic/notice/get/${noid}`);
};
// 삭제 /api/basic/notice/{noid}
const remove = (noid, data) => {
  return axios.delete(baseURL + `/basic/notice/${noid}`, data);
};

// insert(upload)
// form 태그 : input 양식, multipart 전송
// TODO: form 태그 == FormData 객체
const insertForm = (data) => {
  // FormData 객체 생성
  let formData = new FormData();
  // FormData 객체에 넣기 : .append(키(벡엔드변수명), 값)
  formData.append("title", data.title);
  formData.append("content", data.contents);
  formData.append("image", data.image);
  formData.append("writer", data.writer);
  // formData.append("insertTime", data.insertTime);

  return formData;
};

/// insert 함수
// 다운로드 함수 : 첨부파일입니다.(헤더명시)
const insert = (data) => {
  let form = insertForm(data);
  return axios.post(baseURL + "/basic/notice/add", form, {
    headers: { "Content-Type": "multipart/form-data" },
  });
};

// TODO:부서수정
const update = (noid, data) => {
  let form = insertForm(data);
  return axios.put(baseURL + `/basic/notice/update/${noid}`, form, {
    headers: { "Content-Type": "multipart/form-data" },
  });
};

const AnnouncementService = {
  getAll,
  get,
  remove,
  update,
  insert,
};
export default AnnouncementService;

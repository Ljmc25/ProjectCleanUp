// services - InquiryService
import axios from "axios";
// 벡엔드 주소 : springboot 주소(컨트롤러주소)
const baseURL = "http://localhost:8000/api";

//전체조회(특정사용자)
const getAll = (searchKeyword, pageIndex, recordCountPerPage, email)=> {
    return axios.get(baseURL+`/basic/inquiry?searchKeyword=${searchKeyword}&pageIndex=${pageIndex}&recordCountPerPage=${recordCountPerPage}&email=${email}`);  
}

// 전체조회(관리자)
const getadmin = (
    searchCriteria,
    searchKeyword,
    pageIndex,
    recordCountPerPage
  ) => {
    return axios.get(baseURL + `/basic/inquiry/admin`, {
      params: {
        searchCriteria, // 검색 기준
        searchKeyword, // 검색어
        pageIndex, // 페이지 인덱스
        recordCountPerPage, // 페이지당 레코드 수
      },
    });
  };

//상세조회
const get = (iqid) =>{
    return axios.get(baseURL+`/basic/inquiry/${iqid}`);
}

//INSERT
const insert = (data) => {
    return axios.post(baseURL+"/basic/inquiry",data);
}
//관리자 INSERT
const insertadmin = (data) => {
    return axios.post(baseURL+"/basic/inquiry/admin",data);
}

//UPDATE
const update = (iqid, data) => {
return axios.put(baseURL+`/basic/inquiry/${iqid}`,data);
}
//DELETE
const remove = (iqid)=>{
    return axios.delete(baseURL+`/basic/inquiry/delection/${iqid}`);
}
const InquiryService ={
    getAll,
    get,
    getadmin,
    insert,
    insertadmin,
    update,
    remove

}
export default  InquiryService;
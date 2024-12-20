// services/auth/MemberService.js
import axios from "axios";

const baseURL = "http://localhost:8000/api";

// 전체조회
const getAll = (
    searchCriteria,
    searchKeyword,
    pageIndex,
    recordCountPerPage
  ) => {
    return axios.get(baseURL + `/auth/member`, {
      params: {
        searchCriteria, // 검색 기준 (예: 'email', 'name', 'phoneNumber')
        searchKeyword, // 검색어
        pageIndex, // 페이지 인덱스
        recordCountPerPage, // 페이지당 레코드 수
      },
    });
  };
const get = (email) => {
    return axios.get(baseURL + `/auth/member/${email}`);
}
const update = (email, data) => {
    return axios.put(baseURL +`/auth/member/${email}`, data);
}


const insert = (data) => {
    return axios.post(baseURL+"/auth/register", data);
}

const login = (data) => {
    return axios.post(baseURL+"/auth/login", data);
}

const logout = ()=>{
    // TODO: 사용법(삭제) : localStorage.removeItem("키");
    localStorage.removeItem("user");
}


const MemberService = {
    getAll, 
    get, 
    update,  
    insert,
    login,
    logout
}
export default MemberService;
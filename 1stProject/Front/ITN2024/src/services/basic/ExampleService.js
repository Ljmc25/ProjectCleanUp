// services - DeptService.js
import axios from "axios";
// 벡엔드 주소 : springboot 주소(컨트롤러주소)
const baseURL = "http://localhost:8000/api"
// const baseURL ="http://192.168.1.82:8000/api"

const getAll = (searchKeyword, pageIndex, recordCountPerPage) => {
    return axios.get(baseURL+`/basic/example?searchKeyword=${searchKeyword}&pageIndex=${pageIndex}&recordCountPerPage=${recordCountPerPage}`);
}

const ExampleService = {
    getAll
}

export default ExampleService;
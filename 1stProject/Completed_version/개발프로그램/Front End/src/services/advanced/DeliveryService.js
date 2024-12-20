// services - DeliveryService
import axios from "axios";
// 벡엔드 주소 : springboot 주소(컨트롤러주소)
const baseURL = "http://localhost:8000/api";


//전체조회
const getAll = (searchKeyword, pageIndex, recordCountPerPage) => {
    return axios.get(baseURL+`/advanced/delivery?searchKeyword=${searchKeyword}&pageIndex=${pageIndex}&recordCountPerPage=${recordCountPerPage}`);
}
// 기본키을 이용한 상세조회
const get = (deid) =>{
    return axios.get(baseURL+`/advanced/delivery${deid}`);
}

//결제 ID를 이용한 상세조회
const getFk = (paid) => {
    return axios.get(baseURL+`/advanced/delivery/payment/${paid}`);    
}

// INSERT
const insert = (data) =>{
    return axios.post(baseURL+"/advanced/delivery", data);
}

// UPDATE
const update = (deid, data) =>{
    return axios.put(baseURL+`/advanced/delivery/${deid}`,data);
}


const DeliveryService ={
    getAll,
    get,
    getFk,
    insert,
    update
}



export default DeliveryService;

import axios from "axios";
// 벡엔드 주소 : springboot 주소(컨트롤러주소)
const baseURL = "http://localhost:8000/api/advanced";
// const baseURL ="http://192.168.1.82:8000/api/advanced";

const getAll = (searchKeyword, pageIndex, recordCountPerPage, email) => {
    return axios.get(baseURL+`/payment?searchKeyword=${searchKeyword}&pageIndex=${pageIndex}&recordCountPerPage=${recordCountPerPage}&email=${email}`);
}

const get = (paid) => {
    return axios.get(baseURL+`/payment/${paid}`);
}
const insert = (data) => {
    return axios.post(baseURL+"/payment/insert", data);
}
const update = (paid, data) => {
    return axios.put(baseURL+`/payment/update/${paid}`, data);
}
const remove = (paid) => {
    return axios.delete(baseURL+`/payment/delete/${paid}`);
}
const PaymentService = {
    getAll,
    get,
    insert,
    update,
    remove 
}

export default PaymentService;
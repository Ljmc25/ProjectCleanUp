import axios from "axios";
// 벡엔드 주소 : springboot 주소(컨트롤러주소)
const baseURL = "http://localhost:8000/api/basic"

const getAll = (searchKeyword, pageIndex, recordCountPerPage) => {
    return axios.get(baseURL+`/product?searchKeyword=${searchKeyword}&pageIndex=${pageIndex}&recordCountPerPage=${recordCountPerPage}`);
}
const getAllAdmin = (searchKeyword, pageIndex, recordCountPerPage) => {
    return axios.get(baseURL+`/product/admin?searchKeyword=${searchKeyword}&pageIndex=${pageIndex}&recordCountPerPage=${recordCountPerPage}`);
}
const get = (prid) => {
    return axios.get(baseURL+`/product/${prid}`);
}
const getAdmin = (prid) => {
    return axios.get(baseURL+`/product/admin/${prid}`);
}
const getCategory = (siteName) => {
    return axios.get(baseURL+`/product/${siteName}`);
}
const insert = (data) => {
    return axios.post(baseURL+"/product/insert", data);
}
const update = (prid, data) => {
    return axios.put(baseURL+`/product/update/${prid}`, data);
}
const remove = (prid) => {
    return axios.delete(baseURL+`/product/delete/${prid}`);
}
const ProductService = {
    getAll,
    getAllAdmin,
    get,
    getAdmin,
    getCategory,
    insert,
    update,
    remove 
}

export default ProductService;
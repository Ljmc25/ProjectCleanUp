import axios from "axios";
const baseURL = "http://localhost:8000/api";

const getall = (searchKeyword, pageIndex, recordCountPerPage) => {
    return axios.get(
        baseURL +
            `/basic/product?searchKeyword=${searchKeyword}&pageIndex=${pageIndex}&recordCountPerPage=${recordCountPerPage}`
    );
};

const getallAdmin = (searchKeyword, pageIndex, recordCountPerPage) => {
    return axios.get(
        baseURL +
            `/basic/product/admin?searchKeyword=${searchKeyword}&pageIndex=${pageIndex}&recordCountPerPage=${recordCountPerPage}`
    );
};

const insertForm = (data) => {
    console.log(data);

    let formData = new FormData();
    formData.append("name", data.name);
    formData.append("karegorie", data.karegorie);
    formData.append("gender", data.gender);
    formData.append("price", data.price);
    formData.append("contents", data.contents);
    formData.append("sales", data.sales);

    // 이미지 처리
    if (data.images && data.images.length > 0) {
        for (let i = 0; i < data.images.length; i++) {
            formData.append("images", data.images[i]);
        }
    }

    return formData;
};

const insert = (data) => {
    let form = insertForm(data);
    return axios.post(baseURL + "/basic/product/insert", form, {
        headers: {
            "Content-Type": "multipart/form-data"
        }
    });
};

const get = (prid) => {
    return axios.get(baseURL + `/basic/product/${prid}`);
};

const getAdmin = (prid) => {
    return axios.get(baseURL + `/basic/product/admin/${prid}`);
};

const update = (prid, data) => {
    let form = insertForm(data);
    return axios.put(baseURL + `/basic/product/update/${prid}`, form, {
        headers: {
            "Content-Type": "multipart/form-data"
        }
    });
};

const remove = (prid) => {
    return axios.delete(baseURL + `/basic/product/delete/${prid}`);
};

const ProductService = {
    getall,
    getallAdmin,
    insert,
    get,
    getAdmin,
    update,
    remove
};

export default ProductService;

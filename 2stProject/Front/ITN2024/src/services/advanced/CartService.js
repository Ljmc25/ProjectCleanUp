// services - DeptService.js
import axios from "axios";
// 벡엔드 주소 : springboot 주소(컨트롤러주소)
const baseURL = "http://localhost:8000/api"
// const baseURL ="http://192.168.1.82:8000/api"

// TODO: 전체조회 : pageIndex(현재페이지번호), recordCountPerPage(화면에보일개수)
// TODO:           벡엔드로 요청 -> 벡엔드 sql 실행
// TODO: 사용법) axios.get(url);
const getAll = (searchKeyword,pageIndex, recordCountPerPage,email) => {
  return axios
     .get(baseURL+`/advanced/cart?searchKeyword=${searchKeyword}&pageIndex=${pageIndex}&recordCountPerPage=${recordCountPerPage}&email=${email}`);
};


// TODO: 부서생성 : "/api/basic/dept"
// TODO:           json data(db에 생성될 객체)
// TODO: 사용법) axios.post(baseURL+`/basic/dept`,data);
const insert = (data) => {
  return axios.post(baseURL+`/advanced/cart`,data);
};



// TODO: 상세조회(dno)
const get =(caid)=>{
return axios.get(baseURL+`/advanced/cart/get/${caid}`);
}


    // TODO: 부서수정
    const update = (caid, data) => {
      return axios.put(baseURL + `/advanced/cart/${caid}`, data);
  }

// TODO : 부서 삭제 : /api/basic/dept/{dno}
const remove = (caid) => {
  console.log(caid)
    return axios.delete(baseURL + `/advanced/cart/delete/${caid}`);
  };
  

const CartService = {
     getAll ,
     insert,
     get,
     update,
     remove
    };
export default CartService;


import axios from "axios";
const baseURL = "http://localhost:8000/api";
// const baseURL = "http://192.168.1.82:8000/api";

// `params` 객체를 사용하여 API 호출 시 파라미터 전달 방식 개선
const getAll = (searchKeyword = "", pageIndex = 1, recordCountPerPage = 8) => {
  // pageIndex가 1일 때 offset을 0으로 시작하도록 수정
  const offset = Math.max((pageIndex - 1) * recordCountPerPage, 0);

  return axios
    .get(baseURL + `/basic/product`, {
      params: {
        searchKeyword: searchKeyword,
        offset: offset, // 수정된 offset 값 전달
        size: recordCountPerPage,
      },
    })
    .catch((error) => {
      console.error("API 호출 오류:", error.response || error);
      throw error;
    });
};
// 카테고리별 상품 조회
const getAllByCategory = (
  karegorie,
  searchKeyword = "",
  pageIndex = 1,
  recordCountPerPage = 8
) => {
  const offset = Math.max((pageIndex - 1) * recordCountPerPage, 0);

  return axios
    .get(baseURL + `/basic/product/category`, {
      params: {
        karegorie: karegorie, // 카테고리 파라미터 추가
        searchKeyword: searchKeyword,
        offset: offset,
        size: recordCountPerPage,
      },
    })
    .catch((error) => {
      console.error("API 호출 오류:", error.response || error);
      throw error;
    });
};

const getAllAdmin = (
  searchCriteria = "name",
  searchKeyword = "",
  pageIndex = 1,
  recordCountPerPage = 10
) => {
  return axios
    .get(baseURL + `/basic/product/admin`, {
      params: {
        searchCriteria: searchCriteria, // 'name' 또는 'code'
        searchKeyword: searchKeyword,
        pageIndex: pageIndex,
        recordCountPerPage: recordCountPerPage,
      },
    })
    .catch((error) => {
      console.error("API 호출 오류:", error.response || error);
      throw error;
    });
};

const insertForm = (data) => {
  let formData = new FormData();
  // 백엔드 컨트롤러의 @RequestParam에 맞춰 필드 추가
  formData.append("name", data.name);
  formData.append("karegorie", data.karegorie);
  formData.append("gender", data.gender);
  formData.append("price", data.price);
  formData.append("contents", data.contents);
  formData.append("sales", data.sales !== undefined ? data.sales : 0);

  // 이미지 파일 추가
  if (data.images && data.images.length > 0) {
    data.images.forEach((image) => {
      formData.append("images", image);
    });
  }

  return formData;
};

const insert = async (data) => {
  let form = insertForm(data);
  try {
    const response = await axios.post(baseURL + "/basic/product/insert", form, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    });

    console.log("상품 등록 성공:", response.data);
    return response.data; // { message: "상품이 성공적으로 저장되었습니다.", prid: "생성된 상품 ID" }
  } catch (error) {
    console.error("상품 등록 오류:", error.response || error);
    throw error; // 오류를 호출한 곳으로 다시 던져서 처리할 수 있게 함
  }
};

const get = (prid) => {
  console.log("Fetching product with prid:", prid); // prid 값 확인
  return axios.get(baseURL + `/basic/product/${prid}`);
};

const getAdmin = (prid) => {
  return axios.get(baseURL + `/basic/product/admin/${prid}`);
};

const update = (prid, data) => {
  let form = insertForm(data);
  return axios.put(baseURL + `/basic/product/update/${prid}`, form, {
    headers: {
      "Content-Type": "multipart/form-data",
    },
  });
};

const updateSales = (prid,data,sales) => {
    return axios.put(baseURL + `/basic/product/update/sales/${prid}?num=${sales}`,data);
  };

const remove = (prid) => {
  return axios.delete(baseURL + `/basic/product/delete/${prid}`);
};

const ProductService = {
  getAll,
  getAllByCategory,
  getAllAdmin,
  insert,
  get,
  getAdmin,
  update,
  remove,
  updateSales
};

export default ProductService;

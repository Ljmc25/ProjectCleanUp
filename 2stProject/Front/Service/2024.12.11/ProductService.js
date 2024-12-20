import axios from "axios";
const baseURL = "http://localhost:8000/api";

// `params` 객체를 사용하여 API 호출 시 파라미터 전달 방식 개선
const getAll = (searchKeyword = '', pageIndex = 1, recordCountPerPage = 10) => {
    return axios
        .get(baseURL + `/basic/product`, {
            params: {
                searchKeyword: searchKeyword,
                pageIndex: pageIndex,
                recordCountPerPage: recordCountPerPage
            }
        })
        .catch((error) => {
            console.error("API 호출 오류:", error.response || error);
            throw error;
        });
};

const getAllAdmin = (searchKeyword = '', pageIndex = 1, recordCountPerPage = 10) => {
    return axios.get(baseURL + `/basic/product/admin`, {
        params: {
            searchKeyword: searchKeyword,
            pageIndex: pageIndex,
            recordCountPerPage: recordCountPerPage
        }
    }).catch((error) => {
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
        data.images.forEach(image => {
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
    getAll,
    getAllAdmin,
    insert,
    get,
    getAdmin,
    update,
    remove
};

export default ProductService;

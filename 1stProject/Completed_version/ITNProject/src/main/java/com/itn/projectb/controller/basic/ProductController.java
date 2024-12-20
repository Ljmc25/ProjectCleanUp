package com.itn.projectb.controller.basic;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itn.projectb.service.basic.ProductService;
import com.itn.projectb.vo.basic.MemberVO;
import com.itn.projectb.vo.basic.ProductVO;
import com.itn.projectb.vo.common.Criteria;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
public class ProductController {
	   @Autowired
	   ProductService productService;
	   
	//   전체 조회 함수
	   @GetMapping("/movie_all")
	   public String selectMovie_allList(
	         @ModelAttribute("searchVO") Criteria searchVO,
	         Model model) throws Exception {
	   
	//   TODO: 0) 페이징 변수에 설정 : 
	   searchVO.setPageUnit(12); // 1페이지당 화면에 보이는 개수
	   searchVO.setPageSize(2); // 페이지 번호를 보여줄 개수
	   
	//   TODO: 페이지 객체 생성
	   PaginationInfo paginationInfo = new PaginationInfo();         // 페이징 객체
	   paginationInfo.setCurrentPageNo(searchVO.getPageIndex());     // 현재 페이지 번호 저장
	   paginationInfo.setRecordCountPerPage(searchVO.getPageUnit()); // 1페이지당 보일 게시물 개수
	   paginationInfo.setPageSize(searchVO.getPageSize());           // 페이지 번호를 보여줄 개수
	   
	//   TODO: searchVO 객체 페이징 정보 저장
	   searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());           // 첫페이지번호
	   searchVO.setLastIndex(paginationInfo.getLastRecordIndex());             // 끝페이지번호
	   searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage()); // 1페이지당 보일 게시물 개수

	   
	   
	//   1) 서비스 객체의 함수를 실행 : deptService.전체조회()
	   List<?> productList = productService.selectMovie_allList(searchVO);
	//   2) 모델에 결과(depts) 담아 jsp 전송
	   model.addAttribute("productList", productList);
	   
	//   TODO: 3) 부서 테이블의 총개수(서비스 객체의 함수를 실행) : 페이지 객체 저장
	   int totCnt = productService.selectMovie_allListTotCnt(searchVO);
	   paginationInfo.setTotalRecordCount(totCnt);
	//   TODO: 페이징 객체 -> jsp 전달
	   model.addAttribute("paginationInfo", paginationInfo);
	   
	   return "product/productList";
	}



	   public ProductController(ProductService productService) {
	      super();
	      this.productService = productService;
	   }



	//   상세조회 페이지 열기 :
	   @GetMapping("/view_details")
	   public String updateMovie_allView(@RequestParam(defaultValue = "") String pdid, Model model) 
	               throws Exception {
	      System.out.println(pdid);
//	      1) deptService 의 상세조회 함수 실행 
	      ProductVO productVO = productService.selectMovie_all(pdid);
	      
//	      2) 모델에 담아 Jsp 로 전송
	      model.addAttribute("productVO", productVO);
	      
	      List<?> productReviewVO = productService.selectMovie_reviews(pdid);
	      
//	      2) 모델에 결과(depts) 담아 jsp 전송
	      model.addAttribute("productReviewVO", productReviewVO);
	      
//	      log.info("테스트"+movie_allVO);
	      return "product/productDetail";
	   }
	   
	//   관람평 등록
	   @PostMapping("/view_details_review_register")
	   public String review_register (
	                           		@RequestParam(defaultValue = "") String pdid, 
	                                @RequestParam String review, 
	                                HttpSession session, 
	                                Model model,
	                                RedirectAttributes redirectAttributes) throws Exception {

	       // 세션에서 로그인한 사용자 정보 가져오기 (작성자 정보)
	       MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
	       if (memberVO == null) {
	           redirectAttributes.addFlashAttribute("errorMessage", "로그인이 필요합니다.");
	           return "redirect:/login";
	       }

	       // PDID를 이용해 영화 정보를 가져오기
	       ProductVO prodectVO = productService.selectMovie_all(pdid);
	              
	       // 리뷰 및 작성자 정보 설정
	       prodectVO.setReview(review);  // 리뷰 내용 설정
	       prodectVO.setWriter(memberVO.getEmail());  // 작성자 정보 설정 (세션에서 가져옴)

	       model.addAttribute("prodectVO", prodectVO);
	       try {
	           // 리뷰 등록 호출
	    	   productService.review_register(prodectVO); 
	           redirectAttributes.addFlashAttribute("successMessage", "리뷰가 성공적으로 등록되었습니다.");
	       } catch (Exception e) {
	           redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
	           String encodedPdid = URLEncoder.encode(prodectVO.getPdid(), StandardCharsets.UTF_8.toString());
	           return "redirect:/view_details?pdid=" + encodedPdid;  // 오류 발생 시 리다이렉트
	       }

	       // 성공 시 상세보기 페이지로 리다이렉트
	       String encodedPdid = URLEncoder.encode(prodectVO.getPdid(), StandardCharsets.UTF_8.toString());
	       return "redirect:/view_details?pdid=" + encodedPdid;
	   }
	   
	    @GetMapping("/product/list")
	    public String movieListPage(@RequestParam(value = "pageIndex", defaultValue = "1") int pageIndex, Model model) throws Exception {
	        Criteria criteria = new Criteria(); // Criteria 객체 생성
	        criteria.setPageNum2(pageIndex); // 현재 페이지 번호 설정
	        criteria.setRecordCountPerPage(6); // 페이지당 6개 항목

	        // 전체 레코드 수를 가져오는 로직 추가
	        int totalRecords = productService.getTotalProductCount(criteria); // 전체 영화 수를 가져오는 서비스 메서드
	        int totalPages = (int) Math.ceil((double) totalRecords / criteria.getRecordCountPerPage()); // 총 페이지 수 계산

	        criteria.setTotalPages(totalPages); // 총 페이지 수 설정

	        // 전체 영화 목록을 가져옵니다.
	        List<ProductVO> productList = productService.selectProductList(criteria); // Criteria를 파라미터로 사용

	        // 모델에 제품 목록과 Criteria 추가
	        model.addAttribute("productList", productList);
	        model.addAttribute("criteria", criteria);

	        return "admin/product/product_all"; // 뷰 이름 반환
	    }

	    @GetMapping("/product/addition")
	    public String createAddMovieView() {
	        return "admin/product/add_product";
	    }

	    @PostMapping("/product/add")
	    public String createProduct(
	            @RequestParam("title") String title,
	            @RequestParam("contents") String contents,
	            @RequestParam("director") String director,
	            @RequestParam("genre") String genre,
	            @RequestParam("runningTime") int runningTime,
	            @RequestParam("rate") String rate,
	            @RequestParam("rateUrl") MultipartFile rateFile, 
	            @RequestParam("openingDate") String openingDate,
	            @RequestParam("movieCast") String movieCast,
	            @RequestParam("imageMainUrl") MultipartFile imageFile, 
	            @RequestParam("imageSmallUrl") MultipartFile smallImageFile, 
	            @RequestParam("imageBackgroundUrl") MultipartFile backgroundImageFile, 
	            @RequestParam("imageThril1Url") MultipartFile thril1ImageFile, 
	            @RequestParam("imageThril2Url") MultipartFile thril2ImageFile, 
	            @RequestParam("imageThril3Url") MultipartFile thril3ImageFile, 
	            @RequestParam("trailerMainUrl") String trailerMainUrl,
	            @RequestParam("trailer1Url") String trailer1Url,
	            @RequestParam("trailer2Url") String trailer2Url
	            ) throws Exception {
	    	
	        
	    	// 파일 URL 생성
	        String rateUrl = createFileUrl(rateFile, "age"); // rateFile의 URL 생성
	        String imageUrl = createFileUrl(imageFile, "big_background"); // imageFile의 URL 생성
	        String smallImageUrl = createFileUrl(smallImageFile, ""); // smallImageFile의 URL 생성
	        String backgroundImageUrl = createFileUrl(backgroundImageFile, "background"); // backgroundImageFile의 URL 생성
	        String thril1ImageUrl = createFileUrl(thril1ImageFile, "still"); // thril1ImageFile의 URL 생성
	        String thril2ImageUrl = createFileUrl(thril2ImageFile, "still"); // thril2ImageFile의 URL 생성
	        String thril3ImageUrl = createFileUrl(thril3ImageFile, "still"); // thril3ImageFile의 URL 생성
	      
	        // ProductVO 생성 및 설정
	        ProductVO product = new ProductVO(
	            title, contents, director, genre, runningTime, rate, 
	            rateUrl, openingDate, movieCast, 
	            imageUrl, smallImageUrl, backgroundImageUrl, 
	            thril1ImageUrl, thril2ImageUrl, thril3ImageUrl, 
	            trailerMainUrl, trailer1Url, trailer2Url
	        );
	       
	        // 제품 추가
	        productService.insert(product);
	       

	        return "redirect:/product/list";
	    }
	    
	    private String createFileUrl(MultipartFile file, String foldder) {
	        String fileUrl = null;
	        if (!file.isEmpty()) {
	            // 파일 이름을 가져와서 URL 형식으로 경로 설정
	            String fileName = file.getOriginalFilename();
	            fileUrl = "resources/images/movies/" + foldder +"/"+ fileName; // 서버에 저장하는 대신 URL만 생성
	        }
	        return fileUrl; // 생성된 URL 반환
	    }

	 
	    @GetMapping("/product/edit/{pdid}")
	    public String showEditProductForm(@PathVariable("pdid") String pdid, Model model) {
	        ProductVO product = productService.selectProductById(pdid);
	        model.addAttribute("product", product);
	        return "admin/product/update_product";
	    }

	    @PostMapping("/product/update")
	    public String updateProduct(@ModelAttribute ProductVO productVO, 
	                                @RequestParam Map<String, MultipartFile> files) {
	        
	        // 기존 제품 정보 조회
	        ProductVO existingProduct = productService.selectProductById(productVO.getPdid());

	        // 모든 파일에 대해 URL 처리
	        existingProduct.setImageMainUrl(processFileUrl(files.get("imageMainFile"), existingProduct.getImageMainUrl(), "big_background"));
	        existingProduct.setImageSmallUrl(processFileUrl(files.get("imageSmallFile"), existingProduct.getImageSmallUrl(),""));
	        existingProduct.setImageBackgroundUrl(processFileUrl(files.get("imageBackgroundFile"), existingProduct.getImageBackgroundUrl(), "background"));
	        existingProduct.setImageThril1Url(processFileUrl(files.get("imageThril1File"), existingProduct.getImageThril1Url(), "still"));
	        existingProduct.setImageThril2Url(processFileUrl(files.get("imageThril2File"), existingProduct.getImageThril2Url(), "still"));
	        existingProduct.setImageThril3Url(processFileUrl(files.get("imageThril3File"), existingProduct.getImageThril3Url(), "still"));
	        existingProduct.setRateUrl(processFileUrl(files.get("rateFile"), existingProduct.getRateUrl(), "age"));

	        // 필드 값 복사 (제목, 내용, 감독, 장르 등)
	        existingProduct.setTitle(productVO.getTitle());
	        existingProduct.setContents(productVO.getContents());
	        existingProduct.setDirector(productVO.getDirector());
	        existingProduct.setGenre(productVO.getGenre());
	        existingProduct.setRunningTime(productVO.getRunningTime());
	        existingProduct.setOpeningDate(productVO.getOpeningDate());
	        existingProduct.setMovieCast(productVO.getMovieCast());
	        existingProduct.setRate(productVO.getRate());
	        existingProduct.setTrailerMainUrl(productVO.getRate());
	        existingProduct.setTrailer1Url(productVO.getRate());
	        existingProduct.setTrailer2Url(productVO.getRate());

	        // 업데이트 로직 수행
	        productService.update(existingProduct);

	        return "redirect:/product/list";
	    }


	    private String processFileUrl(MultipartFile file, String existingUrl, String folder) {
	        if (file != null && !file.isEmpty()) {
	            String fileExtension = getFileExtension(file.getOriginalFilename());
	            
	            // 파일이 이미지인지 비디오인지 체크
	            if (isImageFile(fileExtension)) {
	                return "resources/images/movies/" +folder+ "/"+ file.getOriginalFilename(); // 이미지 URL
	            } 
	        }
	        return existingUrl; // 파일이 없으면 기존 URL 유지
	    }


	    private String getFileExtension(String filename) {
	        if (filename != null && filename.contains(".")) {
	            return filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
	        }
	        return "";
	    }


	    private boolean isImageFile(String fileExtension) {
	        return fileExtension.equals("jpg") || fileExtension.equals("jpeg") || 
	               fileExtension.equals("png") || fileExtension.equals("gif");
	    }


	    @PostMapping("/product/delete") // 변경
	    public String delete(@RequestParam("pdid") String pdid) {
	    	productService.delete(pdid);
	    	return "redirect:/product/list";
	    }
}

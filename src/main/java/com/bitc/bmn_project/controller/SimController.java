package com.bitc.bmn_project.controller;

import com.bitc.bmn_project.DTO.CeoDTO;
import com.bitc.bmn_project.DTO.CustomerDTO;
import com.bitc.bmn_project.DTO.ReviewDTO;
import com.bitc.bmn_project.DTO.ReviewTagDTO;
import com.bitc.bmn_project.common.FileUtils;
import com.bitc.bmn_project.service.SimService;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

@Controller
public class SimController {

    @Autowired
    private SimService simService;

    @Autowired
    private FileUtils fileUtils;


//    @RequestMapping("/")
//    public String index() throws Exception {
//        return "index";
//    }

    @RequestMapping(value = "/bmn/login", method = RequestMethod.POST)
    public String doLoginProcess(@RequestParam String userId,
                                 @RequestParam String userPw,
                                 HttpServletRequest req) throws Exception {


        // 로그인 성공 혹은 실패시 시도했던 주소로 리다이렉트 해주기 위한 값
        String returnUrl = req.getHeader("Referer");

        // 로그인 로직을 수행하기 위한 변수
        int result = 0;
        int grade = 0;

        // 1. DB에 존재하는 ID인지 확인(손님 / 사장)
        int isUser = simService.isUser(userId);

        HttpSession session = req.getSession();
        session.setMaxInactiveInterval(3600); // 세션 시간 1분으로 설정
        CustomerDTO customer;
        CeoDTO ceo;

        // 없는 ID일 경우 메시지 리턴
        if (isUser == 0) {
            session.setAttribute("failMsg", "존재하지 않는 아이디입니다.");
            session.setMaxInactiveInterval(1);
            return "redirect:" + returnUrl;
        }

        int isBanned = simService.isBanned(userId);
        if (isBanned == 1) {
            session.setAttribute("failMsg", "영구정지 된 아이디입니다.");
            session.setMaxInactiveInterval(1);
            return "redirect:" + returnUrl;
        }



        //
        // 2-1-1. 손님 DB에 데이터 존재하는지 확인
        // 2-1-2. 관리자인지 확인()
        int who = simService.isCustomer(userId, userPw);
        if (who != 0) {
            grade = simService.getGrade(userId);
        }

        switch (grade) {
            // 손님이 아님(사장이거나, 로그인 실패)
            // 2-2. 사장님 DB에 데이터 존재하는지 확인
            case 0 -> result = simService.isCeo(userId, userPw);

            // 3-1 로그인 성공 일반회원
            // 3-2 로그인 성공 우수회원
            case 1, 2 -> {
                customer = simService.getCustomerInfo(userId);
                session.setAttribute("user", "customer");
                session.setAttribute("customer", customer);
                session.removeAttribute("failMsg");

                return "redirect:" + returnUrl;
            }

            // 3-3 로그인 성공 관리자
            case 10 -> {
                session.setAttribute("user", "admin"); // 어드민은 가져와야할 값이 없기 때문에 구분만 했음
                session.removeAttribute("failMsg");
                return "redirect:" + returnUrl;
            }

        }

        if (result == 1) { // 3-4 로그인 성공 사장님
            ceo = simService.getCeoInfo(userId);
            session.setAttribute("user", "ceo");
            session.setAttribute("ceo", ceo);
            session.removeAttribute("failMsg");
        } else {
            // 실패시 메시지 전송
            session.setAttribute("failMsg", "비밀번호가 틀렸습니다.");
            session.setMaxInactiveInterval(1);
        }
        return "redirect:" + returnUrl;
    }


    @RequestMapping("/bmn/logOut")
    public String doLogOut(HttpServletRequest req) throws Exception {

        HttpSession session = req.getSession();
        session.invalidate();

        // 로그아웃 후 메인으로
        return "redirect:/bmn/bmnMain";
    }

    @RequestMapping("/bmn/signUp/customer")
    public String doSignUpCustomerView() throws Exception {


        return "signUp/signUpCustomer";
    }

    @RequestMapping(value = "/bmn/signUp/customer/signUp", method = RequestMethod.POST)
    public String doSignUpCustomerProcess(  // 회원가입 완료시 보던곳 가려면 이전의 이전 페이지 주소 필요
            CustomerDTO customer,
            HttpServletRequest req) throws Exception {

        HttpSession session = req.getSession();

        simService.signUpCustomer(customer);

        CustomerDTO newCustomer = simService.getCustomerInfo(customer.getCustomerId());
        session.setAttribute("user", "customer");
        session.setAttribute("customer", newCustomer);
        session.setMaxInactiveInterval(3600);


// 나중에 메인페이지
        return "redirect:/bmn/bmnMain";
    }

    @RequestMapping("/bmn/signUp/ceo")
    public String doSignUpCeoView() throws Exception {

        return "signUp/signUpCeo";
    }

    @RequestMapping(value = "/bmn/signUp/ceo/signUp", method = RequestMethod.POST)
    public String doSignUpCeoProcess(
            CeoDTO ceo,
            HttpServletRequest req) throws Exception {

        HttpSession session = req.getSession();
        session.setAttribute("user", "ceo");
        session.setAttribute("ceo", ceo);
        session.setMaxInactiveInterval(3600);


        simService.signUpCeo(ceo);

// 나중에 메인페이지
        return "redirect:/bmn/bmnMain";
    }

    @ResponseBody
    @RequestMapping(value = "/bmn/signUp/idCheck", method = RequestMethod.POST)
    public String doIdCheck(@RequestParam("userId") String userId) throws Exception {

        int result1 = simService.idCheckCustomer(userId);
        int result2 = simService.idCheckCeo(userId);

        int result = result1 + result2;

        if (result >= 1) {
            return "true";
        } else {
            return "false";
        }
    }

    @RequestMapping("/bmn/ceoStore")
    public String doCeoStore() throws Exception {

        return "store/ceoStore";
    }


    @RequestMapping(value = "/bmn/ceoStore/popup", method = RequestMethod.GET)
    public String doPopup() throws Exception {

        return "signUp/addrPopup";
    }


    @RequestMapping(value = "/bmn/ceoStore", method = RequestMethod.POST)
    public ModelAndView doPopupCallback(
            @RequestParam("inputYn") String inputYn,
            @RequestParam("roadFullAddr") String roadFullAddr,
            @RequestParam("roadAddrPart1") String roadAddrPart1,
            @RequestParam("roadAddrPart2") String roadAddrPart2,
            @RequestParam("engAddr") String engAddr,
            @RequestParam("jibunAddr") String jibunAddr,
            @RequestParam("zipNo") String zipNo,
            @RequestParam("addrDetail") String addrDetail,
            @RequestParam("admCd") String admCd,
            @RequestParam("rnMgtSn") String rnMgtSn,
            @RequestParam("bdMgtSn") String bdMgtSn,
            @RequestParam("detBdNmList") String detBdNmList,
            @RequestParam("bdNm") String bdNm,
            @RequestParam("bdKdcd") String bdKdcd,
            @RequestParam("siNm") String siNm,
            @RequestParam("sggNm") String sggNm,
            @RequestParam("emdNm") String emdNm,
            @RequestParam("liNm") String liNm,
            @RequestParam("rn") String rn,
            @RequestParam("udrtYn") String udrtYn,
            @RequestParam("buldMnnm") String buldMnnm,
            @RequestParam("buldSlno") String buldSlno,
            @RequestParam("mtYn") String mtYn,
            @RequestParam("lnbrMnnm") String lnbrMnnm,
            @RequestParam("lnbrSlno") String lnbrSlno,
            @RequestParam("emdNo") String emdNo,
            @RequestParam("entX") String entX,
            @RequestParam("entY") String entY,
            HttpServletRequest req
    ) throws Exception {

        ModelAndView mv = new ModelAndView("signUp/addrPopup");

        mv.addObject("inputYn", inputYn);
        mv.addObject("roadFullAddr", roadFullAddr);
        mv.addObject("roadAddrPart1", roadAddrPart1);
        mv.addObject("roadAddrPart2", roadAddrPart2);
        mv.addObject("engAddr", engAddr);
        mv.addObject("jibunAddr", jibunAddr);
        mv.addObject("zipNo", zipNo);
        mv.addObject("addrDetail", addrDetail);
        mv.addObject("admCd", admCd);
        mv.addObject("rnMgtSn", rnMgtSn);
        mv.addObject("bdMgtSn", bdMgtSn);
        mv.addObject("detBdNmList", detBdNmList);
        mv.addObject("bdNm", bdNm);
        mv.addObject("bdKdcd", bdKdcd);
        mv.addObject("siNm", siNm);
        mv.addObject("sggNm", sggNm);
        mv.addObject("emdNm", emdNm);
        mv.addObject("liNm", liNm);
        mv.addObject("rn", rn);
        mv.addObject("udrtYn", udrtYn);
        mv.addObject("buldMnnm", buldMnnm);
        mv.addObject("buldSlno", buldSlno);
        mv.addObject("mtYn", mtYn);
        mv.addObject("lnbrMnnm", lnbrMnnm);
        mv.addObject("lnbrSlno", lnbrSlno);
        mv.addObject("emdNo", emdNo);
        mv.addObject("entX", entX);
        mv.addObject("entY", entY);

        return mv;
    }

    @RequestMapping(value = "/bmn/ceoStore/addStore", method = RequestMethod.POST)
    public String doAddStore(
            @RequestParam(value = "ceoDetailMenu1", required = false) String ceoDetailMenu1,
            @RequestParam(value = "ceoDetailMenu2", required = false) String ceoDetailMenu2,
            @RequestParam(value = "ceoDetailMenu3", required = false) String ceoDetailMenu3,
            @RequestParam(value = "ceoDetailMenu4", required = false) String ceoDetailMenu4,
            @RequestParam(value = "ceoDetailMenu5", required = false) String ceoDetailMenu5,
            @RequestParam(value = "ceoDetailMenu6", required = false) String ceoDetailMenu6,
            CeoDTO store,
            @RequestParam("ceoMainImgFile") MultipartFile mainImage,
            @RequestParam("ceoThumbnailImgFile") MultipartFile thumbnail,
            @RequestParam(value = "files", required = false) List<MultipartFile> files,
            HttpServletRequest req
    ) throws Exception {

        simService.addStore(store, mainImage, thumbnail, files);
        CeoDTO ceo = simService.getCeoInfo(store.getCeoId());
        ceo.setCeoGrade(2);
        System.out.println(ceo);
        HttpSession session = req.getSession();

        session.removeAttribute("ceo");
        session.setAttribute("user", "ceo");
        session.setAttribute("ceo", ceo);


        return "redirect:/bmn/bmnMain";
    }

    @RequestMapping(value = "/bmn/review/{ceoIdx}", method = RequestMethod.GET)
    public ModelAndView doReview(
            @PathVariable("ceoIdx") int ceoIdx
    ) throws Exception {

        ModelAndView mv = new ModelAndView("review/review");

        String ceoStore = simService.getStoreName(ceoIdx);
        mv.addObject("ceoStore", ceoStore);
        mv.addObject("ceoIdx", ceoIdx);

        return mv;
    }

    @RequestMapping(value = "/bmn/review/write", method = RequestMethod.POST)
    public String doReviewWrite(
            ReviewDTO review,
            ReviewTagDTO reviewTag,
            @RequestParam("ceoIdx") int ceoIdx,
            @RequestParam(value = "reviewImgFile", required = false) MultipartFile reviewImgFile
    ) throws Exception {


        // 이미지 파일 파싱 및 실제 파일 저장
        // 파싱을 해서 가져오는 정보는 String 타입의 파일 저장경로
        String reviewImagePath = fileUtils.parseFileInfo(reviewImgFile);
        review.setReviewImg(reviewImagePath);

        // 태그를 제외한 나머지 값 insert 하는 서비스
        simService.reviewWrite(review);

        // 방금 insert한 리뷰의 idx 가져오기(가장 최신, 번호가 가장 높은 idx)
        int newReviewIdx = simService.getReviewIdx();
        reviewTag.setReviewIdx(newReviewIdx);

        // 태그 값 insert 하는 서비스
        simService.reviewWriteTag(reviewTag);

        // 평점 값을 가져와서 평균 내는 서비스
        double avg = simService.getAverage(ceoIdx);
        System.out.println(avg);
        // 평균을 ceoDTO에 update 해주는 서비스
        simService.updateScore(avg, ceoIdx);


        // 상세뷰로 보내야함
        return "redirect:/bmn/viewDetail/" + review.getCeoIdx();
    }

    @RequestMapping("/bmn/getImage")
    @ResponseBody
    public ResponseEntity<byte[]> returnImg(
            @RequestParam("path") String path
    ) throws Exception {
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<byte[]>(IOUtils.toByteArray(
                new FileInputStream(
                        new File(path))),
                header, HttpStatus.CREATED);

    }

    // 관리자 페이지 - 가게 승인 (등록 / 변경?) 리스트 출력
    @RequestMapping("/bmn/admin/ceoManagement")
    public ModelAndView doCeoManagement() throws Exception {

        ModelAndView mv = new ModelAndView("admin/ceoManagement");
        // 리스트 가져오는 서비스
        List<CeoDTO> storeList = simService.getStoreList();

        mv.addObject("storeList", storeList);
        System.out.println(storeList);

        return mv;
    }

    @RequestMapping("/bmn/admin/ceoManagement/Approve")
    public String doCeoManagementApprove(
            @RequestParam("targetIdx") int targetIdx,
            @RequestParam("mode") String mode
    ) throws Exception {


        simService.storeApprove(targetIdx, mode);

        return "redirect:/bmn/admin/ceoManagement";
    }

    @RequestMapping(value = "/bmn/admin/customerManagement", method = RequestMethod.GET)
    public ModelAndView doCustomerManagement(
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum
    ) throws Exception {


        ModelAndView mv = new ModelAndView("admin/customerManagement");
        PageInfo<CustomerDTO> customerList = new PageInfo<>(simService.getCustomerList(pageNum), 3);
        mv.addObject("customerList", customerList);

        return mv;
    }

    @RequestMapping(value = "/bmn/admin/customerManagement", method = RequestMethod.POST)
    public String doCustomerManagementProcess(
            @RequestParam("targetIdx") int targetIdx,
            @RequestParam("grade") int grade
    ) throws Exception {


        simService.changeCustomerGrade(targetIdx, grade);
        return "redirect:/bmn/admin/customerManagement";
    }

    @RequestMapping("/bmn/admin/customerManagement/ban")
    public String doCustomerBan(
            @RequestParam("targetIdx") int targetIdx
    ) throws Exception {


        simService.customerBan(targetIdx);
        return "redirect:/bmn/admin/customerManagement";
    }


}

package com.bitc.bmn_project.controller;

import com.bitc.bmn_project.DTO.CeoDTO;
import com.bitc.bmn_project.DTO.CustomerDTO;
import com.bitc.bmn_project.DTO.ReservationDTO;
import com.bitc.bmn_project.service.LeeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/bmn")
public class LeeController {

    @Autowired
    private LeeService leeService;

    @RequestMapping("/")
    public String index() throws Exception {
        return "index";
    }

    // 받기 전에 상세 페이지에서 get으로 가게 테이블 정보가 제공되어야함, mv.addObject(가게정보 dto) 이런식
    // 고객 정보가 입력된 view 제공
    @RequestMapping(value = "/reservationCus/ceoIdx={ceoIdx}", method = RequestMethod.GET)
    public ModelAndView reservationCusView(@PathVariable int ceoIdx, HttpServletRequest req, HttpServletResponse resp) throws Exception {
//        CustomerDTO customer = new CustomerDTO(); // 로그인 정보 쓸때 주석 해제
//        HttpSession session = req.getSession();   // 로그인 정보 쓸때 주석 해제

//        customer.setCustomerIdx((Integer) session.getAttribute("customerIdx"));
//        customer.setCustomerId((String) session.getAttribute("customerId"));
//        customer.setCustomerPw((String) session.getAttribute("customerPw"));
//        customer.setCustomerName((String) session.getAttribute("customerName"));
//        customer.setCustomerNick((String) session.getAttribute("customerNick"));    //사용할 정보만 주석 해제 닉
//        customer.setCustomerGender((String) session.getAttribute("customerGender"));
//        customer.setCustomerAge((Integer) session.getAttribute("customerAge"));
//        customer.setCustomerFollow((String) session.getAttribute("customerFollow"));
//        customer.setCustomerGrade((Integer) session.getAttribute("customerGrade")); //사용할 정보만 주석 해제 등급
//        customer.setCustomerBan((String) session.getAttribute("customerBan"));


        ModelAndView mv = new ModelAndView("reservation/reservationCus");

        List<ReservationDTO> reservationList = leeService.selectReservation(ceoIdx);  // 가게 전체 예약 정보 들고오기(reservationStat만 들고와도 괜찮)

        mv.addObject("reservationList", reservationList);
//        mv.addObject("customer", customer);    // 실제 예약한 고객의 정보 가져오기, 세션에서 정보(닉, 등급) 가져오기

        return mv;
    }

    // 가게, 날짜 정보에 따른 예약 여부 제공
    @ResponseBody
    @RequestMapping(value = "/reservationCus/ceoIdx={ceoIdx}", method = RequestMethod.POST)
    public Object reservationCusAjax(@RequestParam("reservationDate") String reservationDate, @PathVariable int ceoIdx) throws Exception {    // 날짜정보 제대로 넘어옴!
        List<ReservationDTO> reservationList = new ArrayList<>();   // 전체 정보
        reservationList = leeService.selectReservation(ceoIdx);
        List<Integer> dateList = new ArrayList<>();  // time만

        for (ReservationDTO reservation : reservationList) {
            if (reservationDate.equals(reservation.getReservationDate())) {
                int time = reservation.getReservationTime();   // 날짜 하나 들고오기

                dateList.add(time);
            }
        }
        return dateList; // 제대로 넘어감
    }

    // 고객의 예약 정보 입력 process 및 view 제공(get 방식으로 view만 먼저 구현하는 방법도 생각해보기)
    @RequestMapping(value = "/reservationCus/ceoIdx={ceoIdx}/customerIdx={customerIdx}", method = RequestMethod.POST)   // 주소에 &가 들어가면 안됨, 왜 POST가 안되지? -> form 에 안넣었었음
    public String reservationCusProcess(ReservationDTO reservation, HttpServletResponse resp) throws Exception {
//        ModelAndView mv = new ModelAndView("reservation/reservationCus");

        leeService.insertReservation(reservation);  // 예약 정보 입력(동일 고객 같은날 중복 예약 불가하도록 해야함), DTO에 있는 정보때문에 자꾸 엉뚱한 고객 정보가 얻어져 오는거였음 로그인으로 해결해야할것으로 보임, 이렇게 하니 어노테이션 즉 주소에 있는 데이터를 잘 받아옴. 디버깅 확인 완료

//        int customerIdx = reservation.getCustomerIdx();
//        int ceoIdx = reservation.getCeoIdx();    // @PathVariable 안쓴다면 이렇게 들고와야함
//        String reservationDate = reservation.getReservationDate();
//        int reservationTime = reservation.getReservationTime();
//
//        ReservationDTO reservationInfo = leeService.getReservationInfo(customerIdx, ceoIdx, reservationDate, reservationTime);   // 가게번호, 고객번호, 날짜, 시간을 동시에 조회하여 예약 정보 가져오기

//        mv.addObject("reservationInfo", reservationInfo);

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        String js = "<script>";
        js += "alert('예약 신청 되었습니다.');";
        js += "</script>";

        writer.println(js); // 안나오는 이유 알아보기.. 일단 나중에

        return "redirect:/";  // 리다이렉트는 A에 있었을때 A에서 볼일 끝내고 B로 넘어갈때 쓰는 느낌, 나중에 홈으로 가는 페이지 넣기
    }

    @RequestMapping(value = "/reservationCeo/ceoIdx={ceoIdx}", method = RequestMethod.GET)
    public ModelAndView reservationCeoView(@PathVariable int ceoIdx, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        CeoDTO ceo = new CeoDTO(); // 로그인 정보 쓸때 주석 해제
        HttpSession session = req.getSession();   // 로그인 정보 쓸때 주석 해제

        ceo.setCeoIdx((Integer) session.getAttribute("ceoIdx"));
        ceo.setCeoId((String) session.getAttribute("ceoId"));
        ceo.setCeoPw((String) session.getAttribute("ceoPw"));
        ceo.setCeoName((String) session.getAttribute("ceoName"));

//        customer.setCustomerIdx((Integer) session.getAttribute("customerIdx"));
//        customer.setCustomerId((String) session.getAttribute("customerId"));
//        customer.setCustomerPw((String) session.getAttribute("customerPw"));
//        customer.setCustomerName((String) session.getAttribute("customerName"));
//        customer.setCustomerNick((String) session.getAttribute("customerNick"));    //사용할 정보만 주석 해제 닉
//        customer.setCustomerGender((String) session.getAttribute("customerGender"));
//        customer.setCustomerAge((Integer) session.getAttribute("customerAge"));
//        customer.setCustomerFollow((String) session.getAttribute("customerFollow"));
//        customer.setCustomerGrade((Integer) session.getAttribute("customerGrade")); //사용할 정보만 주석 해제 등급
//        customer.setCustomerBan((String) session.getAttribute("customerBan"));


        ModelAndView mv = new ModelAndView("reservation/reservationCus");

        List<ReservationDTO> reservationList = leeService.selectReservation(ceoIdx);  // 가게 전체 예약 정보 들고오기(reservationStat만 들고와도 괜찮)

        mv.addObject("reservationList", reservationList);
//        mv.addObject("customer", customer);    // 실제 예약한 고객의 정보 가져오기, 세션에서 정보(닉, 등급) 가져오기

        return mv;
    }
}

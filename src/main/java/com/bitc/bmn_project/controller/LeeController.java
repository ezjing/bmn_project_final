package com.bitc.bmn_project.controller;

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
//@RequestMapping("/bmn2")
public class LeeController {

    @Autowired
    private LeeService leeService;

//    @RequestMapping("/")
//    public String index() throws Exception {
//        return "index";
//    }

    // 받기 전에 상세 페이지에서 get으로 가게 테이블 정보(ceoIdx, ceoName)가 제공되어야함 제공된거 타임리프로 input hidden에 꼭 넣어야함, mv.addObject(가게정보 dto) 이런식
    // 고객 정보가 입력된 view 제공
    @RequestMapping(value = "/bmn/reservationCus", method = RequestMethod.GET)  // ceoIdx 통합할때 지워버리기
    public ModelAndView reservationCusView(@RequestParam int ceoIdx, @RequestParam String ceoStore, HttpServletRequest req, HttpServletResponse resp) throws Exception {
//        CustomerDTO customer = new CustomerDTO(); // 로그인 정보 쓸때 주석 해제
//        HttpSession session = req.getSession();   // 로그인 정보 쓸때 주석 해제

//        customer.setCustomerIdx((Integer) session.getAttribute("customerIdx"));
//        customer.setCustomerId((String) session.getAttribute("customerId"));
//        customer.setCustomerPw((String) session.getAttribute("customerPw"));
//        customer.setCustomerName((String) session.getAttribute("customerName"));
//        customer.setCustomerNick((String) session.getAttribute("customerNick"));
//        customer.setCustomerGender((String) session.getAttribute("customerGender"));
//        customer.setCustomerAge((Integer) session.getAttribute("customerAge"));
//        customer.setCustomerFollow((String) session.getAttribute("customerFollow"));
//        customer.setCustomerGrade((Integer) session.getAttribute("customerGrade"));
//        customer.setCustomerBan((String) session.getAttribute("customerBan"));


        ModelAndView mv = new ModelAndView("reservation/reservationCus");

        List<ReservationDTO> reservationList = leeService.selectReservation(ceoIdx);  // 가게 전체 예약 정보 들고오기(reservationStat만 들고와도 괜찮)

        mv.addObject("reservationList", reservationList);
        mv.addObject("ceoIdx", ceoIdx);
        mv.addObject("ceoStore", ceoStore);
//        mv.addObject("customer", customer);    // 실제 예약한 고객의 정보 가져오기, 세션에서 정보(닉, 등급) 가져오기

        return mv;
    }

    // 가게, 날짜 정보에 따른 예약 여부 제공
    @ResponseBody
    @RequestMapping(value = "/bmn/reservationCus/ceoIdx={ceoIdx}", method = RequestMethod.POST)
    public Object reservationCusAjax(@RequestParam("reservationDate") String reservationDate, @PathVariable int ceoIdx) throws Exception {    // 날짜정보 제대로 넘어옴!
        List<ReservationDTO> reservationList = new ArrayList<>();   // 전체 정보
        reservationList = leeService.selectReservation(ceoIdx);
        List<Integer> dayTimeList = new ArrayList<>();  // time만

        for (ReservationDTO reservation : reservationList) {
            if (reservationDate.equals(reservation.getReservationDate())) {
                int time = reservation.getReservationTime();   // 날짜 하나 들고오기

                dayTimeList.add(time);
            }
        }
        return dayTimeList; // 제대로 넘어감
    }

    // 고객의 예약 정보 입력 process 및 view 제공(get 방식으로 view만 먼저 구현하는 방법도 생각해보기)
    @RequestMapping(value = "/bmn/reservationCus/ceoIdx={ceoIdx}/customerIdx={customerIdx}", method = RequestMethod.POST)   // 주소에 &가 들어가면 안됨, 왜 POST가 안되지? -> form 에 안넣었었음
    public String reservationCusProcess(ReservationDTO reservation, HttpServletResponse resp) throws Exception {
//        ModelAndView mv = new ModelAndView("reservation/reservationCus");

        leeService.insertReservation(reservation);  // 예약 정보 입력(동일 고객 같은날 중복 예약 불가하도록 해야함), DTO에 있는 정보때문에 자꾸 엉뚱한 고객 정보가 얻어져 오는거였음 로그인으로 해결해야할것으로 보임, 이렇게 하니 어노테이션 즉 주소에 있는 데이터를 잘 받아옴. 디버깅 확인 완료

//        int customerIdx = reservation.getCustomerIdx();
        int ceoIdx = reservation.getCeoIdx();    // @PathVariable 안쓴다면 이렇게 들고와야함
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

        return "redirect:/bmn/viewDetail/" + ceoIdx;  // 리다이렉트는 A에 있었을때 A에서 볼일 끝내고 B로 넘어갈때 쓰는 느낌, 나중에 홈으로 가는 페이지 넣기
    }





    /////////////////////////////// 여기서부터 사장 /////////////////////////////////




    // 마찬가지로 이전 페이지에서 ceoIdx 보내줘야함. 뷰 파일에서 타임리프로 쓸 수 있게끔.(그렇게 되면 데이터 타입 String -> ModelAndView)
    @RequestMapping(value = "/bmn/reservationCeo", method = RequestMethod.GET)
    public String reservationCeoView() throws Exception { // 고객 페이지 View 타입 String 으로 수정할 것, resp 삭제(수정 시 주석 삭제)
        
        return "reservation/reservationCeo";
    }

    // 날짜에 따른 데이터 ajax 통신
    @ResponseBody
    @RequestMapping(value = "/bmn/reservationCeo/reservationCeoDateInfo", method = RequestMethod.POST)
    public Object reservationCeoDateInfo(@RequestParam("reservationDate") String reservationDate, @RequestParam int ceoIdx) throws Exception {    // 날짜정보 제대로 넘어옴!
        List<ReservationDTO> dateList = leeService.selectDateReservation(ceoIdx, reservationDate);    // 해당날짜 예약 정보 가져오기

        return dateList; // 예약 시간 전송
    }

    // 날짜 + 시간에 따른 데이터 ajax 통신
    @ResponseBody
    @RequestMapping(value = "/bmn/reservationCeo/reservationCeoTimeInfo", method = RequestMethod.POST)
    public Object reservationCeoTimeInfo(@RequestParam String reservationDate, @RequestParam int ceoIdx, @RequestParam int reservationTime) throws Exception {
        ReservationDTO reservation = leeService.selectTimeReservation(reservationDate, ceoIdx, reservationTime);

        return reservation;
    }

    // 승인
    @ResponseBody
    @RequestMapping(value = "/bmn/reservationCeo/reservationConfirm", method = RequestMethod.PUT)
    public Object reservationConfirm(@RequestParam String reservationDate, @RequestParam int ceoIdx, @RequestParam int reservationTime) throws Exception {
        int time = leeService.reservationConfirm(reservationDate, ceoIdx, reservationTime);

        return time;
    }

    // 거절
    @ResponseBody
    @RequestMapping(value = "/bmn/reservationCeo/reservationRefuse", method = RequestMethod.POST)
    public Object reservationRefuse(@RequestParam String reservationDate, @RequestParam int ceoIdx, @RequestParam int reservationTime) throws Exception {
        int time = leeService.reservationRefuse(reservationDate, ceoIdx, reservationTime);

        return time;
    }




    /////////////////////////////// 마이페이지 /////////////////////////////////// 집에서 테스트






    @RequestMapping(value = "/bmn/myPageCus")
    public String myPageView() throws Exception {

        return "myPage/myPageCus";
    }

    @ResponseBody
    @RequestMapping(value = "/bmn/myPageCus/reservation", method = RequestMethod.POST)
    public Object myPageCusReservation(@RequestParam int customerIdx) throws Exception {
        List<ReservationDTO> reservation = leeService.myPageReservation(customerIdx);

        return reservation;
    }

    @ResponseBody
    @RequestMapping(value = "/bmn/myPageCus/follow", method = RequestMethod.POST)
    public Object myPageCusFollow(@RequestParam int customerIdx) throws Exception {

        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/bmn/myPageCus/review", method = RequestMethod.POST)
    public Object myPageCusView(@RequestParam int customerIdx) throws Exception {
        List<ReservationDTO> reservation = leeService.myPageReview(customerIdx);

        return reservation;
    }
}

package com.bitc.bmn_project.mapper;

import com.bitc.bmn_project.DTO.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
public interface LeeMapper {
    void insertReservation(ReservationDTO reservation) throws Exception;

    List<ReservationDTO> selectReservation(int ceoIdx) throws Exception;

    ReservationDTO getReservationInfo(int customerIdx, int ceoIdx, String reservationDate, int reservationTime) throws Exception;

    List<ReservationDTO> selectDateReservation(@Param("ceoIdx") int ceoIdx, @Param("reservationDate") String reservationDate) throws Exception;

    ReservationDTO selectTimeReservation(@Param("reservationDate") String reservationDate, @Param("ceoIdx") int ceoIdx, @Param("reservationTime") int reservationTime) throws Exception;

    void reservationConfirm(String reservationDate, int ceoIdx, int reservationTime) throws Exception;

    void reservationRefuse(String reservationDate, int ceoIdx, int reservationTime) throws Exception;

    List<ReservationDTO> myPageReservation(int customerIdx) throws Exception;

    String myPageFollow(int customerIdx) throws Exception;

    void myPageCusFollowCancel(@Param("ceoStore") String ceoStore, @Param("customerIdx") int customerIdx) throws Exception; // map 타입(매개변수가 2개)일땐 @Param을 생활화 하자...

    List<ReviewDTO> myPageReview(int customerIdx) throws Exception;

    List<QuestionDTO> myPageQuestion(int customerIdx) throws Exception;

    void myPageCusOut(int customerIdx) throws Exception;

    void myPageCusChange(CustomerDTO customer) throws Exception;

    CustomerDTO customerInfo(int customerIdx) throws Exception;

    List<ReservationDTO> myPageCeoReservation(int ceoIdx) throws Exception;

    List<ReviewDTO> myPageCeoReview(int ceoIdx) throws Exception;

    List<QuestionDTO> myPageCeoQuestion(int ceoIdx) throws Exception;

    void myPageCeoOut(int ceoIdx) throws Exception;

    void myPageCeoChange(CeoDTO ceo) throws Exception;

    CeoDTO ceoInfo(int ceoIdx) throws Exception;
}

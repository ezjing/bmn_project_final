package com.bitc.bmn_project.service;

import com.bitc.bmn_project.DTO.*;

import java.util.List;

public interface LeeService {
    void insertReservation(ReservationDTO reservation) throws Exception;

    List<ReservationDTO> selectReservation(int ceoIdx) throws Exception;

    ReservationDTO getReservationInfo(int customerIdx, int ceoIdx, String reservationDate, int reservationTime) throws Exception;

    List<ReservationDTO> selectDateReservation(int ceoIdx, String reservationDate) throws Exception;

    ReservationDTO selectTimeReservation(String reservationDate, int ceoIdx, int reservationTime) throws Exception;

    int reservationConfirm(String reservationDate, int ceoIdx, int reservationTime) throws  Exception;

    int reservationRefuse(String reservationDate, int ceoIdx, int reservationTime) throws  Exception;

    List<ReservationDTO> myPageReservation(int customerIdx) throws Exception;

    String myPageFollow(int customerIdx) throws Exception;

    void myPageCusFollowCancel(String ceoStore, int customerIdx) throws Exception;

    List<ReviewDTO> myPageReview(int customerIdx) throws Exception;

    List<QuestionDTO> myPageQuestion(int customerIdx) throws Exception;

    void myPageCusOut(int customerIdx) throws Exception;

    CustomerDTO myPageCusChange(CustomerDTO customer) throws Exception;

    List<ReservationDTO> myPageCeoReservation(int ceoIdx) throws Exception;

    List<ReviewDTO> myPageCeoReview(int ceoIdx) throws Exception;

    List<QuestionDTO> myPageCeoQuestion(int ceoIdx) throws Exception;

    void myPageCeoOut(int ceoIdx) throws Exception;

    CeoDTO myPageCeoUpdate(CeoDTO ceo) throws Exception;

    CeoDTO myPageCeoStoreUpdate(CeoDTO ceo) throws Exception;
}

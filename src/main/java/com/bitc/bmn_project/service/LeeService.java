package com.bitc.bmn_project.service;

import com.bitc.bmn_project.DTO.ReservationDTO;

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

    List<ReservationDTO> myPageReview(int customerIdx) throws Exception;
}

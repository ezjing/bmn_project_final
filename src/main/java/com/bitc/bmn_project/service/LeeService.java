package com.bitc.bmn_project.service;

import com.bitc.bmn_project.DTO.ReservationDTO;

import java.util.List;

public interface LeeService {
    void insertReservation(ReservationDTO reservation) throws Exception;

    List<ReservationDTO> selectReservation(int ceoIdx) throws Exception;

    ReservationDTO getReservationInfo(int customerIdx, int ceoIdx, String reservationDate, int reservationTime) throws Exception;
}

package com.bitc.bmn_project.mapper;

import com.bitc.bmn_project.DTO.ReservationDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LeeMapper {
    void insertReservation(ReservationDTO reservation) throws Exception;

    List<ReservationDTO> selectReservation(int ceoIdx) throws Exception;

    ReservationDTO getReservationInfo(int customerIdx, int ceoIdx, String reservationDate, int reservationTime) throws Exception;

    List<ReservationDTO> selectDateReservation(int ceoIdx, String reservationDate) throws Exception;

    ReservationDTO selectTimeReservation(String reservationDate, int ceoIdx, int reservationTime) throws Exception;

    void reservationConfirm(String reservationDate, int ceoIdx, int reservationTime) throws Exception;

    void reservationRefuse(String reservationDate, int ceoIdx, int reservationTime) throws Exception;

    List<ReservationDTO> myPageReservation(int customerIdx) throws Exception;

    List<ReservationDTO> myPageReview(int customerIdx) throws Exception;
}

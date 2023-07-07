package com.bitc.bmn_project.mapper;

import com.bitc.bmn_project.DTO.ReservationDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LeeMapper {
    void insertReservation(ReservationDTO reservation) throws Exception;

    List<ReservationDTO> selectReservation(int ceoIdx) throws Exception;

    ReservationDTO getReservationInfo(int customerIdx, int ceoIdx, String reservationDate, int reservationTime) throws Exception;
}

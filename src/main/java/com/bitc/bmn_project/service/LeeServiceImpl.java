package com.bitc.bmn_project.service;

import com.bitc.bmn_project.DTO.ReservationDTO;
import com.bitc.bmn_project.mapper.LeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeeServiceImpl implements LeeService{
    @Autowired
    private LeeMapper leeMapper;

    @Override
    public void insertReservation(ReservationDTO reservation) throws Exception {
        leeMapper.insertReservation(reservation);
    }

    @Override
    public List<ReservationDTO> selectReservation(int ceoIdx) throws Exception {
        return leeMapper.selectReservation(ceoIdx);
    }

    @Override
    public ReservationDTO getReservationInfo(int customerIdx, int ceoIdx,  String reservationDate,int reservationTime) throws Exception {
        return leeMapper.getReservationInfo(customerIdx, ceoIdx, reservationDate, reservationTime);
    }
}

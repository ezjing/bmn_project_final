package com.bitc.bmn_project.service;

import com.bitc.bmn_project.DTO.*;
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

    @Override
    public List<ReservationDTO> selectDateReservation(int ceoIdx, String reservationDate) throws Exception {
        return leeMapper.selectDateReservation(ceoIdx, reservationDate);
    }

    @Override
    public ReservationDTO selectTimeReservation(String reservationDate, int ceoIdx, int reservationTime) throws Exception {
        return leeMapper.selectTimeReservation(reservationDate, ceoIdx, reservationTime);
    }

    @Override
    public int reservationConfirm(String reservationDate, int ceoIdx, int reservationTime) throws Exception {
        leeMapper.reservationConfirm(reservationDate, ceoIdx, reservationTime);

        int time = reservationTime;

        return time;
    }

    @Override
    public int reservationRefuse(String reservationDate, int ceoIdx, int reservationTime) throws Exception {
        leeMapper.reservationRefuse(reservationDate, ceoIdx, reservationTime);

        int time = reservationTime;

        return time;
    }

    @Override
    public List<ReservationDTO> myPageReservation(int customerIdx) throws Exception {
        return leeMapper.myPageReservation(customerIdx);
    }

    @Override
    public String myPageFollow(int customerIdx) throws Exception {
        return leeMapper.myPageFollow(customerIdx);
    }

    @Override
    public void myPageCusFollowCancel(String ceoStore, int customerIdx) throws Exception {
        leeMapper.myPageCusFollowCancel(ceoStore, customerIdx);
    }

    @Override
    public List<ReviewDTO> myPageReview(int customerIdx) throws Exception {
        return leeMapper.myPageReview(customerIdx);
    }

    @Override
    public List<QuestionDTO> myPageQuestion(int customerIdx) throws Exception {
        return leeMapper.myPageQuestion(customerIdx);
    }

    @Override
    public void myPageCusOut(int customerIdx) throws Exception {
        leeMapper.myPageCusOut(customerIdx);
    }

    @Override
    public CustomerDTO myPageCusChange(CustomerDTO customer) throws Exception {
        leeMapper.myPageCusChange(customer);

        return leeMapper.customerInfo(customer.getCustomerIdx());
    }

    @Override
    public List<ReservationDTO> myPageCeoReservation(int ceoIdx) throws Exception {
        return leeMapper.myPageCeoReservation(ceoIdx);
    }

    @Override
    public List<CustomerDTO> myPageCeoFollowerList(String ceoStore) throws Exception {
        List<CustomerDTO> followerList = leeMapper.myPageCeoFollowerList(ceoStore);
        return followerList;
    }

    @Override
    public void myPageCeoFollowerDelete(String ceoStore, int customerIdx) throws Exception {
        leeMapper.myPageCeoFollowerDelete(ceoStore, customerIdx);
    }

    @Override
    public List<ReviewDTO> myPageCeoReview(int ceoIdx) throws Exception {
        return leeMapper.myPageCeoReview(ceoIdx);
    }

    @Override
    public List<QuestionDTO> myPageCeoQuestion(int ceoIdx) throws Exception {
        return leeMapper.myPageCeoQuestion(ceoIdx);

    }

    @Override
    public void myPageCeoOut(int ceoIdx) throws Exception {
        leeMapper.myPageCeoOut(ceoIdx);
    }

    @Override
    public CeoDTO myPageCeoUpdate(CeoDTO ceo) throws Exception {
        leeMapper.myPageCeoUpdate(ceo);

        return leeMapper.ceoInfo(ceo.getCeoIdx());
    }

    @Override
    public CeoDTO myPageCeoStoreUpdate(CeoDTO ceo) throws Exception {
        leeMapper.myPageCeoStoreUpdate(ceo);

        return leeMapper.ceoInfo(ceo.getCeoIdx());
    }
}

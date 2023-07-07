package com.bitc.bmn_project.DTO;

import lombok.Data;

@Data
public class ReservationDTO {
  private int reservationIdx;
  private int customerIdx;
  private String customerName;
  private int ceoIdx;
  private int reservationStat;
  private int reservationPeople;
  private String reservationContents;
  private String reservationDate;
  private int reservationTime;
}

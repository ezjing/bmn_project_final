package com.bitc.bmn_project.DTO;

import lombok.Data;

@Data
public class CustomerDTO {
    private int customerIdx;
    private String customerId;
    private String customerPw;
    private String customerName;
    private String customerNick;
    private String customerGender;
    private int customerAge;
    private String customerFollow;
    private int customerGrade;
    private String customerBan;
}
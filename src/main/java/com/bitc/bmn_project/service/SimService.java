package com.bitc.bmn_project.service;

import com.bitc.bmn_project.DTO.CeoDTO;
import com.bitc.bmn_project.DTO.CustomerDTO;
import com.bitc.bmn_project.DTO.ReviewDTO;
import com.bitc.bmn_project.DTO.ReviewTagDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SimService {

    int idCheckCustomer(String userId) throws Exception;
    int idCheckCeo(String userId) throws Exception;

    void signUpCustomer(CustomerDTO customer) throws Exception;

    void signUpCeo(CeoDTO ceo) throws Exception;

    void addStore(CeoDTO store, MultipartFile mainImage, MultipartFile thumbnail, List<MultipartFile> files) throws Exception;

    int isCustomer(String userId, String userPw) throws Exception;

    int isCeo(String userId, String userPw) throws Exception;

    int isUser(String userId) throws Exception;

    CustomerDTO getCustomerInfo(String userId) throws Exception;

    CeoDTO getCeoInfo(String userId) throws Exception;

    int getGrade(String userId) throws Exception;

    String getStoreName(int ceoIdx) throws Exception;

    void reviewWrite(ReviewDTO review) throws Exception;

    int getReviewIdx() throws Exception;

    void reviewWriteTag(ReviewTagDTO reviewTag) throws Exception;
}

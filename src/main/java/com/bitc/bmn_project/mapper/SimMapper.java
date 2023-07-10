package com.bitc.bmn_project.mapper;

import com.bitc.bmn_project.DTO.CeoDTO;
import com.bitc.bmn_project.DTO.CustomerDTO;
import com.bitc.bmn_project.DTO.ReviewDTO;
import com.bitc.bmn_project.DTO.ReviewTagDTO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SimMapper {

    int idCheckCustomer(String userId) throws Exception;

    int idCheckCeo(String userId) throws Exception;

    void signUpCustomer(CustomerDTO customer) throws Exception;

    void signUpCeo(CeoDTO ceo) throws Exception;

    void addStore(CeoDTO store) throws Exception;

    int isCustomer(@Param("userId") String userId, @Param("userPw") String userPw) throws Exception;

    int isCeo(@Param("userId") String userId, @Param("userPw") String userPw) throws Exception;

    int isUser(String userId) throws Exception;

    CustomerDTO getCustomerInfo(String userId) throws Exception;

    CeoDTO getCeoInfo(String userId) throws Exception;

    int getGrade(String userId) throws Exception;

    String getStoreName(int ceoIdx) throws Exception;

    void reviewWrite(ReviewDTO review) throws Exception;

    int getReviewIdx() throws Exception;

    void reviewWriteTag(ReviewTagDTO reviewTag) throws Exception;

    List<CeoDTO> getStoreList() throws Exception;

    void storeApprove(int targetIdx, int mode) throws Exception;

    Page<CustomerDTO> getCustomerList() throws Exception;

    void changeCustomerGrade(int targetIdx, int grade) throws Exception;

    void customerBan(int targetIdx) throws Exception;


//    Page<CustomerDTO> getPageList() throws Exception;
}

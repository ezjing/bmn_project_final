package com.bitc.bmn_project.service;

import com.bitc.bmn_project.DTO.CeoDTO;
import com.bitc.bmn_project.DTO.CustomerDTO;
import com.bitc.bmn_project.DTO.ReviewDTO;
import com.bitc.bmn_project.DTO.ReviewTagDTO;
import com.bitc.bmn_project.common.FileUtils;
import com.bitc.bmn_project.mapper.SimMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class SimServiceImpl implements SimService {

    @Autowired
    private SimMapper simMapper;

    @Autowired
    private FileUtils fileUtils;

    @Override
    public int idCheckCustomer(String userId) throws Exception {

        return simMapper.idCheckCustomer(userId);
    }

    @Override
    public int idCheckCeo(String userId) throws Exception {

        return simMapper.idCheckCeo(userId);
    }

    @Override
    public void signUpCustomer(CustomerDTO customer) throws Exception {
        simMapper.signUpCustomer(customer);
    }

    @Override
    public void signUpCeo(CeoDTO ceo) throws Exception {
        simMapper.signUpCeo(ceo);
    }

    @Override
    public void addStore(CeoDTO store, MultipartFile mainImage, MultipartFile thumbnail, List<MultipartFile> files) throws Exception {

        // 파싱을 해서 가져오는 정보는 String 타입의 파일 저장경로 및 실질적 파일 저장
        String mainImagePath = fileUtils.parseFileInfo(mainImage);
        String thumbnailPath = fileUtils.parseFileInfo(thumbnail);
        List<String> filesPath = fileUtils.parseFileInfo(files);

        // 파싱해온 정보(파일의 경로만)를 CeoDTO 타입의 객체에 값 세팅
        store.setCeoMainImg(mainImagePath);
        store.setCeoThumbnailImg(thumbnailPath);

        store.setCeoMenuImg1(filesPath.get(0));
        store.setCeoMenuImg2(filesPath.get(1));
        store.setCeoMenuImg3(filesPath.get(2));
        store.setCeoMenuImg4(filesPath.get(3));
        store.setCeoMenuImg5(filesPath.get(4));
        store.setCeoMenuImg6(filesPath.get(5));


        System.out.println(mainImagePath);
        System.out.println(thumbnailPath);
        System.out.println(store);

        simMapper.addStore(store);

    }

    @Override
    public int isCustomer(String userId, String userPw) throws Exception {

        return simMapper.isCustomer(userId, userPw);
    }

    @Override
    public int isCeo(String userId, String userPw) throws Exception {

        return simMapper.isCeo(userId, userPw);
    }

    @Override
    public int isUser(String userId) throws Exception {
        return simMapper.isUser(userId);
    }

    @Override
    public CustomerDTO getCustomerInfo(String userId) throws Exception {
        return simMapper.getCustomerInfo(userId);
    }

    @Override
    public CeoDTO getCeoInfo(String userId) throws Exception {
        return simMapper.getCeoInfo(userId);
    }

    @Override
    public int getGrade(String userId) throws Exception {
        return simMapper.getGrade(userId);
    }

    @Override
    public String getStoreName(int ceoIdx) throws Exception {
        return simMapper.getStoreName(ceoIdx);
    }

    @Override
    public void reviewWrite(ReviewDTO review) throws Exception {
        simMapper.reviewWrite(review);
    }

    @Override
    public int getReviewIdx() throws Exception {

        return simMapper.getReviewIdx();
    }

    @Override
    public void reviewWriteTag(ReviewTagDTO reviewTag) throws Exception {

        // 전달되는 태그의 값을 변경 해줘야함 on - > Y
//        simMapper.reviewWriteTag(reviewTag);
    }

}

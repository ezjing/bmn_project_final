package com.bitc.bmn_project.common;


import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


// @Bean과 @Component는 동일하게 스프링프레임워크에서 객체를 생성하여 사용하고 관리하는 라이브러리라는 의미 어노테이션
// @Bean : 스프링프레임워크 및 각종 서드파티 회사에서 제공하는 미리 생성해 놓은 라이브러리
// @Component : 사용자가 직접 생성한 라이브러리

@Component
public class FileUtils {

    // 파일 1개에 대한 파싱 처리
    public String parseFileInfo(MultipartFile uploadFile) throws Exception {

        // 스프링프레임워크가 제공하는 ObjectUtils를 사용하여 업로드된 파일이 있는지 확인, 없으면 true
        if (ObjectUtils.isEmpty(uploadFile)) {
            // 파일이 없을 경우 바로 종료
            return null;
        }

        // 시간 정보 패턴 생성
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // 현재 타임존을 기준으로 하여 현재 시간을 가져오기
        ZonedDateTime current = ZonedDateTime.now();

        // 경로 생성, 현재 타임존 기준 시간을 패턴에 맞게 설정하여 출력
//        String path = "images/" + current.format(format);
        // 현재 StandardServletMultipartResolver 사용 시 전체 경로가 아니면 파일 복사 시 오류가 발생함
        String path = "C:\\smart505\\bmn_project\\src\\main\\resources\\static\\images\\" + current.format(format);
        // 자바의 File 클래스 객체 생성, 위에서 생성한 경로로 생성
        File file = new File(path);

        // 지정한 경로가 존재하는지 확인, 있으면 true, 없으면 false
        if (file.exists() == false) {
            // 없을 경우 폴더 생성
            file.mkdirs();
        }

        // 업로드된 파일 정보에서 파일 이름 목록 가져옴

        String newFileName = null; // 새 파일명을 저장할 변수
        String originalFileExtension = null; // 원본 파일의 확장자명을 저장할 변수
        String contentType = uploadFile.getContentType(); // 파일의 확장자명을 저장할 변수
        String storedFileName = null;


        // 파일 이름 목록 중 다음 내용이 존재하는지 확인

        // 파일 정보가 비었는지 확인, 비었으면 true, 존재하면 false
        if (uploadFile.isEmpty() == false) {
            // 확장자명 가져오기
            if (ObjectUtils.isEmpty(contentType)) {
            } else {
                // 확장자명에 따라 변수에 확장자명 저장
                if (contentType.contains("image/jpeg")) {
                    originalFileExtension = ".jpg";
                } else if (contentType.contains("image/png")) {
                    originalFileExtension = ".png";
                } else if (contentType.contains("image/gif")) {
                    originalFileExtension = ".gif";
                } else {
                }
            }

            // 현재 시간을 기준으로 새 파일명 생성, nanoTime()을 사용하여 동일한 이름이 나올 수 없도록 함,
            // 위에서 생성한 확장자명하고 문자열을 연결함
            newFileName = Long.toString(System.nanoTime()) + originalFileExtension;

            // 파일이름을 합하여 파일을 저장할 전체 경로를 생성함
            storedFileName = path + "/" + newFileName;
            //

            // 자바의 File 클래스 객체를 생성한 파일이름 및 경로로 생성함
            file = new File(path + "/" + newFileName);
            // 서버의 지정된 위치에 실제 파일을 복사함
            uploadFile.transferTo(file);
        }


        // 파일 정보 목록을 반환
        return storedFileName;
    }


    // 파일 여러개의 리스트에 대한 파싱 처리
    public List<String> parseFileInfo(List<MultipartFile> files) throws Exception{

        List<String> filesPath = new ArrayList<>();

        // 이터레이터 사용 고려

        for (MultipartFile uploadFile : files) {

            // 스프링프레임워크가 제공하는 ObjectUtils를 사용하여 업로드된 파일이 있는지 확인, 없으면 true
            if (ObjectUtils.isEmpty(uploadFile)) {
                // 파일이 없을 경우 바로 종료
                return null;
            }

            // 시간 정보 패턴 생성
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            // 현재 타임존을 기준으로 하여 현재 시간을 가져오기
            ZonedDateTime current = ZonedDateTime.now();

            // 경로 생성, 현재 타임존 기준 시간을 패턴에 맞게 설정하여 출력
//        String path = "images/" + current.format(format);
            // 현재 StandardServletMultipartResolver 사용 시 전체 경로가 아니면 파일 복사 시 오류가 발생함
            String path = "C:\\smart505\\bmn_project\\src\\main\\resources\\static\\images\\" + current.format(format);
            // 자바의 File 클래스 객체 생성, 위에서 생성한 경로로 생성
            File file = new File(path);

            // 지정한 경로가 존재하는지 확인, 있으면 true, 없으면 false
            if (file.exists() == false) {
                // 없을 경우 폴더 생성
                file.mkdirs();
            }

            // 업로드된 파일 정보에서 파일 이름 목록 가져옴

            String newFileName = null; // 새 파일명을 저장할 변수
            String originalFileExtension = null; // 원본 파일의 확장자명을 저장할 변수
            String contentType = uploadFile.getContentType(); // 파일의 확장자명을 저장할 변수
            String storedFileName = null;


            // 파일 이름 목록 중 다음 내용이 존재하는지 확인

            // 파일 정보가 비었는지 확인, 비었으면 true, 존재하면 false
            if (uploadFile.isEmpty() == false) {
                // 확장자명 가져오기
                if (ObjectUtils.isEmpty(contentType)) {
                } else {
                    // 확장자명에 따라 변수에 확장자명 저장
                    if (contentType.contains("image/jpeg")) {
                        originalFileExtension = ".jpg";
                    } else if (contentType.contains("image/png")) {
                        originalFileExtension = ".png";
                    } else if (contentType.contains("image/gif")) {
                        originalFileExtension = ".gif";
                    } else {
                    }
                }

                // 현재 시간을 기준으로 새 파일명 생성, nanoTime()을 사용하여 동일한 이름이 나올 수 없도록 함,
                // 위에서 생성한 확장자명하고 문자열을 연결함
                newFileName = Long.toString(System.nanoTime()) + originalFileExtension;

                // 파일이름을 합하여 파일을 저장할 전체 경로를 생성함
                storedFileName = path + "/" + newFileName;
                //

                // 자바의 File 클래스 객체를 생성한 파일이름 및 경로로 생성함
                file = new File(path + "/" + newFileName);
                // 서버의 지정된 위치에 실제 파일을 복사함
                uploadFile.transferTo(file);
            }

            filesPath.add(storedFileName);
        } // 여기까지가 for문

        return filesPath;
    }
}

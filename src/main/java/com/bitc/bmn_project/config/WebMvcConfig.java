package com.bitc.bmn_project.config;

import com.bitc.bmn_project.interceptor.LoginCheck;
import jakarta.servlet.MultipartConfigElement;
import lombok.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Bean
    public MultipartResolver multipartResolver() {
        StandardServletMultipartResolver multipartResolver = new StandardServletMultipartResolver();

        return new StandardServletMultipartResolver();
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // 업로드 파일의 크기 설정
        factory.setMaxRequestSize(DataSize.ofBytes(5 * 1024 * 1024));
        factory.setMaxFileSize(DataSize.ofBytes(5 * 1024 * 1024));

        return factory.createMultipartConfig();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 인터셉터 파일 설정
//        registry.addInterceptor(new LoginCheck())
//                // 인터셉터가 동작될 컨트롤러 URL 설정(bmn 밑의 모든 페이지)
//                .addPathPatterns("/bmn/*")
//                // 인터셉터 동작에서 제외할 URL 설정
//                .excludePathPatterns("/bmn1/*")
//                .excludePathPatterns("/bmn2/*")
//                .excludePathPatterns("/bmn3/*")
//                .excludePathPatterns("/bmn4/*")
//                .excludePathPatterns("/bmn/*")
//                .excludePathPatterns("/bmn/login")
//                .excludePathPatterns("/bmn/logOut")
//
//                .excludePathPatterns("/bmn/signUp/customer")
//                .excludePathPatterns("/bmn/signUp/customer/signup")
//
//                .excludePathPatterns("/bmn/sigunUp/ceo")
//                .excludePathPatterns("/bmn/sigunUp/ceo/signup")
//                .excludePathPatterns("/bmn/sigunUp/idCheck")
//
//                // 나중에 합칠때 빼줘야함
//                // (사장이 로그인 했을때)
//                .excludePathPatterns("/bmn/ceoStore")
//                .excludePathPatterns("/bmn/ceoStore/popup")
//                .excludePathPatterns("/bmn/ceoStore/addStore")
//
//                // (리뷰 작성시)
//                .excludePathPatterns("/bmn/review")
//                .excludePathPatterns("/bmn/review/write");


    }

//    @Value("${resource.img.location1}") // value에 있는 주소의 사진이 imgLocation에 대입됨
//    private String imgLocation1;
//
//    @Value("${resource.img.location2}")
//    private String imgLocation2;

    // 외부 폴더를 스프링프레임워크 내부 리소스 폴더로 등록
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // addResourceHandler() : 스프링프레임워크 내부 리소스 폴더명 지정
        // ** : 모든 하위 폴더 및 모든 파일이라는 의미
        // addResourceLocations() : 실제 물리적 폴더 경로를 설정
        // 여러개의 폴더를 등록하고자 할 경우 ',' 로 구분하여 경로 입력
        // 윈도우와 리눅스 및 맥OS의 경로 입력 방식이 다름
        // 윈도우는 file:///경로명 (/3개)
        // 리눅스, 맥OS 는 file://경로명 (/2개)
        // application.properties 설정 파일에 경로명을 입력하고 Config 파일에서 경로 값을 불러와서 사용하는 방식이 존재함
        // @Value : application.properties 설정 파일에 존재하는 내용을 가져와서 변수에 저장하여 사용하게 해주는 어노테이션

        // 기본방식
//        registry.addResourceHandler("/images/**").addResourceLocations("file:///C:/smart505/images/");

        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // 현재 타임존을 기준으로 하여 현재 시간을 가져오기
        ZonedDateTime current = ZonedDateTime.now();

        // 경로 2개 이상 사용 방식
        registry.addResourceHandler("/images/**").addResourceLocations("file:///C:/smart505/bmn_project/src/main/resources/static/images/" + current.format(format), "file:///C:/smart505/bmn_project/src/main/resources/static/images/**");

        // application.properties 파일 설정값을 사용 방식
//        String path1 = "file:///" + imgLocation1;
//        String path2 = "file:///" + imgLocation2;
//        registry.addResourceHandler("/img2/**").addResourceLocations(path1, path2);
    }
}

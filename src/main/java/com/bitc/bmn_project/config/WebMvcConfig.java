package com.bitc.bmn_project.config;

import com.bitc.bmn_project.interceptor.LoginCheck;
import jakarta.servlet.MultipartConfigElement;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
        registry.addInterceptor(new LoginCheck())
                // 인터셉터가 동작될 컨트롤러 URL 설정(bmn 밑의 모든 페이지)
                .addPathPatterns("/bmn/*")
                // 인터셉터 동작에서 제외할 URL 설정
                .excludePathPatterns("/bmn/")

                .excludePathPatterns("/bmn/login")
                .excludePathPatterns("/bmn/logOut")

                .excludePathPatterns("/bmn/signUp/customer")
                .excludePathPatterns("/bmn/signUp/customer/signup")

                .excludePathPatterns("/bmn/sigunUp/ceo")
                .excludePathPatterns("/bmn/sigunUp/ceo/signup")
                .excludePathPatterns("/bmn/sigunUp/idCheck")

                // 나중에 합칠때 빼줘야함
                // (사장이 로그인 했을때)
                .excludePathPatterns("/bmn/ceoStore")
                .excludePathPatterns("/bmn/ceoStore/popup")
                .excludePathPatterns("/bmn/ceoStore/addStore")

                // (리뷰 작성시)
                .excludePathPatterns("/bmn/review")
                .excludePathPatterns("/bmn/review/write");


    }
}

package com.bitc.bmn_project.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

// Spring이 제공하는 기술로써, 디스패처 서블릿(Dispatcher Servlet)이 컨트롤러를 호출하기 전과 후에 요청과 응답을 참조하거나 가공할 수 있는 기능을 제공
// Interceptor을 사용하기 위해서 HandlerInterceptor 인터페이스를 상속받음
public class LoginCheck implements HandlerInterceptor {

    // preHandle() : 지정한 컨트롤러가 동작되기 이전에 먼저 수행됨(주로 사용)
    // postHandle() : 지정한 컨트롤러가 동작 후 View 가 동작되기전에 수행됨
    // afterCompletion() : 모든 동작이 완료된 후 수행 됨
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {

        // 세션 정보 가져오기
        HttpSession session = req.getSession();

        // 화면 출력용
        System.out.println("========== Interceptor 동작 =========");
        // 세션에 저장된 정보 가져오기
        String userId = (String) session.getAttribute("userId");
        System.out.println(userId);

        // 세션 정보로 로그인 상태 확인
        if (userId == null || userId.equals("")) {
            // 비 로그인 상태
            System.out.println("\n***** Interceptor *****");
            System.out.println("비 로그인 상태");
            System.out.println((String) session.getAttribute("userId"));

            // 메인 페이지로 리다이렉트
            resp.sendRedirect("/bmn/login");
            // 인터셉터를 통과하지 못했으므로 false를 리턴, 원하는 컨트롤러에 접근 불가능
            return false;
        }
        else {
            // 로그인 상태일 경우
            System.out.println("\n***** Interceptor *****");
            System.out.println("로그인 상태");
            // 세션 사용 시간을 초기화
            session.setMaxInactiveInterval(60);

            // 인터셉터를 정상적으로 통과하여 원하는 컨트롤러에 접근이 가능함
            return true;
        }
    }
}
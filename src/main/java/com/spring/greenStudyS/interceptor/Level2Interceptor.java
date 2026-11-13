package com.spring.greenStudyS.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class Level2Interceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		// 관리자:0, 우수회원:1, 정회원:2, 준회원:3 , 비회원:999
		
		HttpSession session = request.getSession();
		int level = session.getAttribute("sLevel")==null ? 999 : (int) session.getAttribute("sLevel");
		
		// 정회원 처리 => 맴버가 아닌경우, 3인 경우 처리
		if(level > 2) {
			if(level == 999) { //맴버가 아닌 경우
				request.getRequestDispatcher("/message/memberNo").forward(request, response);
			}
			else { //맴버는 맞지만 준회원 (3) 의 경우 => 맴버 등급이 아니다 메세지
				request.getRequestDispatcher("/message/memberLevelNo").forward(request, response);
			}
			return false;
		}
		
		return true;
	}
	
}

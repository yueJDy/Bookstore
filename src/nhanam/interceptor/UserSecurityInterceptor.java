package nhanam.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import nhanam.bean.UserInfo;

public class UserSecurityInterceptor  extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest request,
				HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		UserInfo user = (UserInfo) session.getAttribute("user");
		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/dang-nhap.htm");
			return false;
		}
		return true;
	}

}

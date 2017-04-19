package org.jasig.cas.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class CasValidedCodeFilter
 */
public class CasValidedCodeFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public CasValidedCodeFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String ch=request.getParameter("ch");
		if(ch!=null&&ch.equals("on")){
			Cookie cookie0 = new Cookie("username",username);
			resp.addCookie(cookie0);
			cookie0.setMaxAge(7*24*60*60);
			
			Cookie cookie1 = new Cookie("password",password);
			resp.addCookie(cookie1);
			cookie1.setMaxAge(7*24*60*60);
			
			Cookie cookie2 = new Cookie("ch","checked");
			resp.addCookie(cookie2);
			cookie2.setMaxAge(7*24*60*60);
			
		}
		if(request.getParameter("lt")!=null&&ch==null){
			
			Cookie cookie0 = new Cookie("username","");
			resp.addCookie(cookie0);
			cookie0.setMaxAge(-1);
			
			Cookie cookie1 = new Cookie("password","");
			resp.addCookie(cookie1);
			cookie1.setMaxAge(-1);
			
			Cookie cookie2 = new Cookie("ch","");
			resp.addCookie(cookie2);
			cookie2.setMaxAge(-1);
		}
		
		chain.doFilter(request, response);
			
			
		

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

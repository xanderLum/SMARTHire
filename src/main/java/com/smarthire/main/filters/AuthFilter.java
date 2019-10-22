package com.smarthire.main.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

public class AuthFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest) request).getSession();
		String username = (String) session.getAttribute("username");
		System.out.println(username);
		if (StringUtils.isEmpty(username)) {

			System.out.println(username);
			((HttpServletResponse) response).sendRedirect("/SMARTHire");
			return;
		}
		chain.doFilter(request, response);
	}

}

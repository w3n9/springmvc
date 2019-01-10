package spittr.filter;

import javax.servlet.*;
import java.io.IOException;

public class MyFilter implements Filter {
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		System.out.println("fuck");
		System.out.println(servletRequest.getServerName());
		filterChain.doFilter(servletRequest,servletResponse);
	}

	public void destroy() {

	}
}

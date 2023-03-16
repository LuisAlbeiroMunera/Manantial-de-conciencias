package org.oasisDeVida;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


@Component
public class RequestResponseLoggingFilter implements Filter {


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("ENTRA POR FILTRO ");
		boolean rechazada = true;
		List<String> listaRecursosRol = getPrivilegios();
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		if (rechazada == true && SecurityContextHolder.getContext().getAuthentication().getName() != null) {
			for (String recurso : listaRecursosRol) {
				System.out.println("URL SOLICITADA " + req.getRequestURI() +" URL A VALIDAR "+recurso);
				if (req.getRequestURI().startsWith("/oasisdevida" + recurso)) {
					rechazada = false;
					chain.doFilter(request, response);
					return;
				}
			}
			System.out.println("URL RECHAZADA " + req.getRequestURI());
			res.sendRedirect("/oasisdevida/inicio");
		} else {
			System.out.println("URL RECHAZADA SIN LOGIN");
			res.sendRedirect("/oasisdevida/login");
		}
	}
			
		

	private List<String> getPrivilegios() {

		String[] resources = new String[] { "/include/", "/css/", "/icons", "/img/", "/js/", "/layer/","/login","/logout", "/changePassword", "/updatePassword" };

		List<String> listaPrivilegios = new ArrayList<String>();

		if (SecurityContextHolder.getContext() !=null && SecurityContextHolder.getContext().getAuthentication() != null && SecurityContextHolder.getContext().getAuthentication().getPrincipal() !=null && !SecurityContextHolder.getContext().getAuthentication().getName().equalsIgnoreCase("anonymousUser")) {
			System.out.println("ENTRA ATENTICADO :::: ");
			UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal != null && principal.getAuthorities() != null) {
				String[] privilegios = new String[principal.getAuthorities().size()];
				int inter = 0;
				for (GrantedAuthority privilege : principal.getAuthorities()) {
					if (!privilege.getAuthority().startsWith("ROLE_")) {
						privilegios[inter] = privilege.getAuthority();
						listaPrivilegios.add(privilegios[inter]);
						inter++;
					}
				}

			}
		}

		for (int i = 0; i < resources.length; i++) {
			listaPrivilegios.add(resources[i]);
		}

		return listaPrivilegios;

	}

	
	
	@Bean
	public FilterRegistrationBean<RequestResponseLoggingFilter> loggingFilter() {
		FilterRegistrationBean<RequestResponseLoggingFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new RequestResponseLoggingFilter());
		registrationBean.addUrlPatterns("/*");

		return registrationBean;
	}



	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}

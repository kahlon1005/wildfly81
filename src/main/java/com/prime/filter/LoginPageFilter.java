package com.prime.filter;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.ResourceHandler;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * To prevent user from going back to Login page if the user already logged in
 *
 * @author prime
 */
@WebFilter(urlPatterns="/Login.xhtml")
public class LoginPageFilter implements Filter {
	
    @Inject
    private transient Logger logger;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (request.getUserPrincipal() != null) { //If user is already authenticated

            String navigateString = "/user/expirepage.xhtml";
            
            String url = request.getContextPath() + navigateString;
            response.sendRedirect(url);
            logger.log(Level.INFO, "Redirect to {0}",url);
        } else {
        	if (!request.getRequestURI().startsWith(request.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER)) {
                response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                response.setHeader("Pragma", "no-cache");
                response.setDateHeader("Expires", 0); 
                logger.log(Level.INFO, "Clear Cache {0}",response.toString());
            }	
            filterChain.doFilter(servletRequest, response);
        }
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

}

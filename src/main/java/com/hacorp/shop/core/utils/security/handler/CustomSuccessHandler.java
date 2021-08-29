package com.hacorp.shop.core.utils.security.handler;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;


@Component
public class CustomSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final GrantedAuthority adminAuthority = new SimpleGrantedAuthority(
            "ROLE_ADMIN");
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        if(isAdminAuthority(authentication)){
            String targetUrl="/home/admin";
            clearAuthenticationAttributes(request);
            getRedirectStrategy().sendRedirect(request, response, targetUrl);
        }
        else{
//        	HttpSession session = request.getSession();
//            if (session != null) {
//                String redirectUrl = (String) session.getAttribute("url_prior_login");
//                if (redirectUrl != null) {
//                    // we do not forget to clean this attribute from session
//                    session.removeAttribute("url_prior_login");
//                    // then we redirect
//                    getRedirectStrategy().sendRedirect(request, response, redirectUrl);
//                } else {
//                    super.onAuthenticationSuccess(request, response, authentication);
//                }
//            } else {
//                super.onAuthenticationSuccess(request, response, authentication);
//            }
        	clearAuthenticationAttributes(request);
        	String targetUrl="/home";
            getRedirectStrategy().sendRedirect(request, response, targetUrl);
        }
    }



    protected boolean isAdminAuthority(final Authentication authentication)
    {
        return CollectionUtils.isNotEmpty(authentication.getAuthorities())
                && authentication.getAuthorities().contains(adminAuthority);
    }
}
package com.iaec.guichetautomatique.security;

import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {

        iGuichetUserDetails userDetails = (iGuichetUserDetails) authentication.getPrincipal();

        if(userDetails.hasRole("ADMIN")){
            response.sendRedirect("/admin");
        } else if(userDetails.hasRole("USER")){
            response.sendRedirect("/user");
        }


        /*System.out.println("Login : " + userDetails.getUsername());

        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        authorities.forEach(auth -> System.out.println(auth.getAuthority()));

        super.onAuthenticationSuccess(request, response, authentication);*/
    }
}

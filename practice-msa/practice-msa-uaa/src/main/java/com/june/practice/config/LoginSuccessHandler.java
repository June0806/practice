package com.june.practice.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        Object principal = authentication.getPrincipal();
        String username = "";
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        if (request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST") != null) {
            String savedRequest = request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST").toString();
            String[] params = savedRequest.split("&");
            for (int i = 0; i < params.length; i++) {
                String requestUrl = params[i].split("=")[1].split("]")[0];
                response.sendRedirect(requestUrl);
            }
        } else {
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}

package com.june.practice.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 声明需要鉴权的Url信息 配置
 */
@Configuration
@EnableOAuth2Sso
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 配置url授权规则
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling().authenticationEntryPoint(new UnAuthorizedEntryPoint("/login"));
        http.authorizeRequests()
                .antMatchers("/login", "/hello/home").permitAll()//允许路由通过，不需要授权验证
                .anyRequest().authenticated()//允许路由授权验证通过
                .and()
                .csrf().disable()//禁止伪跨域请求验证
        ;
    }

    @Bean
    public FilterRegistrationBean OAuth2ClientFilterRegistration(OAuth2ClientContextFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(filter);
        filter.setRedirectStrategy(new MyRedirectStrategy());
        registration.setOrder(-100);
        return registration;
    }

    public class MyRedirectStrategy extends DefaultRedirectStrategy {
        @Override
        public void sendRedirect(HttpServletRequest request, HttpServletResponse response, String url) throws IOException {
            String redirectUrl = calculateRedirectUrl(request.getContextPath(), url);
            redirectUrl = response.encodeRedirectURL(redirectUrl);
            String originUrl = request.getSession().getAttribute("origin-url").toString();
            redirectUrl += "&origin_url=" + originUrl;
            response.sendRedirect(redirectUrl);
        }
    }

    public class UnAuthorizedEntryPoint extends LoginUrlAuthenticationEntryPoint {

        /**
         * @param loginFormUrl URL where the login page can be found. Should either be
         *                     relative to the web-app context path (include a leading {@code /}) or an absolute
         *                     URL.
         */
        public UnAuthorizedEntryPoint(String loginFormUrl) {
            super(loginFormUrl);
        }

        @Override
        public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
            request.getSession().setAttribute("origin-url", request.getRequestURL());
            super.commence(request, response, authException);
        }
    }
}

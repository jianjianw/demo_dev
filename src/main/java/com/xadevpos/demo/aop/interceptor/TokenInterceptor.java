package com.xadevpos.demo.aop.interceptor;

import com.xadevpos.demo.Util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String token = httpServletRequest.getHeader(tokenHeader);
        if (token == null){
            httpServletRequest.setAttribute("message", "token验证失败");
            httpServletRequest.getRequestDispatcher("/admin/error").forward(httpServletRequest, httpServletResponse);
            return false;
        }else{
            String username = jwtTokenUtil.getUserNameFromToken(token);
            if(username == null){
                httpServletRequest.setAttribute("message", "token验证失败");
                httpServletRequest.getRequestDispatcher("/admin/error").forward(httpServletRequest, httpServletResponse);
            }
            httpServletRequest.setAttribute("username",username);
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}

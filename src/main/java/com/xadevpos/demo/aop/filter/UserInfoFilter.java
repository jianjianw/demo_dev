package com.xadevpos.demo.aop.filter;

import com.xadevpos.demo.Util.JwtTokenUtil;
import com.xadevpos.demo.model.Permission;
import com.xadevpos.demo.service.AdminService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebFilter(filterName = "memberInfo", urlPatterns = "/member/getInfoById/*")
public class UserInfoFilter implements Filter {

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.secret}")
    private String secret;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private AdminService adminService;



    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest hrequest = (HttpServletRequest)servletRequest;

        String token = hrequest.getHeader(tokenHeader);
        if(token != null ){
            //得到令牌
            Claims claims = jwtTokenUtil.getClaimsFromToken(token);
            Integer id = (Integer)claims.get("id");
            //根据id获取权限
            List<String> permissionList = adminService.getURLList(id.longValue());
            //判断用户是否有权限  /pms/product/index   /member/getInfoById
            boolean flag = permissionList.stream().anyMatch(uri -> uri.contains("/member/getInfoById"));
            if(flag){
                filterChain.doFilter(servletRequest, servletResponse);
            }else{
                servletRequest.setAttribute("message", "用户没有权限");
                servletRequest.getRequestDispatcher("/admin/error").forward(servletRequest, servletResponse);
            }
        }else{
            servletRequest.setAttribute("message", "token验证失败");
            servletRequest.getRequestDispatcher("/admin/error").forward(servletRequest, servletResponse);
        }
    }



    @Override
    public void destroy() {

    }
}

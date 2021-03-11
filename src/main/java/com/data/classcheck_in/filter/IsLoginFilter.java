package com.data.classcheck_in.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@WebFilter(filterName = "IsLoginFilter",urlPatterns = {"/*"})
public class IsLoginFilter implements Filter {

    private HttpServletRequest request;
    private HttpServletResponse response;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //强转成对应的子类，用request获取HttpSession
        this.request = (HttpServletRequest)servletRequest;
        this.response = (HttpServletResponse)servletResponse;
        response.setCharacterEncoding("UTF-8");
        //对指定uri进行放行
        String uri = request.getRequestURI();
        //对含有这些后缀的uri放行
        String[] passUris = new String[]{".css",".js",".ico",".png","PNG"};
        boolean pass = false;
        for(String passUri:passUris){
            if(uri.indexOf(passUri)!=-1){
                pass = true;
                break;
            }
        }
        if (uri.equals("/check") || uri.equals("/check?param=用户名或密码错误")
                || uri.equals("/check?param=请先登录") || uri.equals("/check/index.html")
                || uri.equals("/check/login")|| uri.equals("/check/toInsert")
                || uri.equals("/check/insert") || pass){
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            boolean flag = request.getSession().getAttribute("login")==null?false:true;
            if (flag){
                filterChain.doFilter(servletRequest, servletResponse);
            }else {
                //response.sendRedirect("/check?param=请先登录"); --> /check?param=????
                //解决方法，把中文用URLEncoder.encode() 方法处理
                String message = "请先登录";
                message = URLEncoder.encode(message,"UTF-8");
                response.sendRedirect("/check?param="+message);
            }
        }
        return;
    }

    @Override
    public void destroy() {

    }

}

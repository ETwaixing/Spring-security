package com.example.security;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

/**
 * 该过滤器的主要作用就是通过spring著名的IoC生成securityMetadataSource。
 * securityMetadataSource相当于本包中自定义的MyInvocationSecurityMetadataSourceService。
 * 该MyInvocationSecurityMetadataSourceService的作用提从数据库提取权限和资源，装配到HashMap中，
 * 供Spring Security使用，用于权限校验。
 *
 * Created by yonglang on 2017/4/13.
 */
@Component
public class MySecurityFilter extends AbstractSecurityInterceptor implements Filter{
    @Autowired
    private MyInvocationSecurityMetadataSourceService securityMetadataSource;
    @Autowired
    private MyAccessDecisionManager accessDecisionManager;
    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * 进行资源注入  authenticationManager--认证管理器   accessDecisionManager--决策管理器
     */
    @PostConstruct
    public void init(){
        super.setAuthenticationManager(authenticationManager);
        super.setAccessDecisionManager(accessDecisionManager);
    }
    /**
     *   登陆后，每次访问资源都通过这个拦截器拦截
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        FilterInvocation fi = new FilterInvocation( servletRequest, servletResponse, filterChain );
        invoke(fi);
    }

    /**
     *    invoke()---重要流程演示
     */
    public void invoke( FilterInvocation fi ) throws IOException, ServletException{
        //fi里面有一个被拦截的url
        //里面调用MyInvocationSecurityMetadataSource的getAttributes(Object object)这个方法获取fi对应的所有权限
        //再调用MyAccessDecisionManager的decide方法来校验用户的权限是否足够
        System.out.println("执行拦截器----------------");
        InterceptorStatusToken token = super.beforeInvocation(fi);
        try{
            //执行下一个拦截器
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        }finally{
            super.afterInvocation(token, null);
        }
    }
    @Override
    public void init( FilterConfig filterconfig ) throws ServletException{
        System.out.println("filter==============init");
    }
    @Override
    public void destroy() {
        System.out.println("filter==============destroy");
    }
    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }
    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.securityMetadataSource;
    }
}

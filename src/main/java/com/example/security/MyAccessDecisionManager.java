package com.example.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;

import java.util.Collection;

/**
 *    权限鉴定是由AccessDecisionManager接口负责的
 *    ----决策器，决定某个用户具有的角色，是否有足够的权限去访问某个资源
 *
 * Created by yonglang on 2017/4/12.
 */
public class MyAccessDecisionManager implements AccessDecisionManager {
    //该方法接收三个参数，第一个参数是包含当前用户信息的Authentication对象；第二个参数表示当前正在请求的受保护的对象，
    // 基本上来说是MethodInvocation（使用AOP）、JoinPoint（使用Aspectj）和FilterInvocation（Web请求）三种类型；第三个
    // 参数表示与当前正在访问的受保护对象的配置属性，如一个角色列表。
    //在这个方法中,需要比较与configAttributes身份验证。
    // 1,对象是一个URL、一个过滤器被这个URL找到权限配置,并传递到这里。
    // 2,检查认证在权限配置属性(configAttributes)
    // 3,如果不匹配相应的认证,AccessDeniedException。
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {

    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}

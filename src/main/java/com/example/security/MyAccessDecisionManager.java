package com.example.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;

/**
 *    权限鉴定是由AccessDecisionManager接口负责的
 *    ----决策器，决定某个用户具有的角色，是否有足够的权限去访问某个资源
 *
 *      AccessdecisionManager在Spring security中是很重要的。
 *      在验证部分简略提过了，所有的Authentication实现需要保存在一个GrantedAuthority对象数组中。
 *      这就是赋予给主体的权限。 GrantedAuthority对象通过AuthenticationManager
 *      保存到 Authentication对象里，然后从AccessDecisionManager读出来，进行授权判断。
 *
 *      Spring Security提供了一些拦截器，来控制对安全对象的访问权限，例如方法调用或web请求。
 *      一个是否允许执行调用的预调用决定，是由AccessDecisionManager实现的。
 *      这个 AccessDecisionManager 被AbstractSecurityInterceptor调用，
 *      它用来作最终访问控制的决定。 这个AccessDecisionManager接口包含三个方法：
 *
 */
@Component
public class MyAccessDecisionManager implements AccessDecisionManager {
    //该方法接收三个参数，第一个参数是包含当前用户信息的Authentication对象；第二个参数表示当前正在请求的受保护的对象，
    // 基本上来说是MethodInvocation（使用AOP）、JoinPoint（使用Aspectj）和FilterInvocation（Web请求）三种类型；第三个
    // 参数表示与当前正在访问的受保护对象的配置属性，如一个角色列表。
    //在这个方法中,需要比较与configAttributes身份验证。
    // 1,对象是一个URL、一个过滤器被这个URL找到权限配置,并传递到这里。
    // 2,检查认证在权限配置属性(configAttributes)
    // 3,如果不匹配相应的认证,AccessDeniedException。

    /**
     *从第一个方法可以看出来，AccessDecisionManager使用方法参数传递所有信息，这好像在认证评估时进行决定。
     *特别是，在真实的安全方法期望调用的时候，传递安全Object启用那些参数。
     *比如，让我们假设安全对象是一个MethodInvocation。
     *很容易为任何Customer参数查询MethodInvocation，
     *然后在AccessDecisionManager里实现一些有序的安全逻辑，来确认主体是否允许在那个客户上操作。
     *如果访问被拒绝，实现将抛出一个AccessDeniedException异常。
     *这个supports(ConfigAttribute) 方法在启动的时候被
     *AbstractSecurityInterceptor调用，来决定AccessDecisionManager
     *是否可以执行传递ConfigAttribute。
     *supports(Class)方法被安全拦截器实现调用，
     *包含安全拦截器将显示的AccessDecisionManager支持安全对象的类型。
     */
    //检查用户是否够权限访问资源
    //参数authentication是从spring的全局缓存SecurityContextHolder中拿到的，里面是用户的权限信息
    //参数object是url
    //参数configAttributes所需的权限      
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection)
            throws AccessDeniedException, InsufficientAuthenticationException {
        System.out.println("决策器启动--------决定某个用户具有的角色，是否有足够的权限去访问某个资源");

        //查看url    /login  就放行
//        FilterInvocation filterInvocation = (FilterInvocation) o;
//        filterInvocation.getHttpRequest();
//        String resURL = "/login";
//        RequestMatcher requestMatcher = new AntPathRequestMatcher(resURL);
//        if(requestMatcher.matches(filterInvocation.getHttpRequest())){
//            return;
//        }
        //进行拦截
        if(null== collection || collection.size() <=0) {
            return;
        }
        ConfigAttribute c;
        String needRole;
        for(Iterator<ConfigAttribute> iter = collection.iterator(); iter.hasNext(); ) {
            c = iter.next();
            needRole = c.getAttribute();
            for(GrantedAuthority ga : authentication.getAuthorities()) {
                if(needRole.trim().equals(ga.getAuthority())) {
                    return;
                }
            }
        }
        throw new AccessDeniedException("没有权限");
    }

    //都要设为true
    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    //都要设为true
    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}

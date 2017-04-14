package com.example.security;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * 最核心的地方，就是提供某个资源对应的权限定义，即getAttributes方法返回的结果。
 * 此类在初始化时，应该取到所有资源及其对应角色的定义。
 *
 * Created by yonglang on 2017/4/13.
 */
@Component
public class MyInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {
    //定义存储权限的map集合
    private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

    /**
     * 在Web服务器启动时，提取系统中的所有权限
     *
     * 被@PostConstruct修饰的方法会在服务器加载Servle的时候运行，并且只会被服务器执行一次。
     * PostConstruct在构造函数之后执行,init()方法之前执行
     */
    @PostConstruct
    private void loadResourceDefine(){
        System.out.println("加载权限");
        //模拟数据  以后将从数据库调用------访问这三个页面都需要权限6
        resourceMap = new HashMap<>();
        Collection<ConfigAttribute> atts = new ArrayList<>();
        ConfigAttribute ca = new SecurityConfig("ROLE_6");
        atts.add(ca);
        resourceMap.put("/index", atts);
        Collection<ConfigAttribute> attsno =new ArrayList<>();
        ConfigAttribute cano = new SecurityConfig("ROLE_6");
        attsno.add(cano);
        resourceMap.put("/user/index", attsno);
        Collection<ConfigAttribute> attsno1 =new ArrayList<>();
        ConfigAttribute cano1 = new SecurityConfig("ROLE_999");
        attsno1.add(cano1);
        resourceMap.put("/user/demo", attsno1);
    }

    /**
     *  根据URL，找到相关的权限配置。
     *  object 是一个URL，被用户请求的url
     *  缓存脏数据问题-----可以不需要loadResourceDefine方法了，直接在getAttributes方法里面调用dao（这个是加载完，后来才会调用的，所以可以使用dao），
     *  通过被拦截url获取数据库中的所有权限，封装成Collection<ConfigAttribute>返回就行了
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        System.out.println("根据url返回所需的权限");
        FilterInvocation filterInvocation = (FilterInvocation) o;
        if (resourceMap == null) {
            loadResourceDefine();
        }
        Iterator<String> ite = resourceMap.keySet().iterator();
        while (ite.hasNext()) {
            String resURL = ite.next();
            RequestMatcher requestMatcher = new AntPathRequestMatcher(resURL);
            if(requestMatcher.matches(filterInvocation.getHttpRequest())) {
                return resourceMap.get(resURL);
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}

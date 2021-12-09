package com.example.config.sercurity.component;

import com.example.pojo.Menu;
import com.example.pojo.Role;
import com.example.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * 权限控制根据请求url分析出所需的角色
 * @author mlNothing
 * @date 2021/12/6 16:08
 */
@Component
public class CustomFiter implements FilterInvocationSecurityMetadataSource {
    @Autowired
    private IMenuService menuService;
    AntPathMatcher antPathMatcher=    new AntPathMatcher();
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
//        获取请求的url
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
//            获取菜单
        List<Menu> menus = menuService.getAllMenusWithRole();
        for (Menu menu : menus) {
//            判断请求url与菜单角色是否匹配
         if( antPathMatcher.match(menu.getUrl(),requestUrl)){
              String[] str=  menu.getRoles().stream().map(Role::getName).toArray(String[]::new);
              return SecurityConfig.createList(str);
            }
        }
//        没匹配的url默认为登录即可访问
        return SecurityConfig.createList("ROLE_LOGIN");
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

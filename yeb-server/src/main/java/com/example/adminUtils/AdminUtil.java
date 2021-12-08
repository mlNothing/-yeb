package com.example.adminUtils;

import com.example.pojo.Admin;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

/**
 * @author mlNothing
 * @date 2021/12/8 15:34
 */
public class AdminUtil {
    public static final Admin getCurrentAdmin(){
        Admin admin = (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return admin;
    }
}

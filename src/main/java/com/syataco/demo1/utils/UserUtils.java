/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syataco.demo1.utils;

import com.syataco.demo1.bean.BranchBean;
import com.syataco.demo1.bean.UserBean;
import com.syataco.demo1.model.User;

/**
 *
 * @author Saroff
 */
public class UserUtils {
    
    public static UserBean toBean(User user) {
        if (user != null) {
            UserBean userBean = new UserBean();
            userBean.setId(user.getId());
            userBean.setNames(user.getNames());
            userBean.setUsername(user.getUsername());
            userBean.setPassword(user.getPassword());           
            userBean.setBranchId(user.getBranch().getId());
           
            return userBean;
        }
        return null;
    }
    
}

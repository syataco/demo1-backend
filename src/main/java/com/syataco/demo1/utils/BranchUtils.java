/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syataco.demo1.utils;

import com.syataco.demo1.bean.BranchBean;
import com.syataco.demo1.model.Branch;

/**
 *
 * @author Saroff
 */
public class BranchUtils {
    
    public static BranchBean toBean(Branch branch) {
        if (branch != null) {
            BranchBean branchBean = new BranchBean();
            branchBean.setId(branch.getId());
            branchBean.setName(branch.getName());
            return branchBean;
        }
        return null;
    }
}

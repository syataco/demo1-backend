/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syataco.demo1.bean;

import com.google.gson.annotations.Expose;
import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Saroff
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class BranchBean implements Serializable {
    
    @Expose
    private Long id;
    
    @Expose
    private String name;
    
    private List<UserBean> users;
}

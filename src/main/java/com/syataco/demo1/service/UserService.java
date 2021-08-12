/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syataco.demo1.service;

import com.syataco.demo1.bean.BranchBean;
import com.syataco.demo1.bean.UserBean;
import com.syataco.demo1.model.Branch;
import com.syataco.demo1.model.User;
import com.syataco.demo1.repository.BranchRepository;
import com.syataco.demo1.repository.UserRepository;
import com.syataco.demo1.utils.UserUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Saroff
 */
@Service
@Transactional
@Log4j2
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private BranchRepository branchRepository;
    
    public User createUser(UserBean userBean) {
        if (userBean != null) {
            User user = new User();
            user.setNames(userBean.getNames());
            user.setUsername(userBean.getUsername());
            user.setPassword(userBean.getPassword());
        
            Optional<Branch> optionalBranch = branchRepository.findById(userBean.getBranchId());
            if (optionalBranch.isPresent()) {
                user.setBranch(optionalBranch.get());
            }
            user = userRepository.save(user);
            
            log.info("An user with id={} was created.", user.getId());
            return user;
        }
        log.error("The user cannot was created");
        return null;        
    }
    
    public UserBean getUserById(Long id) {
        if (id != null) {
            Optional<User> optionalUser = userRepository.findById(id);
            if (optionalUser.isPresent()) {
                UserBean userBean = new UserBean();
                userBean.setId(optionalUser.get().getId());
                userBean.setNames(optionalUser.get().getNames());
                userBean.setUsername(optionalUser.get().getUsername());
                userBean.setPassword(optionalUser.get().getPassword());
                
                BranchBean branchBean = new BranchBean();
                branchBean.setId(optionalUser.get().getBranch().getId());
                
                userBean.setBranchId(branchBean.getId());
                return userBean;
            }
        }
        return null;
    }
    
    public List<UserBean> getAllUsers() {
        List<UserBean> userBeanList = new ArrayList<>();
        userRepository.findAll().forEach(
                element -> userBeanList.add(UserUtils.toBean(element))
        );
        return userBeanList;
    }
    
    public UserBean updateUser(Long id, UserBean userBean) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setNames(userBean.getNames());
            user.setUsername(userBean.getUsername());
            user.setPassword(userBean.getPassword());
            
            Branch branch = new Branch();
            branch.setId(userBean.getBranchId());
            
            user.setBranch(branch);
            user = userRepository.save(user);
            return UserUtils.toBean(user);
        }
        return null;
    }
    
    public void deleteUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()) {
            userRepository.deleteById(id);
        }
    }
    
    
}

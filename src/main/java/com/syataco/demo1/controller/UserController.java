/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syataco.demo1.controller;

import com.google.gson.GsonBuilder;
import com.syataco.demo1.bean.UserBean;
import com.syataco.demo1.model.User;
import com.syataco.demo1.service.UserService;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Saroff
 */
@RestController
@Log4j2
@RequestMapping("/service/user")
@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:4200"})
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private GsonBuilder gsonBuilder;
    
    @RequestMapping(value="/", method=RequestMethod.POST)
    public ResponseEntity<String> createUser(@RequestBody UserBean userBean) {
        try {
            if (userBean != null) {
                User user = userService.createUser(userBean);
                return new ResponseEntity<>(gsonBuilder.create().toJson(user), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.PRECONDITION_FAILED);
        }
    }
    
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<String> getUsert(@PathVariable("id") Long id) {
        try {
            UserBean userBean = userService.getUserById(id);
            if (userBean != null) {
                return new ResponseEntity<>(gsonBuilder.create().toJson(userBean), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.PRECONDITION_FAILED);
        }
    }
    
    @RequestMapping(value="/", method=RequestMethod.GET)
    public ResponseEntity<String> getAllUsers() {
        try {
            List<UserBean> userBeanList = userService.getAllUsers();
            if (!userBeanList.isEmpty()) {
                return new ResponseEntity<>(gsonBuilder.create().toJson(userBeanList), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.PRECONDITION_FAILED);
        }
    }
    
    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<String> updateUser(@RequestBody UserBean userBean, @PathVariable("id") Long id) {
        try {
            UserBean userBeanResponse = userService.updateUser(id, userBean);
            if (userBeanResponse != null) {
                return new ResponseEntity<>(gsonBuilder.create().toJson(userBeanResponse), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.PRECONDITION_FAILED);
        }
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        try {
            if (id != null) {
                userService.deleteUser(id);
                return new ResponseEntity<>(gsonBuilder.create().toJson("Delete successful"), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.PRECONDITION_FAILED);
        }
    }
}

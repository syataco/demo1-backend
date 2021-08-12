/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syataco.demo1.controller;

import com.google.gson.GsonBuilder;
import com.syataco.demo1.bean.BranchBean;
import com.syataco.demo1.model.Branch;
import com.syataco.demo1.service.BranchService;
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
@RequestMapping("/service/branch")
@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:4200"})
public class BranchController {
    @Autowired
    private BranchService branchService;
    
    @Autowired
    private GsonBuilder gsonBuilder;
    
    @RequestMapping(value="/", method=RequestMethod.POST)
    public ResponseEntity<String> createBranch(@RequestBody BranchBean branchBean) {
        try {
            if (branchBean != null) {
                Branch branch = branchService.createBranch(branchBean);
                return new ResponseEntity<>(gsonBuilder.create().toJson(branch), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.PRECONDITION_FAILED);
        }
    }
    
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<String> getBranch(@PathVariable("id") Long id) {
        try {
            BranchBean branchBean = branchService.getBranchById(id);
            if (branchBean != null) {
                return new ResponseEntity<>(gsonBuilder.create().toJson(branchBean), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.PRECONDITION_FAILED);
        }
    }
    
    @RequestMapping(value="/", method=RequestMethod.GET)
    public ResponseEntity<String> getAllBranches() {
        try {
            List<BranchBean> branchBeanList = branchService.getAllBranches();
            if (!branchBeanList.isEmpty()) {
                return new ResponseEntity<>(gsonBuilder.create().toJson(branchBeanList), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.PRECONDITION_FAILED);
        }
    }
    
    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<String> updateBranch(@RequestBody BranchBean branchBean, @PathVariable("id") Long id) {
        try {
            BranchBean branchBeanResponse = branchService.updateBranch(id, branchBean);
            if (branchBeanResponse != null) {
                return new ResponseEntity<>(gsonBuilder.create().toJson(branchBeanResponse), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.PRECONDITION_FAILED);
        }
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteBranch(@PathVariable Long id) {
        try {
            if (id != null) {
                branchService.deleteBranch(id);
                return new ResponseEntity<>(gsonBuilder.create().toJson("Delete successful"), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.PRECONDITION_FAILED);
        }
    }
}

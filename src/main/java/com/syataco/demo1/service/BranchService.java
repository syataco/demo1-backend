/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syataco.demo1.service;

import com.syataco.demo1.bean.BranchBean;
import com.syataco.demo1.model.Branch;
import com.syataco.demo1.repository.BranchRepository;
import com.syataco.demo1.utils.BranchUtils;
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
public class BranchService {
    
    @Autowired
    private BranchRepository branchRepository;
    
    public Branch createBranch(BranchBean branchBean) {
        if (branchBean != null) {
            Branch branch = new Branch();
            branch.setName(branchBean.getName());
            branch = branchRepository.save(branch);
            log.info("An branch whit id={} was created", branch.getId());
            return branch;
        }
        log.error("Cannot create a branch");
        return null;        
    }
    
    public BranchBean getBranchById(Long id) {
        if (id != null) {
            Optional<Branch> optionalBranch = branchRepository.findById(id);
            if (optionalBranch.isPresent()) {
                BranchBean branchBean = new BranchBean();
                branchBean.setId(optionalBranch.get().getId());
                branchBean.setName(optionalBranch.get().getName());
                return branchBean;
            }
        }
        return null;
    }
    
    public List<BranchBean> getAllBranches() {
        List<BranchBean> branchBeanList = new ArrayList<>();
        branchRepository.findAll().forEach(
                element -> branchBeanList.add(BranchUtils.toBean(element))
        );
        return branchBeanList;
    }
    
    public BranchBean updateBranch(Long id, BranchBean branchBean) {
        Optional<Branch> optionalBranch = branchRepository.findById(id);
        if (optionalBranch.isPresent()) {
            Branch branch = optionalBranch.get();
            branch.setName(branchBean.getName());
            branch = branchRepository.save(branch);
            return BranchUtils.toBean(branch);
        }
        return null;
    }
    
    public void deleteBranch(Long id) {
        Optional<Branch> optionalBranch = branchRepository.findById(id);
        if(optionalBranch.isPresent() && optionalBranch.get().getUsers().isEmpty()) {
            branchRepository.deleteById(id);
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syataco.demo1.repository;

import com.syataco.demo1.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Saroff
 */
public interface BranchRepository extends JpaRepository<Branch, Long> {
    
}

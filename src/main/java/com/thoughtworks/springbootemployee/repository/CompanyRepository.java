package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author ZHUDO2
 * @Date 7/29/2020 11:08 AM
 * @Version 1.0
 */
@Repository
public interface CompanyRepository extends JpaRepository<Company,Integer> {

}

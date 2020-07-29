package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author ZHUDO2
 * @Date 7/28/2020 8:01 PM
 * @Version 1.0
 */
@RestController
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public void addCompany(@RequestBody Company company) {
        companyService.addCompany(company);
    }

    @GetMapping("/{companyId}/employees")
    public List<Employee> findAllEmployeesByCompanyId(@PathVariable("companyId") int companyId) {
        return companyService.findEmployeesByCompanyId(companyId);
    }

    @GetMapping("/{companyId}")
    public Company findCompanyById(@PathVariable("companyId") int companyId) {
        return companyService.findCompanyById(companyId);
    }

    @GetMapping
    public List<Company> findCompanies(@PageableDefault Pageable pageable, @RequestParam(defaultValue = "false") boolean unpaged) {
        if (unpaged) {
            pageable = Pageable.unpaged();
        }
        return companyService.findAllCompanies(pageable);
    }

    @PutMapping("/{companyId}")
    public void updateCompany(@PathVariable("companyId") int companyId,
                              @RequestBody Company company) {
        companyService.updateCompany(companyId, company);
    }

    @DeleteMapping("/{companyId}")
    public void deleteEmployeesByCompanyId(@PathVariable("companyId") int companyId) {
        companyService.deleteEmployeesByCompanyId(companyId);
    }


}

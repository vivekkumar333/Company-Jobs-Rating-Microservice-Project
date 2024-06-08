package com.learnwithtiwari.companyservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learnwithtiwari.companyservice.entity.Company;
import com.learnwithtiwari.companyservice.request.UpdateCompanyRequest;
import com.learnwithtiwari.companyservice.service.CompanyService;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @GetMapping("/getList")
    public ResponseEntity<Object> getCompanyList(){
        return companyService.fetchCompanyList();
    }


    @PostMapping("/create")
    public ResponseEntity<String> createCompany(@RequestBody Company input){
        return companyService.createCompany(input);
    }

    @PutMapping("/update/{jobId}")
    public ResponseEntity<String> updateComapny(@PathVariable Long companyId, @RequestBody UpdateCompanyRequest updateReq){
        return companyService.updateCompany(companyId,updateReq);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getCompanyById(@PathVariable Long id){
        return companyService.getCompanyById(id);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteCompany(@PathVariable Long id){
        return companyService.deleteCompany(id);
    }

}

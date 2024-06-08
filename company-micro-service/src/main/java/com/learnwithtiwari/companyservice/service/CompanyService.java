package com.learnwithtiwari.companyservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.learnwithtiwari.companyservice.entity.Company;
import com.learnwithtiwari.companyservice.repo.CompanyRepo;
import com.learnwithtiwari.companyservice.request.UpdateCompanyRequest;

@Service
public class CompanyService {

    @Autowired
    CompanyRepo companyRepo;

    public ResponseEntity<Object> fetchCompanyList(){
        List<Company> compList = companyRepo.findAll();

        if(compList.size() > 0) {
            return new ResponseEntity<>(compList, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Resource Not found! Company list is empty", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<String> createCompany(Company data){
        Company savedCompany = null;
        savedCompany= companyRepo.save(new Company(data.getCompanyName(),
                data.getCompanyBusiness(),
                data.getCompanyOrigin()));

        if(savedCompany != null){
            return new ResponseEntity<>("Company details stored Successfully", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Storing Company info failed!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> updateCompany(Long id, UpdateCompanyRequest data){
        Optional<Company> savedData = companyRepo.findById(id);
        if(savedData.isPresent()){
            companyRepo.save(new Company(id,
                    data.getCompanyName(),
                    data.getCompanyBusiness(),
                    data.getCompanyOrigin()));

            return new ResponseEntity<>("Company info has been updated successfully", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Failed! Company id not found", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Object> getCompanyById(Long id){
        Optional<Company> savedData = companyRepo.findById(id);

        if(savedData.isPresent()) {
            return new ResponseEntity<>(savedData.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Failed! Company info has not found in the database", HttpStatus.NOT_FOUND);
        }
    }


    public ResponseEntity<Object> deleteCompany(Long id){
        Optional<Company> companyInfo = companyRepo.findById(id);

        if(companyInfo.isPresent()) {
            companyRepo.delete(companyInfo.get());
            return new ResponseEntity<>("Successful: Company details has been deleted", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Failed! Company with ID not found in the database", HttpStatus.NOT_FOUND);
        }
    }
}

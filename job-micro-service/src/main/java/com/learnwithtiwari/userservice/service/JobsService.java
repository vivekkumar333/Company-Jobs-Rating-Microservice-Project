package com.learnwithtiwari.userservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.learnwithtiwari.userservice.entity.Jobs;
import com.learnwithtiwari.userservice.repo.JobsRepository;
import com.learnwithtiwari.userservice.request.Company;
import com.learnwithtiwari.userservice.request.JobCreationRequest;
import com.learnwithtiwari.userservice.request.UpdateJobRequest;
import com.learnwithtiwari.userservice.response.CompanyDTO;
import com.learnwithtiwari.userservice.response.JobsResponseDTO;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class JobsService {

    @Autowired
    JobsRepository jobsRepo;
    
    @Autowired
    RestTemplate restTemplate;
    
    final String COMPANY_BASE_URL="http://COMPANY-MICRO-SERVICE:8081/company";
    
    
    public ResponseEntity<Object> companySerivceBreakerFallBack(Exception ex){
    	 ex.printStackTrace();
         return new ResponseEntity<>("Fallback response: Service is currently unavailable", HttpStatus.SERVICE_UNAVAILABLE);
     }

    

    @CircuitBreaker(name = "companyServiceBreaker", fallbackMethod = "companySerivceBreakerFallBack")
    public ResponseEntity<Object> getAllJobs(){
    	
//    	try {
    		List<Jobs> jobList = jobsRepo.findAll();
            
            List<JobsResponseDTO> jobResponse = new ArrayList<>();
            List<Company> companyList = new ArrayList<Company>();
            
            
            List<Long> companyIdList = jobList.stream().map(Jobs::getCompanyId).collect(Collectors.toList());
            companyIdList.forEach(id -> {
            	Company companyInfo = restTemplate.getForObject(COMPANY_BASE_URL+"/get/"+id, Company.class);
            	if(companyInfo !=null) {
            		companyList.add(companyInfo);
            	}
            });
            
                    
            
            if(jobList.size() > 0) {
            	jobList.stream().forEach(j -> {
            		Optional<Company> company = companyList.stream().filter(c -> c.getId().equals(j.getCompanyId())).findAny();
            		jobResponse.add(new JobsResponseDTO(j.getJobTitle(), j.getSkillSet(), j.getDescription(), j.getMinSalary(), 
            				j.getMaxSalary(), j.getLocation(), 
            				new CompanyDTO(company.get().getId(),company.get().getCompanyName(), company.get().getCompanyBusiness(), company.get().getCompanyOrigin())));
            		
            	});
                return new ResponseEntity<>(jobResponse, HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Not found! Job list is empty", HttpStatus.NOT_FOUND);
            }
//    	}catch(RestClientException ex) {
//    		ex.printStackTrace();
//    		return new ResponseEntity<>(ex.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
//    	}catch(RuntimeException ex) {
//    		ex.printStackTrace();
//    		return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//    	}
        
    }

    
    public ResponseEntity<String> createJobs(JobCreationRequest jobInput){
    	
    	try {
    		Company company= restTemplate.getForObject(COMPANY_BASE_URL+"/get/"+jobInput.getCompanyId(), Company.class);
    		
            Jobs savedJob = null;
            if(company!=null){
                savedJob= jobsRepo.save(new Jobs(
                        jobInput.getJobTitle(),
                        jobInput.getSkillSet(),
                        jobInput.getDescription(),
                        jobInput.getMinSalary(),
                        jobInput.getMaxSalary(),
                        jobInput.getLocation(),
                        company.getId()));
            }else{
                return new ResponseEntity<>("failed! Company not found", HttpStatus.NOT_FOUND);
            }
            if(savedJob != null){
                return new ResponseEntity<>("Jobs created Successfully", HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Job creation failed", HttpStatus.INTERNAL_SERVER_ERROR);
            }
    	}catch(RestClientException ex) {
    		ex.printStackTrace();
    		return new ResponseEntity<>(ex.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
    	}catch(RuntimeException ex) {
    		ex.printStackTrace();
    		return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    	
        
    }

    
    public ResponseEntity<String> updateJobs(Long jobId, UpdateJobRequest updateJob){
    	
    	try {
    		 Optional<Jobs> savedJob = jobsRepo.findById(jobId);
    	        Company company= restTemplate.getForObject(COMPANY_BASE_URL+"/get/"+updateJob.getCompanyId(), Company.class);

    	        if(company!=null){
    	            if(savedJob.isPresent()){
    	                jobsRepo.save(new Jobs(
    	                		jobId,
    	                		updateJob.getJobTitle(),
    	                        updateJob.getSkillSet(),
    	                        updateJob.getDescription(),
    	                        updateJob.getMinSalary(),
    	                        updateJob.getMaxSalary(),
    	                        updateJob.getLocation(),
    	                        company.getId()));

    	                return new ResponseEntity<>("Job has been updated successfully", HttpStatus.OK);
    	            }else{
    	                return new ResponseEntity<>("Failed! JobId not found", HttpStatus.NOT_FOUND);
    	            }
    	        }else{
    	            return new ResponseEntity<>("failed! Updated Company not found", HttpStatus.NOT_FOUND);
    	        }
    	}catch(RestClientException ex) {
    		ex.printStackTrace();
    		return new ResponseEntity<>(ex.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
    	}catch(RuntimeException ex) {
    		ex.printStackTrace();
    		return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    	
       

    }

    public ResponseEntity<Object> getJobById(Long id){
    	try {
    		 Optional<Jobs> job = jobsRepo.findByJobId(id);
            
            List<JobsResponseDTO> jobResponse = new ArrayList<>();                
            
            if(job.isPresent()) {
            		Company company = restTemplate.getForObject(COMPANY_BASE_URL+"/get/"+job.get().getCompanyId(), Company.class);
            		
            		jobResponse.add(new JobsResponseDTO(job.get().getJobTitle(), job.get().getSkillSet(), job.get().getDescription(),job.get().getMinSalary(), 
            				job.get().getMaxSalary(), job.get().getLocation(), 
            				new CompanyDTO(company.getId(),company.getCompanyName(), company.getCompanyBusiness(), company.getCompanyOrigin())));

                return new ResponseEntity<>(jobResponse, HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Not found! Job list is empty", HttpStatus.NOT_FOUND);
            }
    	}catch(RestClientException ex) {
    		ex.printStackTrace();
    		return new ResponseEntity<>(ex.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
    	}catch(RuntimeException ex) {
    		ex.printStackTrace();
    		return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }


    public ResponseEntity<Object> deleteJob(Long id){
        Optional<Jobs> job = jobsRepo.findByJobId(id);

        if(job.isPresent()) {
            jobsRepo.delete(job.get());
            return new ResponseEntity<>("Successful: Job has been deleted", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Failed! Job not found in the database", HttpStatus.NOT_FOUND);
        }
    }

}

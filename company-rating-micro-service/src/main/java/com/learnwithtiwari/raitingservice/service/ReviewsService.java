package com.learnwithtiwari.raitingservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.learnwithtiwari.raitingservice.dto.CompanyDTO;
import com.learnwithtiwari.raitingservice.dto.JobsDTO;
import com.learnwithtiwari.raitingservice.dto.RaitingDTO;
import com.learnwithtiwari.raitingservice.entity.ReviewRaiting;
import com.learnwithtiwari.raitingservice.repo.ReviewsRepo;
import com.learnwithtiwari.raitingservice.request.Company;
import com.learnwithtiwari.raitingservice.request.ReviewRequest;
import com.learnwithtiwari.raitingservice.response.JobCompanyRaitingsResponse;
import com.learnwithtiwari.raitingservice.response.RaitingResponse;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@Service
public class ReviewsService {
    @Autowired
    ReviewsRepo reviewsRepo;
    
    @Autowired
    RestTemplate restTemplate;
    
    int attempt = 0;

  @Value("${job-service.url}")  
  private String Job_SERVICE_BASE_URL;
  
  @Value("${company-service.url}")
  private String COMPANY_SERVICE_BASE_URL;;   
//    final String Job_SERVICE_BASE_URL="http://JOB-MICRO-SERVICE:8082/job";
//    final String COMPANY_SERVICE_BASE_URL="http://COMPANY-MICRO-SERVICE:8081/company";
//    final String Job_SERVICE_BASE_URL="http://localhost:8082/job";
//    final String COMPANY_SERVICE_BASE_URL="http://localhost:8081/company";

    @CircuitBreaker(name = "jobServiceBreaker", fallbackMethod = "jobServiceFallBackMethod")
//    @Retry(name = "jobServiceBreaker", fallbackMethod = "jobServiceFallBackMethod")
    @RateLimiter(name = "jobServiceBreaker", fallbackMethod = "jobServiceFallBackMethod")
    public ResponseEntity<Object> fetchRaitingList(){
//        try {
        	
        	List<JobsDTO> jobs = restTemplate.exchange(Job_SERVICE_BASE_URL+"/fetchList", HttpMethod.GET, null, new ParameterizedTypeReference<List<JobsDTO>>() {}).getBody();
        			
        	List<JobCompanyRaitingsResponse> jobCompanyRaitingList = new ArrayList();
        	
            if(jobs!= null) {
            	jobs.stream().forEach(job -> {
            		List<RaitingDTO> raitingDtoList = new ArrayList();
            		Optional<List<ReviewRaiting>> raitingList = reviewsRepo.findByCompanyId(job.getCompany().getId());
            		if(raitingList.isPresent()) {
            			raitingDtoList= raitingList.get().stream().map(raiting -> new RaitingDTO(raiting.getReviewId(),raiting.getRaiting(), raiting.getRemarks())).collect(Collectors.toList());
            		}
            		
            		jobCompanyRaitingList.add(new JobCompanyRaitingsResponse(job.getJobTitle(), job.getSkillSet(), 
            				job.getDescription(), job.getMinSalary(), job.getMaxSalary(), job.getLocation(), job.getCompany(), raitingDtoList));
            		
            	});
            	
            	return new ResponseEntity<Object>(jobCompanyRaitingList,HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Not found! Job List is empty, First Create Jobs to fetch company raiting List", HttpStatus.OK);
            }
//    	}catch(RestClientException ex) {
//    		ex.printStackTrace();
//    		return new ResponseEntity<>(ex.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
//    	}catch(RuntimeException ex) {
//    		ex.printStackTrace();
//    		return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//    	}
        
    }
    
    

    @CircuitBreaker(name="companyServiceBreaker", fallbackMethod = "companySerivceFallBackMethod")
//    @Retry(name = "companyServiceBreaker")
    @RateLimiter(name = "companyServiceBreaker", fallbackMethod = "companySerivceFallBackMethod")
    public ResponseEntity<String> createReviews(ReviewRequest reviewReq){  
//        try {
    		Company company= restTemplate.getForObject(COMPANY_SERVICE_BASE_URL+"/get/"+reviewReq.getCompanyId(), Company.class);
    		
    		ReviewRaiting savedReview = null;
            if(company!=null){
            	savedReview= reviewsRepo.save(new ReviewRaiting(reviewReq.getRaiting(),reviewReq.getRemarks(),company.getId()));
            }else{
                return new ResponseEntity<>("failed! Company not found", HttpStatus.NOT_FOUND);
            }
            if(savedReview != null){
                return new ResponseEntity<>("Company Raiting saved Successfully", HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Company raiting failed", HttpStatus.INTERNAL_SERVER_ERROR);
            }
//    	}catch(RestClientException ex) {
//    		ex.printStackTrace();
//    		return new ResponseEntity<>(ex.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
//    	}catch(RuntimeException ex) {
//    		ex.printStackTrace();
//    		return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//    	}
    }

    @CircuitBreaker(name="companyServiceBreaker", fallbackMethod = "companySerivceFallBackMethod")
//  @Retry(name = "companyServiceBreaker")
  @RateLimiter(name = "companyServiceBreaker", fallbackMethod = "companySerivceFallBackMethod")
    public ResponseEntity<String> updateComapnyRaiting(Long reviewId, ReviewRequest updateReview){      
//        try {
    		Company company= restTemplate.getForObject(COMPANY_SERVICE_BASE_URL+"/get/"+updateReview.getCompanyId(), Company.class);
    		Optional<ReviewRaiting> savedReview = reviewsRepo.findById(reviewId);
    	
            if(company!=null){
            	 if(savedReview.isPresent()){
            		 reviewsRepo.save(new ReviewRaiting(reviewId,updateReview.getRaiting(),updateReview.getRemarks(),updateReview.getCompanyId()));

                     return new ResponseEntity<>("Company raiting has updated successfully", HttpStatus.OK);
                 }else{
                     return new ResponseEntity<>("Failed! Raiting not found", HttpStatus.NOT_FOUND);
                 }
            }else{
                return new ResponseEntity<>("failed! Company not found", HttpStatus.NOT_FOUND);
            }
//    	}catch(RestClientException ex) {
//    		ex.printStackTrace();
//    		return new ResponseEntity<>(ex.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
//    	}catch(RuntimeException ex) {
//    		ex.printStackTrace();
//    		return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//    	}

    }

    @CircuitBreaker(name="companyServiceBreaker", fallbackMethod = "companySerivceFallBackMethod")
//  @Retry(name = "companyServiceBreaker")
    @RateLimiter(name = "companyServiceBreaker", fallbackMethod = "companySerivceFallBackMethod")
    public ResponseEntity<Object> getRaitingById(Long id){
 //       try {
        	Optional<ReviewRaiting> savedReview = reviewsRepo.findById(id);
        	if(savedReview.isPresent()) {
        		Company company= restTemplate.getForObject(COMPANY_SERVICE_BASE_URL+"/get/"+savedReview.get().getCompanyId(), Company.class);
        		
        		RaitingResponse raitingResponseDto = new RaitingResponse(savedReview.get().getRaiting(),savedReview.get().getRemarks(),  
        				new CompanyDTO(company.getCompanyName(), company.getCompanyBusiness(), company.getCompanyOrigin()));
        		return new ResponseEntity<>(raitingResponseDto, HttpStatus.OK);
        	}else{
                return new ResponseEntity<>("Not found! Company raiting not found", HttpStatus.OK);
            }
           
//    	}catch(RestClientException ex) {
//    		ex.printStackTrace();
//    		return new ResponseEntity<>(ex.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
//    	}catch(RuntimeException ex) {
//    		ex.printStackTrace();
//    		return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//    	}
    }


    public ResponseEntity<Object> deleteRaiting(Long id){
        Optional<ReviewRaiting> review = reviewsRepo.findByReviewId(id);

        if(review.isPresent()) {
            reviewsRepo.delete(review.get());
            return new ResponseEntity<>("Successful: Review has been deleted", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Not Found! Company Raiting not found", HttpStatus.OK);
        }
    }
    
    
    public ResponseEntity<Object> jobServiceFallBackMethod(Throwable t){
    	System.out.println("---- Attempt Count: "+  attempt++);	
   	 t.printStackTrace();
     return new ResponseEntity<>("Fallback response: Job-Service is currently unavailable", HttpStatus.SERVICE_UNAVAILABLE);
   }
   
   public ResponseEntity<Object> companySerivceFallBackMethod(Exception ex){
  	 ex.printStackTrace();
       return new ResponseEntity<>("Fallback response: Company-Service is currently unavailable", HttpStatus.SERVICE_UNAVAILABLE);
   }
}

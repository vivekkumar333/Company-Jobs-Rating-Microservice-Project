package com.learnwithtiwari.raitingservice.controller;

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

import com.learnwithtiwari.raitingservice.request.ReviewRequest;
import com.learnwithtiwari.raitingservice.service.ReviewsService;

@RestController
@RequestMapping("/rating")
public class ReviewsController {
    @Autowired
    ReviewsService reviewsService;

    @GetMapping("/getJobsRaitigs")
    public ResponseEntity<Object> getAllReviews(){
        return reviewsService.fetchRaitingList();
    }


    @PostMapping("/create")
    public ResponseEntity<String> createReview(@RequestBody ReviewRequest input){
        return reviewsService.createReviews(input);
    }

    @PutMapping("/update/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long reviewId, @RequestBody ReviewRequest upReq){
        return reviewsService.updateComapnyRaiting(reviewId,upReq);
    }

    @GetMapping("/get/{reviewId}")
    public ResponseEntity<Object> findRaitingById(@PathVariable Long reviewId){
        return reviewsService.getRaitingById(reviewId);
    }


    @DeleteMapping("/delete/{reviewId}")
    public ResponseEntity<Object> deleteRaiting(@PathVariable Long reviewId){
        return reviewsService.deleteRaiting(reviewId);
    }


}

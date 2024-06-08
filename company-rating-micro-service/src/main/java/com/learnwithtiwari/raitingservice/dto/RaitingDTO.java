package com.learnwithtiwari.raitingservice.dto;

public class RaitingDTO {
	
	 private Long reviewId;

	 private Integer raiting;

	 private String remarks;

	 
	 
	public RaitingDTO(Long reviewId, Integer raiting, String remarks) {
		super();
		this.reviewId = reviewId;
		this.raiting = raiting;
		this.remarks = remarks;
	}

	public Long getReviewId() {
		return reviewId;
	}

	public Integer getRaiting() {
		return raiting;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setReviewId(Long reviewId) {
		this.reviewId = reviewId;
	}

	public void setRaiting(Integer raiting) {
		this.raiting = raiting;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	 
	 
}

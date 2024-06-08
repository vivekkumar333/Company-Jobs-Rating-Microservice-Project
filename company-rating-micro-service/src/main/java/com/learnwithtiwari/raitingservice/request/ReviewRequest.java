package com.learnwithtiwari.raitingservice.request;

public class ReviewRequest {

	private Integer raiting;

	private String remarks;

	private Long companyId;

	public Integer getRaiting() {
		return raiting;
	}

	public String getRemarks() {
		return remarks;
	}

	public Long getCompanyId() {
		return companyId;
	}
}

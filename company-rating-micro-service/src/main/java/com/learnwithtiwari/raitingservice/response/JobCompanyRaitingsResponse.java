package com.learnwithtiwari.raitingservice.response;

import java.util.List;

import com.learnwithtiwari.raitingservice.dto.CompanyDTO;
import com.learnwithtiwari.raitingservice.dto.RaitingDTO;
import com.learnwithtiwari.raitingservice.entity.ReviewRaiting;

public class JobCompanyRaitingsResponse {
	private String jobTitle;

    private String skillSet;
    
    private String description;

    private String minSalary;
    
    private String maxSalary;

    private String location;

    private CompanyDTO company;

	private List<RaitingDTO> raitingList;
	
	
	

	public JobCompanyRaitingsResponse(String jobTitle, String skillSet, String description, String minSalary,
			String maxSalary, String location, CompanyDTO company, List<RaitingDTO> raitingList) {
		super();
		this.jobTitle = jobTitle;
		this.skillSet = skillSet;
		this.description = description;
		this.minSalary = minSalary;
		this.maxSalary = maxSalary;
		this.location = location;
		this.company = company;
		this.raitingList = raitingList;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public String getSkillSet() {
		return skillSet;
	}

	public String getDescription() {
		return description;
	}

	public String getMinSalary() {
		return minSalary;
	}

	public String getMaxSalary() {
		return maxSalary;
	}

	public String getLocation() {
		return location;
	}

	public CompanyDTO getCompany() {
		return company;
	}

	public List<RaitingDTO> getRaitingList() {
		return raitingList;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public void setSkillSet(String skillSet) {
		this.skillSet = skillSet;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setMinSalary(String minSalary) {
		this.minSalary = minSalary;
	}

	public void setMaxSalary(String maxSalary) {
		this.maxSalary = maxSalary;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setCompany(CompanyDTO company) {
		this.company = company;
	}

	public void setRaitingList(List<RaitingDTO> raitingList) {
		this.raitingList = raitingList;
	}

	
    
}

package com.learnwithtiwari.userservice.request;

public class UpdateJobRequest {

	 private String jobTitle;

	    private String skillSet;
	    
	    private String description;

	    private String minSalary;
	    
	    private String maxSalary;

	    private String location;

	    private Long companyId;

		public UpdateJobRequest(String jobTitle, String skillSet, String description, String minSalary, String maxSalary,
				String location, Long companyId) {
			super();
			this.jobTitle = jobTitle;
			this.skillSet = skillSet;
			this.description = description;
			this.minSalary = minSalary;
			this.maxSalary = maxSalary;
			this.location = location;
			this.companyId = companyId;
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

		public Long getCompanyId() {
			return companyId;
		}

}

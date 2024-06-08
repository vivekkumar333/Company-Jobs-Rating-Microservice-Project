package com.learnwithtiwari.raitingservice.dto;

public class JobsDTO {
	 private String jobTitle;

	    private String skillSet;
	    
	    private String description;

	    private String minSalary;
	    
	    private String maxSalary;

	    private String location;

	    private CompanyDTO company;

		public JobsDTO(String jobTitle, String skillSet, String description, String minSalary, String maxSalary,
				String location, CompanyDTO company) {
			super();
			this.jobTitle = jobTitle;
			this.skillSet = skillSet;
			this.description = description;
			this.minSalary = minSalary;
			this.maxSalary = maxSalary;
			this.location = location;
			this.company = company;
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


		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((company == null) ? 0 : company.hashCode());
			result = prime * result + ((description == null) ? 0 : description.hashCode());
			result = prime * result + ((jobTitle == null) ? 0 : jobTitle.hashCode());
			result = prime * result + ((location == null) ? 0 : location.hashCode());
			result = prime * result + ((maxSalary == null) ? 0 : maxSalary.hashCode());
			result = prime * result + ((minSalary == null) ? 0 : minSalary.hashCode());
			result = prime * result + ((skillSet == null) ? 0 : skillSet.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			JobsDTO other = (JobsDTO) obj;
			if (company == null) {
				if (other.company != null)
					return false;
			} else if (!company.equals(other.company))
				return false;
			if (description == null) {
				if (other.description != null)
					return false;
			} else if (!description.equals(other.description))
				return false;
			if (jobTitle == null) {
				if (other.jobTitle != null)
					return false;
			} else if (!jobTitle.equals(other.jobTitle))
				return false;
			if (location == null) {
				if (other.location != null)
					return false;
			} else if (!location.equals(other.location))
				return false;
			if (maxSalary == null) {
				if (other.maxSalary != null)
					return false;
			} else if (!maxSalary.equals(other.maxSalary))
				return false;
			if (minSalary == null) {
				if (other.minSalary != null)
					return false;
			} else if (!minSalary.equals(other.minSalary))
				return false;
			if (skillSet == null) {
				if (other.skillSet != null)
					return false;
			} else if (!skillSet.equals(other.skillSet))
				return false;
			return true;
		}
	    
	    
}

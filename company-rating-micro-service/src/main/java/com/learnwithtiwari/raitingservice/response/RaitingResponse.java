package com.learnwithtiwari.raitingservice.response;

import com.learnwithtiwari.raitingservice.dto.CompanyDTO;

public class RaitingResponse {
	private Integer raiting;

	private String remarks;

	private CompanyDTO company;

	public RaitingResponse(Integer raiting, String remarks, CompanyDTO company) {
		super();
		this.raiting = raiting;
		this.remarks = remarks;
		this.company = company;
	}

	public Integer getRaiting() {
		return raiting;
	}

	public String getRemarks() {
		return remarks;
	}

	public CompanyDTO getCompany() {
		return company;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((raiting == null) ? 0 : raiting.hashCode());
		result = prime * result + ((remarks == null) ? 0 : remarks.hashCode());
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
		RaitingResponse other = (RaitingResponse) obj;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (raiting == null) {
			if (other.raiting != null)
				return false;
		} else if (!raiting.equals(other.raiting))
			return false;
		if (remarks == null) {
			if (other.remarks != null)
				return false;
		} else if (!remarks.equals(other.remarks))
			return false;
		return true;
	}

}

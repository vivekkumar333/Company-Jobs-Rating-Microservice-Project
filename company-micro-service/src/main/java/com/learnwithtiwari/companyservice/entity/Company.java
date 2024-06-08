package com.learnwithtiwari.companyservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "company_tbl")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;

    private String companyBusiness;

    private String companyOrigin;

    public Company() {}

    
    public Company(Long id, String companyName, String companyBusiness, String companyOrigin) {
		super();
		this.id = id;
		this.companyName = companyName;
		this.companyBusiness = companyBusiness;
		this.companyOrigin = companyOrigin;
	}


	public Company(String companyName, String companyBusiness, String companyOrigin) {
		super();
		this.companyName = companyName;
		this.companyBusiness = companyBusiness;
		this.companyOrigin = companyOrigin;
	}


	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }


    public String getCompanyBusiness() {
		return companyBusiness;
	}


	public void setCompanyBusiness(String companyBusiness) {
		this.companyBusiness = companyBusiness;
	}


	public String getCompanyOrigin() {
		return companyOrigin;
	}


	public void setCompanyOrigin(String companyOrigin) {
		this.companyOrigin = companyOrigin;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((companyBusiness == null) ? 0 : companyBusiness.hashCode());
		result = prime * result + ((companyName == null) ? 0 : companyName.hashCode());
		result = prime * result + ((companyOrigin == null) ? 0 : companyOrigin.hashCode());
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
		Company other = (Company) obj;
		if (companyBusiness == null) {
			if (other.companyBusiness != null)
				return false;
		} else if (!companyBusiness.equals(other.companyBusiness))
			return false;
		if (companyName == null) {
			if (other.companyName != null)
				return false;
		} else if (!companyName.equals(other.companyName))
			return false;
		if (companyOrigin == null) {
			if (other.companyOrigin != null)
				return false;
		} else if (!companyOrigin.equals(other.companyOrigin))
			return false;
		return true;
	}



}

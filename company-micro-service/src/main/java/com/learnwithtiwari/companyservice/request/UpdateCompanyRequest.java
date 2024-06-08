package com.learnwithtiwari.companyservice.request;

public class UpdateCompanyRequest {

    private String companyName;

    private String companyBusiness;

    private String companyOrigin;

    public UpdateCompanyRequest(String companyName, String companyBusiness, String companyOrigin) {
        this.companyName = companyName;
        this.companyBusiness = companyBusiness;
        this.companyOrigin = companyOrigin;
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

  
}

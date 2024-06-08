package com.learnwithtiwari.raitingservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="REVIEW_RAITING_TBL")
public class ReviewRaiting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    private Integer raiting;

    private String remarks;

    private Long companyId;

    public ReviewRaiting() {
    }

	public ReviewRaiting(Long reviewId, Integer raiting, String remarks, Long companyId) {
		super();
		this.reviewId = reviewId;
		this.raiting = raiting;
		this.remarks = remarks;
		this.companyId = companyId;
	}

	public ReviewRaiting(Integer raiting, String remarks, Long companyId) {
		super();
		this.raiting = raiting;
		this.remarks = remarks;
		this.companyId = companyId;
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

	public Long getCompanyId() {
		return companyId;
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

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((raiting == null) ? 0 : raiting.hashCode());
		result = prime * result + ((remarks == null) ? 0 : remarks.hashCode());
		result = prime * result + ((reviewId == null) ? 0 : reviewId.hashCode());
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
		ReviewRaiting other = (ReviewRaiting) obj;
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
		if (reviewId == null) {
			if (other.reviewId != null)
				return false;
		} else if (!reviewId.equals(other.reviewId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReviewRaiting [reviewId=" + reviewId + ", raiting=" + raiting + ", remarks=" + remarks + ", companyId="
				+ companyId + "]";
	}

   
}

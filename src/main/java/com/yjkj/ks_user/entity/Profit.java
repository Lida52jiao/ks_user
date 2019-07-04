package com.yjkj.ks_user.entity;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "t_mp_profit")
public class Profit implements Serializable {

	private static final long serialVersionUID = 1435806600360019300L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	@Column(name = "profitModel")
	private String profitModel;
	@Column(name = "institutionId")
	private String institutionId;
	
	public Profit() {
		super();
	}

	public Profit(Long id, String profitModel, String institutionId) {
		super();
		this.id = id;
		this.profitModel = profitModel;
		this.institutionId = institutionId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProfitModel() {
		return profitModel;
	}

	public void setProfitModel(String profitModel) {
		this.profitModel = profitModel;
	}

	public String getInstitutionId() {
		return institutionId;
	}

	public void setInstitutionId(String institutionId) {
		this.institutionId = institutionId;
	}

}

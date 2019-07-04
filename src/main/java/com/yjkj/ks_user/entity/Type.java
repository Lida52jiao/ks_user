package com.yjkj.ks_user.entity;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "num_equity_enjoy")
public class Type implements Serializable {

	private static final long serialVersionUID = -8457468268074704782L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	@Column(name = "minOrNot")
	private String minOrNot;
	@Column(name = "maxOrNot")
	private String maxOrNot;
	
	public Type() {
		super();
	}

	public Type(Long id, String minOrNot, String maxOrNot) {
		super();
		this.id = id;
		this.minOrNot = minOrNot;
		this.maxOrNot = maxOrNot;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMinOrNot() {
		return minOrNot;
	}

	public void setMinOrNot(String minOrNot) {
		this.minOrNot = minOrNot;
	}

	public String getMaxOrNot() {
		return maxOrNot;
	}

	public void setMaxOrNot(String maxOrNot) {
		this.maxOrNot = maxOrNot;
	}
	
}

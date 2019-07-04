package com.yjkj.ks_user.entity;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "agentlevel_compare_mertype")
public class AgentLevelMerType implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6958463064587217417L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	@Column(name = "agentLevel")
	private String agentLevel;
	@Column(name = "merType")
	private String merType;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAgentLevel() {
		return agentLevel;
	}
	public void setAgentLevel(String agentLevel) {
		this.agentLevel = agentLevel;
	}
	public String getMerType() {
		return merType;
	}
	public void setMerType(String merType) {
		this.merType = merType;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}

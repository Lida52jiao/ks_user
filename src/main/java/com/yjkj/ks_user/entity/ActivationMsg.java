package com.yjkj.ks_user.entity;

public class ActivationMsg {
	
	private String merMp;
	
	private String activationCode;
	
	private String agentName;
	
	private String agentMerMp;

	public ActivationMsg() {
		super();
	}

	public ActivationMsg(String merMp, String activationCode, String agentName,
                         String agentMerMp) {
		super();
		this.merMp = merMp;
		this.activationCode = activationCode;
		this.agentName = agentName;
		this.agentMerMp = agentMerMp;
	}

	public String getMerMp() {
		return merMp;
	}

	public void setMerMp(String merMp) {
		this.merMp = merMp;
	}

	public String getActivationCode() {
		return activationCode;
	}

	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getAgentMerMp() {
		return agentMerMp;
	}

	public void setAgentMerMp(String agentMerMp) {
		this.agentMerMp = agentMerMp;
	}
}

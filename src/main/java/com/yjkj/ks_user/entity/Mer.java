package com.yjkj.ks_user.entity;


public class Mer {
	
	private String merChantId;
	
	private String status;
	
	public Mer() {
		super();
	}

	public Mer(String merChantId, String status) {
		super();
		this.merChantId = merChantId;
		this.status = status;
	}

	public String getMerChantId() {
		return merChantId;
	}

	public void setMerChantId(String merChantId) {
		this.merChantId = merChantId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}

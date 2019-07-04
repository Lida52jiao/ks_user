package com.yjkj.ks_user.entity;

import java.io.Serializable;
import java.util.List;

public class KSProviceArea  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4817602825388124878L;
	
	private List<Area> proviceArea;

	public List<Area> getProviceArea() {
		return proviceArea;
	}

	public void setProviceArea(List<Area> proviceArea) {
		this.proviceArea = proviceArea;
	}
	
}

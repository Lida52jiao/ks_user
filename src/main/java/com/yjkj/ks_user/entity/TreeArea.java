package com.yjkj.ks_user.entity;

import java.io.Serializable;
import java.util.List;

//在城市联动中存省用的
public class TreeArea implements Serializable {

	private static final long serialVersionUID = -2362329501221054210L;
	private Long id;
	private String parent_id;
	private String area_name;
	private String code;
	private String level;
	private String is_last;
	private List<TreeArea> list;
	public TreeArea() {
		super();
	}

	public TreeArea(Long id, String parent_id, String area_name, String code,
                    String level, String is_last) {
		super();
		this.id = id;
		this.parent_id = parent_id;
		this.area_name = area_name;
		this.code = code;
		this.level = level;
		this.is_last = is_last;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public String getArea_name() {
		return area_name;
	}

	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getIs_last() {
		return is_last;
	}

	public void setIs_last(String is_last) {
		this.is_last = is_last;
	}

	public List<TreeArea> getList() {
		return list;
	}

	public void setList(List<TreeArea> list) {
		this.list = list;
	}
}

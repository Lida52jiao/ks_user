package com.yjkj.ks_user.entity;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "t_mp_num")
public class Num implements Serializable {
	
	private static final long serialVersionUID = -7319080699351571502L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	@Column(name = "merType")
	private String merType;
	@Column(name = "num")
	private String num;
	@Column(name = "validTime")
	private String validTime;
	@Column(name = "oneMerchant")
	private String oneMerchant;
	@Column(name = "twoMerchant")
	private String twoMerchant;
	@Column(name = "ThreeMerchant")
	private String ThreeMerchant;
	@Column(name = "oneAgent")
	private String oneAgent;
	@Column(name = "twoAgent")
	private String twoAgent;
	@Column(name = "countyAgent")
	private String countyAgent;
	@Column(name = "cityAgent")
	private String cityAgent;
	@Column(name = "provinceAgent")
	private String provinceAgent;
	@Column(name = "institution")
	private String institution;
	@Column(name = "amount")
	private String amount;
	@Column(name = "level")
	private String level;
	@Column(name = "payType")
	private String payType;
	@Column(name = "vipType")
	private String vipType;
	@Column(name = "topAgent")
	private String topAgent;
	@Column(name = "levelWeight")
	private String levelWeight;
	
	public Num() {
		super();
	}

	public Num(Long id, String merType, String num, String validTime,
               String oneMerchant, String twoMerchant, String threeMerchant,
               String oneAgent, String twoAgent, String countyAgent,
               String cityAgent, String provinceAgent, String institution,
               String amount, String level, String payType, String vipType,
               String topAgent) {
		super();
		this.id = id;
		this.merType = merType;
		this.num = num;
		this.validTime = validTime;
		this.oneMerchant = oneMerchant;
		this.twoMerchant = twoMerchant;
		ThreeMerchant = threeMerchant;
		this.oneAgent = oneAgent;
		this.twoAgent = twoAgent;
		this.countyAgent = countyAgent;
		this.cityAgent = cityAgent;
		this.provinceAgent = provinceAgent;
		this.institution = institution;
		this.amount = amount;
		this.level = level;
		this.payType = payType;
		this.vipType = vipType;
		this.topAgent = topAgent;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMerType() {
		return merType;
	}

	public void setMerType(String merType) {
		this.merType = merType;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getValidTime() {
		return validTime;
	}

	public void setValidTime(String validTime) {
		this.validTime = validTime;
	}

	public String getOneMerchant() {
		return oneMerchant;
	}

	public void setOneMerchant(String oneMerchant) {
		this.oneMerchant = oneMerchant;
	}

	public String getTwoMerchant() {
		return twoMerchant;
	}

	public void setTwoMerchant(String twoMerchant) {
		this.twoMerchant = twoMerchant;
	}

	public String getThreeMerchant() {
		return ThreeMerchant;
	}

	public void setThreeMerchant(String threeMerchant) {
		ThreeMerchant = threeMerchant;
	}

	public String getOneAgent() {
		return oneAgent;
	}

	public void setOneAgent(String oneAgent) {
		this.oneAgent = oneAgent;
	}

	public String getTwoAgent() {
		return twoAgent;
	}

	public void setTwoAgent(String twoAgent) {
		this.twoAgent = twoAgent;
	}

	public String getCountyAgent() {
		return countyAgent;
	}

	public void setCountyAgent(String countyAgent) {
		this.countyAgent = countyAgent;
	}

	public String getCityAgent() {
		return cityAgent;
	}

	public void setCityAgent(String cityAgent) {
		this.cityAgent = cityAgent;
	}

	public String getProvinceAgent() {
		return provinceAgent;
	}

	public void setProvinceAgent(String provinceAgent) {
		this.provinceAgent = provinceAgent;
	}

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getVipType() {
		return vipType;
	}

	public void setVipType(String vipType) {
		this.vipType = vipType;
	}

	public String getTopAgent() {
		return topAgent;
	}

	public void setTopAgent(String topAgent) {
		this.topAgent = topAgent;
	}

	public String getLevelWeight() {
		return levelWeight;
	}

	public void setLevelWeight(String levelWeight) {
		this.levelWeight = levelWeight;
	}

}

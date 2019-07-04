package com.yjkj.ks_user.entity;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "t_mp_agentmerlevel")
public class Agentmerlevel implements Serializable {

	private static final long serialVersionUID = -740423029896793067L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	@Column(name = "levelName")
	private String levelName;
	@Column(name = "levelLogo")
	private String levelLogo;
	@Column(name = "levelBCard")
	private String levelBCard;
	@Column(name = "levelWeight")
	private String levelWeight;
	@Column(name = "merLevel")
	private String merLevel;
	@Column(name = "agentOrNot")
	private String agentOrNot;
	@Column(name = "agentStatus")
	private String agentStatus;
	@Column(name = "agentId")
	private String agentId;
	@Column(name = "showOrNot")
	private String showOrNot;
	@Column(name = "funcExplain")
	private String funcExplain;
	@Column(name = "usableModule")
	private String usableModule;
	
	public Agentmerlevel() {
		super();
	}

	public Agentmerlevel(Long id, String levelName, String levelLogo,
                         String levelBCard, String levelWeight, String agentOrNot,
                         String agentStatus, String agentId, String showOrNot,
                         String funcExplain, String usableModule) {
		super();
		this.id = id;
		this.levelName = levelName;
		this.levelLogo = levelLogo;
		this.levelBCard = levelBCard;
		this.levelWeight = levelWeight;
		this.agentOrNot = agentOrNot;
		this.agentStatus = agentStatus;
		this.agentId = agentId;
		this.showOrNot = showOrNot;
		this.funcExplain = funcExplain;
		this.usableModule = usableModule;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getLevelLogo() {
		return levelLogo;
	}

	public void setLevelLogo(String levelLogo) {
		this.levelLogo = levelLogo;
	}

	public String getLevelBCard() {
		return levelBCard;
	}

	public void setLevelBCard(String levelBCard) {
		this.levelBCard = levelBCard;
	}

	public String getLevelWeight() {
		return levelWeight;
	}

	public void setLevelWeight(String levelWeight) {
		this.levelWeight = levelWeight;
	}

	public String getMerLevel() {
		return merLevel;
	}

	public void setMerLevel(String merLevel) {
		this.merLevel = merLevel;
	}

	public String getAgentOrNot() {
		return agentOrNot;
	}

	public void setAgentOrNot(String agentOrNot) {
		this.agentOrNot = agentOrNot;
	}

	public String getAgentStatus() {
		return agentStatus;
	}

	public void setAgentStatus(String agentStatus) {
		this.agentStatus = agentStatus;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getShowOrNot() {
		return showOrNot;
	}

	public void setShowOrNot(String showOrNot) {
		this.showOrNot = showOrNot;
	}

	public String getFuncExplain() {
		return funcExplain;
	}

	public void setFuncExplain(String funcExplain) {
		this.funcExplain = funcExplain;
	}

	public String getUsableModule() {
		return usableModule;
	}

	public void setUsableModule(String usableModule) {
		this.usableModule = usableModule;
	}

}

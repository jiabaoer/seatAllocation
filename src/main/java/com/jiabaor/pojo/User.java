package com.jiabaor.pojo;

public class User {

	// 用户身份证号
	private String idCard;
	// 用户名姓名
	private String name;
	// 性别
	private Integer sex;
	// vip用户
	private Integer vip;
	// 携带婴儿
	private Integer baby;
	// 残疾
	private Integer disability;
	// 座位偏好
	private Integer preference;
	// 是否是组
	private String userGroup;
	// 用户订单
	private Seatallocation seatallocatio;

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getVip() {
		return vip;
	}

	public void setVip(Integer vip) {
		this.vip = vip;
	}

	public Integer getBaby() {
		return baby;
	}

	public void setBaby(Integer baby) {
		this.baby = baby;
	}

	public Integer getDisability() {
		return disability;
	}

	public void setDisability(Integer disability) {
		this.disability = disability;
	}

	public Integer getPreference() {
		return preference;
	}

	public void setPreference(Integer preference) {
		this.preference = preference;
	}

	public String getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(String userGroup) {
		this.userGroup = userGroup;
	}

	public Seatallocation getSeatallocation() {
		return seatallocatio;
	}

	public void setSeatallocation(Seatallocation seatallocatio) {
		this.seatallocatio = seatallocatio;
	}

}

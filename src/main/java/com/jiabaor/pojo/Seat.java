package com.jiabaor.pojo;

public class Seat {

	// 座位编号
	private String serialNumber;
	// 座位属性
	private int type;
	private String planeType;
	// vip座位
	private int vip;
	// 婴儿座位
	private int babySeat;
	// 是否是座位
	private int usable;

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getPlaneType() {
		return planeType;
	}

	public void setPlaneType(String planeType) {
		this.planeType = planeType;
	}

	public int getVip() {
		return vip;
	}

	public void setVip(int vip) {
		this.vip = vip;
	}

	public int getBabySeat() {
		return babySeat;
	}

	public void setBabySeat(int babySeat) {
		this.babySeat = babySeat;
	}

	public int getUsable() {
		return usable;
	}

	public void setUsable(int usable) {
		this.usable = usable;
	}

}

package com.jiabaor.pojo;

import com.jiabaor.pojo.Msg;

public class Msg {

	// 状态码 100-失败 200-成功
	private int code;

	// 用户要返回给浏览器的数据
	private UserListAndNonMeet extend = new UserListAndNonMeet();

	public static Msg success() {
		// 成功
		Msg msg = new Msg();
		msg.setCode(200);
		return msg;
	}

	public static Msg fail() {
		// 失败
		Msg msg = new Msg();
		msg.setCode(100);
		return msg;
	}

	public Msg add(UserListAndNonMeet extend) {
		this.setExtend(extend);
		return this;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public UserListAndNonMeet getExtend() {
		return extend;
	}

	public void setExtend(UserListAndNonMeet extend) {
		this.extend = extend;
	}

}

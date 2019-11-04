package com.jiabaor.pojo;

import java.util.List;

public class UserListAndNonMeet {

	// 页面显示的用户
	private List<User> userListShow;
	// 页面显示的不满足用户
	private List<User> noMeetUserList;
	// 页面显示分配时间
	private float time;

	public float getTime() {
		return time;
	}

	public void setTime(float time) {
		this.time = time;
	}

	public List<User> getUserListShow() {
		return userListShow;
	}

	public void setUserListShow(List<User> userListShow) {
		this.userListShow = userListShow;
	}

	public List<User> getNoMeetUserList() {
		return noMeetUserList;
	}

	public void setNoMeetUserList(List<User> noMeetUserList) {
		this.noMeetUserList = noMeetUserList;
	}

}

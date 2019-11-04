package com.jiabaor.service;

import java.util.HashMap;
import java.util.List;

import com.jiabaor.pojo.Seat;
import com.jiabaor.pojo.User;

public interface ThreePersonGroupAllocation {

	/**
	 * 三人组分配座位
	 * 
	 * @param a                       每排座位数
	 * @param groupList               用户组集合
	 * @param randomAndDissatisfylist 随机和不满足用户信息
	 * @param seatList                所有座位信息
	 * @param map                     所有用户身份证号和座位编号
	 * @param notMeetUser             页面显示不满足用户信息
	 * @param userListShow            页面显示所用户信息
	 * @return
	 */
	public boolean threeUserGroupAllocation(int a, List<User> groupList, List<User> randomAndDissatisfylist,
			List<Seat> seatList, HashMap<String, String> map, List<User> notMeetUser, List<User> userListShow);
}

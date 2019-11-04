package com.jiabaor.service;

import java.util.HashMap;
import java.util.List;

import com.jiabaor.pojo.Seat;
import com.jiabaor.pojo.User;

public interface NoMeetAndRandomAllocation {

	/**
	 * 随机分配座位
	 * 
	 * @param randomAndDissatisfylist 随机和不满足用户信息
	 * @param seatList                所有座位信息
	 * @param map                     所有用户身份证号和座位编号
	 * @param userListShow            页面显示所用户信息
	 */
	public void randomAllocationSeat(List<User> randomAndDissatisfylist, List<Seat> seatList,
			HashMap<String, String> map, List<User> userListShow);
}

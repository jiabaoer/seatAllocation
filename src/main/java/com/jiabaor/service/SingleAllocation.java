package com.jiabaor.service;

import java.util.HashMap;
import java.util.List;

import com.jiabaor.pojo.Seat;
import com.jiabaor.pojo.User;

public interface SingleAllocation {

	/**
	 * 整机单人分配座位
	 * 
	 * @param i                       用户的索引编号
	 * @param userList                所有用户信息
	 * @param randomAndDissatisfylist 随机和不满足用户
	 * @param seatList                所有座位信息
	 * @param map                     用户身份证和座位编号
	 * @param notMeetUser             页面显示不满足用户
	 * @param userListShow            页面显示所有用户信息
	 * @return
	 */
	public boolean oneAllocationSeat(int i, List<User> userList, List<User> randomAndDissatisfylist,
			List<Seat> seatList, HashMap<String, String> map, List<User> notMeetUser, List<User> userListShow);

	/**
	 * 单个用户分配
	 * 
	 * @param user         单个用户信息
	 * @param userListShow 所有座位信息
	 * @param seatList 
	 */
	public void singlSeatAllocation(User user, List<User> userListShow, List<Seat> seatList);

}

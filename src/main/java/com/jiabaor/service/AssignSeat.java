package com.jiabaor.service;

import java.util.HashMap;
import java.util.List;

import com.jiabaor.pojo.Seat;
import com.jiabaor.pojo.User;

public interface AssignSeat {

	/**
	 * 单人分配座位
	 * 
	 * @param i            用户索引编号
	 * @param j            座位索引编号
	 * @param userList     所有用户信息
	 * @param seatList     所有座位信息
	 * @param map          身份证号和座位编号
	 * @param userListShow 页面显示用户信息
	 */
	void assignSeatByUser(int i, int j, List<User> userList, List<Seat> seatList, HashMap<String, String> map,
			List<User> userListShow);

	/**
	 * 组分配座位
	 * 
	 * @param preferenceList          存放一组用户的个人偏好
	 * @param excludingRandomList     存放一组用户的个人偏好除去随机
	 * @param meetGroupSeatList       存放所有满足的座位
	 * @param seatAttributeList       存放一组座位的属性
	 * @param groupSeatList           存放一组座位
	 * @param groupList               用户组集合
	 * @param randomAndDissatisfylist 随机和不满足用户
	 * @param seatList                所有座位信息
	 * @param map                     所有用户身份证号和座位编号
	 * @param notMeetUser             页面显示不满足用户信息
	 * @param userListShow            页面显示所有用户信息
	 * @return true分配成功，false分配失败
	 */
	boolean groupAssignSeat(List<Integer> preferenceList, List<Integer> excludingRandomList,
			List<Seat> meetGroupSeatList, List<Integer> seatAttributeList, List<Seat> groupSeatList,
			List<User> groupList, List<User> randomAndDissatisfylist, List<Seat> seatList, HashMap<String, String> map,
			List<User> notMeetUser, List<User> userListShow);

}

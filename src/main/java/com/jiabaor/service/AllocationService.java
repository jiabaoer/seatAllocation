package com.jiabaor.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.jiabaor.pojo.User;
import com.jiabaor.pojo.UserListAndNonMeet;

public interface AllocationService {

	/**
	 * 整机分配
	 * 
	 * @param userListAndNoMeet 页面显示所有用户信息和不满足用户信息
	 * @param planeType         机型号
	 * @param a                 每排座位数
	 * @param request           请求
	 * @return 页面显示的数据
	 */
	public boolean allocationSeatList(UserListAndNonMeet userListAndNoMeet, String planeType, int a,
			HttpServletRequest request);

	/**
	 * 单人分配
	 * 
	 * @param planeType 机型号
	 * @param user  单个用户信息
	 * @return 页面显示的数据
	 */
	public UserListAndNonMeet oneAllAllocation(String planeType, User user);

	/**
	 * 组分配
	 * 
	 * @param a         每排座位数
	 * @param planeType 机型号
	 * @param groupList 组用户信息
	 * @return 页面显示的数据
	 */
	public UserListAndNonMeet moreAllAllocation(int a, String planeType, List<User> groupList);

	/**
	 * 更新用户的座位编号
	 * 
	 * @param idCard 用户身份证号
	 * @param seat   座位编号
	 */
	public void insertSeatAllocation(String idCard, String seat);

}

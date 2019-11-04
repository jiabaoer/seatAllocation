package com.jiabaor.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiabaor.pojo.Seat;
import com.jiabaor.pojo.User;
import com.jiabaor.service.AssignSeat;
import com.jiabaor.service.SingleAllocation;
import com.jiabaor.service.TowPersonGroupAllocation;

@Service
public class TowPersonGroupAllocationImpl implements TowPersonGroupAllocation {

	@Autowired
	private SingleAllocation singleAllocation;
	@Autowired
	private AssignSeat assignSeat;

	/**
	 * 1.遍历所有座位的集合(seatList) 
	 * 2.找二个相邻的座位存到meetGroupSeatList集合，如果没有相邻座位就调用单人分配方法
	 */
	// 二人组
	public boolean twoUserGroupAllocation(int a, List<User> groupList, List<User> randomAndDissatisfylist,
			List<Seat> seatList, HashMap<String, String> map, List<User> notMeetUser, List<User> userListShow) {
		// 存放所有满足的座位
		List<Seat> meetGroupSeatList = new ArrayList<Seat>();
		// 存放一组用户的个人偏好
		List<Integer> preferenceList = new ArrayList<Integer>();
		// 存放一组用户的个人偏好除去随机
		List<Integer> excludingRandomList = new ArrayList<Integer>();
		// 存放一组座位的属性
		List<Integer> seatAttributeList = new ArrayList<Integer>();
		// 存放一组座位
		List<Seat> groupSeatList = new ArrayList<Seat>();
		boolean bl = false;
		for (int j1 = 0; j1 < seatList.size(); j1++) {
			// 找连续座位
			if (j1 < seatList.size() && (j1 + 1) < seatList.size()) {
				// 判断是否被占
				if ((!"0".equals(seatList.get(j1).getSerialNumber()) && seatList.get(j1).getUsable() == 1)
						&& (!"0".equals(seatList.get(j1 + 1).getSerialNumber())
								&& seatList.get(j1 + 1).getUsable() == 1)) {
					// 分配座位
					meetGroupSeatList.add(seatList.get(j1));
					meetGroupSeatList.add(seatList.get(j1 + 1));
				}
			}
			if (j1 < seatList.size() && (j1 + a) < seatList.size()) {
				// 判断是否被占
				if ((!"0".equals(seatList.get(j1).getSerialNumber()) && seatList.get(j1).getUsable() == 1)
						&& (!"0".equals(seatList.get(j1 + a).getSerialNumber())
								&& seatList.get(j1 + a).getUsable() == 1)
						&& (j1 + a) < seatList.size()) {
					// 分配座位
					meetGroupSeatList.add(seatList.get(j1));
					meetGroupSeatList.add(seatList.get(j1 + a));
				}
			}
		}
		// 判断集合有没有相邻座位
		if (meetGroupSeatList.size() > 0) {
			bl = assignSeat.groupAssignSeat(preferenceList, excludingRandomList, meetGroupSeatList, seatAttributeList,
					groupSeatList, groupList, randomAndDissatisfylist, seatList, map, notMeetUser, userListShow);
			return bl;
		} else {
			// 没有相邻座位,分开坐
			for (int i = 0; i < groupList.size(); i++) {
				singleAllocation.oneAllocationSeat(i, groupList, randomAndDissatisfylist, seatList, map, notMeetUser,
						userListShow);
			}
			bl = false;
			return bl;
		}
	}
}

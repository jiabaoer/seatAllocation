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
import com.jiabaor.service.ThreePersonGroupAllocation;

@Service
public class ThreePersonGroupAllocationImpl implements ThreePersonGroupAllocation {

	@Autowired
	private SingleAllocation singleAllocation;
	@Autowired
	private AssignSeat assignSeat;

	/**
	 * 1.遍历所有座位的集合(seatList) 
	 * 2.找三个相邻的座位存到meetGroupSeatList集合，如果没有相邻座位就调用单人分配方法
	 */
	// 三人组
	public boolean threeUserGroupAllocation(int a, List<User> groupList, List<User> randomAndDissatisfylist,
			List<Seat> seatList, HashMap<String, String> map, List<User> notMeetUser, List<User> userListShow) {
		// 存放所有满足的座位
		List<Seat> meetGroupSeatList = new ArrayList<Seat>();
		// 存放一组用户的个人偏好
		List<Integer> preferenceList = new ArrayList<Integer>();
		// 存放一组用户的个人偏好随机
		List<Integer> excludingRandomList = new ArrayList<Integer>();
		// 存放一组座位的属性
		List<Integer> seatAttributeList = new ArrayList<Integer>();
		// 存放一组座位
		List<Seat> groupSeatList = new ArrayList<Seat>();
		boolean bl = false;
		// 找座位
		for (int j1 = 0; j1 < seatList.size(); j1++) {
			// 连续座位
			if (j1 < seatList.size() && (j1 + 1) < seatList.size() && (j1 + 2) < seatList.size()) {
				// 连续座位
				if ((!"0".equals(seatList.get(j1).getSerialNumber()) && seatList.get(j1).getUsable() == 1)
						&& (!"0".equals(seatList.get(j1 + 1).getSerialNumber())
								&& seatList.get(j1 + 1).getUsable() == 1)
						&& (!"0".equals(seatList.get(j1 + 2).getSerialNumber())
								&& seatList.get(j1 + 2).getUsable() == 1)) {
					meetGroupSeatList.add(seatList.get(j1));
					meetGroupSeatList.add(seatList.get(j1 + 1));
					meetGroupSeatList.add(seatList.get(j1 + 2));
				}
			}
			if (j1 < seatList.size() && (j1 + a) < seatList.size() && (j1 + (a + 1)) < seatList.size()) {
				// 前一个后两个
				if ((0 <= j1 % a && j1 % a < a) && (0 <= (j1 + a) % a && (j1 + a) % a < a)
						&& (1 <= (j1 + (a + 1)) % a && (j1 + (a + 1)) % a < a)) {
					// 判断是否被占
					if ((!"0".equals(seatList.get(j1).getSerialNumber()) && seatList.get(j1).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + a).getSerialNumber())
									&& seatList.get(j1 + a).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + (a + 1)).getSerialNumber())
									&& seatList.get(j1 + (a + 1)).getUsable() == 1)) {
						meetGroupSeatList.add(seatList.get(j1));
						meetGroupSeatList.add(seatList.get(j1 + a));
						meetGroupSeatList.add(seatList.get(j1 + (a + 1)));
					}
				}
			}
			if ((j1 + (a + 1)) < seatList.size() && (j1 + a) < seatList.size() && (j1 + (a + 1)) < seatList.size()) {
				// 前一个后两个
				if ((1 <= (j1 + 1) % a && (j1 + 1) % a < a) && (0 <= (j1 + a) % a && (j1 + a) % a < a)
						&& (1 <= (j1 + (a + 1)) % a && (j1 + (a + 1)) % a < a)) {
					// 判断是否被占
					if ((!"0".equals(seatList.get(j1 + 1).getSerialNumber()) && seatList.get(j1 + 1).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + a).getSerialNumber())
									&& seatList.get(j1 + a).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + (a + 1)).getSerialNumber())
									&& seatList.get(j1 + (a + 1)).getUsable() == 1)) {
						meetGroupSeatList.add(seatList.get(j1 + 1));
						meetGroupSeatList.add(seatList.get(j1 + a));
						meetGroupSeatList.add(seatList.get(j1 + (a + 1)));
					}
				}
			}
			if (j1 < seatList.size() && (j1 + 1) < seatList.size() && (j1 + a) < seatList.size()) {
				// 前两个后一个
				if ((0 <= j1 % a && j1 % a < a) && (1 <= (j1 + 1) % a && (j1 + 1) % a < a)
						&& (0 <= (j1 + a) % a && (j1 + a) % a < a)) {
					// 判断是否被占
					if ((!"0".equals(seatList.get(j1).getSerialNumber()) && seatList.get(j1).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + 1).getSerialNumber())
									&& seatList.get(j1 + 1).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + a).getSerialNumber())
									&& seatList.get(j1 + a).getUsable() == 1)) {
						meetGroupSeatList.add(seatList.get(j1));
						meetGroupSeatList.add(seatList.get(j1 + 1));
						meetGroupSeatList.add(seatList.get(j1 + a));
					}
				}
			}
			if (j1 < seatList.size() && (j1 + 1) < seatList.size() && (j1 + (a + 1)) < seatList.size()) {
				// 前两个后一个
				if ((0 <= j1 % a && j1 % a < a) && (1 <= (j1 + 1) % a && (j1 + 1) % a < a)
						&& (1 <= (j1 + (a + 1)) % a && (j1 + (a + 1)) % a < a) && (j1 + (a + 1)) < seatList.size()) {
					// 判断是否被占
					if ((!"0".equals(seatList.get(j1).getSerialNumber()) && seatList.get(j1).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + 1).getSerialNumber())
									&& seatList.get(j1 + 1).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + (a + 1)).getSerialNumber())
									&& seatList.get(j1 + (a + 1)).getUsable() == 1)) {
						meetGroupSeatList.add(seatList.get(j1));
						meetGroupSeatList.add(seatList.get(j1 + 1));
						meetGroupSeatList.add(seatList.get(j1 + (a + 1)));
						continue;
					}
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

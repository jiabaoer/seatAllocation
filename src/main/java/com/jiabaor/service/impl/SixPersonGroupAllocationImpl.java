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
import com.jiabaor.service.SixPersonGroupAllocation;

@Service
public class SixPersonGroupAllocationImpl implements SixPersonGroupAllocation {

	@Autowired
	private SingleAllocation singleAllocation;
	@Autowired
	private AssignSeat assignSeat;

	/**
	 * 1.遍历所有座位的集合(seatList) 
	 * 2.找六个相邻的座位存到meetGroupSeatList集合，如果没有相邻座位就调用单人分配方法
	 */
	// 六人组
	public boolean sixUserGroupAllocation(int a, List<User> groupList, List<User> randomAndDissatisfylist,
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
			if (j1 < seatList.size() && (j1 + 1) < seatList.size() && (j1 + 2) < seatList.size()
					&& (j1 + 3) < seatList.size() && (j1 + 4) < seatList.size() && (j1 + 5) < seatList.size()) {
				// 判断是否被占
				if ((!"0".equals(seatList.get(j1).getSerialNumber()) && seatList.get(j1).getUsable() == 1)
						&& (!"0".equals(seatList.get(j1 + 1).getSerialNumber())
								&& seatList.get(j1 + 1).getUsable() == 1)
						&& (!"0".equals(seatList.get(j1 + 2).getSerialNumber())
								&& seatList.get(j1 + 2).getUsable() == 1)
						&& (!"0".equals(seatList.get(j1 + 3).getSerialNumber())
								&& seatList.get(j1 + 3).getUsable() == 1)
						&& (!"0".equals(seatList.get(j1 + 4).getSerialNumber())
								&& seatList.get(j1 + 4).getUsable() == 1)
						&& (!"0".equals(seatList.get(j1 + 5).getSerialNumber())
								&& seatList.get(j1 + 5).getUsable() == 1)) {
					meetGroupSeatList.add(seatList.get(j1));
					meetGroupSeatList.add(seatList.get(j1 + 1));
					meetGroupSeatList.add(seatList.get(j1 + 2));
					meetGroupSeatList.add(seatList.get(j1 + 3));
					meetGroupSeatList.add(seatList.get(j1 + 4));
					meetGroupSeatList.add(seatList.get(j1 + 5));
				}
			}
			// 前三个后三个
			if (j1 < seatList.size() && (j1 + 1) < seatList.size() && (j1 + 2) < seatList.size()
					&& (j1 + a) < seatList.size() && (j1 + (a + 1)) < seatList.size()
					&& (j1 + (a + 2)) < seatList.size()) {
				if ((0 <= j1 % a && j1 % a < a) && (1 <= (j1 + 1) % a && (j1 + 1) % a < a)
						&& (2 <= (j1 + 2) % a && (j1 + 2) % a < a) && (0 <= (j1 + a) % a && (j1 + a) % a < a)
						&& (1 <= (j1 + (a + 1)) % a && (j1 + (a + 1)) % a < a)
						&& (2 <= (j1 + (a + 2)) % a && (j1 + (a + 2)) % a < a)) {
					// 判断是否被占
					if ((!"0".equals(seatList.get(j1).getSerialNumber()) && seatList.get(j1).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + 1).getSerialNumber())
									&& seatList.get(j1 + 1).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + 2).getSerialNumber())
									&& seatList.get(j1 + 2).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + a).getSerialNumber())
									&& seatList.get(j1 + a).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + (a + 1)).getSerialNumber())
									&& seatList.get(j1 + (a + 1)).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + (a + 2)).getSerialNumber())
									&& seatList.get(j1 + (a + 2)).getUsable() == 1)) {
						meetGroupSeatList.add(seatList.get(j1));
						meetGroupSeatList.add(seatList.get(j1 + 1));
						meetGroupSeatList.add(seatList.get(j1 + 2));
						meetGroupSeatList.add(seatList.get(j1 + a));
						meetGroupSeatList.add(seatList.get(j1 + (a + 1)));
						meetGroupSeatList.add(seatList.get(j1 + (a + 2)));
					}
				}
			}
			// 前二后四
			if (j1 < seatList.size() && (j1 + 1) < seatList.size() && (j1 + a) < seatList.size()
					&& (j1 + (a + 1)) < seatList.size() && (j1 + (a + 2)) < seatList.size()
					&& (j1 + (a + 3)) < seatList.size()) {
				if ((0 <= j1 % a && j1 % a < a) && (1 <= (j1 + 1) % a && (j1 + 1) % a < a)
						&& (0 <= (j1 + a) % a && (j1 + a) % a < a)
						&& (1 <= (j1 + (a + 1)) % a && (j1 + (a + 1)) % a < a)
						&& (2 <= (j1 + (a + 2)) % a && (j1 + (a + 2)) % a < a)
						&& (3 <= (j1 + (a + 3)) % a && (j1 + (a + 3)) % a < a)) {
					// 判断是否被占
					if ((!"0".equals(seatList.get(j1).getSerialNumber()) && seatList.get(j1).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + 1).getSerialNumber())
									&& seatList.get(j1 + 1).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + a).getSerialNumber())
									&& seatList.get(j1 + a).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + (a + 1)).getSerialNumber())
									&& seatList.get(j1 + (a + 1)).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + (a + 2)).getSerialNumber())
									&& seatList.get(j1 + (a + 2)).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + (a + 3)).getSerialNumber())
									&& seatList.get(j1 + (a + 3)).getUsable() == 1)) {
						meetGroupSeatList.add(seatList.get(j1));
						meetGroupSeatList.add(seatList.get(j1 + 1));
						meetGroupSeatList.add(seatList.get(j1 + a));
						meetGroupSeatList.add(seatList.get(j1 + (a + 1)));
						meetGroupSeatList.add(seatList.get(j1 + (a + 2)));
						meetGroupSeatList.add(seatList.get(j1 + (a + 3)));
					}
				}
			}
			// 前二后四
			if ((j1 + 1) < seatList.size() && (j1 + 2) < seatList.size() && (j1 + a) < seatList.size()
					&& (j1 + (a + 1)) < seatList.size() && (j1 + (a + 2)) < seatList.size()
					&& (j1 + (a + 3)) < seatList.size()) {
				if ((1 <= (j1 + 1) % a && (j1 + 1) % a < a) && (2 < +(j1 + 2) % a && (j1 + 2) % a < a)
						&& (0 <= (j1 + a) % a && (j1 + a) % a < a)
						&& (1 <= (j1 + (a + 1)) % a && (j1 + (a + 1)) % a < a)
						&& (2 <= (j1 + (a + 2)) % a && (j1 + (a + 2)) % a < a)
						&& (3 <= (j1 + (a + 3)) % a && (j1 + (a + 3)) % a < a)) {
					// 判断是否被占
					if ((!"0".equals(seatList.get(j1 + 1).getSerialNumber()) && seatList.get(j1 + 1).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + 2).getSerialNumber())
									&& seatList.get(j1 + 2).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + a).getSerialNumber())
									&& seatList.get(j1 + a).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + (a + 1)).getSerialNumber())
									&& seatList.get(j1 + (a + 1)).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + (a + 2)).getSerialNumber())
									&& seatList.get(j1 + (a + 2)).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + (a + 3)).getSerialNumber())
									&& seatList.get(j1 + (a + 3)).getUsable() == 1)) {
						meetGroupSeatList.add(seatList.get(j1 + 1));
						meetGroupSeatList.add(seatList.get(j1 + 2));
						meetGroupSeatList.add(seatList.get(j1 + a));
						meetGroupSeatList.add(seatList.get(j1 + (a + 1)));
						meetGroupSeatList.add(seatList.get(j1 + (a + 2)));
						meetGroupSeatList.add(seatList.get(j1 + (a + 3)));
					}
				}
			}
			// 前二后四
			if ((j1 + 2) < seatList.size() && (j1 + 3) < seatList.size() && (j1 + a) < seatList.size()
					&& (j1 + (a + 1)) < seatList.size() && (j1 + (a + 2)) < seatList.size()
					&& (j1 + (a + 3)) < seatList.size()) {
				if ((2 <= (j1 + 2) % a && (j1 + 2) % a < a) && (3 <= (j1 + 3) % a && (j1 + 3) % a < a)
						&& (0 <= (j1 + a) % a && (j1 + a) % a < a)
						&& (1 <= (j1 + (a + 1)) % a && (j1 + (a + 1)) % a < a)
						&& (2 <= (j1 + (a + 2)) % a && (j1 + (a + 2)) % a < a)
						&& (3 <= (j1 + (a + 3)) % a && (j1 + (a + 3)) % a < a)) {
					// 判断是否被占
					if ((!"0".equals(seatList.get(j1 + 2).getSerialNumber()) && seatList.get(j1 + 2).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + 3).getSerialNumber())
									&& seatList.get(j1 + 3).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + a).getSerialNumber())
									&& seatList.get(j1 + a).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + (a + 1)).getSerialNumber())
									&& seatList.get(j1 + (a + 1)).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + (a + 2)).getSerialNumber())
									&& seatList.get(j1 + (a + 2)).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + (a + 3)).getSerialNumber())
									&& seatList.get(j1 + (a + 3)).getUsable() == 1)) {
						meetGroupSeatList.add(seatList.get(j1 + 2));
						meetGroupSeatList.add(seatList.get(j1 + 3));
						meetGroupSeatList.add(seatList.get(j1 + a));
						meetGroupSeatList.add(seatList.get(j1 + (a + 1)));
						meetGroupSeatList.add(seatList.get(j1 + (a + 2)));
						meetGroupSeatList.add(seatList.get(j1 + (a + 3)));
					}
				}
			}
			// 前四后二
			if (j1 < seatList.size() && (j1 + 1) < seatList.size() && (j1 + 2) < seatList.size()
					&& (j1 + 3) < seatList.size() && (j1 + a) < seatList.size() && (j1 + (a + 1)) < seatList.size()) {

				if ((0 <= j1 % a && j1 % a < a) && (1 <= (j1 + 1) % a && (j1 + 1) % a < a)
						&& (2 <= (j1 + 2) % a && (j1 + 2) % a < a) && (3 <= (j1 + 3) % a && (j1 + 3) % a < a)
						&& (0 <= (j1 + a) % a && (j1 + a) % a < a)
						&& (1 <= (j1 + (a + 1)) % a && (j1 + (a + 1)) % a < a)) {
					// 判断是否被占
					if ((!"0".equals(seatList.get(j1).getSerialNumber()) && seatList.get(j1).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + 1).getSerialNumber())
									&& seatList.get(j1 + 1).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + 2).getSerialNumber())
									&& seatList.get(j1 + 2).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + 3).getSerialNumber())
									&& seatList.get(j1 + 3).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + a).getSerialNumber())
									&& seatList.get(j1 + a).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + (a + 1)).getSerialNumber())
									&& seatList.get(j1 + (a + 1)).getUsable() == 1)) {
						meetGroupSeatList.add(seatList.get(j1));
						meetGroupSeatList.add(seatList.get(j1 + 1));
						meetGroupSeatList.add(seatList.get(j1 + 2));
						meetGroupSeatList.add(seatList.get(j1 + 3));
						meetGroupSeatList.add(seatList.get(j1 + a));
						meetGroupSeatList.add(seatList.get(j1 + (a + 1)));
					}
				}
			}
			// 前四后二
			if (j1 < seatList.size() && (j1 + 1) < seatList.size() && (j1 + 2) < seatList.size()
					&& (j1 + 3) < seatList.size() && (j1 + (a + 1)) < seatList.size()
					&& (j1 + (a + 2)) < seatList.size()) {
				if ((0 <= j1 % a && j1 % a < a) && (1 <= (j1 + 1) % a && (j1 + 1) % a < a)
						&& (2 <= (j1 + 2) % a && (j1 + 2) % a < a) && (3 <= (j1 + 3) % a && (j1 + 3) % a < a)
						&& (1 <= (j1 + (a + 1)) % a && (j1 + (a + 1)) % a < a)
						&& (2 <= (j1 + (a + 2)) % a && (j1 + (a + 2)) % a < a)) {
					// 判断是否被占
					if ((!"0".equals(seatList.get(j1).getSerialNumber()) && seatList.get(j1).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + 1).getSerialNumber())
									&& seatList.get(j1 + 1).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + 2).getSerialNumber())
									&& seatList.get(j1 + 2).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + 3).getSerialNumber())
									&& seatList.get(j1 + 3).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + (a + 1)).getSerialNumber())
									&& seatList.get(j1 + (a + 1)).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + (a + 2)).getSerialNumber())
									&& seatList.get(j1 + (a + 2)).getUsable() == 1)) {
						meetGroupSeatList.add(seatList.get(j1));
						meetGroupSeatList.add(seatList.get(j1 + 1));
						meetGroupSeatList.add(seatList.get(j1 + 2));
						meetGroupSeatList.add(seatList.get(j1 + 3));
						meetGroupSeatList.add(seatList.get(j1 + (a + 1)));
						meetGroupSeatList.add(seatList.get(j1 + (a + 2)));
					}
				}
			}
			// 前四后二
			if (j1 < seatList.size() && (j1 + 1) < seatList.size() && (j1 + 2) < seatList.size()
					&& (j1 + 3) < seatList.size() && (j1 + (a + 2)) < seatList.size()
					&& (j1 + (a + 3)) < seatList.size()) {
				if ((0 <= j1 % a && j1 % a < a) && (1 <= (j1 + 1) % a && (j1 + 1) % a < a)
						&& (2 <= (j1 + 2) % a && (j1 + 2) % a < a) && (3 <= (j1 + 3) % a && (j1 + 3) % a < a)
						&& (2 <= (j1 + (a + 2)) % a && (j1 + (a + 2)) % a < a)
						&& (3 <= (j1 + (a + 3)) % a && (j1 + (a + 3)) % a < a)) {
					// 判断是否被占
					if ((!"0".equals(seatList.get(j1).getSerialNumber()) && seatList.get(j1).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + 1).getSerialNumber())
									&& seatList.get(j1 + 1).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + 2).getSerialNumber())
									&& seatList.get(j1 + 2).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + 3).getSerialNumber())
									&& seatList.get(j1 + 3).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + (a + 2)).getSerialNumber())
									&& seatList.get(j1 + (a + 2)).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + (a + 3)).getSerialNumber())
									&& seatList.get(j1 + (a + 3)).getUsable() == 1)) {
						meetGroupSeatList.add(seatList.get(j1));
						meetGroupSeatList.add(seatList.get(j1 + 1));
						meetGroupSeatList.add(seatList.get(j1 + 2));
						meetGroupSeatList.add(seatList.get(j1 + 3));
						meetGroupSeatList.add(seatList.get(j1 + (a + 2)));
						meetGroupSeatList.add(seatList.get(j1 + (a + 3)));
					}
				}
			}
			// 前一后五
			if (j1 < seatList.size() && (j1 + a) < seatList.size() && (j1 + (a + 1)) < seatList.size()
					&& (j1 + (a + 2)) < seatList.size() && (j1 + (a + 3)) < seatList.size()
					&& (j1 + (a + 4)) < seatList.size()) {

				if ((0 <= j1 % a && j1 % a < a) && (0 <= (j1 + a) % a && (j1 + a) % a < a)
						&& (1 <= (j1 + (a + 1)) % a && (j1 + (a + 1)) % a < a)
						&& (2 <= (j1 + (a + 2)) % a && (j1 + (a + 2)) % a < a)
						&& (3 <= (j1 + (a + 3)) % a && (j1 + (a + 3)) % a < a)
						&& (4 <= (j1 + (a + 4)) % a && (j1 + (a + 4)) % a < a)) {
					// 判断是否被占
					if ((!"0".equals(seatList.get(j1).getSerialNumber()) && seatList.get(j1).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + a).getSerialNumber())
									&& seatList.get(j1 + a).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + (a + 1)).getSerialNumber())
									&& seatList.get(j1 + (a + 1)).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + (a + 2)).getSerialNumber())
									&& seatList.get(j1 + (a + 2)).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + (a + 3)).getSerialNumber())
									&& seatList.get(j1 + (a + 3)).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + (a + 4)).getSerialNumber())
									&& seatList.get(j1 + (a + 4)).getUsable() == 1)) {
						meetGroupSeatList.add(seatList.get(j1));
						meetGroupSeatList.add(seatList.get(j1 + a));
						meetGroupSeatList.add(seatList.get(j1 + (a + 1)));
						meetGroupSeatList.add(seatList.get(j1 + (a + 2)));
						meetGroupSeatList.add(seatList.get(j1 + (a + 3)));
						meetGroupSeatList.add(seatList.get(j1 + (a + 4)));
					}
				}
			}
			// 前一后五
			if ((j1 + 1) < seatList.size() && (j1 + a) < seatList.size() && (j1 + (a + 1)) < seatList.size()
					&& (j1 + (a + 2)) < seatList.size() && (j1 + (a + 3)) < seatList.size()
					&& (j1 + (a + 4)) < seatList.size()) {
				if ((1 <= (j1 + 1) % a && (j1 + 1) % a < a) && (0 <= (j1 + a) % a && (j1 + a) % a < a)
						&& (1 <= (j1 + (a + 1)) % a && (j1 + (a + 1)) % a < a)
						&& (2 <= (j1 + (a + 2)) % a && (j1 + (a + 2)) % a < a)
						&& (3 <= (j1 + (a + 3)) % a && (j1 + (a + 3)) % a < a)
						&& (4 <= (j1 + (a + 4)) % a && (j1 + (a + 4)) % a < a)) {
					// 判断是否被占
					if ((!"0".equals(seatList.get(j1 + 1).getSerialNumber()) && seatList.get(j1 + 1).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + a).getSerialNumber())
									&& seatList.get(j1 + a).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + (a + 1)).getSerialNumber())
									&& seatList.get(j1 + (a + 1)).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + (a + 2)).getSerialNumber())
									&& seatList.get(j1 + (a + 2)).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + (a + 3)).getSerialNumber())
									&& seatList.get(j1 + (a + 3)).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + (a + 4)).getSerialNumber())
									&& seatList.get(j1 + (a + 4)).getUsable() == 1)) {
						meetGroupSeatList.add(seatList.get(j1 + 1));
						meetGroupSeatList.add(seatList.get(j1 + a));
						meetGroupSeatList.add(seatList.get(j1 + (a + 1)));
						meetGroupSeatList.add(seatList.get(j1 + (a + 2)));
						meetGroupSeatList.add(seatList.get(j1 + (a + 3)));
						meetGroupSeatList.add(seatList.get(j1 + (a + 4)));
					}
				}
			}
			// 前一后五
			if ((j1 + 2) < seatList.size() && (j1 + a) < seatList.size() && (j1 + (a + 1)) < seatList.size()
					&& (j1 + (a + 2)) < seatList.size() && (j1 + (a + 3)) < seatList.size()
					&& (j1 + (a + 4)) < seatList.size()) {
				if ((2 <= (j1 + 2) % a && (j1 + 2) % a < a) && (0 <= (j1 + a) % a && (j1 + a) % a < a)
						&& (1 <= (j1 + (a + 1)) % a && (j1 + (a + 1)) % a < a)
						&& (2 <= (j1 + (a + 2)) % a && (j1 + (a + 2)) % a < a)
						&& (3 <= (j1 + (a + 3)) % a && (j1 + (a + 3)) % a < a)
						&& (4 <= (j1 + (a + 4)) % a && (j1 + (a + 4)) % a < a)) {
					// 判断是否被占
					if ((!"0".equals(seatList.get(j1 + 2).getSerialNumber()) && seatList.get(j1 + 2).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + a).getSerialNumber())
									&& seatList.get(j1 + a).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + (a + 1)).getSerialNumber())
									&& seatList.get(j1 + (a + 1)).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + (a + 2)).getSerialNumber())
									&& seatList.get(j1 + (a + 2)).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + (a + 3)).getSerialNumber())
									&& seatList.get(j1 + (a + 3)).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + (a + 4)).getSerialNumber())
									&& seatList.get(j1 + (a + 4)).getUsable() == 1)) {
						meetGroupSeatList.add(seatList.get(j1 + 2));
						meetGroupSeatList.add(seatList.get(j1 + a));
						meetGroupSeatList.add(seatList.get(j1 + (a + 1)));
						meetGroupSeatList.add(seatList.get(j1 + (a + 2)));
						meetGroupSeatList.add(seatList.get(j1 + (a + 3)));
						meetGroupSeatList.add(seatList.get(j1 + (a + 4)));
					}
				}
			}
			// 前一后五
			if ((j1 + 3) < seatList.size() && (j1 + a) < seatList.size() && (j1 + (a + 1)) < seatList.size()
					&& (j1 + (a + 2)) < seatList.size() && (j1 + (a + 3)) < seatList.size()
					&& (j1 + (a + 4)) < seatList.size()) {
				if ((3 <= (j1 + 3) % a && (j1 + 3) % a < a) && (0 <= (j1 + a) % a && (j1 + a) % a < a)
						&& (1 <= (j1 + (a + 1)) % a && (j1 + (a + 1)) % a < a)
						&& (2 <= (j1 + (a + 2)) % a && (j1 + (a + 2)) % a < a)
						&& (3 <= (j1 + (a + 3)) % a && (j1 + (a + 3)) % a < a)
						&& (4 <= (j1 + (a + 4)) % a && (j1 + (a + 4)) % a < a)) {
					// 判断是否被占
					if ((!"0".equals(seatList.get(j1 + 3).getSerialNumber()) && seatList.get(j1 + 3).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + a).getSerialNumber())
									&& seatList.get(j1 + a).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + (a + 1)).getSerialNumber())
									&& seatList.get(j1 + (a + 1)).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + (a + 2)).getSerialNumber())
									&& seatList.get(j1 + (a + 2)).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + (a + 3)).getSerialNumber())
									&& seatList.get(j1 + (a + 3)).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + (a + 4)).getSerialNumber())
									&& seatList.get(j1 + (a + 4)).getUsable() == 1)) {
						meetGroupSeatList.add(seatList.get(j1 + 3));
						meetGroupSeatList.add(seatList.get(j1 + a));
						meetGroupSeatList.add(seatList.get(j1 + (a + 1)));
						meetGroupSeatList.add(seatList.get(j1 + (a + 2)));
						meetGroupSeatList.add(seatList.get(j1 + (a + 3)));
						meetGroupSeatList.add(seatList.get(j1 + (a + 4)));
					}
				}
			}
			// 前一后五
			if ((j1 + 4) < seatList.size() && (j1 + a) < seatList.size() && (j1 + (a + 1)) < seatList.size()
					&& (j1 + (a + 2)) < seatList.size() && (j1 + (a + 3)) < seatList.size()
					&& (j1 + (a + 4)) < seatList.size()) {
				if ((4 <= (j1 + 4) % a && (j1 + 4) % a < a) && (0 <= (j1 + a) % a && (j1 + a) % a < a)
						&& (1 <= (j1 + (a + 1)) % a && (j1 + (a + 1)) % a < a)
						&& (2 <= (j1 + (a + 2)) % a && (j1 + (a + 2)) % a < a)
						&& (3 <= (j1 + (a + 3)) % a && (j1 + (a + 3)) % a < a)
						&& (4 <= (j1 + (a + 4)) % a && (j1 + (a + 4)) % a < a)) {
					// 判断是否被占
					if ((!"0".equals(seatList.get(j1 + 4).getSerialNumber()) && seatList.get(j1 + 4).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + a).getSerialNumber())
									&& seatList.get(j1 + a).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + (a + 1)).getSerialNumber())
									&& seatList.get(j1 + (a + 1)).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + (a + 2)).getSerialNumber())
									&& seatList.get(j1 + (a + 2)).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + (a + 3)).getSerialNumber())
									&& seatList.get(j1 + (a + 3)).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + (a + 4)).getSerialNumber())
									&& seatList.get(j1 + (a + 4)).getUsable() == 1)) {
						meetGroupSeatList.add(seatList.get(j1 + 4));
						meetGroupSeatList.add(seatList.get(j1 + a));
						meetGroupSeatList.add(seatList.get(j1 + (a + 1)));
						meetGroupSeatList.add(seatList.get(j1 + (a + 2)));
						meetGroupSeatList.add(seatList.get(j1 + (a + 3)));
						meetGroupSeatList.add(seatList.get(j1 + (a + 4)));
					}
				}
			}
			// 前五后一
			if (j1 < seatList.size() && (j1 + 1) < seatList.size() && (j1 + 2) < seatList.size()
					&& (j1 + 3) < seatList.size() && (j1 + 4) < seatList.size() && (j1 + a) < seatList.size()) {
				if ((0 <= j1 % a && j1 % a < a) && (1 <= (j1 + 1) % a && (j1 + 1) % a < a)
						&& (2 <= (j1 + 2) % a && (j1 + 2) % a < a) && (3 <= (j1 + 3) % a && (j1 + 3) % a < a)
						&& (4 <= (j1 + 4) % a && (j1 + 4) % a < a) && (0 <= (j1 + a) % a && (j1 + a) % a < a)) {
					// 判断是否被占
					if ((!"0".equals(seatList.get(j1).getSerialNumber()) && seatList.get(j1).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + 1).getSerialNumber())
									&& seatList.get(j1 + 1).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + 2).getSerialNumber())
									&& seatList.get(j1 + 2).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + 3).getSerialNumber())
									&& seatList.get(j1 + 3).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + 4).getSerialNumber())
									&& seatList.get(j1 + 4).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + a).getSerialNumber())
									&& seatList.get(j1 + a).getUsable() == 1)) {
						meetGroupSeatList.add(seatList.get(j1));
						meetGroupSeatList.add(seatList.get(j1 + 1));
						meetGroupSeatList.add(seatList.get(j1 + 2));
						meetGroupSeatList.add(seatList.get(j1 + 3));
						meetGroupSeatList.add(seatList.get(j1 + 4));
						meetGroupSeatList.add(seatList.get(j1 + a));
					}
				}
			}
			// 前五后一
			if (j1 < seatList.size() && (j1 + 1) < seatList.size() && (j1 + 2) < seatList.size()
					&& (j1 + 3) < seatList.size() && (j1 + 4) < seatList.size() && (j1 + (a + 1)) < seatList.size()) {
				if ((0 <= j1 % a && j1 % a < a) && (1 <= (j1 + 1) % a && (j1 + 1) % a < a)
						&& (2 <= (j1 + 2) % a && (j1 + 2) % a < a) && (3 <= (j1 + 3) % a && (j1 + 3) % a < a)
						&& (4 <= (j1 + 4) % a && (j1 + 4) % a < a)
						&& (1 <= (j1 + (a + 1)) % a && (j1 + (a + 1)) % a < a)) {
					// 判断是否被占
					if ((!"0".equals(seatList.get(j1).getSerialNumber()) && seatList.get(j1).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + 1).getSerialNumber())
									&& seatList.get(j1 + 1).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + 2).getSerialNumber())
									&& seatList.get(j1 + 2).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + 3).getSerialNumber())
									&& seatList.get(j1 + 3).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + 4).getSerialNumber())
									&& seatList.get(j1 + 4).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + (a + 1)).getSerialNumber())
									&& seatList.get(j1 + (a + 1)).getUsable() == 1)) {
						meetGroupSeatList.add(seatList.get(j1));
						meetGroupSeatList.add(seatList.get(j1 + 1));
						meetGroupSeatList.add(seatList.get(j1 + 2));
						meetGroupSeatList.add(seatList.get(j1 + 3));
						meetGroupSeatList.add(seatList.get(j1 + 4));
						meetGroupSeatList.add(seatList.get(j1 + (a + 1)));
					}
				}
			}
			// 前五后一
			if (j1 < seatList.size() && (j1 + 1) < seatList.size() && (j1 + 2) < seatList.size()
					&& (j1 + 3) < seatList.size() && (j1 + 4) < seatList.size() && (j1 + (a + 2)) < seatList.size()) {
				if ((0 <= j1 % a && j1 % a < a) && (1 <= (j1 + 1) % a && (j1 + 1) % a < a)
						&& (2 <= (j1 + 2) % a && (j1 + 2) % a < a) && (3 <= (j1 + 3) % a && (j1 + 3) % a < a)
						&& (4 <= (j1 + 4) % a && (j1 + 4) % a < a)
						&& (2 <= (j1 + (a + 2)) % a && (j1 + (a + 2)) % a < a)) {
					// 判断是否被占
					if ((!"0".equals(seatList.get(j1).getSerialNumber()) && seatList.get(j1).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + 1).getSerialNumber())
									&& seatList.get(j1 + 1).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + 2).getSerialNumber())
									&& seatList.get(j1 + 2).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + 3).getSerialNumber())
									&& seatList.get(j1 + 3).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + 4).getSerialNumber())
									&& seatList.get(j1 + 4).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + (a + 2)).getSerialNumber())
									&& seatList.get(j1 + (a + 2)).getUsable() == 1)) {
						meetGroupSeatList.add(seatList.get(j1));
						meetGroupSeatList.add(seatList.get(j1 + 1));
						meetGroupSeatList.add(seatList.get(j1 + 2));
						meetGroupSeatList.add(seatList.get(j1 + 3));
						meetGroupSeatList.add(seatList.get(j1 + 4));
						meetGroupSeatList.add(seatList.get(j1 + (a + 2)));
					}
				}
			}
			// 前五后一
			if (j1 < seatList.size() && (j1 + 1) < seatList.size() && (j1 + 2) < seatList.size()
					&& (j1 + 3) < seatList.size() && (j1 + 4) < seatList.size() && (j1 + (a + 3)) < seatList.size()) {
				if ((0 <= j1 % a && j1 % a < a) && (1 <= (j1 + 1) % a && (j1 + 1) % a < a)
						&& (2 <= (j1 + 2) % a && (j1 + 2) % a < a) && (3 <= (j1 + 3) % a && (j1 + 3) % a < a)
						&& (4 <= (j1 + 4) % a && (j1 + 4) % a < a)
						&& (3 <= (j1 + (a + 3)) % a && (j1 + (a + 3)) % a < a)) {
					// 判断是否被占
					if ((!"0".equals(seatList.get(j1).getSerialNumber()) && seatList.get(j1).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + 1).getSerialNumber())
									&& seatList.get(j1 + 1).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + 2).getSerialNumber())
									&& seatList.get(j1 + 2).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + 3).getSerialNumber())
									&& seatList.get(j1 + 3).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + 4).getSerialNumber())
									&& seatList.get(j1 + 4).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + (a + 3)).getSerialNumber())
									&& seatList.get(j1 + (a + 3)).getUsable() == 1)) {
						meetGroupSeatList.add(seatList.get(j1));
						meetGroupSeatList.add(seatList.get(j1 + 1));
						meetGroupSeatList.add(seatList.get(j1 + 2));
						meetGroupSeatList.add(seatList.get(j1 + 3));
						meetGroupSeatList.add(seatList.get(j1 + 4));
						meetGroupSeatList.add(seatList.get(j1 + (a + 3)));
					}
				}
			}
			// 前五后一
			if (j1 < seatList.size() && (j1 + 1) < seatList.size() && (j1 + 2) < seatList.size()
					&& (j1 + 3) < seatList.size() && (j1 + 4) < seatList.size() && (j1 + (a + 4)) < seatList.size()) {
				if ((0 <= j1 % a && j1 % a < a) && (1 <= (j1 + 1) % a && (j1 + 1) % a < a)
						&& (2 <= (j1 + 2) % a && (j1 + 2) % a < a) && (3 <= (j1 + 3) % a && (j1 + 3) % a < a)
						&& (4 <= (j1 + 4) % a && (j1 + 4) % a < a)
						&& (4 <= (j1 + (a + 4)) % a && (j1 + (a + 4)) % a < a)) {
					// 判断是否被占
					if ((!"0".equals(seatList.get(j1).getSerialNumber()) && seatList.get(j1).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + 1).getSerialNumber())
									&& seatList.get(j1 + 1).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + 2).getSerialNumber())
									&& seatList.get(j1 + 2).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + 3).getSerialNumber())
									&& seatList.get(j1 + 3).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + 4).getSerialNumber())
									&& seatList.get(j1 + 4).getUsable() == 1)
							&& (!"0".equals(seatList.get(j1 + (a + 4)).getSerialNumber())
									&& seatList.get(j1 + (a + 4)).getUsable() == 1)) {
						meetGroupSeatList.add(seatList.get(j1));
						meetGroupSeatList.add(seatList.get(j1 + 1));
						meetGroupSeatList.add(seatList.get(j1 + 2));
						meetGroupSeatList.add(seatList.get(j1 + 3));
						meetGroupSeatList.add(seatList.get(j1 + 4));
						meetGroupSeatList.add(seatList.get(j1 + (a + 4)));
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

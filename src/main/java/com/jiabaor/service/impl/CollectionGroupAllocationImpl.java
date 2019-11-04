package com.jiabaor.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiabaor.pojo.Seat;
import com.jiabaor.pojo.User;
import com.jiabaor.service.CollectionGroupAllocation;
import com.jiabaor.service.EightPersonGroupAllocation;
import com.jiabaor.service.FivePersonGroupAllocation;
import com.jiabaor.service.FourPersonGroupAllocation;
import com.jiabaor.service.SevenPersonGroupAllocation;
import com.jiabaor.service.SixPersonGroupAllocation;
import com.jiabaor.service.ThreePersonGroupAllocation;
import com.jiabaor.service.TowPersonGroupAllocation;

@Service
public class CollectionGroupAllocationImpl implements CollectionGroupAllocation {

	@Autowired
	private TowPersonGroupAllocation towPersonGroupAllocation;
	@Autowired
	private ThreePersonGroupAllocation threePersonGroupAllocation;
	@Autowired
	private FourPersonGroupAllocation fourPersonGroupAllocation;
	@Autowired
	private FivePersonGroupAllocation fivePersonGroupAllocation;
	@Autowired
	private SixPersonGroupAllocation sixPersonGroupAllocation;
	@Autowired
	private SevenPersonGroupAllocation sevenPersonGroupAllocation;
	@Autowired
	private EightPersonGroupAllocation eightPersonGroupAllocation;

	// 多组分配
	public boolean collectionGroupAllocation(int a, List<List<User>> listGroup, List<User> randomAndDissatisfylist,
			List<Seat> seatList, HashMap<String, String> map, List<User> notMeetUser, List<User> userListShow) {
		boolean bl = false;
		// 座位一排的个数
		// 遍历多组
		for (int j = 0; j < listGroup.size(); j++) {
			List<User> groupList = listGroup.get(j);
			// 二人组
			if (groupList.size() == 2) {
				// 分配二人组
				bl = towPersonGroupAllocation.twoUserGroupAllocation(a, groupList, randomAndDissatisfylist, seatList,
						map, notMeetUser, userListShow);
				// 如果bl为flase,表示没有相邻座位，添加到未满足
				if (bl == false) {
					for (int j1 = 0; j1 < groupList.size(); j1++) {
						notMeetUser.add(groupList.get(j1));
					}
				}
			}
			// 三人组
			if (groupList.size() == 3) {
				bl = threePersonGroupAllocation.threeUserGroupAllocation(a, groupList, randomAndDissatisfylist,
						seatList, map, notMeetUser, userListShow);
				// 如果bl为flase,表示没有相邻座位，添加到未满足
				if (bl == false) {
					for (int j1 = 0; j1 < groupList.size(); j1++) {
						notMeetUser.add(groupList.get(j1));
					}
				}
			}
			// 四人组
			if (groupList.size() == 4) {
				bl = fourPersonGroupAllocation.fourUserGroupAllocation(a, groupList, randomAndDissatisfylist, seatList,
						map, notMeetUser, userListShow);
				// 如果bl为flase,表示没有相邻座位，添加到未满足
				if (bl == false) {
					for (int j1 = 0; j1 < groupList.size(); j1++) {
						notMeetUser.add(groupList.get(j1));
					}
				}
			}
			// 五人组
			if (groupList.size() == 5) {
				bl = fivePersonGroupAllocation.fiveUserGroupAllocation(a, groupList, randomAndDissatisfylist, seatList,
						map, notMeetUser, userListShow);
				// 如果bl为flase,表示没有相邻座位，添加到未满足
				if (bl == false) {
					for (int j1 = 0; j1 < groupList.size(); j1++) {
						notMeetUser.add(groupList.get(j1));
					}
				}
			}
			// 六人组
			if (groupList.size() == 6) {
				bl = sixPersonGroupAllocation.sixUserGroupAllocation(a, groupList, randomAndDissatisfylist, seatList,
						map, notMeetUser, userListShow);
				// 如果bl为flase,表示没有相邻座位，添加到未满足
				if (bl == false) {
					for (int j1 = 0; j1 < groupList.size(); j1++) {
						notMeetUser.add(groupList.get(j1));
					}
				}
			}
			// 七人组
			if (groupList.size() == 7) {
				bl = sevenPersonGroupAllocation.sevenUserGroupAllocation(a, groupList, randomAndDissatisfylist,
						seatList, map, notMeetUser, userListShow);
				// 如果bl为flase,表示没有相邻座位，添加到未满足
				if (bl == false) {
					for (int j1 = 0; j1 < groupList.size(); j1++) {
						notMeetUser.add(groupList.get(j1));
					}
				}
			}
			// 八人组
			if (groupList.size() == 8) {
				bl = eightPersonGroupAllocation.eightUserGroupAllocation(a, groupList, randomAndDissatisfylist,
						seatList, map, notMeetUser, userListShow);
				// 如果bl为flase,表示没有相邻座位，添加到未满足
				if (bl == false) {
					for (int j1 = 0; j1 < groupList.size(); j1++) {
						notMeetUser.add(groupList.get(j1));
					}
				}
			}
		}
		return bl;
	}
}

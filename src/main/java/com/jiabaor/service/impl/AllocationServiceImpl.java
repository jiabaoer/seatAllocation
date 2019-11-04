package com.jiabaor.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiabaor.dao.SeatAllocatioMapper;
import com.jiabaor.pojo.Seat;
import com.jiabaor.pojo.User;
import com.jiabaor.pojo.UserListAndNonMeet;
import com.jiabaor.service.AllocationService;
import com.jiabaor.service.CollectionGroupAllocation;
import com.jiabaor.service.EightPersonGroupAllocation;
import com.jiabaor.service.FivePersonGroupAllocation;
import com.jiabaor.service.FourPersonGroupAllocation;
import com.jiabaor.service.NoMeetAndRandomAllocation;
import com.jiabaor.service.SevenPersonGroupAllocation;
import com.jiabaor.service.SingleAllocation;
import com.jiabaor.service.SixPersonGroupAllocation;
import com.jiabaor.service.ThreePersonGroupAllocation;
import com.jiabaor.service.TowPersonGroupAllocation;

@Service
public class AllocationServiceImpl implements AllocationService {

	@Autowired
	private SeatAllocatioMapper dao;
	@Autowired
	private SingleAllocation singleAllocation;
	@Autowired
	private NoMeetAndRandomAllocation noMeetAndRandomAllocation;
	@Autowired
	private CollectionGroupAllocation collectionGroupAllocation;
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

	// 所有的用户信息
	List<User> userList = null;
	// 所有的座位信息
	List<Seat> seatList = null;

	// 整机分配
	public boolean allocationSeatList(UserListAndNonMeet userListAndNoMeet, String planeType, int a,
			HttpServletRequest request) {
		// 所有用户信息
		userList = dao.selectUserList();
		// 所有可用座位，根据机型查询
		int usableSeatCount = dao.selectUsableSeat(planeType);
		if (userList.size() <= usableSeatCount) {
			// 页面显示用户信息
			List<User> userListShow = new ArrayList<User>();
			// 存储用户座位号
			HashMap<String, String> map = new HashMap<String, String>();
			// 存储未满足座位需求的旅客
			List<User> notMeetUser = new ArrayList<User>();
			// 存储不满足和随机，最后分配
			List<User> randomAndDissatisfylist = new ArrayList<User>();
			// 存储多个组
			List<List<User>> listGroup = new ArrayList<List<User>>();
			try {
				// 所有座位信息，根据机型查询
				seatList = dao.selectSeatList(planeType);
				// 遍历所有用户，分配单人或一组用户
				jiabaor: for (int i = 0; i < userList.size(); i++) {
					// 判断是否是单个用户
					if ("".equals(userList.get(i).getUserGroup()) || userList.get(i).getUserGroup() == null) {
						// 分配单个用户
						singleAllocation.oneAllocationSeat(i, userList, randomAndDissatisfylist, seatList, map,
								notMeetUser, userListShow);
					} else {
						if (userList.get(i).getIdCard() != "") {
							// 一组用户
							// 存储一组编号
							List<Integer> integers = new ArrayList<Integer>();
							// 组集合
							List<User> groupList = new ArrayList<User>();
							// 遍历，找一组用户
							for (int j = i; j < userList.size(); j++) {
								// 判断下一个用户为一组
								if (userList.get(i).getUserGroup().equals(userList.get(j).getUserGroup())) {
									// 用户组集合
									User user = new User();
									user.setIdCard(userList.get(j).getIdCard());
									user.setName(userList.get(j).getName());
									user.setSex(userList.get(j).getSex());
									user.setBaby(userList.get(j).getBaby());
									user.setDisability(userList.get(j).getDisability());
									user.setPreference(userList.get(j).getPreference());
									user.setVip(userList.get(j).getVip());
									user.setUserGroup(userList.get(j).getUserGroup());
									user.setSeatallocation(userList.get(j).getSeatallocation());
									// 添加编号
									integers.add(j);
									// 添加到groupList
									groupList.add(user);
								}
							}
							// 设置身份证为空，表示已分配
							for (int j = 0; j < integers.size(); j++) {
								userList.get(integers.get(j)).setIdCard("");
							}
							// 添加到组集合中
							listGroup.add(groupList);
							// 继续下一个用户
							continue jiabaor;
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 判断是否有一组或多个组
			if (listGroup.size() > 0) {
				// 分配多个组用户
				collectionGroupAllocation.collectionGroupAllocation(a, listGroup, randomAndDissatisfylist, seatList,
						map, notMeetUser, userListShow);
			}
			// 判断是否有随机和不满足用户
			if (randomAndDissatisfylist.size() > 0) {
				// 分配随机和不满足的用户
				noMeetAndRandomAllocation.randomAllocationSeat(randomAndDissatisfylist, seatList, map, userListShow);
			}
			// 存储所有已分配用户信息
			userListAndNoMeet.setUserListShow(userListShow);
			// 存储不满足用户信息
			userListAndNoMeet.setNoMeetUserList(notMeetUser);
			// 存储用户的身份证和座位号到Session中
			request.getSession().setAttribute("orderList", map);
			return true;
		} else {
			return false;
		}
	}

	// 单人分配
	public UserListAndNonMeet oneAllAllocation(String planeType, User user) {
		// 页面显示用户信息
		UserListAndNonMeet userListAndNoMeet = new UserListAndNonMeet();
		List<User> userListShow = new ArrayList<User>();
		// 根据机型查询所有座位信息
		seatList = dao.selectSeatList(planeType);
		// 单人分配座位,0表示第一个用户
		singleAllocation.singlSeatAllocation(user, userListShow,seatList);
		userListAndNoMeet.setUserListShow(userListShow);
		return userListAndNoMeet;
	}

	// 组分配
	public UserListAndNonMeet moreAllAllocation(int a, String planeType, List<User> groupList) {
		// 页面显示用户信息
		List<User> userListShow = new ArrayList<User>();
		// 存储用户座位号
		HashMap<String, String> map = new HashMap<String, String>();
		// 存储未满足座位需求的旅客
		List<User> notMeetUser = new ArrayList<User>();
		// 存储不满足和随机，最后分配
		List<User> randomAndDissatisfylist = new ArrayList<User>();
		// 页面显示用户和不满足用户
		UserListAndNonMeet userListAndNoMeet = new UserListAndNonMeet();
		// 根据机型查询座位
		seatList = dao.selectSeatList(planeType);
		if (groupList.size() == 2) {
			// 二人组分配
			towPersonGroupAllocation.twoUserGroupAllocation(a, groupList, randomAndDissatisfylist, seatList, map,
					notMeetUser, userListShow);
		} else if (groupList.size() == 3) {
			// 三人组分配
			threePersonGroupAllocation.threeUserGroupAllocation(a, groupList, randomAndDissatisfylist, seatList, map,
					notMeetUser, userListShow);
		} else if (groupList.size() == 4) {
			// 四人组分配
			fourPersonGroupAllocation.fourUserGroupAllocation(a, groupList, randomAndDissatisfylist, seatList, map,
					notMeetUser, userListShow);
		} else if (groupList.size() == 5) {
			// 五人组分配
			fivePersonGroupAllocation.fiveUserGroupAllocation(a, groupList, randomAndDissatisfylist, seatList, map,
					notMeetUser, userListShow);
		} else if (groupList.size() == 6) {
			// 六人组分配
			sixPersonGroupAllocation.sixUserGroupAllocation(a, groupList, randomAndDissatisfylist, seatList, map,
					notMeetUser, userListShow);
		} else if (groupList.size() == 7) {
			// 七人组分配
			sevenPersonGroupAllocation.sevenUserGroupAllocation(a, groupList, randomAndDissatisfylist, seatList, map,
					notMeetUser, userListShow);
		} else if (groupList.size() == 8) {
			// 八人组分配
			eightPersonGroupAllocation.eightUserGroupAllocation(a, groupList, randomAndDissatisfylist, seatList, map,
					notMeetUser, userListShow);
		}
		userListAndNoMeet.setUserListShow(userListShow);
		userListAndNoMeet.setNoMeetUserList(notMeetUser);
		return userListAndNoMeet;
	}

	// 根据身份证插入座位到订单中
	public void insertSeatAllocation(String idCard, String seat) {
		try {
			dao.insertSeatAllocation(idCard, seat);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

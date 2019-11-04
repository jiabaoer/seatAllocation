package com.jiabaor.service.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Service;
import com.jiabaor.pojo.Seat;
import com.jiabaor.pojo.User;
import com.jiabaor.service.AssignSeat;

@Service
public class AssignSeatImpl implements AssignSeat {

	// 用户身份证号
	String card = null;
	// 分配的座位号
	String seat = null;

	// 单个用户分配座位
	public void assignSeatByUser(int i, int j, List<User> userList, List<Seat> seatList, HashMap<String, String> map,
			List<User> userListShow) {
		// 用户身份证号
		card = userList.get(i).getIdCard();
		// 分配的座位号
		seat = seatList.get(j).getSerialNumber();
		map.put(card, seat);
		userList.get(i).getSeatallocation().setSeatSerialNumber(seat);
		userListShow.add(userList.get(i));
		Seat seat2 = seatList.get(j);
		seat2.setSerialNumber("0");
		seatList.set(j, seat2);
	}

	/*
	 * 1.把用户偏好存放到集合里面
	 * 2.遍历所有满足组用户座位的集合，找组用户偏好的连续座位
	 * 3.分配座位
	*/	
	// 组用户分配座位
	@SuppressWarnings("unused")
	public boolean groupAssignSeat(List<Integer> preferenceList, List<Integer> excludingRandomList,
			List<Seat> meetGroupSeatList, List<Integer> seatAttributeList, List<Seat> groupSeatList,
			List<User> groupList, List<User> randomAndDissatisfylist, List<Seat> seatList, HashMap<String, String> map,
			List<User> notMeetUser, List<User> userListShow) {
		boolean bl = false;
		// 把一组用户的个人偏好存在集合里面
		for (int i = 0; i < groupList.size(); i++) {
			preferenceList.add(groupList.get(i).getPreference());
		}
		// 把一组用户的个人偏好存在集合里面除偏好随机的
		for (int i = 0; i < groupList.size(); i++) {
			if (!(groupList.get(i).getPreference() == 0)) {
				excludingRandomList.add(groupList.get(i).getPreference());
			}
		}
		// 循环所有满足的座位集合，根据preferenceList集合分配
		for (int i = 0; i <= meetGroupSeatList.size() - groupList.size(); i = i + groupList.size()) {
			for (int k = i; k < groupList.size() + i; k++) {
				// 循环把座位Type属性添加到integer型seatAttributeList集合中
				seatAttributeList.add(meetGroupSeatList.get(k).getType());
				// 循环把座位信息添加到GroupSeatList中
				groupSeatList.add(meetGroupSeatList.get(k));
			}
			// 把seatAttributeList和preferenceList俩个integer型的集合排序
			Collections.sort(seatAttributeList);
			Collections.sort(preferenceList);
			boolean b2 = true;
			// 循环判断俩个集合里面的元素是否一一相等
			for (int k = 0; k < seatAttributeList.size(); k++) {
				if (!(seatAttributeList.get(k).equals(preferenceList.get(k)))) {
					b2 = false;
					break;
				}
			}
			// 如果全部相等分配座位
			if (b2) {
				b: for (int g = 0; g < groupList.size(); g++) {
					for (int h = 0; h < groupSeatList.size(); h++) {
						if (groupList.get(g).getPreference() == groupSeatList.get(h).getType()) {
							// 分配座位
							card = groupList.get(g).getIdCard();
							seat = groupSeatList.get(h).getSerialNumber();
							map.put(card, seat);
							groupList.get(g).getSeatallocation().setSeatSerialNumber(seat);
							userListShow.add(groupList.get(g));
							Seat seat2 = groupSeatList.get(h);
							seat2.setSerialNumber("0");
							groupSeatList.set(h, seat2);
							groupSeatList.remove(h);
							h = h - 1;
							groupList.remove(g);
							g = g - 1;
							continue b;
						}
					}
				}
				bl = true;
				return bl;
			}
			// 把GroupSeatList和seatAttributeList集合的元素全部清空
			groupSeatList.clear();
			seatAttributeList.clear();
			// 如果i到了最后一个，循环所有满足的座位，根据excludingRandomList集合分配
			if (i == meetGroupSeatList.size() - groupList.size()) {
				boolean b5 = true;
				// 判断excludingRandomList集合大小是否与groupList集合大小相等，excludingRandomList集合大小是否等于0
				if (!(excludingRandomList.size() == groupList.size() && excludingRandomList.size() == 0)) {
					b5 = false;
					for (int i1 = 0; i1 <= meetGroupSeatList.size() - groupList.size(); i1 = i1 + groupList.size()) {
						for (int k = i1; k < groupList.size() + i1; k++) {
							// 循环把座位Type属性添加到integer型seatAttributeList集合中
							seatAttributeList.add(meetGroupSeatList.get(k).getType());
							// 循环把座位信息添加到GroupSeatList中
							groupSeatList.add(meetGroupSeatList.get(k));
						}
						boolean b3 = false;
						// 判断seatAttributeList集合是否包含randomList集合
						if (seatAttributeList.containsAll(excludingRandomList)) {
							// 把seatAttributeList和randomList俩个integer型的集合排序
							Collections.sort(seatAttributeList);
							Collections.sort(excludingRandomList);
							b3 = true;
							// 循环randomList集合和seatAttributeList集合
							b: for (int kk = 0; kk < excludingRandomList.size(); kk++) {
								for (int k = 0; k < seatAttributeList.size(); k++) {
									// 如果seatAttributeList里面的元素等于excludingRandomList里面的元素
									if (seatAttributeList.get(k) == excludingRandomList.get(kk)) {
										// 移除相对应坐标的元素
										seatAttributeList.remove(k);
										k = k - 1;
										continue b;
									}
									// 如果k到了最后都没有找到相等的
									if (k == seatAttributeList.size() - 1) {
										b3 = false;
										break b;
									}
								}
							}
						}
						// 如果b3等于true，分配座位
						if (b3) {
							b: for (int g = 0; g < groupSeatList.size(); g++) {
								for (int h = 0; h < groupList.size(); h++) {
									// 如果h到最后一个都没有找到属性相对应的座位
									if (h == groupList.size() - 1) {
										// 循环用户集合
										for (int i2 = 0; i2 < groupList.size(); i2++) {
											// 当用户偏好是随机的（getPreference等于0），分配座位
											if (groupList.get(i2).getPreference() == 0) {
												card = groupList.get(i2).getIdCard();
												seat = groupSeatList.get(g).getSerialNumber();
												map.put(card, seat);
												groupList.get(i2).getSeatallocation().setSeatSerialNumber(seat);
												userListShow.add(groupList.get(i2));
												Seat seat2 = groupSeatList.get(g);
												seat2.setSerialNumber("0");
												groupSeatList.set(g, seat2);
												groupSeatList.remove(g);
												g = g - 1;
												groupList.remove(i2);
												i2 = i2 - 1;
												continue b;
											}
										}
									}
									// 如果座位属性跟用户个人偏好相等，分配座位
									if (groupSeatList.get(g).getType() == groupList.get(h).getPreference()) {
										// 分配座位
										card = groupList.get(h).getIdCard();
										seat = groupSeatList.get(g).getSerialNumber();
										map.put(card, seat);
										groupList.get(h).getSeatallocation().setSeatSerialNumber(seat);
										userListShow.add(groupList.get(h));
										Seat seat2 = groupSeatList.get(g);
										seat2.setSerialNumber("0");
										groupSeatList.set(g, seat2);
										groupSeatList.remove(g);
										g = g - 1;
										groupList.remove(h);
										h = h - 1;
										continue b;
									}
								}

							}
							bl = true;
							return bl;
						}
						// 把GroupSeatList和seatAttributeList集合的元素全部清空
						groupSeatList.clear();
						seatAttributeList.clear();
						// 如果i1到了最后，都没有分配座位
						if (i1 == meetGroupSeatList.size() - groupList.size()) {
							b5 = true;
						}
					}
				}
				// 如果b5等于true
				if (b5) {
					// 直接找俩个连续的座位分配
					for (int i2 = 0; i2 <= meetGroupSeatList.size() - groupList.size(); i2 = i2 + groupList.size()) {
						for (int k = i2; k < groupList.size() + i2; k++) {
							// 把座位信息添加到GroupSeatList集合中
							groupSeatList.add(meetGroupSeatList.get(k));
						}
						// 循环座位集合和用户集合
						b: for (int g = 0; g < groupList.size(); g++) {
							for (int h = 0; h < groupSeatList.size(); h++) {
								// 分配座位
								card = groupList.get(g).getIdCard();
								seat = groupSeatList.get(h).getSerialNumber();
								map.put(card, seat);
								groupList.get(g).getSeatallocation().setSeatSerialNumber(seat);
								userListShow.add(groupList.get(g));
								Seat seat2 = groupSeatList.get(h);
								seat2.setSerialNumber("0");
								groupSeatList.set(h, seat2);
								groupSeatList.remove(h);
								h = h - 1;
								groupList.remove(g);
								g = g - 1;
								continue b;
							}
						}
						bl = true;
						return bl;
					}
				}
			}
		}
		return bl;
	}

}

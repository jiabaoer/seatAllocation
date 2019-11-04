package com.jiabaor.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiabaor.pojo.Seat;
import com.jiabaor.pojo.User;
import com.jiabaor.service.AssignSeat;
import com.jiabaor.service.SingleAllocation;

@Service
public class SingleAllocationImpl implements SingleAllocation {

	@Autowired
	private AssignSeat assignSeat;

	// 单人分配
	public boolean oneAllocationSeat(int i, List<User> userList, List<User> randomAndDissatisfylist,
			List<Seat> seatList, HashMap<String, String> map, List<User> notMeetUser, List<User> userListShow) {
		boolean bl = false;
		// 分配vip座位
		if (userList.get(i).getVip() == 1) {
			// 分配vip携带婴儿用户
			if (userList.get(i).getBaby() == 1) {
				for (int j = 0; j < seatList.size(); j++) {
					// 判断是否被占
					if (!"0".equals(seatList.get(j).getSerialNumber()) && seatList.get(j).getUsable() == 1) {
						if (seatList.get(j).getBabySeat() == 1) {
							assignSeat.assignSeatByUser(i, j, userList, seatList, map, userListShow);
							bl = true;
							return bl;
						}
					}
				}
				// 判断携带婴儿用户是否残疾
				if (userList.get(i).getDisability() == 1) {
					for (int j2 = 0; j2 < seatList.size(); j2++) {
						// 判断座位是否被占
						if (!"0".equals(seatList.get(j2).getSerialNumber()) && seatList.get(j2).getUsable() == 1) {
							if (seatList.get(j2).getType() == 2 && seatList.get(j2).getVip() == 1) {
								assignSeat.assignSeatByUser(i, j2, userList, seatList, map, userListShow);
								bl = true;
								return bl;
							}
						}
					}
					for (int j5 = 0; j5 < seatList.size(); j5++) {
						// 判断座位是否被占
						if (!"0".equals(seatList.get(j5).getSerialNumber()) && seatList.get(j5).getUsable() == 1) {
							if (seatList.get(j5).getType() == 2) {
								assignSeat.assignSeatByUser(i, j5, userList, seatList, map, userListShow);
								bl = true;
								return bl;
							}
						}
					}
					// 如果等于最后一个座位说明没有婴儿座位
					// 判断携带婴儿用户是登机口
					if (userList.get(i).getPreference() == 3) {
						for (int j3 = 0; j3 < seatList.size(); j3++) {
							// 判断座位是否被占
							if (!"0".equals(seatList.get(j3).getSerialNumber()) && seatList.get(j3).getUsable() == 1) {
								if (seatList.get(j3).getType() == 3) {
									assignSeat.assignSeatByUser(i, j3, userList, seatList, map, userListShow);
									bl = true;
									return bl;
								}
							}
						}
						// 不满足用户需求
						notMeetUser.add(userList.get(i));
						// 添加，最后随机分配
						randomAndDissatisfylist.add(userList.get(i));
						bl = true;
						return bl;
					}
					// 判断携带婴儿残疾用户是否过道
					if (userList.get(i).getPreference() == 2) {
						// 不满足用户需求
						notMeetUser.add(userList.get(i));
						// 添加，最后随机分配
						randomAndDissatisfylist.add(userList.get(i));
						bl = true;
						return bl;
					}
					// 判断携带婴儿残疾用户是否靠窗
					if (userList.get(i).getPreference() == 1) {
						for (int j3 = 0; j3 < seatList.size(); j3++) {
							// 判断座位是否被占
							if (!"0".equals(seatList.get(j3).getSerialNumber()) && seatList.get(j3).getUsable() == 1) {
								if (seatList.get(j3).getType() == 1 && seatList.get(j3).getVip() == 1) {
									assignSeat.assignSeatByUser(i, j3, userList, seatList, map, userListShow);
									bl = true;
									return bl;
								}
							}
						}
						for (int j6 = 0; j6 < seatList.size(); j6++) {
							// 判断座位是否被占
							if (!"0".equals(seatList.get(j6).getSerialNumber()) && seatList.get(j6).getUsable() != 0) {
								if (seatList.get(j6).getType() == 1) {
									assignSeat.assignSeatByUser(i, j6, userList, seatList, map, userListShow);
									bl = true;
									return bl;
								}
							}
						}
						// 不满足用户需求
						notMeetUser.add(userList.get(i));
						randomAndDissatisfylist.add(userList.get(i));
						bl = true;
						return bl;
					}
					// 判断携带婴儿残疾用户是否随机
					if (userList.get(i).getPreference() == 0) {
						randomAndDissatisfylist.add(userList.get(i));
						bl = true;
						return bl;
					}
				}
				// 判断携带婴儿用户是否登机口
				if (userList.get(i).getPreference() == 3) {
					for (int j3 = 0; j3 < seatList.size(); j3++) {
						// 判断座位是否被占
						if (!"0".equals(seatList.get(j3).getSerialNumber()) && seatList.get(j3).getUsable() == 1) {
							if (seatList.get(j3).getType() == 3) {
								assignSeat.assignSeatByUser(i, j3, userList, seatList, map, userListShow);
								bl = true;
								return bl;
							}
						}
					}
					// 不满足用户需求
					notMeetUser.add(userList.get(i));
					randomAndDissatisfylist.add(userList.get(i));
					bl = true;
					return bl;
				}
				// 判断携带婴儿用户是否过道
				if (userList.get(i).getPreference() == 2) {
					for (int j3 = 0; j3 < seatList.size(); j3++) {
						// 判断座位是否被占
						if (!"0".equals(seatList.get(j3).getSerialNumber()) && seatList.get(j3).getUsable() == 1) {
							if (seatList.get(j3).getType() == 2 && seatList.get(j3).getVip() == 1) {
								assignSeat.assignSeatByUser(i, j3, userList, seatList, map, userListShow);
								bl = true;
								return bl;
							}
						}
					}
					for (int j5 = 0; j5 < seatList.size(); j5++) {
						// 判断座位是否被占
						if (!"0".equals(seatList.get(j5).getSerialNumber()) && seatList.get(j5).getUsable() == 1) {
							if (seatList.get(j5).getType() == 2) {
								assignSeat.assignSeatByUser(i, j5, userList, seatList, map, userListShow);
								bl = true;
								return bl;
							}
						}
					}
					// 不满足用户需求
					notMeetUser.add(userList.get(i));
					randomAndDissatisfylist.add(userList.get(i));
					bl = true;
					return bl;
				}
				// 判断携带婴儿残疾用户是否靠窗
				if (userList.get(i).getPreference() == 1) {
					for (int j3 = 0; j3 < seatList.size(); j3++) {
						// 判断座位是否被占
						if (!"0".equals(seatList.get(j3).getSerialNumber()) && seatList.get(j3).getUsable() == 1) {
							if (seatList.get(j3).getType() == 1 && seatList.get(j3).getVip() == 1) {
								assignSeat.assignSeatByUser(i, j3, userList, seatList, map, userListShow);
								bl = true;
								return bl;
							}
						}
					}
					for (int j4 = 0; j4 < seatList.size(); j4++) {
						// 判断座位是否被占
						if (!"0".equals(seatList.get(j4).getSerialNumber()) && seatList.get(j4).getUsable() == 1
								&& seatList.get(j4).getType() == 1) {
							assignSeat.assignSeatByUser(i, j4, userList, seatList, map, userListShow);
							bl = true;
							return bl;
						}
					}
					// 不满足用户需求
					notMeetUser.add(userList.get(i));
					randomAndDissatisfylist.add(userList.get(i));
					bl = true;
					return bl;
				}
				// 判断携带婴儿残疾用户是否随机
				if (userList.get(i).getPreference() == 0) {
					randomAndDissatisfylist.add(userList.get(i));
					bl = true;
					return bl;
				}
			}
			// 判断vip残疾用户
			if (userList.get(i).getDisability() == 1) {
				for (int j = 0; j < seatList.size(); j++) {
					// 判断座位是否被占
					if (!"0".equals(seatList.get(j).getSerialNumber()) && seatList.get(j).getUsable() == 1) {
						if (seatList.get(j).getType() == 2 && seatList.get(j).getVip() == 1) {
							assignSeat.assignSeatByUser(i, j, userList, seatList, map, userListShow);
							bl = true;
							return bl;
						}
					}
				}
				for (int j2 = 0; j2 < seatList.size(); j2++) {
					// 判断座位是否被占
					if (!"0".equals(seatList.get(j2).getSerialNumber()) && seatList.get(j2).getUsable() == 1
							&& seatList.get(j2).getType() == 2) {
						assignSeat.assignSeatByUser(i, j2, userList, seatList, map, userListShow);
						bl = true;
						return bl;
					}
				}
				// 判断残疾用户是否登机口
				if (userList.get(i).getPreference() == 3) {
					for (int j3 = 0; j3 < seatList.size(); j3++) {
						// 判断座位是否被占
						if (!"0".equals(seatList.get(j3).getSerialNumber()) && seatList.get(j3).getUsable() == 1) {
							if (seatList.get(j3).getType() == 3) {
								assignSeat.assignSeatByUser(i, j3, userList, seatList, map, userListShow);
								bl = true;
								return bl;
							}
						}
					}
					// 不满足用户需求
					notMeetUser.add(userList.get(i));
					randomAndDissatisfylist.add(userList.get(i));
					bl = true;
					return bl;
				}
				// 判断残疾用户是否过道
				if (userList.get(i).getPreference() == 2) {
					// 不满足用户需求
					notMeetUser.add(userList.get(i));
					randomAndDissatisfylist.add(userList.get(i));
					bl = true;
					return bl;
				}
				// 判断残疾用户是否靠窗
				if (userList.get(i).getPreference() == 1) {
					for (int j3 = 0; j3 < seatList.size(); j3++) {
						// 判断座位是否被占
						if (!"0".equals(seatList.get(j3).getSerialNumber()) && seatList.get(j3).getUsable() == 1) {
							if (seatList.get(j3).getType() == 1 && seatList.get(j3).getVip() == 1) {
								assignSeat.assignSeatByUser(i, j3, userList, seatList, map, userListShow);
								bl = true;
								return bl;
							}
						}
					}
					for (int j4 = 0; j4 < seatList.size(); j4++) {
						// 判断座位是否被占
						if (!"0".equals(seatList.get(j4).getSerialNumber()) && seatList.get(j4).getUsable() == 1
								&& seatList.get(j4).getType() == 1) {
							assignSeat.assignSeatByUser(i, j4, userList, seatList, map, userListShow);
							bl = true;
							return bl;
						}
					}
					// 不满足用户需求
					notMeetUser.add(userList.get(i));
					randomAndDissatisfylist.add(userList.get(i));
					bl = true;
					return bl;
				}
				// 判断残疾用户是否随机
				if (userList.get(i).getPreference() == 0) {
					randomAndDissatisfylist.add(userList.get(i));
					bl = true;
					return bl;
				}
			}
			// 分配vip登机口用户
			if (userList.get(i).getPreference() == 3) {
				for (int j3 = 0; j3 < seatList.size(); j3++) {
					// 判断座位是否被占
					if (!"0".equals(seatList.get(j3).getSerialNumber()) && seatList.get(j3).getUsable() == 1) {
						if (seatList.get(j3).getType() == 3) {
							assignSeat.assignSeatByUser(i, j3, userList, seatList, map, userListShow);
							bl = true;
							return bl;
						}
					}
				}
				// 不满足用户需求
				notMeetUser.add(userList.get(i));
				randomAndDissatisfylist.add(userList.get(i));
				bl = true;
				return bl;
			}
			// 分配vip过道用户
			if (userList.get(i).getPreference() == 2) {
				for (int j3 = 0; j3 < seatList.size(); j3++) {
					// 判断座位是否被占
					if (!"0".equals(seatList.get(j3).getSerialNumber()) && seatList.get(j3).getUsable() == 1) {
						if (seatList.get(j3).getType() == 2 && seatList.get(j3).getVip() == 1) {
							assignSeat.assignSeatByUser(i, j3, userList, seatList, map, userListShow);
							bl = true;
							return bl;
						}
					}
				}
				for (int j5 = 0; j5 < seatList.size(); j5++) {
					// 判断座位是否被占
					if (!"0".equals(seatList.get(j5).getSerialNumber()) && seatList.get(j5).getUsable() == 1) {
						if (seatList.get(j5).getType() == 2) {
							assignSeat.assignSeatByUser(i, j5, userList, seatList, map, userListShow);
							bl = true;
							return bl;
						}
					}
				}
				// 不满足用户需求
				notMeetUser.add(userList.get(i));
				randomAndDissatisfylist.add(userList.get(i));
				bl = true;
				return bl;
			}
			// 分配vip靠窗用户
			if (userList.get(i).getPreference() == 1) {
				for (int j3 = 0; j3 < seatList.size(); j3++) {
					// 判断座位是否被占
					if (!"0".equals(seatList.get(j3).getSerialNumber()) && seatList.get(j3).getUsable() == 1) {
						if (seatList.get(j3).getType() == 1 && seatList.get(j3).getVip() == 1) {
							assignSeat.assignSeatByUser(i, j3, userList, seatList, map, userListShow);
							bl = true;
							return bl;
						}
					}
				}
				for (int j5 = 0; j5 < seatList.size(); j5++) {
					// 判断座位是否被占
					if (!"0".equals(seatList.get(j5).getSerialNumber()) && seatList.get(j5).getUsable() == 1) {
						if (seatList.get(j5).getType() == 1) {
							assignSeat.assignSeatByUser(i, j5, userList, seatList, map, userListShow);
							bl = true;
							return bl;
						}
					}
				}
				// 不满足用户需求
				notMeetUser.add(userList.get(i));
				randomAndDissatisfylist.add(userList.get(i));
				bl = true;
				return bl;
			}
			// 判断vip用户是否随机
			if (userList.get(i).getPreference() == 0) {
				randomAndDissatisfylist.add(userList.get(i));
				bl = true;
				return bl;
			}
		}
		// 分配普通婴儿用户
		if (userList.get(i).getBaby() == 1) {
			for (int j = 0; j < seatList.size(); j++) {
				// 判断是否被占
				if (!"0".equals(seatList.get(j).getSerialNumber()) && seatList.get(j).getUsable() == 1) {
					if (seatList.get(j).getBabySeat() == 1) {
						assignSeat.assignSeatByUser(i, j, userList, seatList, map, userListShow);
						bl = true;
						return bl;
					}
				}
			}
			// 判断携带婴儿用户是否残疾
			if (userList.get(i).getDisability() == 1) {
				for (int j2 = 0; j2 < seatList.size(); j2++) {
					// 判断座位是否被占
					if (!"0".equals(seatList.get(j2).getSerialNumber()) && seatList.get(j2).getUsable() == 1) {
						if (seatList.get(j2).getType() == 2 && seatList.get(j2).getVip() == 0) {
							assignSeat.assignSeatByUser(i, j2, userList, seatList, map, userListShow);
							bl = true;
							return bl;
						}
					}
				}
				for (int j4 = 0; j4 < seatList.size(); j4++) {
					// 判断座位是否被占
					if (!"0".equals(seatList.get(j4).getSerialNumber()) && seatList.get(j4).getUsable() == 1) {
						if (seatList.get(j4).getType() == 2) {
							assignSeat.assignSeatByUser(i, j4, userList, seatList, map, userListShow);
							bl = true;
							return bl;
						}
					}
				}
				// 判断携带婴儿用户是否登机口
				if (userList.get(i).getPreference() == 3) {
					for (int j3 = 0; j3 < seatList.size(); j3++) {
						// 判断座位是否被占
						if (!"0".equals(seatList.get(j3).getSerialNumber()) && seatList.get(j3).getUsable() == 1) {
							if (seatList.get(j3).getType() == 3) {
								assignSeat.assignSeatByUser(i, j3, userList, seatList, map, userListShow);
								bl = true;
								return bl;
							}
						}
					}
					// 不满足用户需求
					notMeetUser.add(userList.get(i));
					randomAndDissatisfylist.add(userList.get(i));
					bl = true;
					return bl;
				}
				// 判断携带婴儿残疾用户是否过道
				if (userList.get(i).getPreference() == 2) {
					// 不满足用户需求
					notMeetUser.add(userList.get(i));
					randomAndDissatisfylist.add(userList.get(i));
					bl = true;
					return bl;
				}
				// 判断携带婴儿残疾用户是否靠窗
				if (userList.get(i).getPreference() == 1) {
					for (int j3 = 0; j3 < seatList.size(); j3++) {
						// 判断座位是否被占
						if (!"0".equals(seatList.get(j3).getSerialNumber()) && seatList.get(j3).getUsable() == 1) {
							if (seatList.get(j3).getType() == 1 && seatList.get(j3).getVip() == 0) {
								assignSeat.assignSeatByUser(i, j3, userList, seatList, map, userListShow);
								bl = true;
								return bl;
							}
						}
					}
					for (int j5 = 0; j5 < seatList.size(); j5++) {
						// 判断座位是否被占
						if (!"0".equals(seatList.get(j5).getSerialNumber()) && seatList.get(j5).getUsable() == 1) {
							if (seatList.get(j5).getType() == 1) {
								assignSeat.assignSeatByUser(i, j5, userList, seatList, map, userListShow);
								bl = true;
								return bl;
							}
						}
					}
					// 不满足用户需求
					notMeetUser.add(userList.get(i));
					randomAndDissatisfylist.add(userList.get(i));
					bl = true;
					return bl;
				}
				// 判断携带婴儿残疾用户是否随机
				if (userList.get(i).getPreference() == 0) {
					randomAndDissatisfylist.add(userList.get(i));
					bl = true;
					return bl;
				}
			}
		}
		// 分配普通残疾用户
		if (userList.get(i).getDisability() == 1) {
			for (int j = 0; j < seatList.size(); j++) {
				// 判断座位是否被占
				if (!"0".equals(seatList.get(j).getSerialNumber()) && seatList.get(j).getUsable() == 1) {
					if (seatList.get(j).getType() == 2 && seatList.get(j).getVip() == 0) {
						assignSeat.assignSeatByUser(i, j, userList, seatList, map, userListShow);
						bl = true;
						return bl;
					}
				}
			}
			for (int j1 = 0; j1 < seatList.size(); j1++) {
				if (!"0".equals(seatList.get(j1).getSerialNumber()) && seatList.get(j1).getUsable() == 1) {
					if (seatList.get(j1).getType() == 2) {
						assignSeat.assignSeatByUser(i, j1, userList, seatList, map, userListShow);
						bl = true;
						return bl;
					}
				}
			}
			// 判断残疾用户是否登机口
			if (userList.get(i).getPreference() == 3) {
				for (int j3 = 0; j3 < seatList.size(); j3++) {
					// 判断座位是否被占
					if (!"0".equals(seatList.get(j3).getSerialNumber()) && seatList.get(j3).getUsable() == 1) {
						if (seatList.get(j3).getType() == 3) {
							assignSeat.assignSeatByUser(i, j3, userList, seatList, map, userListShow);
							bl = true;
							return bl;
						}
					}
				}
				// 不满足用户需求
				notMeetUser.add(userList.get(i));
				randomAndDissatisfylist.add(userList.get(i));
				bl = true;
				return bl;
			}
			// 判断残疾用户是否靠窗
			if (userList.get(i).getPreference() == 1) {
				for (int j3 = 0; j3 < seatList.size(); j3++) {
					// 判断座位是否被占
					if (!"0".equals(seatList.get(j3).getSerialNumber()) && seatList.get(j3).getUsable() == 1) {
						if (seatList.get(j3).getType() == 1 && seatList.get(j3).getVip() == 0) {
							assignSeat.assignSeatByUser(i, j3, userList, seatList, map, userListShow);
							bl = true;
							return bl;
						}
					}
				}
				for (int j4 = 0; j4 < seatList.size(); j4++) {
					// 判断座位是否被占
					if (!"0".equals(seatList.get(j4).getSerialNumber()) && seatList.get(j4).getUsable() == 1) {
						if (seatList.get(j4).getType() == 1) {
							assignSeat.assignSeatByUser(i, j4, userList, seatList, map, userListShow);
							bl = true;
							return bl;
						}
					}
				}
				// 不满足用户需求
				notMeetUser.add(userList.get(i));
				randomAndDissatisfylist.add(userList.get(i));
				bl = true;
				return bl;
			}
			// 判断残疾用户是否过道
			if (userList.get(i).getPreference() == 2) {
				// 不满足用户需求
				notMeetUser.add(userList.get(i));
				randomAndDissatisfylist.add(userList.get(i));
				bl = true;
				return bl;
			}
			// 判断残疾用户是否随机
			if (userList.get(i).getPreference() == 0) {
				randomAndDissatisfylist.add(userList.get(i));
				bl = true;
				return bl;
			}
		}
		// 分配普通登机口用户
		if (userList.get(i).getPreference() == 3) {
			for (int j3 = 0; j3 < seatList.size(); j3++) {
				// 判断座位是否被占
				if (!"0".equals(seatList.get(j3).getSerialNumber()) && seatList.get(j3).getUsable() == 1) {
					if (seatList.get(j3).getType() == 3) {
						assignSeat.assignSeatByUser(i, j3, userList, seatList, map, userListShow);
						bl = true;
						return bl;
					}
				}
			}
			// 不满足用户需求
			notMeetUser.add(userList.get(i));
			randomAndDissatisfylist.add(userList.get(i));
			bl = true;
			return bl;
		}
		// 分配普通过道用户
		if (userList.get(i).getPreference() == 2) {
			for (int j3 = 0; j3 < seatList.size(); j3++) {
				// 判断座位是否被占
				if (!"0".equals(seatList.get(j3).getSerialNumber()) && seatList.get(j3).getUsable() == 1) {
					if (seatList.get(j3).getType() == 2 && seatList.get(j3).getVip() == 0) {
						assignSeat.assignSeatByUser(i, j3, userList, seatList, map, userListShow);
						bl = true;
						return bl;
					}
				}
			}
			// 没有普通过道座位
			for (int j4 = 0; j4 < seatList.size(); j4++) {
				// 判断座位是否被占
				if (!"0".equals(seatList.get(j4).getSerialNumber()) && seatList.get(j4).getUsable() == 1) {
					if (seatList.get(j4).getType() == 2) {
						assignSeat.assignSeatByUser(i, j4, userList, seatList, map, userListShow);
						bl = true;
						return bl;
					}
				}
			}
			// 不满足用户需求
			notMeetUser.add(userList.get(i));
			randomAndDissatisfylist.add(userList.get(i));
			bl = true;
			return bl;
		}
		// 分配普通靠窗用户
		if (userList.get(i).getPreference() == 1) {
			for (int j3 = 0; j3 < seatList.size(); j3++) {
				// 判断座位是否被占
				if (!"0".equals(seatList.get(j3).getSerialNumber()) && seatList.get(j3).getUsable() == 1) {
					if (seatList.get(j3).getType() == 1 && seatList.get(j3).getVip() == 0) {
						assignSeat.assignSeatByUser(i, j3, userList, seatList, map, userListShow);
						bl = true;
						return bl;
					}
				}
			}
			for (int j4 = 0; j4 < seatList.size(); j4++) {
				// 判断座位是否被占
				if (!"0".equals(seatList.get(j4).getSerialNumber()) && seatList.get(j4).getUsable() == 1) {
					if (seatList.get(j4).getType() == 1) {
						assignSeat.assignSeatByUser(i, j4, userList, seatList, map, userListShow);
						bl = true;
						return bl;
					}
				}
			}
			// 不满足用户需求
			notMeetUser.add(userList.get(i));
			randomAndDissatisfylist.add(userList.get(i));
			bl = true;
			return bl;
		}
		// 判断普通用户是否随机
		if (userList.get(i).getPreference() == 0) {
			randomAndDissatisfylist.add(userList.get(i));
			bl = true;
			return bl;
		}
		return bl;
	}

	public void singlSeatAllocation(User user, List<User> userListShow, List<Seat> seatList) {
		if (user.getVip() == 1) {
			// 分配vip携带婴儿用户
			if (user.getBaby() == 1) {
				for (int j = 0; j < seatList.size(); j++) {
					// 判断是否被占
					if (!"0".equals(seatList.get(j).getSerialNumber()) && seatList.get(j).getUsable() == 1) {
						if (seatList.get(j).getBabySeat() == 1) {
							user.getSeatallocation().setSeatSerialNumber(seatList.get(j).getSerialNumber());
							userListShow.add(user);
							return;
						}
					}
				}
				// 判断携带婴儿用户是否残疾
				if (user.getDisability() == 1) {
					for (int j2 = 0; j2 < seatList.size(); j2++) {
						// 判断座位是否被占
						if (!"0".equals(seatList.get(j2).getSerialNumber()) && seatList.get(j2).getUsable() == 1) {
							if (seatList.get(j2).getType() == 2 && seatList.get(j2).getVip() == 1) {
								user.getSeatallocation().setSeatSerialNumber(seatList.get(j2).getSerialNumber());
								userListShow.add(user);
								return;
							}
						}
					}
					for (int j5 = 0; j5 < seatList.size(); j5++) {
						// 判断座位是否被占
						if (!"0".equals(seatList.get(j5).getSerialNumber()) && seatList.get(j5).getUsable() == 1) {
							if (seatList.get(j5).getType() == 2) {
								user.getSeatallocation().setSeatSerialNumber(seatList.get(j5).getSerialNumber());
								userListShow.add(user);
								return;
							}
						}
					}
					// 判断携带婴儿用户是登机口
					if (user.getPreference() == 3) {
						for (int j3 = 0; j3 < seatList.size(); j3++) {
							// 判断座位是否被占
							if (!"0".equals(seatList.get(j3).getSerialNumber()) && seatList.get(j3).getUsable() == 1) {
								if (seatList.get(j3).getType() == 3) {
									user.getSeatallocation().setSeatSerialNumber(seatList.get(j3).getSerialNumber());
									userListShow.add(user);
									return;
								}
							}
						}
					}
					// 判断携带婴儿残疾用户是否靠窗
					if (user.getPreference() == 1) {
						for (int j3 = 0; j3 < seatList.size(); j3++) {
							// 判断座位是否被占
							if (!"0".equals(seatList.get(j3).getSerialNumber()) && seatList.get(j3).getUsable() == 1) {
								if (seatList.get(j3).getType() == 1 && seatList.get(j3).getVip() == 1) {
									user.getSeatallocation().setSeatSerialNumber(seatList.get(j3).getSerialNumber());
									userListShow.add(user);
									return;
								}
							}
						}
						for (int j6 = 0; j6 < seatList.size(); j6++) {
							// 判断座位是否被占
							if (!"0".equals(seatList.get(j6).getSerialNumber()) && seatList.get(j6).getUsable() != 0) {
								if (seatList.get(j6).getType() == 1) {
									user.getSeatallocation().setSeatSerialNumber(seatList.get(j6).getSerialNumber());
									userListShow.add(user);
									return;
								}
							}
						}
					}
					// 判断携带婴儿残疾用户是否随机
					if (user.getPreference() == 0) {
						for (int j3 = 0; j3 < seatList.size(); j3++) {
							if (!"0".equals(seatList.get(j3).getSerialNumber()) && seatList.get(j3).getUsable() == 1) {
								if (seatList.get(j3).getVip() == 1 && seatList.get(j3).getType() == 0) {
									user.getSeatallocation().setSeatSerialNumber(seatList.get(j3).getSerialNumber());
									userListShow.add(user);
									return;
								}
							}
						}
					}
				}
				// 判断携带婴儿用户是否登机口
				if (user.getPreference() == 3) {
					for (int j3 = 0; j3 < seatList.size(); j3++) {
						// 判断座位是否被占
						if (!"0".equals(seatList.get(j3).getSerialNumber()) && seatList.get(j3).getUsable() == 1) {
							if (seatList.get(j3).getType() == 3) {
								user.getSeatallocation().setSeatSerialNumber(seatList.get(j3).getSerialNumber());
								userListShow.add(user);
								return;
							}
						}
					}
				}
				// 判断携带婴儿用户是否过道
				if (user.getPreference() == 2) {
					for (int j3 = 0; j3 < seatList.size(); j3++) {
						// 判断座位是否被占
						if (!"0".equals(seatList.get(j3).getSerialNumber()) && seatList.get(j3).getUsable() == 1) {
							if (seatList.get(j3).getType() == 2 && seatList.get(j3).getVip() == 1) {
								user.getSeatallocation().setSeatSerialNumber(seatList.get(j3).getSerialNumber());
								userListShow.add(user);
								return;
							}
						}
					}
					for (int j5 = 0; j5 < seatList.size(); j5++) {
						// 判断座位是否被占
						if (!"0".equals(seatList.get(j5).getSerialNumber()) && seatList.get(j5).getUsable() == 1) {
							if (seatList.get(j5).getType() == 2) {
								user.getSeatallocation().setSeatSerialNumber(seatList.get(j5).getSerialNumber());
								userListShow.add(user);
								return;
							}
						}
					}
				}
				// 判断携带婴儿残疾用户是否靠窗
				if (user.getPreference() == 1) {
					for (int j3 = 0; j3 < seatList.size(); j3++) {
						// 判断座位是否被占
						if (!"0".equals(seatList.get(j3).getSerialNumber()) && seatList.get(j3).getUsable() == 1) {
							if (seatList.get(j3).getType() == 1 && seatList.get(j3).getVip() == 1) {
								user.getSeatallocation().setSeatSerialNumber(seatList.get(j3).getSerialNumber());
								userListShow.add(user);
								return;
							}
						}
					}
					for (int j4 = 0; j4 < seatList.size(); j4++) {
						// 判断座位是否被占
						if (!"0".equals(seatList.get(j4).getSerialNumber()) && seatList.get(j4).getUsable() == 1
								&& seatList.get(j4).getType() == 1) {
							user.getSeatallocation().setSeatSerialNumber(seatList.get(j4).getSerialNumber());
							userListShow.add(user);
							return;
						}
					}
				}
				// 判断携带婴儿残疾用户是否随机
				if (user.getPreference() == 0) {
					for (int j3 = 0; j3 < seatList.size(); j3++) {
						if (!"0".equals(seatList.get(j3).getSerialNumber()) && seatList.get(j3).getUsable() == 1) {
							if (seatList.get(j3).getVip() == 1 && seatList.get(j3).getType() == 0) {
								user.getSeatallocation().setSeatSerialNumber(seatList.get(j3).getSerialNumber());
								userListShow.add(user);
								return;
							}
						}
					}
				}
			}
			// 判断vip残疾用户
			if (user.getDisability() == 1) {
				for (int j = 0; j < seatList.size(); j++) {
					// 判断座位是否被占
					if (!"0".equals(seatList.get(j).getSerialNumber()) && seatList.get(j).getUsable() == 1) {
						if (seatList.get(j).getType() == 2 && seatList.get(j).getVip() == 1) {
							user.getSeatallocation().setSeatSerialNumber(seatList.get(j).getSerialNumber());
							userListShow.add(user);
							return;
						}
					}
				}
				for (int j2 = 0; j2 < seatList.size(); j2++) {
					// 判断座位是否被占
					if (!"0".equals(seatList.get(j2).getSerialNumber()) && seatList.get(j2).getUsable() == 1
							&& seatList.get(j2).getType() == 2) {
						user.getSeatallocation().setSeatSerialNumber(seatList.get(j2).getSerialNumber());
						userListShow.add(user);
						return;
					}
				}
				// 判断残疾用户是否登机口
				if (user.getPreference() == 3) {
					for (int j3 = 0; j3 < seatList.size(); j3++) {
						// 判断座位是否被占
						if (!"0".equals(seatList.get(j3).getSerialNumber()) && seatList.get(j3).getUsable() == 1) {
							if (seatList.get(j3).getType() == 3) {
								user.getSeatallocation().setSeatSerialNumber(seatList.get(j3).getSerialNumber());
								userListShow.add(user);
								return;
							}
						}
					}
				}
				// 判断残疾用户是否过道
				if (user.getPreference() == 2) {
					for (int j3 = 0; j3 < seatList.size(); j3++) {
						if (!"0".equals(seatList.get(j3).getSerialNumber()) && seatList.get(j3).getUsable() == 1) {
							if (seatList.get(j3).getVip() == 1 && seatList.get(j3).getType() == 0) {
								user.getSeatallocation().setSeatSerialNumber(seatList.get(j3).getSerialNumber());
								userListShow.add(user);
								return;
							}
						}
					}
				}
				// 判断残疾用户是否靠窗
				if (user.getPreference() == 1) {
					for (int j3 = 0; j3 < seatList.size(); j3++) {
						// 判断座位是否被占
						if (!"0".equals(seatList.get(j3).getSerialNumber()) && seatList.get(j3).getUsable() == 1) {
							if (seatList.get(j3).getType() == 1 && seatList.get(j3).getVip() == 1) {
								user.getSeatallocation().setSeatSerialNumber(seatList.get(j3).getSerialNumber());
								userListShow.add(user);
								return;
							}
						}
					}
					for (int j4 = 0; j4 < seatList.size(); j4++) {
						// 判断座位是否被占
						if (!"0".equals(seatList.get(j4).getSerialNumber()) && seatList.get(j4).getUsable() == 1
								&& seatList.get(j4).getType() == 1) {
							user.getSeatallocation().setSeatSerialNumber(seatList.get(j4).getSerialNumber());
							userListShow.add(user);
							return;
						}
					}
				}
				// 判断残疾用户是否随机
				if (user.getPreference() == 0) {
					for (int j3 = 0; j3 < seatList.size(); j3++) {
						if (!"0".equals(seatList.get(j3).getSerialNumber()) && seatList.get(j3).getUsable() == 1) {
							if (seatList.get(j3).getVip() == 1 && seatList.get(j3).getType() == 0) {
								user.getSeatallocation().setSeatSerialNumber(seatList.get(j3).getSerialNumber());
								userListShow.add(user);
								return;
							}
						}
					}
				}
			}
			// 分配vip登机口用户
			if (user.getPreference() == 3) {
				for (int j3 = 0; j3 < seatList.size(); j3++) {
					// 判断座位是否被占
					if (!"0".equals(seatList.get(j3).getSerialNumber()) && seatList.get(j3).getUsable() == 1) {
						if (seatList.get(j3).getType() == 3) {
							user.getSeatallocation().setSeatSerialNumber(seatList.get(j3).getSerialNumber());
							userListShow.add(user);
							return;
						}
					}
				}
			}
			// 分配vip过道用户
			if (user.getPreference() == 2) {
				for (int j3 = 0; j3 < seatList.size(); j3++) {
					// 判断座位是否被占
					if (!"0".equals(seatList.get(j3).getSerialNumber()) && seatList.get(j3).getUsable() == 1) {
						if (seatList.get(j3).getType() == 2 && seatList.get(j3).getVip() == 1) {
							user.getSeatallocation().setSeatSerialNumber(seatList.get(j3).getSerialNumber());
							userListShow.add(user);
							return;
						}
					}
				}
				for (int j5 = 0; j5 < seatList.size(); j5++) {
					// 判断座位是否被占
					if (!"0".equals(seatList.get(j5).getSerialNumber()) && seatList.get(j5).getUsable() == 1) {
						if (seatList.get(j5).getType() == 2) {
							user.getSeatallocation().setSeatSerialNumber(seatList.get(j5).getSerialNumber());
							userListShow.add(user);
							return;
						}
					}
				}
			}
			// 分配vip靠窗用户
			if (user.getPreference() == 1) {
				for (int j3 = 0; j3 < seatList.size(); j3++) {
					// 判断座位是否被占
					if (!"0".equals(seatList.get(j3).getSerialNumber()) && seatList.get(j3).getUsable() == 1) {
						if (seatList.get(j3).getType() == 1 && seatList.get(j3).getVip() == 1) {
							user.getSeatallocation().setSeatSerialNumber(seatList.get(j3).getSerialNumber());
							userListShow.add(user);
							return;
						}
					}
				}
				for (int j5 = 0; j5 < seatList.size(); j5++) {
					// 判断座位是否被占
					if (!"0".equals(seatList.get(j5).getSerialNumber()) && seatList.get(j5).getUsable() == 1) {
						if (seatList.get(j5).getType() == 1) {
							user.getSeatallocation().setSeatSerialNumber(seatList.get(j5).getSerialNumber());
							userListShow.add(user);
							return;
						}
					}
				}
			}
			// 判断vip用户是否随机
			if (user.getPreference() == 0) {
				for (int j3 = 0; j3 < seatList.size(); j3++) {
					if (!"0".equals(seatList.get(j3).getSerialNumber()) && seatList.get(j3).getUsable() == 1) {
						if (seatList.get(j3).getVip() == 1 && seatList.get(j3).getType() == 0) {
							user.getSeatallocation().setSeatSerialNumber(seatList.get(j3).getSerialNumber());
							userListShow.add(user);
							return;
						}
					}
				}
			}
		}
		// 分配普通婴儿用户
		if (user.getBaby() == 1) {
			for (int j = 0; j < seatList.size(); j++) {
				// 判断是否被占
				if (!"0".equals(seatList.get(j).getSerialNumber()) && seatList.get(j).getUsable() == 1) {
					if (seatList.get(j).getBabySeat() == 1) {
						user.getSeatallocation().setSeatSerialNumber(seatList.get(j).getSerialNumber());
						userListShow.add(user);
						return;
					}
				}
			}
			// 判断携带婴儿用户是否残疾
			if (user.getDisability() == 1) {
				for (int j2 = 0; j2 < seatList.size(); j2++) {
					// 判断座位是否被占
					if (!"0".equals(seatList.get(j2).getSerialNumber()) && seatList.get(j2).getUsable() == 1) {
						if (seatList.get(j2).getType() == 2 && seatList.get(j2).getVip() == 0) {
							user.getSeatallocation().setSeatSerialNumber(seatList.get(j2).getSerialNumber());
							userListShow.add(user);
							return;
						}
					}
				}
				for (int j4 = 0; j4 < seatList.size(); j4++) {
					// 判断座位是否被占
					if (!"0".equals(seatList.get(j4).getSerialNumber()) && seatList.get(j4).getUsable() == 1) {
						if (seatList.get(j4).getType() == 2) {
							user.getSeatallocation().setSeatSerialNumber(seatList.get(j4).getSerialNumber());
							userListShow.add(user);
							return;
						}
					}
				}
				// 判断携带婴儿用户是否登机口
				if (user.getPreference() == 3) {
					for (int j3 = 0; j3 < seatList.size(); j3++) {
						// 判断座位是否被占
						if (!"0".equals(seatList.get(j3).getSerialNumber()) && seatList.get(j3).getUsable() == 1) {
							if (seatList.get(j3).getType() == 3) {
								user.getSeatallocation().setSeatSerialNumber(seatList.get(j3).getSerialNumber());
								userListShow.add(user);
								return;
							}
						}
					}
				}
				// 判断携带婴儿残疾用户是否靠窗
				if (user.getPreference() == 1) {
					for (int j3 = 0; j3 < seatList.size(); j3++) {
						// 判断座位是否被占
						if (!"0".equals(seatList.get(j3).getSerialNumber()) && seatList.get(j3).getUsable() == 1) {
							if (seatList.get(j3).getType() == 1 && seatList.get(j3).getVip() == 0) {
								user.getSeatallocation().setSeatSerialNumber(seatList.get(j3).getSerialNumber());
								userListShow.add(user);
								return;
							}
						}
					}
				}
				// 判断携带婴儿残疾用户是否随机
				if (user.getPreference() == 0) {
					for (int j3 = 0; j3 < seatList.size(); j3++) {
						if (!"0".equals(seatList.get(j3).getSerialNumber()) && seatList.get(j3).getUsable() == 1) {
							if (seatList.get(j3).getVip() == 1 && seatList.get(j3).getType() == 0) {
								user.getSeatallocation().setSeatSerialNumber(seatList.get(j3).getSerialNumber());
								userListShow.add(user);
								return;
							}
						}
					}
				}
			}
		}
		// 分配普通残疾用户
		if (user.getDisability() == 1) {
			for (int j = 0; j < seatList.size(); j++) {
				// 判断座位是否被占
				if (!"0".equals(seatList.get(j).getSerialNumber()) && seatList.get(j).getUsable() == 1) {
					if (seatList.get(j).getType() == 2 && seatList.get(j).getVip() == 0) {
						user.getSeatallocation().setSeatSerialNumber(seatList.get(j).getSerialNumber());
						userListShow.add(user);
						return;
					}
				}
			}
			for (int j1 = 0; j1 < seatList.size(); j1++) {
				if (!"0".equals(seatList.get(j1).getSerialNumber()) && seatList.get(j1).getUsable() == 1) {
					if (seatList.get(j1).getType() == 2) {
						user.getSeatallocation().setSeatSerialNumber(seatList.get(j1).getSerialNumber());
						userListShow.add(user);
						return;
					}
				}
			}
			// 判断残疾用户是否登机口
			if (user.getPreference() == 3) {
				for (int j3 = 0; j3 < seatList.size(); j3++) {
					// 判断座位是否被占
					if (!"0".equals(seatList.get(j3).getSerialNumber()) && seatList.get(j3).getUsable() == 1) {
						if (seatList.get(j3).getType() == 3) {
							user.getSeatallocation().setSeatSerialNumber(seatList.get(j3).getSerialNumber());
							userListShow.add(user);
							return;
						}
					}
				}
			}
			// 判断残疾用户是否靠窗
			if (user.getPreference() == 1) {
				for (int j3 = 0; j3 < seatList.size(); j3++) {
					// 判断座位是否被占
					if (!"0".equals(seatList.get(j3).getSerialNumber()) && seatList.get(j3).getUsable() == 1) {
						if (seatList.get(j3).getType() == 1 && seatList.get(j3).getVip() == 0) {
							user.getSeatallocation().setSeatSerialNumber(seatList.get(j3).getSerialNumber());
							userListShow.add(user);
							return;
						}
					}
				}
				for (int j4 = 0; j4 < seatList.size(); j4++) {
					// 判断座位是否被占
					if (!"0".equals(seatList.get(j4).getSerialNumber()) && seatList.get(j4).getUsable() == 1) {
						if (seatList.get(j4).getType() == 1) {
							user.getSeatallocation().setSeatSerialNumber(seatList.get(j4).getSerialNumber());
							userListShow.add(user);
							return;
						}
					}
				}
			}
			// 判断残疾用户是否随机
			if (user.getPreference() == 0) {
				for (int j3 = 0; j3 < seatList.size(); j3++) {
					if (!"0".equals(seatList.get(j3).getSerialNumber()) && seatList.get(j3).getUsable() == 1) {
						if (seatList.get(j3).getVip() == 1 && seatList.get(j3).getType() == 0) {
							user.getSeatallocation().setSeatSerialNumber(seatList.get(j3).getSerialNumber());
							userListShow.add(user);
							return;
						}
					}
				}
			}
		}
		// 分配普通登机口用户
		if (user.getPreference() == 3) {
			for (int j3 = 0; j3 < seatList.size(); j3++) {
				// 判断座位是否被占
				if (!"0".equals(seatList.get(j3).getSerialNumber()) && seatList.get(j3).getUsable() == 1) {
					if (seatList.get(j3).getType() == 3) {
						user.getSeatallocation().setSeatSerialNumber(seatList.get(j3).getSerialNumber());
						userListShow.add(user);
						return;
					}
				}
			}
		}
		// 分配普通过道用户
		if (user.getPreference() == 2) {
			for (int j3 = 0; j3 < seatList.size(); j3++) {
				// 判断座位是否被占
				if (!"0".equals(seatList.get(j3).getSerialNumber()) && seatList.get(j3).getUsable() == 1) {
					if (seatList.get(j3).getType() == 2 && seatList.get(j3).getVip() == 0) {
						user.getSeatallocation().setSeatSerialNumber(seatList.get(j3).getSerialNumber());
						userListShow.add(user);
						return;
					}
				}
			}
			// 没有普通过道座位
			for (int j4 = 0; j4 < seatList.size(); j4++) {
				// 判断座位是否被占
				if (!"0".equals(seatList.get(j4).getSerialNumber()) && seatList.get(j4).getUsable() == 1) {
					if (seatList.get(j4).getType() == 2) {
						user.getSeatallocation().setSeatSerialNumber(seatList.get(j4).getSerialNumber());
						userListShow.add(user);
						return;
					}
				}
			}
		}
		// 分配普通靠窗用户
		if (user.getPreference() == 1) {
			for (int j3 = 0; j3 < seatList.size(); j3++) {
				// 判断座位是否被占
				if (!"0".equals(seatList.get(j3).getSerialNumber()) && seatList.get(j3).getUsable() == 1) {
					if (seatList.get(j3).getType() == 1 && seatList.get(j3).getVip() == 0) {
						user.getSeatallocation().setSeatSerialNumber(seatList.get(j3).getSerialNumber());
						userListShow.add(user);
						return;
					}
				}
			}
			for (int j4 = 0; j4 < seatList.size(); j4++) {
				// 判断座位是否被占
				if (!"0".equals(seatList.get(j4).getSerialNumber()) && seatList.get(j4).getUsable() == 1) {
					if (seatList.get(j4).getType() == 1) {
						user.getSeatallocation().setSeatSerialNumber(seatList.get(j4).getSerialNumber());
						userListShow.add(user);
						return;
					}
				}
			}
		}
		// 判断普通用户是否随机
		if (user.getPreference() == 0) {
			for (int j3 = 0; j3 < seatList.size(); j3++) {
				if (!"0".equals(seatList.get(j3).getSerialNumber()) && seatList.get(j3).getUsable() == 1) {
					if (seatList.get(j3).getVip() == 0 && seatList.get(j3).getType() == 0) {
						user.getSeatallocation().setSeatSerialNumber(seatList.get(j3).getSerialNumber());
						userListShow.add(user);
						return;
					}
				}
			}
		}
	}

}

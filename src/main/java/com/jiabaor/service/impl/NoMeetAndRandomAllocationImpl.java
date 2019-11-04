package com.jiabaor.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiabaor.pojo.Seat;
import com.jiabaor.pojo.User;
import com.jiabaor.service.AssignSeat;
import com.jiabaor.service.NoMeetAndRandomAllocation;

@Service
public class NoMeetAndRandomAllocationImpl implements NoMeetAndRandomAllocation {

	@Autowired
	private AssignSeat assignSeat;

	public void randomAllocationSeat(List<User> randomAndDissatisfylist, List<Seat> seatList,
			HashMap<String, String> map, List<User> userListShow) {
		for (int j = 0; j < randomAndDissatisfylist.size(); j++) {
			// 判断是否是女和残疾
			if (randomAndDissatisfylist.get(j).getSex() == 2 || randomAndDissatisfylist.get(j).getDisability() == 1) {
				if (randomAndDissatisfylist.get(j).getVip() == 1) {
					for (int j7 = 0; j7 < seatList.size(); j7++) {
						// 判断座位是否被占
						if (!"0".equals(seatList.get(j7).getSerialNumber()) && seatList.get(j7).getUsable() == 1
								&& seatList.get(j7).getVip() == 1 && seatList.get(j7).getType() == 0
								&& seatList.get(j7).getType() != 3) {
							// 分配座位
							assignSeat.assignSeatByUser(j, j7, randomAndDissatisfylist, seatList, map, userListShow);
							break;
						}
						if (j7 == seatList.size() - 1) {
							for (int j8 = 0; j8 < seatList.size(); j8++) {
								// 判断座位是否被占
								if (!"0".equals(seatList.get(j8).getSerialNumber()) && seatList.get(j8).getUsable() == 1
										&& seatList.get(j8).getType() == 0 && seatList.get(j8).getType() != 3) {
									// 分配座位
									assignSeat.assignSeatByUser(j, j8, randomAndDissatisfylist, seatList, map,
											userListShow);
									break;
								}
								if (j8 == seatList.size() - 1) {
									for (int j9 = 0; j9 < seatList.size(); j9++) {
										// 判断座位是否被占
										if (!"0".equals(seatList.get(j9).getSerialNumber())
												&& seatList.get(j9).getUsable() == 1) {
											// 分配座位
											assignSeat.assignSeatByUser(j, j9, randomAndDissatisfylist, seatList, map,
													userListShow);
											break;
										}
									}
								}
							}
						}
					}
				} else {
					for (int j7 = 0; j7 < seatList.size(); j7++) {
						// 判断座位是否被占
						if (!"0".equals(seatList.get(j7).getSerialNumber()) && seatList.get(j7).getUsable() == 1
								&& seatList.get(j7).getVip() == 0 && seatList.get(j7).getType() == 0
								&& seatList.get(j7).getType() != 3) {
							// 分配座位
							assignSeat.assignSeatByUser(j, j7, randomAndDissatisfylist, seatList, map, userListShow);
							break;
						}
						if (j7 == seatList.size() - 1) {
							for (int j8 = 0; j8 < seatList.size(); j8++) {
								// 判断座位是否被占
								if (!"0".equals(seatList.get(j8).getSerialNumber()) && seatList.get(j8).getUsable() == 1
										&& seatList.get(j8).getType() == 0 && seatList.get(j8).getType() != 3) {
									// 分配座位
									assignSeat.assignSeatByUser(j, j8, randomAndDissatisfylist, seatList, map,
											userListShow);
									break;
								}
								if (j8 == seatList.size() - 1) {
									for (int j9 = 0; j9 < seatList.size(); j9++) {
										// 判断座位是否被占
										if (!"0".equals(seatList.get(j9).getSerialNumber())
												&& seatList.get(j9).getUsable() == 1) {
											// 分配座位
											assignSeat.assignSeatByUser(j, j9, randomAndDissatisfylist, seatList, map,
													userListShow);
											break;
										}
									}
								}
							}
						}
					}
				}
			} else {
				if (randomAndDissatisfylist.get(j).getVip() == 1) {
					// 是男
					for (int j7 = 0; j7 < seatList.size(); j7++) {
						// 判断座位是否被占
						if (!"0".equals(seatList.get(j7).getSerialNumber()) && seatList.get(j7).getUsable() == 1
								&& seatList.get(j7).getVip() == 1 && seatList.get(j7).getType() == 0) {
							// 分配座位
							assignSeat.assignSeatByUser(j, j7, randomAndDissatisfylist, seatList, map, userListShow);
							break;
						}
						if (j7 == seatList.size() - 1) {
							for (int j8 = 0; j8 < seatList.size(); j8++) {
								// 判断座位是否被占
								if (!"0".equals(seatList.get(j8).getSerialNumber()) && seatList.get(j8).getUsable() == 1
										&& seatList.get(j8).getType() == 0) {
									// 分配座位
									assignSeat.assignSeatByUser(j, j8, randomAndDissatisfylist, seatList, map,
											userListShow);
									break;
								}
								if (j8 == seatList.size() - 1) {
									for (int j9 = 0; j9 < seatList.size(); j9++) {
										// 判断座位是否被占
										if (!"0".equals(seatList.get(j9).getSerialNumber())
												&& seatList.get(j9).getUsable() == 1) {
											// 分配座位
											assignSeat.assignSeatByUser(j, j9, randomAndDissatisfylist, seatList, map,
													userListShow);
											break;
										}
									}
								}
							}
						}
					}
				} else {
					for (int j7 = 0; j7 < seatList.size(); j7++) {
						// 判断座位是否被占
						if (!"0".equals(seatList.get(j7).getSerialNumber()) && seatList.get(j7).getUsable() == 1
								&& seatList.get(j7).getVip() == 0 && seatList.get(j7).getType() == 0) {
							// 分配座位
							assignSeat.assignSeatByUser(j, j7, randomAndDissatisfylist, seatList, map, userListShow);
							break;
						}
						if (j7 == seatList.size() - 1) {
							for (int j8 = 0; j8 < seatList.size(); j8++) {
								// 判断座位是否被占
								if (!"0".equals(seatList.get(j8).getSerialNumber()) && seatList.get(j8).getUsable() == 1
										&& seatList.get(j8).getType() == 0) {
									// 分配座位
									assignSeat.assignSeatByUser(j, j8, randomAndDissatisfylist, seatList, map,
											userListShow);
									break;
								}
								if (j8 == seatList.size() - 1) {
									for (int j9 = 0; j9 < seatList.size(); j9++) {
										// 判断座位是否被占
										if (!"0".equals(seatList.get(j9).getSerialNumber())
												&& seatList.get(j9).getUsable() == 1) {
											// 分配座位
											assignSeat.assignSeatByUser(j, j9, randomAndDissatisfylist, seatList, map,
													userListShow);
											break;
										}
									}
								}
							}
						}
					}
				}
			}
		}

	}
}

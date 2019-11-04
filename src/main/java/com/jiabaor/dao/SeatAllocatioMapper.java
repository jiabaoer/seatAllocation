package com.jiabaor.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jiabaor.pojo.Seat;
import com.jiabaor.pojo.User;

public interface SeatAllocatioMapper {

	// 查询所有用户信息
	List<User> selectUserList();

	// 查询座位信息
	List<Seat> selectSeatList(String planeType);

	// 更新座位
	void insertSeatAllocation(@Param("idCard") String idCard, @Param("seat") String seat);

	// 查询所有可用座位信息
	int selectUsableSeat(String planeType);

	List<User> selectUserListByIdCast(String idCard);

	List<User> selectUserListAndSeatListByUsergroup(String userGroup);

}

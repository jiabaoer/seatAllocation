package com.jiabaor.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiabaor.dao.SeatAllocatioMapper;
import com.jiabaor.pojo.Msg;
import com.jiabaor.pojo.Seatallocation;
import com.jiabaor.pojo.User;
import com.jiabaor.pojo.UserListAndNonMeet;
import com.jiabaor.service.AllocationService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class AllocateAllSeatController {

	@Autowired
	private AllocationService allocationService;

	@Autowired 
	private SeatAllocatioMapper seatAllocatioMapper;
	/**
	 * 整机分配
	 * 
	 * @param planeType 机型好
	 * @param a         每排座位数
	 * @param request   请求
	 * @return
	 */
	@RequestMapping("allAllocation")
	@ResponseBody
	public Msg allAllocation(String planeType, int a, HttpServletRequest request) {
		// 开始时间
		long start = System.currentTimeMillis();
		// 页面显示用户和不满足用户
		UserListAndNonMeet userListAndNonMeet = new UserListAndNonMeet();
		// 分配座位
		boolean bl = allocationService.allocationSeatList(userListAndNonMeet, planeType, a, request);
		// 结束时间
		long endTime = System.currentTimeMillis();
		// 最终时间
		float excTime = (float) (endTime - start) / 1000;
		if (bl) {
			userListAndNonMeet.setTime(excTime);
			return Msg.success().add(userListAndNonMeet);
		} else {
			return Msg.fail();
		}
	}

	/**
	 * 单人分配
	 * 
	 * @param oneUser   个人用户信息（字符串类型）
	 * @param planeType 机型好
	 * @return
	 */
	@RequestMapping("oneAllocation")
	@ResponseBody
	public UserListAndNonMeet oneAllocation(String oneUser, String planeType) {
		// 开始时间
		long start = System.currentTimeMillis();
		// 将字符串oneUser转换为json集合
		JSONArray jsonArray = JSONArray.fromObject(oneUser);
		// 获取第一个集合转换为object
		JSONArray jsonArr = JSONArray.fromObject(jsonArray.getJSONObject(0).getString("passenger_info"));
		// 获取键为passenger_info的值
		User user = new User();
		Seatallocation seatallocation = new Seatallocation();
		// 存储用户信息
		// 获取第一个用户
		JSONObject json = jsonArr.getJSONObject(0);
		// 获取并存储
		user.setIdCard(json.getString("idCard"));
		user.setName(json.getString("name"));
		user.setSex(json.getInt("sex"));
		user.setVip(json.getInt("vip"));
		user.setBaby(json.getInt("baby"));
		user.setDisability(json.getInt("disability"));
		user.setPreference(json.getInt("preference"));
		seatallocation.setUserIdCard(json.getString("idCard"));
		user.setSeatallocation(seatallocation);
		// 分配座位
		UserListAndNonMeet oneUserList = allocationService.oneAllAllocation(planeType, user);
		// 结束时间
		long endTime = System.currentTimeMillis();
		// 最终时间
		float excTime = (float) (endTime - start) / 1000;
		oneUserList.setTime(excTime);
		return oneUserList;
	}

	/**
	 * 组分配
	 * 
	 * @param groupUserList 组用户信息
	 * @param planeType     机型号
	 * @param a             每排座位数
	 * @return
	 */
	@RequestMapping("moreAllocation")
	@ResponseBody
	public UserListAndNonMeet moreAllocation(String groupUserList, String planeType, int a) {
		// 开始时间
		long start = System.currentTimeMillis();
		// 将字符串groupUserList转换为json集合
		JSONArray jsonArray = JSONArray.fromObject(groupUserList);
		// 获取第一个集合
		JSONArray jsonArr = JSONArray.fromObject(jsonArray.getJSONObject(0).getString("passenger_info"));
		// 组号
		String userGroup = UUID.randomUUID().toString().substring(0, 4);
		User user = null;
		Seatallocation seatallocation = null;
		// 存储组用户信息
		List<User> groupList = new ArrayList<User>();
		// 遍历
		for (int i = 0; i < jsonArr.size(); i++) {
			// 获取每个对象
			JSONObject json = jsonArr.getJSONObject(i);
			user = new User();
			// 获取每个值并存储
			user.setIdCard(json.getString("idCard"));
			user.setName(json.getString("name"));
			user.setSex(json.getInt("sex"));
			user.setVip(json.getInt("vip"));
			user.setBaby(json.getInt("baby"));
			user.setDisability(json.getInt("disability"));
			user.setPreference(json.getInt("preference"));
			user.setUserGroup(userGroup);
			seatallocation = new Seatallocation();
			seatallocation.setUserIdCard(json.getString("idCard"));
			user.setSeatallocation(seatallocation);
			groupList.add(user);
		}
		// 分配座位
		UserListAndNonMeet moreUserList = allocationService.moreAllAllocation(a, planeType, groupList);
		// 结束时间
		long endTime = System.currentTimeMillis();
		// 最终时间
		float excTime = (float) (endTime - start) / 1000;
		moreUserList.setTime(excTime);
		return moreUserList;
	}

	/**
	 * 更新座位
	 * 
	 * @param session 会话
	 */
	@RequestMapping("insertSeatAllocation")
	public void insertSeatAllocation(HttpSession session) {
		@SuppressWarnings("unchecked")
		// 获取session中的所有用户的身份证和座位编号
		Map<String, String> attribute = (Map<String, String>) session.getAttribute("orderList");
		// 遍历
		for (String order : attribute.keySet()) {
			String idCard = order;
			String seat = attribute.get(order);
			// 更新每个用户的座位
			allocationService.insertSeatAllocation(idCard, seat);
		}
	}

	@RequestMapping("checkUsers")
	@ResponseBody
	public Msg checkUsersByIdCard(String idCard) {
		List<User> userlist = seatAllocatioMapper.selectUserListByIdCast(idCard);
		UserListAndNonMeet userListAndNonMeet = new UserListAndNonMeet();
		if (userlist.size() > 0) {
			if (!(userlist.get(0).getUserGroup()==null || "".equals(userlist.get(0).getUserGroup()))) {
				String userGroup = userlist.get(0).getUserGroup();
				List<User> userlistGroup = seatAllocatioMapper.selectUserListAndSeatListByUsergroup(userGroup);
				userListAndNonMeet.setUserListShow(userlistGroup);
				return Msg.success().add(userListAndNonMeet);
			} else {
				userListAndNonMeet.setUserListShow(userlist);
				return Msg.success().add(userListAndNonMeet);
			}
		} else {
			return Msg.fail();
		}
	}

}

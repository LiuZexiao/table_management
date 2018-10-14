package com.information.service;

import java.util.List;

import com.information.entity.UserEntity;
import com.mysql.fabric.xmlrpc.base.Data;

public interface UserinfoService {
	/**
	 * 登陆
	 * @param workNum
	 * @param uPassword
	 * @return
	 */
	public abstract UserEntity login(String workNum,String uPassword);
	/**
	 * 查询我的信息
	 * @param workNum
	 * @return
	 */
	public abstract UserEntity myUserinfo(String workNum);
	/**
	 * 修改密码
	 * @param newPassword
	 * @return
	 */
	public abstract int myUpdatePassword(String workNum, String oldPassword, String newPassword);
	/**
	 * 修改我的信息
	 * @param workNum
	 * @param uName
	 * @param departmentId
	 * @return
	 */
	public abstract int myUpdateInfo(String workNum, String uName, int departmentId);
	/**
	 * 添加用户
	 * @param workNum
	 * @param uName
	 * @param uPassword
	 * @param departmentId
	 * @param roleId
	 * @return
	 */
	public abstract int addUser(String workNum, String uName, String uPassword, int departmentId, int roleId);
	/**
	 * 修改用户信息
	 * @param id
	 * @param workNum
	 * @param uName
	 * @param uPassword
	 * @param departmentId
	 * @param roleId
	 * @return
	 */
	public abstract int updataUser(int id, String workNum, String uName, String uPassword, int departmentId, int roleId);
	/**
	 * 删除用户
	 * @param list
	 * @return
	 */
	public abstract int deleteUser(List list);
}

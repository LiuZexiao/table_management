package com.information.dao;

import java.util.List;

import com.information.entity.UserEntity;

public interface UserDao {
	/**
	 * 登录
	 * @param workNum
	 * @return
	 */
	public abstract UserEntity userLogin(String workNum);
	/**
	 * 修改我的密码
	 * @param workNum
	 * @param newPassword
	 * @return
	 */
	public abstract int myUpdatePassword(String workNum,String newPassword);
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
//	/**
//	 * 根据部门查询用户信息
//	 * @param departmentId
//	 * @return
//	 */
//	public abstract List<UserEntity> selectUser(int departmentId);
	public abstract int addAllUser(List<UserEntity> users);
}


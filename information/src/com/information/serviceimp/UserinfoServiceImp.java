package com.information.serviceimp;

import java.util.List;

import com.information.dao.UserDao;
import com.information.entity.UserEntity;
import com.information.impdao.UserImpDao;
import com.information.service.UserinfoService;
import com.mysql.fabric.xmlrpc.base.Data;

public class UserinfoServiceImp implements UserinfoService{
	private UserDao dao = new UserImpDao();
	// 登陆
	public UserEntity login(String workNum, String uPassword) {

		UserEntity info=new UserEntity();
		info = dao.userLogin(workNum);
		if(info!=null&&info.getuPassword().equals(uPassword))
		{
			return info;
		}
		return null;
	}
	// 查询当前用户信息
	public UserEntity myUserinfo(String workNum) {
		
		UserEntity info=new UserEntity();
		info = dao.userLogin(workNum);
		
		if(info!=null)
		{
			return info;
		}
		return null;
	}	
	// 修改密码
	public int myUpdatePassword(String workNum, String oldPassword, String newPassword) {

		int number = 0;
		UserEntity info=new UserEntity();
		info = myUserinfo(workNum);
		if(info.getuPassword().equals(oldPassword)) {
			number = dao.myUpdatePassword(workNum, newPassword);
		}
		
		return number;
	}
	// 修改我的信息
	public int myUpdateInfo(String workNum, String uName, int departmentId) {

		int number = 0;
		number = dao.myUpdateInfo(workNum, uName, departmentId);
		return number;
	}
	// 添加用户
	public int addUser(String workNum, String uName, String uPassword, int departmentId, int roleId) {

		int number = 0;
		number = dao.addUser(workNum, uName, uPassword, departmentId, roleId);
		System.out.println(number);
		return number;
	}
	// 修改用户信息
	public int updataUser(int id, String workNum, String uName, String uPassword, int departmentId, int roleId) {

		int number = 0;
		number = dao.updataUser(id, workNum, uName, uPassword, departmentId, roleId);
		return number;
	}
	// 删除用户
	public int deleteUser(List list) {
		int number = 0;
		number = dao.deleteUser(list);
		return number;
	}

	
}

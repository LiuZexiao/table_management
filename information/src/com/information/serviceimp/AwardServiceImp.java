package com.information.serviceimp;

import java.util.ArrayList;
import java.util.List;

import com.information.dao.AwardDao;
import com.information.dao.UserDao;
import com.information.entity.AwardEntity;
import com.information.entity.UserEntity;
import com.information.impdao.AwardImpDao;
import com.information.impdao.UserImpDao;
import com.information.service.AwardService;

public class AwardServiceImp implements AwardService{
	
	// 查询当前用户信息
	public UserEntity myUserinfo(String workNum) {
		UserDao dao = new UserImpDao();
		
		UserEntity info=new UserEntity();
		info = dao.userLogin(workNum);
		
		if(info!=null)
		{
			return info;
		}
		return null;
	}
	// 技能竞赛填报
	public int addAward(String workNum, String name, int departmentId, String identity, String stuId, String stuClass, String rank,
			String proName, String grade, String type, String certificationTime, String certificationCompany,
			String semester, String plan) {
		AwardDao dao = new AwardImpDao();
		int number = 0;
		UserEntity info=new UserEntity();
		info = myUserinfo(workNum);
		System.out.println(info.getId());
		number = dao.addAward(name, departmentId, identity, stuId, stuClass, rank, proName, grade, type, certificationTime, certificationCompany, semester, plan, info.getId());
		return number;
	}
	// 修改技能竞赛
	public int updataAward(int id, String name, int departmentId, String identity, String stuId, String stuClass,
			String rank, String proName, String grade, String type, String certificationTime,
			String certificationCompany, String semester, String plan, int userinfoId) {
		AwardDao dao = new AwardImpDao();
		int number = 0;
		number = dao.updataAward(id, name, departmentId, identity, stuId, stuClass, rank, proName, grade, type, certificationTime, certificationCompany, semester, plan, userinfoId);
		return number;
	}
	// 删除技能竞赛
	public int deleteAward(List list) {
		AwardDao dao = new AwardImpDao();
		int number = 0;
		number = dao.deleteAward(list);
		return number;
	}
	// 按条件查询所有技能竞赛
	public List<AwardEntity> awardinfo(Object[] param) {
		List<AwardEntity> award = new ArrayList<AwardEntity>();
		AwardDao dao = new AwardImpDao();
		award = dao.awardinfo(param);
		return award;
	}
}

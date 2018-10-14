package com.information.serviceimp;

import java.util.ArrayList;
import java.util.List;

import com.information.dao.AwardDao;
import com.information.dao.ScientificDao;
import com.information.dao.UserDao;
import com.information.entity.AwardEntity;
import com.information.entity.ScientificEntity;
import com.information.entity.UserEntity;
import com.information.impdao.AwardImpDao;
import com.information.impdao.ScientificImpDao;
import com.information.impdao.UserImpDao;
import com.information.service.ScientificService;

public class ScientificServiceImp implements ScientificService{
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
	// 科研成果填报
	public int addScientific(String workNum, String name, int categoryId, String proName, String proSource, String rank,
			String certificationTime, String situation, int departmentId) {
		ScientificDao dao = new ScientificImpDao();
		int number = 0;
		UserEntity info=new UserEntity();
		info = myUserinfo(workNum);
		System.out.println(info.getId());
		number = dao.addScientific(name, categoryId, proName, proSource, rank, certificationTime, situation, departmentId, info.getId());
		return number;
	}
	// 修改科研成果
	public int updataScientific(int id, String name, int categoryId, String proName, String proSource, String rank,
			String certificationTime, String situation, int departmentId, int userinfoId) {
		ScientificDao dao = new ScientificImpDao();
		int number = 0;
		number = dao.updataScientific(id, name, categoryId, proName, proSource, rank, certificationTime, situation, departmentId, userinfoId);
		return number;
	}
	// 删除科研成果
	public int deleteScientific(List list) {
		ScientificDao dao = new ScientificImpDao();
		int number = 0;
		number = dao.deleteScientific(list);
		return number;
	}
	// 按条件查询所有科研成果
	public List<ScientificEntity> scientificinfo(Object[] param) {
		List<ScientificEntity> scientific = new ArrayList<ScientificEntity>();
		ScientificDao dao = new ScientificImpDao();
		scientific = dao.scientificinfo(param);
		return scientific;
	}
}

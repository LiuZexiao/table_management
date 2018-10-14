package com.information.serviceimp;

import java.util.List;

import com.information.dao.PageDao;
import com.information.entity.AwardEntity;
import com.information.entity.PageEntity;
import com.information.entity.ScientificEntity;
import com.information.entity.UserEntity;
import com.information.impdao.ImpPageDao;
import com.information.service.PageService;

public class PageImpService implements PageService{
		PageDao dao = new ImpPageDao();
	// 获取用户列表信息
	public PageEntity<UserEntity> pageUser(int pageNo, int pageSize, Object[] param) {
		PageEntity<UserEntity> pe = new PageEntity<>(pageNo, pageSize);
		
		pe.setList(dao.pageinfo(pageNo, pageSize, param));
		pe.setTotalItemNumber(dao.userTotalItemNumber(param));
		
		return pe;
	}
	// 获取技能竞赛列表
	public PageEntity<AwardEntity> awardinfo(int pageNo, int pageSize, Object[] param) {
		PageEntity<AwardEntity> pe = new PageEntity<>(pageNo, pageSize);
		
		pe.setList(dao.awardinfo(pageNo, pageSize, param));
		pe.setTotalItemNumber(dao.awardTotalItemNumber(param));
		
		return pe;
	}
	// 获取科研列表
	public PageEntity<ScientificEntity> scientificinfo(int pageNo, int pageSize, Object[] param) {
		PageEntity<ScientificEntity> pe = new PageEntity<>(pageNo, pageSize);
		
		pe.setList(dao.scientificinfo(pageNo, pageSize, param));
		pe.setTotalItemNumber(dao.scientificTotalItemNumber(param));
		
		return pe;
	}

}

package com.information.service;

import java.util.List;

import com.information.entity.AwardEntity;
import com.information.entity.PageEntity;
import com.information.entity.ScientificEntity;

public interface PageService {
	/**
	 * 获取用户列表信息
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public abstract PageEntity pageUser(int page, int pageSize, Object[] param);
	/**
	 * 获取技能竞赛列表
	 * @param pageNo
	 * @param pageSize
	 * @param param
	 * @return
	 */
	public abstract PageEntity awardinfo(int pageNo, int pageSize, Object[] param);
	/**
	 * 获取科研列表
	 * @param pageNo
	 * @param pageSize
	 * @param param
	 * @return
	 */
	public abstract PageEntity scientificinfo(int pageNo, int pageSize, Object[] param);
}

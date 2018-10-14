package com.information.dao;

import java.util.List;

import com.information.entity.AwardEntity;
import com.information.entity.ScientificEntity;
import com.information.entity.UserEntity;

public interface PageDao {
	/**
	 * 获取用户列表
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public abstract List<UserEntity> pageinfo(int pageNo, int pageSize, Object[] param);
	/**
	 * 获取用户记录数
	 * @return
	 */
	public abstract int userTotalItemNumber(Object[] param);
	/**
	 * 获取技能竞赛列表
	 * @param pageNo
	 * @param pageSize
	 * @param param
	 * @return
	 */
	public abstract List<AwardEntity> awardinfo(int pageNo, int pageSize, Object[] param);
	/**
	 * 获取技能竞赛记录数
	 */
	public abstract int awardTotalItemNumber(Object[] param);
	/**
	 * 获取科研列表
	 * @param pageNo
	 * @param pageSize
	 * @param param
	 * @return
	 */
	public abstract List<ScientificEntity> scientificinfo(int pageNo, int pageSize, Object[] param);
	/**
	 * 获取科研记录数
	 * @param param
	 * @return
	 */
	public abstract int scientificTotalItemNumber(Object[] param);
}

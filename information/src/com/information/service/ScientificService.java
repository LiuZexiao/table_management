package com.information.service;

import java.util.List;

import com.information.entity.AwardEntity;
import com.information.entity.ScientificEntity;

public interface ScientificService {
	/**
	 * 科研成果填报
	 * @param name
	 * @param categoryId
	 * @param proName
	 * @param proSource
	 * @param rank
	 * @param certificationTime
	 * @param situation
	 * @param remarks
	 * @param departmentId
	 * @param userinfoId
	 * @return
	 */
	public abstract int addScientific(String workNum, String name, int categoryId, String proName, String proSource, String rank, String certificationTime, String situation, int departmentId);
	/**
	 * 修改科研成果
	 * @param id
	 * @param name
	 * @param categoryId
	 * @param proName
	 * @param proSource
	 * @param rank
	 * @param certificationTime
	 * @param situation
	 * @param departmentId
	 * @param userinfoId
	 * @return
	 */
	public abstract int updataScientific(int id, String name, int categoryId, String proName, String proSource, String rank, String certificationTime, String situation, int departmentId, int userinfoId);
	/**
	 * 删除科研成果
	 * @param list
	 * @return
	 */
	public abstract int deleteScientific(List list);
	/**
	 * 按条件查询所有科研成果
	 * @param pageNo
	 * @param pageSize
	 * @param param
	 * @return
	 */
	public abstract List<ScientificEntity> scientificinfo(Object[] param);

}

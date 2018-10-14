package com.information.dao;

import java.util.List;

import com.information.entity.AwardEntity;
import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;

public interface AwardDao {
	/**
	 * 技能竞赛填报
	 * @param name
	 * @param departmentId
	 * @param identity
	 * @param stuId
	 * @param stuClass
	 * @param rank
	 * @param proName
	 * @param grade
	 * @param type
	 * @param certificationTime
	 * @param certificationCompany
	 * @param semester
	 * @param plan
	 * @param userinfoId
	 * @return
	 */
	public abstract int addAward(String name, int departmentId, String identity, String stuId, String stuClass,String rank, String proName, String grade, String type, String certificationTime, String certificationCompany, String semester, String plan, int userinfoId);
	/** 修改技能竞赛
	 * 
	 * @param id
	 * @param name
	 * @param departmentId
	 * @param identity
	 * @param stuId
	 * @param stuClass
	 * @param rank
	 * @param proName
	 * @param grade
	 * @param type
	 * @param certificationTime
	 * @param certificationCompany
	 * @param semester
	 * @param plan
	 * @param userinfoId
	 * @return
	 */
	public abstract int updataAward(int id, String name, int departmentId, String identity, String stuId, String stuClass, String rank, String proName, String grade, String type, String certificationTime, String certificationCompany, String semester, String plan, int userinfoId);
	/**
	 * 删除技能竞赛
	 * @param list
	 * @return
	 */
	public abstract int deleteAward(List list);
	/**
	 * 按条件查询所有技能竞赛
	 * @param pageNo
	 * @param pageSize
	 * @param param
	 * @return
	 */
	public abstract List<AwardEntity> awardinfo(Object[] param);

}

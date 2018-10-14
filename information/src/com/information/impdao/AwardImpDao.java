package com.information.impdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.information.dao.AwardDao;
import com.information.entity.AwardEntity;
import com.information.util.JDBC;

public class AwardImpDao implements AwardDao{
	/**
	 * 技能竞赛填报
	 */
	public int addAward(String name, int departmentId, String identity,String stuId,String stuClass,String rank, String proName, String grade, String type,
			String certificationTime, String certificationCompany, String semester, String plan, int userinfoId) {
		int number = JDBC.executeUpdate("insert into award(name, departmentId, identity, stuId, stuClass, rank, proName, grade, type, certificationTime, certificationCompany, semester, plan, userinfoId) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)", new Object[] {name, departmentId, identity, stuId, stuClass, rank, proName, grade, type, certificationTime, certificationCompany, semester, plan, userinfoId});
		if (number > 0) {
			System.out.println("添加成功");
		}
		return number;
	}
	/**
	 *  修改技能竞赛
	 */
	public int updataAward(int id, String name, int departmentId, String identity, String stuId, String stuClass,
			String rank, String proName, String grade, String type, String certificationTime,
			String certificationCompany, String semester, String plan, int userinfoId) {
		int number = JDBC.executeUpdate("update award set name=?,departmentId=?,identity=?,stuId=?,stuClass=?,rank=?,proName=?,grade=?,type=?,certificationTime=?,certificationCompany=?,semester=?,plan=?,userinfoId=? where id=?", new Object[] {name, departmentId, identity, stuId, stuClass, rank, proName, grade, type, certificationTime, certificationCompany, semester, plan, userinfoId, id});
		if (number > 0) {
			System.out.println("修改成功");
		}
		return number;
	}
	/**
	 * 删除技能竞赛
	 */
	public int deleteAward(List list) {
		String sum="0";
		
		Iterator Iter = list.iterator();
		while(Iter.hasNext()) {
			sum += ","+Iter.next();
		}
		
		System.out.println(sum);
		
		int number = JDBC.executeUpdate("delete from award where id in ("+sum+")", new Object[] {});
		return number;
	
	}
	// 按条件查询所有技能竞赛
	public List<AwardEntity> awardinfo(Object[] param) {
		Connection	con = null;
		PreparedStatement pt = null;
		ResultSet res = null;
		AwardEntity ae = null; 
		List<AwardEntity> award = null;
		String premise = "";
		
		int departmentId =  (int) param[0];
		String identity =  (String) param[1];
		String type = (String) param[2];
		String rank = (String) param[3];
		String clock1 = (String) param[4];
		String clock2 = (String) param[5];
		String uName = (String) param[6];
		int userinfoId = (int) param[7];
		
		if (userinfoId != 0) {
			premise += " and userinfoId=" +userinfoId;
		}
		if (departmentId != 0) {
			premise += " and award.departmentId=" +departmentId;
		}
		if(!identity.equals("")) {
			premise += " and identity="+"'"+identity+"'";
		}
		if(!type.equals("")) {
			premise += " and type="+"'"+type+"'";
		}
		if(!rank.equals("")) {
			premise += " and rank="+"'"+rank+"'";
		}
		if(!clock1.equals("") && !clock2.equals("")) {
			premise += " and certificationTime>="+"'"+clock1+"'";
			premise += " and certificationTime<="+"'"+clock2+"'";
		}
		if(!uName.equals("")) {
			premise += " and uName="+"'"+uName+"'";
		}
		
		System.out.println("premise" + premise);
		String sql = "select * from award LEFT JOIN department on award.departmentId=department.id LEFT JOIN userinfo on award.userinfoId = userinfo.id where 1=1"+premise+" order by award.id desc ";
		try {
			con = JDBC.getConnection();
			pt = con.prepareStatement(sql);
			
			res = pt.executeQuery();
			while(res.next()) {
				ae = new AwardEntity();
				ae.setId(res.getInt("id"));
				ae.setName(res.getString("name"));
				ae.setDepartmentId(res.getInt("departmentId"));
				ae.setIdentity(res.getString("identity"));
				ae.setStuId(res.getString("stuId"));
				ae.setStuClass(res.getString("stuClass"));
				ae.setRank(res.getString("rank"));
				ae.setProName(res.getString("proName"));
				ae.setGrade(res.getString("grade"));
				ae.setStuClass(res.getString("stuClass"));
				ae.setType(res.getString("type"));
				ae.setCertificationTime((res.getString("certificationTime")).substring(0,7));
				ae.setCertificationCompany(res.getString("certificationCompany"));
				ae.setSemester(res.getString("semester"));
				ae.setPlan(res.getString("plan"));
				ae.setuName(res.getString("uName"));
				ae.setDepartment(res.getString("department"));
				ae.setUserinfoId(res.getInt("userinfoId"));
				if(award == null) {
					award = new ArrayList<AwardEntity>();
				}
				award.add(ae);
			}
		} catch (Exception e) {
			throw new RuntimeException("数据库查询失败");
		}finally {
			JDBC.close(con);
			JDBC.close(pt);
			JDBC.close(res);
		}
		
		return award;
	}
	
}

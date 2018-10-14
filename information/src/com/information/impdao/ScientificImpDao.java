package com.information.impdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.information.dao.ScientificDao;
import com.information.entity.AwardEntity;
import com.information.entity.ScientificEntity;
import com.information.util.JDBC;

public class ScientificImpDao implements ScientificDao{
	/**
	 * 科研填报
	 */
	public int addScientific(String name, int categoryId, String proName, String proSource, String rank,
			String certificationTime, String situation, int departmentId, int userinfoId) {
		int number = JDBC.executeUpdate("insert into scientific(name, categoryId, proName, proSource, rank, certificationTime, situation, departmentId, userinfoId) values(?,?,?,?,?,?,?,?,?)", new Object[] {name, categoryId, proName, proSource, rank, certificationTime, situation, departmentId, userinfoId});
		if (number > 0) {
			System.out.println("添加成功");
		}
		return number;
	}
	/**
	 *  修改科研成果
	 */
	public int updataScientific(int id, String name, int categoryId, String proName, String proSource, String rank,
			String certificationTime, String situation, int departmentId, int userinfoId) {
		int number = JDBC.executeUpdate("update scientific set name=?,categoryId=?,proName=?,proSource=?,rank=?,certificationTime=?,situation=?,departmentId=?,userinfoId=? where id=?", new Object[] {name, categoryId, proName, proSource, rank, certificationTime, situation, departmentId, userinfoId, id});
		if (number > 0) {
			System.out.println("修改成功");
		}
		return number;
	}
	/**
	 * 删除科研
	 */
	public int deleteScientific(List list) {
		String sum="0";
		
		Iterator Iter = list.iterator();
		while(Iter.hasNext()) {
			sum += ","+Iter.next();
		}
		
		System.out.println(sum);
		
		int number = JDBC.executeUpdate("delete from scientific where id in ("+sum+")", new Object[] {});
		return number;
	
	}
	/**
	 * 按条件查询所有科研成果
	 */
	public List<ScientificEntity> scientificinfo(Object[] param) {
		Connection	con = null;
		PreparedStatement pt = null;
		ResultSet res = null;
		ScientificEntity se = null; 
		List<ScientificEntity> scientific = null;
		String premise = "";
		
		int departmentId =  (int) param[0];
		int categoryId = (int) param[1];
		String rank = (String) param[2];
		String clock1 = (String) param[3];
		String clock2 = (String) param[4];
		String uName = (String) param[5];
		int userinfoId = (int) param[6];
		
		if (userinfoId != 0) {
			premise += " and userinfoId=" +userinfoId;
		}
		if (departmentId != 0) {
			premise += " and scientific.departmentId=" +departmentId;
		}
		if(categoryId != 0) {
			premise += " and categoryId="+categoryId;
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
		String sql = "select * from scientific LEFT JOIN department on scientific.departmentId=department.id LEFT JOIN userinfo on scientific.userinfoId = userinfo.id LEFT JOIN category on scientific.categoryId = category.id where 1=1"+premise+" order by scientific.id desc";
		try {
			con = JDBC.getConnection();
			pt = con.prepareStatement(sql);
			
			res = pt.executeQuery();
			while(res.next()) {
				se = new ScientificEntity();
				se.setId(res.getInt("id"));
				se.setName(res.getString("name"));
				se.setCategoryId(res.getInt("categoryId"));
				se.setProName(res.getString("proName"));
				se.setProSource(res.getString("proSource"));
				se.setCertificationTime((res.getString("certificationTime")).substring(0,7));
				se.setRank(res.getString("rank"));
				se.setDepartmentId(res.getInt("departmentId"));
				se.setSituation(res.getString("situation"));
				se.setuName(res.getString("uName"));
				se.setDepartment(res.getString("department"));
				se.setCategoryName(res.getString("categoryName"));
				se.setUserinfoId(res.getInt("userinfoId"));
				if(scientific == null) {
					scientific = new ArrayList<ScientificEntity>();
				}
				scientific.add(se);
			}
		} catch (Exception e) {
			throw new RuntimeException("数据库查询失败");
		}finally {
			JDBC.close(con);
			JDBC.close(pt);
			JDBC.close(res);
		}
		
		return scientific;
	}
}

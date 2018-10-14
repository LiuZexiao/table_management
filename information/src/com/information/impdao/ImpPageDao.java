package com.information.impdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.information.dao.AwardDao;
import com.information.dao.PageDao;
import com.information.entity.AwardEntity;
import com.information.entity.ScientificEntity;
import com.information.entity.UserEntity;
import com.information.util.JDBC;

public class ImpPageDao implements PageDao{

	// 获取用户列表
	public List<UserEntity> pageinfo(int page, int pageSize, Object[] param) {
		Connection	con = null;
		PreparedStatement pt = null;
		ResultSet res = null;
		UserEntity us = null; 
		List<UserEntity> user = null;
		String premise = "";
		int departmentId =  (int) param[0];
		String workNum =  (String) param[1];
		int roleId = (int) param[2];
		if (departmentId != 0) {
			premise += " and departmentId=" +departmentId;
		}
		if(!workNum.equals("")) {
			premise += " and workNum="+"'"+workNum+"'";
		}
		if(roleId != 2) {
			premise += " and userinfo.roleId in (1,2,3)";
		}else {
			premise += " and userinfo.roleId in (2,3)";
		}
		
		System.out.println("premise" + premise);
		String sql = "select * from userinfo LEFT JOIN department on userinfo.departmentId=department.id LEFT JOIN role on userinfo.roleId = role.roleId where 1=1"+premise+" order by userinfo.id desc limit ?,?";
		try {
			con = JDBC.getConnection();
			pt = con.prepareStatement(sql);
			pt.setInt(1, page);
			pt.setInt(2, pageSize);
			
			res = pt.executeQuery();
			while(res.next()) {
				us = new UserEntity();
				us.setId(res.getInt("id"));
				us.setWorkNum(res.getString("workNum"));
				us.setuName(res.getString("uName"));
				us.setuPassword(res.getString("uPassword"));
				us.setDepartment(res.getString("department"));
				us.setRole(res.getString("role"));
				us.setDepartmentId(res.getInt("departmentId"));
				us.setRoleId(res.getInt("roleId"));
				if(user == null) {
					user = new ArrayList<UserEntity>();
				}
				user.add(us);
			}
		} catch (Exception e) {
			throw new RuntimeException("数据库查询失败");
		}finally {
			JDBC.close(con);
			JDBC.close(pt);
			JDBC.close(res);
		}
		
		return user;
	}

	// 获取总记录数
	public int userTotalItemNumber(Object[] param) {
		Connection	con = null;
		PreparedStatement pt = null;
		ResultSet res = null;
		int sum = 0;
		
		String sql = "select count(*) as sum from userinfo where 1=1";
		
		int departmentId =  (int) param[0];
		String workNum =  (String) param[1];
		if (departmentId != 0) {
			sql += " and departmentId=" +departmentId;
		}
		if(!workNum.equals("")) {
			sql += " and workNum="+"'"+workNum+"'";
		}
		
		try {
			con = JDBC.getConnection();
			pt = con.prepareStatement(sql);
			
			res = pt.executeQuery();
			while(res.next()) {
				sum = res.getInt("sum");
			}
		} catch (Exception e) {
			throw new RuntimeException("数据库查询失败");
		}finally {
			JDBC.close(con);
			JDBC.close(pt);
			JDBC.close(res);
		}
		return sum;
	}

	// 获取技能竞赛列表
	public List<AwardEntity> awardinfo(int pageNo, int pageSize, Object[] param) {
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
		String sql = "select * from award LEFT JOIN department on award.departmentId=department.id LEFT JOIN userinfo on award.userinfoId = userinfo.id where 1=1"+premise+" order by award.id desc limit ?,?";
		try {
			con = JDBC.getConnection();
			pt = con.prepareStatement(sql);
			pt.setInt(1, pageNo);
			pt.setInt(2, pageSize);
			
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

	// 获取技能竞赛记录数
	public int awardTotalItemNumber(Object[] param) {
		Connection	con = null;
		PreparedStatement pt = null;
		ResultSet res = null;
		int sum = 0;
		
		String sql = "select count(*) as sum from award where 1=1";
		
		int departmentId =  (int) param[0];
		String identity =  (String) param[1];
		String type = (String) param[2];
		String rank = (String) param[3];
		String clock1 = (String) param[4];
		String clock2 = (String) param[5];
		String uName = (String) param[6];
		int userinfoId = (int) param[7];
		
		if (userinfoId != 0) {
			sql += " and userinfoId=" +userinfoId;
		}
		if (departmentId != 0) {
			sql += " and award.departmentId=" +departmentId;
		}
		if(!identity.equals("")) {
			sql += " and identity="+"'"+identity+"'";
		}
		if(!type.equals("")) {
			sql += " and type="+"'"+type+"'";
		}
		if(!rank.equals("")) {
			sql += " and rank="+"'"+rank+"'";
		}
		if(!clock1.equals("") && !clock2.equals("")) {
			sql += " and certificationTime>="+"'"+clock1+"'";
			sql += " and certificationTime<="+"'"+clock2+"'";
		}
		if(!uName.equals("")) {
			sql += " and uName="+"'"+uName+"'";
		}
		System.out.println("sqp"+sql);
		
		try {
			con = JDBC.getConnection();
			pt = con.prepareStatement(sql);
			
			res = pt.executeQuery();
			while(res.next()) {
				sum = res.getInt("sum");
			}
		} catch (Exception e) {
			throw new RuntimeException("数据库查询失败");
		}finally {
			JDBC.close(con);
			JDBC.close(pt);
			JDBC.close(res);
		}
		return sum;
	}

	// 获取科研列表
	public List<ScientificEntity> scientificinfo(int pageNo, int pageSize, Object[] param) {
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
		String sql = "select * from scientific LEFT JOIN department on scientific.departmentId=department.id LEFT JOIN userinfo on scientific.userinfoId = userinfo.id LEFT JOIN category on scientific.categoryId = category.id where 1=1"+premise+" order by scientific.id desc limit ?,?";
		try {
			con = JDBC.getConnection();
			pt = con.prepareStatement(sql);
			pt.setInt(1, pageNo);
			pt.setInt(2, pageSize);
			
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

	// 获取科研记录数
	public int scientificTotalItemNumber(Object[] param) {
		Connection	con = null;
		PreparedStatement pt = null;
		ResultSet res = null;
		int sum = 0;
		
		String sql = "select count(*) as sum from scientific where 1=1";
		
		int departmentId =  (int) param[0];
		int categoryId = (int) param[1];
		String rank = (String) param[2];
		String clock1 = (String) param[3];
		String clock2 = (String) param[4];
		String uName = (String) param[5];
		int userinfoId = (int) param[6];
		
		if (userinfoId != 0) {
			sql += " and userinfoId=" +userinfoId;
		}
		if (departmentId != 0) {
			sql += " and scientific.departmentId=" +departmentId;
		}
		if(categoryId != 0) {
			sql += " and categoryId="+categoryId;
		}
		if(!rank.equals("")) {
			sql += " and rank="+"'"+rank+"'";
		}
		if(!clock1.equals("") && !clock2.equals("")) {
			sql += " and certificationTime>="+"'"+clock1+"'";
			sql += " and certificationTime<="+"'"+clock2+"'";
		}
		if(!uName.equals("")) {
			sql += " and uName="+"'"+uName+"'";
		}
		System.out.println("sqp"+sql);
		
		try {
			con = JDBC.getConnection();
			pt = con.prepareStatement(sql);
			
			res = pt.executeQuery();
			while(res.next()) {
				sum = res.getInt("sum");
			}
		} catch (Exception e) {
			throw new RuntimeException("数据库查询失败");
		}finally {
			JDBC.close(con);
			JDBC.close(pt);
			JDBC.close(res);
		}
		return sum;
	}

}

package com.information.impdao;

import java.sql.ResultSet;

import com.information.dao.UserDao;
import com.information.entity.UserEntity;
import com.information.util.JDBC;


import java.util.Iterator;
import java.util.List;


import java.sql.Connection;
import java.sql.PreparedStatement;

public class UserImpDao implements UserDao{
	/**
	 * 登录,查询用户信息
	 */
	public UserEntity userLogin(String workNum) {
		Connection	con = null;
		PreparedStatement pt = null;
		ResultSet res = null;
		UserEntity user = null;
		String sql = "select * from userinfo LEFT JOIN department on userinfo.departmentId=department.id where workNum=?";
		
		try {
			con = JDBC.getConnection();
			pt = con.prepareStatement(sql);
			pt.setString(1, workNum);
			
			res = pt.executeQuery();
			if (res != null) { 
				while(res.next()) {
					user = new UserEntity(res.getInt("id"),res.getString("workNum"),res.getString("uName"),res.getString("uPassword"),res.getInt("departmentId"),res.getString("department"),res.getInt("roleId"));
				}
			}else {
				System.out.println("登陆失败");
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
	/**
	 * 修改我的密码
	 **/
	public int myUpdatePassword (String workNum, String newPassword) {
		int number = JDBC.executeUpdate("update userinfo set uPassword=? where workNum=?", new Object[] {newPassword, workNum});
		return number;
	}
	/**
	 * 修改我的信息
	 **/
	public int myUpdateInfo(String workNum, String uName, int departmentId) {
		int number = JDBC.executeUpdate("update userinfo set uName=?,departmentId=? where workNum=?", new Object[] {uName, departmentId, workNum});
		return number;
	}
	/**
	 * 添加用户
	 */
	public int addUser(String workNum, String uName, String uPassword, int departmentId, int roleId) {
		int number = JDBC.executeUpdate("insert into userinfo(workNum, uName, uPassword, departmentId, roleId) values(?,?,?,?,?)", new Object[] {workNum, uName, uPassword, departmentId, roleId});
		return number;
	}
	/**
	 *  修改用户信息
	 */
	public int updataUser(int id, String workNum, String uName, String uPassword, int departmentId, int roleId) {
		int number = JDBC.executeUpdate("update userinfo set workNum=?,uName=?,uPassword=?,departmentId=?,roleId=? where id=?", new Object[] {workNum, uName, uPassword, departmentId, roleId, id});
		return number;
	}
	/**
	 * 删除用户
	 */
	public int deleteUser(List list) {
		String sum="0";
		
		Iterator Iter = list.iterator();
		while(Iter.hasNext()) {
			sum += ","+Iter.next();
		}
//		for(int i=0; i < id.length; i++) {
//			sum += ","+id[i];
//		}
		System.out.println(sum);
		
		int number = JDBC.executeUpdate("delete from userinfo where id in ("+sum+")", new Object[] {});
		return number;
	
	}
	public int addAllUser(List<UserEntity> users){
        int n = 0;
        for(UserEntity user:users){
            try{
                String sql = "insert into userinfo(workNum, uName, uPassword, departmentId, roleId) values(?,?,?,?,?)";
                Object[] params = {user.getWorkNum(),user.getuName(),user.getuPassword(),user.getDepartmentId(),user.getRoleId()};
                int ex = JDBC.executeUpdate(sql,params);
                n+=ex;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return n;
    }
////用于下载导出excel时用的数据库查询方法
//    public List<UserEntity> getAllUser() throws SQLException {
//        List<UserEntity> users = new ArrayList<UserEntity>();
//        UserEntity user = null;
//        try{
//            String sql = "select * from user_table";
//            Object[] params = {};
//            ResultSet rs = this.executeSQL(sql,params);
//            while(rs.next()){
//                user = new UserEntity();
//                user.setUid(rs.getInt(0));
//                user.setUsername(rs.getString(1));
//                user.setAge(rs.getInt(2));
//                user.setText(rs.getString(3));
//                users.add(user);
//            }
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//        return users;
//    }

}

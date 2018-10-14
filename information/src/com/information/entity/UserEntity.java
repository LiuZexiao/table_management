package com.information.entity;

public class UserEntity {
	private int id;
	private String workNum;
	private String uName;
	private String uPassword;
	private int departmentId;
	private int roleId;
	private String department;
	private String role;
	
	public UserEntity() {
		super();
	}
	public UserEntity(int id,String workNum, String uName, String uPassword, int departmentId, String department,int roleId) {
		this.id = id;
		this.workNum = workNum;
		this.uName = uName;
		this.uPassword = uPassword;
		this.departmentId = departmentId;
		this.department = department;
		this.roleId = roleId;
	}
	public UserEntity(int id,String workNum, String uName, String uPassword, int departmentId, int roleId) {
		this.id = id;
		this.workNum = workNum;
		this.uName = uName;
		this.uPassword = uPassword;
		this.departmentId = departmentId;
		this.roleId = roleId;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWorkNum() {
		return workNum;
	}
	public void setWorkNum(String workNum) {
		this.workNum = workNum;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public String getuPassword() {
		return uPassword;
	}
	public void setuPassword(String uPassword) {
		this.uPassword = uPassword;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", workNum=" + workNum + ", uName=" + uName + ", uPassword=" + uPassword
				+ ", department=" + department + ", role=" + role + "]";
	}

	
	

}

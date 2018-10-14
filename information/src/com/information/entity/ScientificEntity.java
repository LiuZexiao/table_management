package com.information.entity;

public class ScientificEntity {
	private int id;
	private String name;
	private int categoryId;
	private String proName;
	private String proSource;
	private String rank;
	private String certificationTime;
	private String situation;
	private String remarks;
	private String department;
	private String categoryName;
	private String uName;
	private int departmentId;
	private int userinfoId;
	
	public ScientificEntity() {
		super();
	}
	public ScientificEntity(int id, String name, int categoryId, String proName, String proSource, String rank,
			String certificationTime, String situation, String remarks, int departmentId, int userinfoId, int roleId) {
		super();
		this.id = id;
		this.name = name;
		this.categoryId = categoryId;
		this.proName = proName;
		this.proSource = proSource;
		this.rank = rank;
		this.certificationTime = certificationTime;
		this.situation = situation;
		this.remarks = remarks;
		this.departmentId = departmentId;
		this.userinfoId = userinfoId;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the categoryId
	 */
	public int getCategoryId() {
		return categoryId;
	}
	/**
	 * @param categoryId the categoryId to set
	 */
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	/**
	 * @return the proName
	 */
	public String getProName() {
		return proName;
	}
	/**
	 * @param proName the proName to set
	 */
	public void setProName(String proName) {
		this.proName = proName;
	}
	/**
	 * @return the proSource
	 */
	public String getProSource() {
		return proSource;
	}
	/**
	 * @param proSource the proSource to set
	 */
	public void setProSource(String proSource) {
		this.proSource = proSource;
	}
	/**
	 * @return the rank
	 */
	public String getRank() {
		return rank;
	}
	/**
	 * @param rank the rank to set
	 */
	public void setRank(String rank) {
		this.rank = rank;
	}
	/**
	 * @return the certificationTime
	 */
	public String getCertificationTime() {
		return certificationTime;
	}
	/**
	 * @param certificationTime the certificationTime to set
	 */
	public void setCertificationTime(String certificationTime) {
		this.certificationTime = certificationTime;
	}
	/**
	 * @return the situation
	 */
	public String getSituation() {
		return situation;
	}
	/**
	 * @param situation the situation to set
	 */
	public void setSituation(String situation) {
		this.situation = situation;
	}
	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}
	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/**
	 * @return the departmentId
	 */
	public int getDepartmentId() {
		return departmentId;
	}
	/**
	 * @param departmentId the departmentId to set
	 */
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	/**
	 * @return the userinfoId
	 */
	public int getUserinfoId() {
		return userinfoId;
	}
	/**
	 * @param userinfoId the userinfoId to set
	 */
	public void setUserinfoId(int userinfoId) {
		this.userinfoId = userinfoId;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ScientificEntity [id=" + id + ", name=" + name + ", categoryId=" + categoryId + ", proName=" + proName
				+ ", proSource=" + proSource + ", rank=" + rank + ", certificationTime=" + certificationTime
				+ ", situation=" + situation + ", remarks=" + remarks + ", departmentId=" + departmentId
				+ ", userinfoId=" + userinfoId + "]";
	}
	/**
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}
	/**
	 * @param department the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}
	/**
	 * @return the category
	 */
	public String getCategoryName() {
		return categoryName;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	/**
	 * @return the uName
	 */
	public String getuName() {
		return uName;
	}
	/**
	 * @param uName the uName to set
	 */
	public void setuName(String uName) {
		this.uName = uName;
	}
	
	
}	

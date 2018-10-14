package com.information.entity;

public class AwardEntity {
	private int id;
	private String name;
	private int departmentId;
	private String identity;
	private String stuId;
	private String stuClass;
	private String rank;
	private String proName;
	private String grade;
	private String type;
	private String certificationTime;
	private String certificationCompany;
	private String semester;
	private String plan;
	private int userinfoId;
	private String department;
	private String uName;
	
	
	public AwardEntity() {
		super();
	}
	
	public AwardEntity(int id, String name, int departmentId, String identity, String stuId, String stuClass,
			String rank, String proName, String grade, String type, String certificationTime,
			String certificationCompany, String semester, String plan, int userinfoId, int roleId) {
		super();
		this.id = id;
		this.name = name;
		this.departmentId = departmentId;
		this.identity = identity;
		this.stuId = stuId;
		this.stuClass = stuClass;
		this.rank = rank;
		this.proName = proName;
		this.grade = grade;
		this.type = type;
		this.certificationTime = certificationTime;
		this.certificationCompany = certificationCompany;
		this.semester = semester;
		this.plan = plan;
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
	 * @return the identity
	 */
	public String getIdentity() {
		return identity;
	}
	/**
	 * @param identity the identity to set
	 */
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	/**
	 * @return the stuId
	 */
	public String getStuId() {
		return stuId;
	}
	/**
	 * @param stuId the stuId to set
	 */
	public void setStuId(String stuId) {
		this.stuId = stuId;
	}
	/**
	 * @return the stuClass
	 */
	public String getStuClass() {
		return stuClass;
	}
	/**
	 * @param stuClass the stuClass to set
	 */
	public void setStuClass(String stuClass) {
		this.stuClass = stuClass;
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
	 * @return the grade
	 */
	public String getGrade() {
		return grade;
	}
	/**
	 * @param grade the grade to set
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
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
	 * @return the certificationCompany
	 */
	public String getCertificationCompany() {
		return certificationCompany;
	}
	/**
	 * @param certificationCompany the certificationCompany to set
	 */
	public void setCertificationCompany(String certificationCompany) {
		this.certificationCompany = certificationCompany;
	}
	/**
	 * @return the semester
	 */
	public String getSemester() {
		return semester;
	}
	/**
	 * @param semester the semester to set
	 */
	public void setSemester(String semester) {
		this.semester = semester;
	}
	/**
	 * @return the plan
	 */
	public String getPlan() {
		return plan;
	}
	/**
	 * @param plan the plan to set
	 */
	public void setPlan(String plan) {
		this.plan = plan;
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
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AwardEntity [id=" + id + ", name=" + name + ", departmentId=" + departmentId + ", identity=" + identity
				+ ", stuId=" + stuId + ", stuClass=" + stuClass + ", rank=" + rank + ", proName=" + proName + ", grade="
				+ grade + ", type=" + type + ", certificationTime=" + certificationTime + ", certificationCompany="
				+ certificationCompany + ", semester=" + semester + ", plan=" + plan + ", userinfoId=" + userinfoId
				+ "]";
	}


}

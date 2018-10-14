package com.information.entity;

import java.util.List;

public class PageEntity<T> {
	
	// 当前第几页
	private int pageNo;
	
	//当前页的 List
	private List<T> list;
	
	//每页显示多少条记录
	private int pageSize = 8;
	
	// 共有多少条记录
	private long totalItemNumber;

	// 构造器中需要对 pageNo 进行初始化
	public PageEntity(int pageNo, int pageSize) {
		super();
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}
	
	// 需要校验
	public int getPageNo() {
		if(pageNo < 0) {
			pageNo = 1;
		}
		if (pageNo > getTotalPageNumber()) {
			pageNo = getTotalPageNumber();
		}
		return pageNo;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	
	public void setList(List<T> list) {
		this.list = list;
	}
	
	public List<T> getList() {
		return list;
	}
	
	// 获取总页数
	public int getTotalPageNumber() {
		
		int totalPageNumber = (int)totalItemNumber / pageSize;
		if (totalItemNumber % pageSize != 0){
			totalPageNumber++;
		}

		return totalPageNumber;
	}
	
	public void setTotalItemNumber(long totalItemNumber) {
		this.totalItemNumber = totalItemNumber;
	}
	
	// 有没有下一页
	public boolean isHasNext() {
		if(getPageNo() < getTotalPageNumber()) {
			return true;
		}
		return false;
	}
	
	// 有没有上一页
	public boolean isHasPrev(){
		if(getPageNo() > 1) {
			return true;
		}
		return false;
	}

	// 返回上一页
	public int getPrevpage() {
		if (isHasPrev()) {
			return getPageNo() - 1;
		}
		
		return getPageNo();
	}
	
	// 返回下一页
	public int getNextPage() {
		if(isHasNext()) {
			return getPageNo() + 1;
		}
		
		return getPageNo();
	}


}

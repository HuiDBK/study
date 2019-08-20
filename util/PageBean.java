package cn.itcast.customer.domain;

import java.util.List;

/**
 * 保存分页查询的数据的
 * 
 * @author seawind
 * 
 */
public class PageBean {
	public static final int NUMPERPAGE = 10; // 每页多少条
	private int pNum; // 当前第几页
	private int totalPageNum; // 总页数
	private int totalRecordNum; // 总记录数
	private List<Customer> customers; // 结果数据

	public int getpNum() {
		return pNum;
	}

	public void setpNum(int pNum) {
		this.pNum = pNum;
	}

	public int getTotalPageNum() {
		return totalPageNum;
	}

	public void setTotalPageNum(int totalPageNum) {
		this.totalPageNum = totalPageNum;
	}

	public int getTotalRecordNum() {
		return totalRecordNum;
	}

	public void setTotalRecordNum(int totalRecordNum) {
		this.totalRecordNum = totalRecordNum;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

}

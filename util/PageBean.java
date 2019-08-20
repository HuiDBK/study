package cn.itcast.customer.domain;

import java.util.List;

/**
 * �����ҳ��ѯ�����ݵ�
 * 
 * @author seawind
 * 
 */
public class PageBean {
	public static final int NUMPERPAGE = 10; // ÿҳ������
	private int pNum; // ��ǰ�ڼ�ҳ
	private int totalPageNum; // ��ҳ��
	private int totalRecordNum; // �ܼ�¼��
	private List<Customer> customers; // �������

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

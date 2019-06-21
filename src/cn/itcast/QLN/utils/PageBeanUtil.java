package cn.itcast.QLN.utils;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public class PageBeanUtil<T> {

	//分页查询参数
	private int page;//当前页码
	private int pageSize;//查询记录数
	private DetachedCriteria detachedCriteria;//查询条件
	
	//分页查询需要返回的响应数据
	private long total;//通过该条件查询到的总记录数目
	private List<T> rows;//通过该条件查询到的所有的记录内容
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public DetachedCriteria getDetachedCriteria() {
		return detachedCriteria;
	}
	public void setDetachedCriteria(DetachedCriteria detachedCriteria) {
		this.detachedCriteria = detachedCriteria;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	
	
}

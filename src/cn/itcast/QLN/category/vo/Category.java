package cn.itcast.QLN.category.vo;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import cn.itcast.QLN.document.vo.Document;

public class Category {
	private Integer cid;
	private String cname;
	//一级分类中应该存放一个文档的集合
	private Set<Document> documents=new HashSet<Document>();
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public Set<Document> getDocuments() {
		return documents;
	}
	public void setDocuments(Set<Document> documents) {
		this.documents = documents;
	}
	
	
}

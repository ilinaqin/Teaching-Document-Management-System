package cn.itcast.QLN.user.vo;

import java.util.HashSet;
import java.util.Set;

import cn.itcast.QLN.document.vo.Document;

/**
 * 用户模块的实体类
 * @author QLN
 *
 */
public class User {
	private int uid;
	private String username;
	private String password;
	private String name;
	private String jobtitle;
	private String phone;
	private String addr;
	
	//一级用户中应该存放一个文档的集合
		private Set<Document> documentss=new HashSet<Document>();
		
		
	
	public String getJobtitle() {
			return jobtitle;
		}
		public void setJobtitle(String jobtitle) {
			this.jobtitle = jobtitle;
		}
	public Set<Document> getDocumentss() {
			return documentss;
		}
		public void setDocumentss(Set<Document> documentss) {
			this.documentss = documentss;
		}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getjobtitle() {
		return jobtitle;
	}
	public void setjobtitle(String jobtitle) {
		this.jobtitle = jobtitle;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}


}

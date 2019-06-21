package cn.itcast.QLN.document.vo;

import java.util.Date;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import cn.itcast.QLN.category.vo.Category;
import cn.itcast.QLN.user.vo.User;

public class Document {
	private Integer did;   
	private String dname;
	private String academy;
	private String department;
	private String ddescrip;
	private String dimg;
	private Date dtime;
	private Integer state;
	private String place;
	private Integer placestate;
	private String manreason;
	private String leadreason;
	private Date teachersubtime;
	private Date mantime;
	private Date leadtime;
	private Date sectime;
	
	//二级分类的外键，在使用hibernate的时候，是建立一个对象
	private Category category;	
	private User user;
	
	
	
	public Integer getPlacestate() {
		return placestate;
	}
	public void setPlacestate(Integer placestate) {
		this.placestate = placestate;
	}
	public Date getTeachersubtime() {
		return teachersubtime;
	}
	public void setTeachersubtime(Date teachersubtime) {
		this.teachersubtime = teachersubtime;
	}
	public Date getMantime() {
		return mantime;
	}
	public void setMantime(Date mantime) {
		this.mantime = mantime;
	}
	public Date getLeadtime() {
		return leadtime;
	}
	public void setLeadtime(Date leadtime) {
		this.leadtime = leadtime;
	}
	public Date getSectime() {
		return sectime;
	}
	public void setSectime(Date sectime) {
		this.sectime = sectime;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getManreason() {
		return manreason;
	}
	public void setManreason(String manreason) {
		this.manreason = manreason;
	}
	public String getLeadreason() {
		return leadreason;
	}
	public void setLeadreason(String leadreason) {
		this.leadreason = leadreason;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Integer getDid() {
		return did;
	}
	public void setDid(Integer did) {
		this.did = did;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	
	public String getDdescrip() {
		return ddescrip;
	}
	public void setDdescrip(String ddescrip) {
		this.ddescrip = ddescrip;
	}
	public String getDimg() {
		return dimg;
	}
	public void setDimg(String dimg) {
		this.dimg = dimg;
	}
	public Date getDtime() {
		return dtime;
	}
	public void setDtime(Date dtime) {
		this.dtime = dtime;
	}
	public String getAcademy() {
		return academy;
	}
	public void setAcademy(String academy) {
		this.academy = academy;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
		
}

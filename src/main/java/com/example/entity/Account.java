package com.example.entity;

import com.mongodb.Function;
import org.bson.Document;

/**
 * 账户实体类
 * 
 * @author jshon
 *
 */
public class Account {
	private String id;
	private String uid;// 账户id
	private String uname;// 账户名
	private String upass;// 账户密码
	private String phone;// 电话号码
	private Integer sex;// 性别
	private Long ctime;// 创建时间
	private String from_appid;// 注册客户端id

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUpass() {
		return upass;
	}

	public void setUpass(String upass) {
		this.upass = upass;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Long getCtime() {
		return ctime;
	}

	public void setCtime(Long ctime) {
		this.ctime = ctime;
	}

	public String getFrom_appid() {
		return from_appid;
	}

	public void setFrom_appid(String from_appid) {
		this.from_appid = from_appid;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", uid=" + uid + ", uname=" + uname + ", upass=" + upass + ", phone=" + phone
				+ ", sex=" + sex + ", ctime=" + ctime + ", from_appid=" + from_appid + "]";
	}

	public static class Mapper implements Function<Document, Account> {
		public Account apply(Document doc) {
			Account account = new Account();
			account.setId(doc.getObjectId("_id").toString());
			account.setUid(doc.getString("uid"));
			account.setUname(doc.getString("uname"));
			account.setUpass(doc.getString("upass"));
			if (doc.containsKey("phone"))
				account.setPhone(doc.getString("phone"));
			if (doc.containsKey("sex"))
				account.setSex(doc.getInteger("sex"));
			if (doc.containsKey("ctime"))
				account.setCtime(doc.getLong("ctime"));
			if (doc.containsKey("from_appid"))
				account.setFrom_appid(doc.getString("from_appid"));
			return account;
		}
	}
}

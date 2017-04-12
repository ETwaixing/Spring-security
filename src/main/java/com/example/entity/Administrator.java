package com.example.entity;

import com.mongodb.Function;
import org.bson.Document;

import java.util.Arrays;
import java.util.List;

/**
 * 管理员实体类
 * 
 * @author jshon
 *
 */
public class Administrator {
	private String admin_name;// 管理员名字
	private String admin_pass;// 管理员密码
	private String[] role_id;// 角色id
	private int admin_id;// 管理员ID
	private String state;// 状态
	private int login_count;// 登录次数
	private long last_login_time;// 最近登录时间
	private String last_login_IP;// 最近登录IP
	private String accesstoken;// 访问令牌
	private List<AdminRole> roles;// 拥有到角色
	private long create_time;// 创建时间
	private String create_admin;// 创建人
	private long update_time;// 修改时间
	private String update_admin;// 修改人
	private String remark;//备注
	private String id;// id
	
	public String getAdmin_name() {
		return admin_name;
	}

	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}

	public String getAdmin_pass() {
		return admin_pass;
	}

	public void setAdmin_pass(String admin_pass) {
		this.admin_pass = admin_pass;
	}

	public String[] getRole_id() {
		return role_id;
	}

	public void setRole_id(String[] role_id) {
		this.role_id = role_id;
	}

	public int getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getLogin_count() {
		return login_count;
	}

	public void setLogin_count(int login_count) {
		this.login_count = login_count;
	}

	public long getLast_login_time() {
		return last_login_time;
	}

	public void setLast_login_time(long last_login_time) {
		this.last_login_time = last_login_time;
	}

	public String getLast_login_IP() {
		return last_login_IP;
	}

	public void setLast_login_IP(String last_login_IP) {
		this.last_login_IP = last_login_IP;
	}

	public String getAccesstoken() {
		return accesstoken;
	}

	public void setAccesstoken(String accesstoken) {
		this.accesstoken = accesstoken;
	}

	public List<AdminRole> getRoles() {
		return roles;
	}

	public void setRoles(List<AdminRole> roles) {
		this.roles = roles;
	}

	public long getCreate_time() {
		return create_time;
	}

	public void setCreate_time(long create_time) {
		this.create_time = create_time;
	}

	public String getCreate_admin() {
		return create_admin;
	}

	public void setCreate_admin(String create_admin) {
		this.create_admin = create_admin;
	}

	public long getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(long update_time) {
		this.update_time = update_time;
	}

	public String getUpdate_admin() {
		return update_admin;
	}

	public void setUpdate_admin(String update_admin) {
		this.update_admin = update_admin;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public static class Mapper implements Function<Document, Administrator> {

		@SuppressWarnings("unchecked")
		public Administrator apply(Document doc) {
			Administrator admin = new Administrator();
			if(null != doc){
				if (doc.containsKey("_id"))
					admin.setId(doc.getObjectId("_id").toString());
				admin.setAdmin_id(doc.getInteger("admin_id"));
				admin.setAdmin_name(doc.getString("admin_name"));
				if (doc.containsKey("admin_pass"))
					admin.setAdmin_pass(doc.getString("admin_pass"));
				admin.setLogin_count(doc.getInteger("login_count"));
				admin.setState(doc.getString("state"));
				admin.setLast_login_IP(doc.getString("last_login_IP"));
				if (doc.containsKey("last_login_time"))
					admin.setLast_login_time(doc.getLong("last_login_time"));
				admin.setAccesstoken(doc.getString("accesstoken"));
				if(doc.containsKey("create_time"))
					admin.setCreate_time(doc.getLong("create_time"));
				if(doc.containsKey("create_admin"))
					admin.setCreate_admin(doc.getString("create_admin"));
				if(doc.containsKey("update_time"))
					admin.setUpdate_time(doc.getLong("update_time"));
				if(doc.containsKey("update_admin"))
					admin.setUpdate_admin(doc.getString("update_admin"));
				if(doc.containsKey("remark"))
					admin.setRemark(doc.getString("remark"));
				if (doc.containsKey("roles"))
					admin.setRoles(doc.get("roles", List.class));
			}
			return admin;
		}

	}


	@Override
	public String toString() {
		return "Administrator [admin_name=" + admin_name + ", admin_pass=" + admin_pass + ", role_id="
				+ Arrays.toString(role_id) + ", admin_id=" + admin_id + ", state=" + state + ", login_count="
				+ login_count + ", last_login_time=" + last_login_time + ", last_login_IP=" + last_login_IP
				+ ", accesstoken=" + accesstoken + ", roles=" + roles + ", create_time=" + create_time
				+ ", create_admin=" + create_admin + ", update_time=" + update_time + ", update_admin=" + update_admin
				+ ", remark=" + remark + ", id=" + id + "]";
	}


}

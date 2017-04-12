package com.example.entity;

import com.mongodb.Function;
import org.bson.Document;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * 管理员角色类
 * 
 * @author jshon
 *
 */
public class AdminRole implements Serializable{//管理员角色类
	private String role_id;//角色id
	private String role_name;//角色名字
	private String role_desc;//角色描述
	private String[] action_id;//权限id
	private String id;//ID
	private Integer group_id;//部门ID
	private String group_name;//部门名称
	private String status;//是否显示1:显示0:不显示
	private List<Module> modules;//拥有的权限列表
	private long ctime;//创建时间
	private long utime;//修改时间
	private String admin_id;//操作人员ID
	private String admin_name;//操作人员名称
	
	public String getRole_id() {
		return role_id;
	}

	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public String getRole_desc() {
		return role_desc;
	}

	public void setRole_desc(String role_desc) {
		this.role_desc = role_desc;
	}

	public String[] getAction_id() {
		return action_id;
	}

	public void setAction_id(String[] action_id) {
		this.action_id = action_id;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getGroup_id() {
		return group_id;
	}
	public void setGroup_id(Integer group_id) {
		this.group_id = group_id;
	}
	public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	
	
	public List<Module> getModules() {
		return modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public long getCtime() {
		return ctime;
	}

	public void setCtime(long ctime) {
		this.ctime = ctime;
	}

	public long getUtime() {
		return utime;
	}

	public void setUtime(long utime) {
		this.utime = utime;
	}

	public String getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}

	public String getAdmin_name() {
		return admin_name;
	}

	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}


	public static class Mapper implements Function<Document, AdminRole> {
		
		@SuppressWarnings("unchecked")
		public AdminRole apply(Document doc) {
			AdminRole role = new AdminRole();
			role.setId(doc.getObjectId("_id").toString());
			role.setRole_desc(doc.getString("role_desc"));
			role.setRole_id(doc.getString("role_id"));
			role.setStatus(doc.getString("status"));
			role.setRole_name(doc.getString("role_name"));
			if(doc.containsKey("group_id"))
				role.setGroup_id(doc.getInteger("group_id"));
			if(doc.containsKey("group_name"))
				role.setGroup_name(doc.getString("group_name"));
			if(doc.containsKey("modules"))
				role.setModules(doc.get("modules",List.class));
			role.setCtime(doc.getLong("ctime"));
			role.setUtime(doc.getLong("utime"));
			role.setAdmin_id(doc.getString("admin_id"));
			role.setAdmin_name(doc.getString("admin_name"));
			return role;
		}

	}


	@Override
	public String toString() {
		return "AdminRole [role_id=" + role_id + ", role_name=" + role_name + ", role_desc=" + role_desc
				+ ", action_id=" + Arrays.toString(action_id) + ", id=" + id + ", group_id=" + group_id
				+ ", group_name=" + group_name + ", status=" + status + ", modules=" + modules + ", ctime=" + ctime
				+ ", utime=" + utime + ", admin_id=" + admin_id + ", admin_name=" + admin_name + "]";
	}

}

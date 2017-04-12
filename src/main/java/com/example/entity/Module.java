package com.example.entity;

import com.mongodb.Function;
import org.bson.Document;

public class Module {
	
	private String id;
	private String project_id;//项目ID
	private String project_name;//项目名称
//	private String function_id;//功能ID
//	private String function_name;//功能名称
	private String module_name;//功能名称
	private String module_key;//功能key
	private String module_url;//功能url:视图
	private String module_desc;//
	private String blocks_id;//区块ID
	private String blocks_name;//区块名称
	private long ctime;//创建时间
	private long utime;//修改时间
	private String admin_id;//操作人员ID
	private String admin_name;//操作人员名称
	private String status;//0:表示需要显示到左边树，1:表示不需要显示到左边树列表到功能
	
	public String getProject_id() {
		return project_id;
	}
	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public String getModule_name() {
		return module_name;
	}
	public void setModule_name(String module_name) {
		this.module_name = module_name;
	}
	public String getModule_url() {
		return module_url;
	}
	public void setModule_url(String module_url) {
		this.module_url = module_url;
	}
	public String getModule_desc() {
		return module_desc;
	}
	public void setModule_desc(String module_desc) {
		this.module_desc = module_desc;
	}
	public String getBlocks_id() {
		return blocks_id;
	}
	public void setBlocks_id(String blocks_id) {
		this.blocks_id = blocks_id;
	}
	public String getBlocks_name() {
		return blocks_name;
	}
	public void setBlocks_name(String blocks_name) {
		this.blocks_name = blocks_name;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getModule_key() {
		return module_key;
	}
	public void setModule_key(String module_key) {
		this.module_key = module_key;
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

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Module [id=" + id + ", project_id=" + project_id + ", project_name=" + project_name + ", module_name="
				+ module_name + ", module_key=" + module_key + ", module_url=" + module_url + ", module_desc="
				+ module_desc + ", blocks_id=" + blocks_id + ", blocks_name=" + blocks_name + ", ctime=" + ctime
				+ ", utime=" + utime + ", admin_id=" + admin_id + ", admin_name=" + admin_name + ", status=" + status
				+ "]";
	}


	public static class Mapper implements Function<Document, Module> {
			
			public Module apply(Document doc) {
				Module module = new Module();
				module.setId(doc.getObjectId("_id").toString());
				module.setProject_id(doc.getString("project_id"));
				module.setProject_name(doc.getString("project_name"));
				module.setModule_url(doc.getString("module_url"));
				module.setModule_key(doc.getString("module_key"));
				module.setModule_name(doc.getString("module_name"));
				module.setModule_desc(doc.getString("module_desc"));
				module.setBlocks_id(doc.getString("blocks_id"));
				module.setBlocks_name(doc.getString("blocks_name"));
				module.setCtime(doc.getLong("ctime"));
				module.setUtime(doc.getLong("utime"));
				module.setAdmin_id(doc.getString("admin_id"));
				module.setAdmin_name(doc.getString("admin_name"));
				module.setStatus(doc.getString("status"));
				
				return module;
			}
	
		}
	
}

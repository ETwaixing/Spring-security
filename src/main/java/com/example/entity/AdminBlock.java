package com.example.entity;

import com.mongodb.Function;
import org.bson.Document;

public class AdminBlock {
	
	private String id;
	private String blocks_id;//功能块ID
	private String blocks_name;//功能块名称次
	private String blocks_url;//功能块名URL
	private String project_id;//项目ID
	private String project_name;//项目名称
	private long   ctime;//创建时间
	private long utime;//修改时间
	private String admin_id;//操作人员ID
	private String admin_name;//操作人员名称
	private String blocks_img;//操作人员名称
	private String blocks_order;//排序序号
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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

	public String getBlocks_url() {
		return blocks_url;
	}
	public void setBlocks_url(String blocks_url) {
		this.blocks_url = blocks_url;
	}

	public String getBlocks_img() {
		return blocks_img;
	}
	public void setBlocks_img(String blocks_img) {
		this.blocks_img = blocks_img;
	}

	public String getBlocks_order() {
		return blocks_order;
	}
	public void setBlocks_order(String blocks_order) {
		this.blocks_order = blocks_order;
	}

	public static class Mapper implements Function<Document, AdminBlock> {
		
		public AdminBlock apply(Document doc) {
			AdminBlock adminBlock = new AdminBlock();
			adminBlock.setId(doc.getObjectId("_id").toString());
			adminBlock.setProject_id(doc.getString("project_id"));
			adminBlock.setProject_name(doc.getString("project_name"));
			adminBlock.setBlocks_id(doc.getString("blocks_id"));
			adminBlock.setBlocks_name(doc.getString("blocks_name"));
			adminBlock.setCtime(doc.getLong("ctime"));
			adminBlock.setUtime(doc.getLong("utime"));
			adminBlock.setAdmin_id(doc.getString("admin_id"));
			adminBlock.setAdmin_name(doc.getString("admin_name"));
			if(doc.containsKey("blocks_order"))
				adminBlock.setBlocks_order(doc.getString("blocks_order"));
			if(doc.containsKey("blocks_img"))
				adminBlock.setBlocks_img(doc.getString("blocks_img"));
			if(doc.containsKey("blocks_url"))
				adminBlock.setBlocks_url(doc.getString("blocks_url"));
			else
				adminBlock.setBlocks_url("#");
			
			return adminBlock;
		}
	}

	@Override
	public String toString() {
		return "AdminBlock [id=" + id + ", blocks_id=" + blocks_id + ", blocks_name=" + blocks_name + ", blocks_url="
				+ blocks_url + ", project_id=" + project_id + ", project_name=" + project_name + ", ctime=" + ctime
				+ ", utime=" + utime + ", admin_id=" + admin_id + ", admin_name=" + admin_name + ", blocks_img="
				+ blocks_img + ", blocks_order=" + blocks_order + "]";
	}

	
}

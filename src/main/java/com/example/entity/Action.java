package com.example.entity;

import com.mongodb.Function;
import org.bson.Document;

/**
 * 权限实体类
 * 
 * @author jshon
 *
 */
public class Action {
	private String id;
	private String action_id;// 权限id
	private String action_name;// 权限名称
	private String action_url;// 对应功能权限
	private String project_id;// 所属后台id
	private String action_desc;// 权限描述

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAction_id() {
		return action_id;
	}

	public void setAction_id(String action_id) {
		this.action_id = action_id;
	}

	public String getAction_name() {
		return action_name;
	}

	public void setAction_name(String action_name) {
		this.action_name = action_name;
	}

	public String getAction_url() {
		return action_url;
	}

	public void setAction_url(String action_url) {
		this.action_url = action_url;
	}

	public String getProject_id() {
		return project_id;
	}

	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}

	public String getAction_desc() {
		return action_desc;
	}

	public void setAction_desc(String action_desc) {
		this.action_desc = action_desc;
	}

	@Override
	public String toString() {
		return "Action [id=" + id + ", action_id=" + action_id + ", action_name=" + action_name + ", action_url="
				+ action_url + ", project_id=" + project_id + ", action_desc=" + action_desc + "]";
	}

	public static class Mapper implements Function<Document, Action> {
		public Action apply(Document doc) {
			Action action = new Action();
			action.setId(doc.getObjectId("_id").toString());
			action.setAction_id(doc.getString("action_id"));
			action.setAction_name(doc.getString("action_name"));
			action.setAction_url(doc.getString("action_url"));
			action.setProject_id(doc.getString("project_id"));
			action.setAction_desc(doc.getString("action_desc"));
			return action;
		}
	}
}

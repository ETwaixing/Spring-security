package com.example.entity;

import com.mongodb.Function;
import org.bson.Document;

/**
 * 后台项目实体类
 * 
 * @author jshon
 *
 */
public class AdminProject {
	private String project_id;// 后台id
	private String project_name;// 后台名称
	private String project_key;// 后台key
	private String id;

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

	public String getProject_key() {
		return project_key;
	}

	public void setProject_key(String project_key) {
		this.project_key = project_key;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "AdminProject [project_id=" + project_id + ", project_name=" + project_name + ", project_key="
				+ project_key + ", id=" + id + "]";
	}

	public static class Mapper implements Function<Document, AdminProject> {

		public AdminProject apply(Document doc) {
			AdminProject adminProject = new AdminProject();
			adminProject.setId(doc.getObjectId("_id").toString());
			adminProject.setProject_id(doc.getString("project_id"));
			adminProject.setProject_key(doc.getString("project_key"));
			adminProject.setProject_name(doc.getString("project_name"));
			return adminProject;
		}

	}

}

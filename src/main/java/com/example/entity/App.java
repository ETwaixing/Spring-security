package com.example.entity;

import com.mongodb.Function;
import org.bson.Document;

/**
 * 客户端实体类
 * 
 * @author jshon
 *
 */
public class App {
	private String id;
	private String appid;// 客户端id
	private String appname;// 客户端名字
	private String appkey;// 客户端key

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getAppname() {
		return appname;
	}

	public void setAppname(String appname) {
		this.appname = appname;
	}

	public String getAppkey() {
		return appkey;
	}

	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}

	@Override
	public String toString() {
		return "App [id=" + id + ", appid=" + appid + ", appname=" + appname + ", appkey=" + appkey + "]";
	}

	public static class Mapper implements Function<Document, App> {

		public App apply(Document doc) {
			App app = new App();
			app.setAppid(doc.getString("appid"));
			app.setAppname(doc.getString("appname"));
			app.setAppkey(doc.getString("appkey"));
			return app;
		}

	}
}

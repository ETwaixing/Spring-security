package com.example.entity;

/**
 * 返回码
 * 
 * @author jshon
 * 
 */
public enum BackMsg {
	/**
	 * 操作成功
	 */
	SUCCESS("0", "成功"),

	/**
	 * 服务端错误
	 */
	FAIL("1001", "服务端错误"),

	/**
	 * 参数列表不完整或传递的值错误
	 */
	PARMERROR("1002", "参数错误"),

	/**
	 * 用户不存在
	 */
	USER_NOT_EXIST("1003", "用户不存在"),

	/**
	 * 用户名已存在
	 */
	USER_EXIST("1017", "用户名存在"),

	/**
	 * 登录已过期
	 */
	LOGINEXPIRE("1004", "登录已过期"),

	/**
	 * 查无数据
	 */
	EMPTY("1009", "查无数据"),

	/**
	 * 密码不正确
	 */
	WRONGPASS("1012", "密码不正确"),

	/**
	 * 请添加角色!
	 */
	ROLE_EXIST("1013", "请添加角色!"),


	/**
	 * 登录失败
	 */
	LOGINFAIL("1014", "登录失败"),

	/**
	 * 验证码错误
	 */
	CHCEKOUTCODE("1015", "验证码错误"),

	/**
	 * 验证码失效
	 */
	CODELOSE("1016", "验证码失效"),

	/**
	 * 非法用户名
	 */
	ILLEGALITYUNAME("2021", "非法用户名"),

	/**
	 * 绑定失败
	 */
	BANGDINGSHIBAI("2022", "绑定失败"),
	
	/**
	 * 手机号码被占用
	 */
	USER_PHONE("2020", "手机号码被占用"),

	/**
	 * 手机号不存在
	 */
	PHONE_NOT_EXIST("3001", "手机号不存在");

	/**
	 * 返回码
	 */

	public String code;

	/**
	 * 说明信息
	 */
	public String msg;

	private BackMsg(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}
}

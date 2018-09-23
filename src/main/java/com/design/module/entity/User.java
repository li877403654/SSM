package com.design.module.entity;

import com.design.framework.base.entity.BaseEntity;
import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * 用户
 * 
 * @author John
 * @datatime 2018-09-11 16:54:18
 */
@Alias(value = "user")
@Table(name = "tm_user_info")
public class User extends BaseEntity<String> {

	private static final long serialVersionUID = 1L;
	private String orgId;
	private String userCode;
	@NotNull(message = "用户名不能为空")
	private String userName;
	@Length(max=2)
	@NotNull(message = "密码不能为空")
	private String userPassword;
	private Integer userSex;
	private String userPhone;
	private String managerId;
	private String deptId;
	private String userMail;

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	
	public String getUserName() {
		return userName;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	
	public String getUserPassword() {
		return userPassword;
	}

	public void setUserSex(Integer userSex) {
		this.userSex = userSex;
	}

	public Integer getUserSex() {
		return userSex;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public String getUserMail() {
		return userMail;
	}

}

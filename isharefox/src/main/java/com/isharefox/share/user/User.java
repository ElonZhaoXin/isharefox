/**
 * 
 */
package com.isharefox.share.user;

import com.isharefox.share.common.BaseEntity;

/**
 * @author zhaoxin
 *
 */

public class User extends BaseEntity{
	private String userId;
	private String nickName;
	private String email;
	private String pwd;
	private String cellPhoneNum;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getCellPhoneNum() {
		return cellPhoneNum;
	}
	public void setCellPhoneNum(String cellPhoneNum) {
		this.cellPhoneNum = cellPhoneNum;
	}
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", nickName=" + nickName + ", email=" + email + ", pwd=" + pwd
				+ ", cellPhoneNum=" + cellPhoneNum + "]";
	}
	
	

}

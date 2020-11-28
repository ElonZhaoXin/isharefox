/**
 * 
 */
package com.isharefox.share.common;

import java.sql.Timestamp;

/**
 * @author zhaoxin
 *
 */
public abstract class BaseEntity {
	private long id;
	private String status;
	private Timestamp createTime;
	private Timestamp updateTime;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	@Override
	public String toString() {
		return "BaseEntity [id=" + id + ", status=" + status + ", createTime=" + createTime + ", updateTime="
				+ updateTime + "]";
	}
	
	

}

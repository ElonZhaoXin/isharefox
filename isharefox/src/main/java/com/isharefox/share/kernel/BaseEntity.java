/**
 * 
 */
package com.isharefox.share.kernel;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.isharefox.share.item.entity.Item;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhaoxin
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class BaseEntity<T extends BaseEntity<?>> extends Model<Model<?>>{
	private int id;
	private String status = "0";
	private LocalDateTime createTime = LocalDateTime.now();
	private LocalDateTime updateTime = LocalDateTime.now();

}

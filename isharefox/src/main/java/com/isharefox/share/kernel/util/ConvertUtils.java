/**
 * 
 */
package com.isharefox.share.kernel.util;

import java.util.HashMap;
import java.util.Map;

import com.baomidou.mybatisplus.core.toolkit.BeanUtils;
import com.baomidou.mybatisplus.core.toolkit.ClassUtils;
import com.google.common.base.CaseFormat;

/**
 * @author zhaoxin
 *
 */
public class ConvertUtils {
	
	
	/**
	 * 将下划线属性的对象值，复制到驼峰标志的对象中
	 * @param <T> 
	 * @param src
	 * @param targetClass
	 * @return
	 */
	public static <T> T lowerUnderScopeToLowerCamel(Object src, Class<T> targetClass) {
		Map<String, Object> srcMap = BeanUtils.beanToMap(src);
		Map<String, Object> targetMap = new HashMap<>();
		srcMap.forEach((key, value) -> {
			String newkey = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, key);
			targetMap.put(newkey, value);
		});
		T target = BeanUtils.mapToBean(targetMap, targetClass);
		return target;
	}


}

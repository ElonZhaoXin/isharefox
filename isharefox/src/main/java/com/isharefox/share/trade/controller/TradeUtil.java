/**
 * 
 */
package com.isharefox.share.trade.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author zhaoxin
 *
 */
public class TradeUtil {
	
	/**
	 * 获取位订单编号
	 * 规则：yyyyMMddHHmmssSSS+4位用户id+4位随机数
	 */
	public static String getTradeNo(String id) {
		
		return getCurrrentTime("yyyyMMddHHmmssSSS") + id + randomNum4Digit();
	}
	
	/**
	 * 获取当前时间
	 * @param pattern
	 * @return
	 */
	public static String getCurrrentTime(String pattern) {
		LocalDateTime timestamp = LocalDateTime.now();
		DateTimeFormatter dtfmt = DateTimeFormatter.ofPattern(pattern);
		String currenttime = timestamp.format(dtfmt);
		return currenttime;
	}
	
	/**
	 * 生成4位随机数
	 * @return
	 */
	public static String randomNum4Digit() {
		return String.valueOf((int)((Math.random()*9+1)*1000));
	}
}

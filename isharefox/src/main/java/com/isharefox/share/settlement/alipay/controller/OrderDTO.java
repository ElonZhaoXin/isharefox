/**
 * 
 */
package com.isharefox.share.settlement.alipay.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

/**
 * @author zhaoxin
 *
 */
@Data
public class OrderDTO {
//		private LocalDateTime notify_time;              //通知时间
		private String notify_type;              //通知类型
		private String notify_id;              //通知校验ID
		private String sign_type;              //签名类型
		private String sign;              //签名
		private String trade_no;              //支付宝交易号
		private String app_id;              //开发者的app_id
		private String out_trade_no;              //商户订单号
		private String out_biz_no;              //商户业务号
		private String buyer_id;              //买家支付宝用户号
		private String buyer_logon_id;              //买家支付宝账号
		private String seller_id;              //卖家支付宝用户号
		private String seller_email;              //卖家支付宝账号
		private String trade_status;              //交易状态
//		private BigDecimal total_amount;              //订单金额
//		private BigDecimal receipt_amount;              //实收金额
//		private BigDecimal invoice_amount;              //开票金额
//		private BigDecimal buyer_pay_amount;              //付款金额
//		private BigDecimal point_amount;              //集分宝金额
//		private BigDecimal refund_fee;              //总退款金额
//		private BigDecimal send_back_fee;              //实际退款金额
		private String subject;              //订单标题
		private String body;              //商品描述
//		private LocalDateTime gmt_create;              //交易创建时间
//		private LocalDateTime gmt_payment;              //交易付款时间
//		private LocalDateTime gmt_refund;              //交易退款时间
//		private LocalDateTime gmt_close;              //交易结束时间
		private String fund_bill_list;              //支付金额信息
		@Override
		public String toString() {
			return "OrderDTO [notify_type=" + notify_type + ", notify_id=" + notify_id + ", sign_type=" + sign_type
					+ ", sign=" + sign + ", trade_no=" + trade_no + ", app_id=" + app_id + ", out_trade_no="
					+ out_trade_no + ", out_biz_no=" + out_biz_no + ", buyer_id=" + buyer_id + ", buyer_logon_id="
					+ buyer_logon_id + ", seller_id=" + seller_id + ", seller_email=" + seller_email + ", trade_status="
					+ trade_status + ", subject=" + subject + ", body=" + body + ", fund_bill_list=" + fund_bill_list
					+ "]";
		}
}

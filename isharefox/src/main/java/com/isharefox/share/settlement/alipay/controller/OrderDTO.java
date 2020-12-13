/**
 *
 */
package com.isharefox.share.settlement.alipay.controller;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author zhaoxin
 *
 */
@Data
public class OrderDTO {
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime notify_time;              //通知时间
    private String notify_type;              //通知类型
    private String notify_id;              //通知校验ID
    private String sign_type;              //签名类型
    private String sign;              //签名
    private String trade_no;              //支付宝交易号
    private String app_id;              //开发者的app_id


    private String out_trade_no;              //商户订单号
    @NumberFormat(style= NumberFormat.Style.CURRENCY)
    private BigDecimal total_amount;              //订单金额

    private String out_biz_no;              //商户业务号
    private String buyer_id;              //买家支付宝用户号
    private String buyer_logon_id;              //买家支付宝账号
    private String seller_id;              //卖家支付宝用户号
    private String seller_email;              //卖家支付宝账号
    private String trade_status;              //交易状态

    @NumberFormat(style= NumberFormat.Style.CURRENCY)
    private BigDecimal receipt_amount;              //实收金额
    @NumberFormat(style= NumberFormat.Style.CURRENCY)
    private BigDecimal invoice_amount;              //开票金额
    @NumberFormat(style= NumberFormat.Style.CURRENCY)
    private BigDecimal buyer_pay_amount;              //付款金额
    @NumberFormat(style= NumberFormat.Style.CURRENCY)
    private BigDecimal point_amount;              //集分宝金额
    @NumberFormat(style= NumberFormat.Style.CURRENCY)
    private BigDecimal refund_fee;              //总退款金额
    @NumberFormat(style= NumberFormat.Style.CURRENCY)
    private BigDecimal send_back_fee;              //实际退款金额

    private String subject;              //订单标题
    private String body;              //商品描述
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime gmt_create;              //交易创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime gmt_payment;              //交易付款时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.S")
    private LocalDateTime gmt_refund;              //交易退款时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime gmt_close;              //交易结束时间

    private String fund_bill_list;              //支付金额信息
}

package com.isharefox.share.settlement.alipay.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhaoxin
 * @since 2020-12-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Order extends Model<Order> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 客户编号
     */
    private String userId;

    /**
     * 通知时间 格式为yyyy-MM-dd HH:mm:ss。
     */
    private LocalDateTime notifyTime;

    /**
     * 通知类型	 trade_status_sync
     */
    private String notifyType;

    /**
     * 支付宝交易号
     */
    private String tradeNo;

    /**
     * 开发者的app_id
     */
    private String appId;

    /**
     * 商户订单号原支付请求的商户订单号
     */
    private String outTradeNo;

    /**
     * 商户业务号商户业务 ID，主要是退款通知中返回退款申请的流水号。	HZRF001
     */
    private String outBizNo;

    /**
     * 买家支付宝用户号 买家支付宝账号对应的支付宝唯一用户号。	2088102122524330
     */
    private String buyerId;

    /**
     * 买家支付宝账号
     */
    private String buyerLogonId;

    /**
     * 卖家支付宝用户号
     */
    private String sellerId;

    /**
     * 卖家支付宝账号
     */
    private String sellerEmail;

    /**
     * 交易状态	 TRADE_CLOSED-未付款交易超时关闭，或支付完成后全额退款;WAIT_BUYER_PAY-交易创建，等待买家付款;TRADE_SUCCESS-交易支付成功;TRADE_FINISHED-交易结束，不可退款.
     */
    private String tradeStatus;

    /**
     * 订单金额	
     */
    private BigDecimal totalAmount;

    /**
     * 实收金额
     */
    private BigDecimal receiptAmount;

    /**
     * 开票金额用户在交易中支付的可开发票的金额。	10
     */
    private BigDecimal invoiceAmount;

    /**
     * 付款金额用户在交易中支付的金额。	13.88
     */
    private BigDecimal buyerPayAmount;

    /**
     * 集分宝金额使用集分宝支付的金额。	12
     */
    private BigDecimal pointAmount;

    /**
     * 总退款金额退款通知中，返回总退款金额，单位为元，支持两位小数。	2.58 
     */
    private BigDecimal refundFee;

    /**
     * 实际退款金额商户实际退款给用户的金额，单位为元，支持两位小数。	2.08 
     */
    private BigDecimal sendBackFee;

    /**
     * 订单标题	商品的标题/交易标题/订单标题/订单关键字等，是请求时对应的参数，原样通知回来。
     */
    private String subject;

    /**
     * 商品描述	该订单的备注、描述、明细等。对应请求时body 参数，原样通知回来。
     */
    private String body;

    /**
     * 交易创建时间该笔交易创建的时间。格式为 yyyy-MM-dd HH:mm:ss
     */
    private LocalDateTime gmtCreate;

    /**
     * 交易付款时间该笔交易的买家付款时间。格式为 yyyy-MM-dd HH:mm:ss
     */
    private LocalDateTime gmtPayment;

    /**
     * 交易退款时间该笔交易的退款时间。格式为 yyyy-MM-dd HH:mm:ss.S。
     */
    private LocalDateTime gmtRefund;

    /**
     * 交易结束时间该笔交易结束时间。格式为 yyyy-MM-dd HH:mm:ss。
     */
    private LocalDateTime gmtClose;

    /**
     * 支付金额信息支付成功的各个渠道金额信息，详见资金明细信息说明。	[{"amount":"15.00","fundChannel":"ALIPAYACCOUNT"}]
     */
    private String fundBillList;

    /**
     * 0-异常；1-正常
     */
    private String status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 最后更新时间
     */
    private LocalDateTime updateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

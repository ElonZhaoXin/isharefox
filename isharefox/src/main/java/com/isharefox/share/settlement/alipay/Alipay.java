/**
 * 
 */
package com.isharefox.share.settlement.alipay;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.factory.Factory.Payment;
import com.alipay.easysdk.kernel.Config;
import com.alipay.easysdk.kernel.util.ResponseChecker;
import com.alipay.easysdk.payment.facetoface.models.AlipayTradePrecreateResponse;

/**
 * @author zhaoxin
 */
public class Alipay {
	static {
		Factory.setOptions(getOptions());
	}
	
	private static Config getOptions() {
        Config config = new Config();
        config.protocol = "https";
        config.gatewayHost = "openapi.alipay.com";
        config.signType = "RSA2";

        config.appId = "<-- 请填写您的AppId，例如：2019091767145019 -->";

        // 为避免私钥随源码泄露，推荐从文件中读取私钥字符串而不是写入源码中
        config.merchantPrivateKey = "<-- 请填写您的应用私钥，例如：MIIEvQIBADANB ... ... -->";

        //注：证书文件路径支持设置为文件系统中的路径或CLASS_PATH中的路径，优先从文件系统中加载，加载失败后会继续尝试从CLASS_PATH中加载
        config.merchantCertPath = "<-- 请填写您的应用公钥证书文件路径，例如：/foo/appCertPublicKey_2019051064521003.crt -->";
        config.alipayCertPath = "<-- 请填写您的支付宝公钥证书文件路径，例如：/foo/alipayCertPublicKey_RSA2.crt -->";
        config.alipayRootCertPath = "<-- 请填写您的支付宝根证书文件路径，例如：/foo/alipayRootCert.crt -->";

        //注：如果采用非证书模式，则无需赋值上面的三个证书路径，改为赋值如下的支付宝公钥字符串即可
        // config.alipayPublicKey = "<-- 请填写您的支付宝公钥，例如：MIIBIjANBg... -->";

        //可设置异步通知接收服务地址（可选）
        config.notifyUrl = "<-- 请填写您的支付类接口异步通知接收服务地址，例如：https://www.test.com/callback -->";

        //可设置AES密钥，调用AES加解密相关接口时需要（可选）
        config.encryptKey = "<-- 请填写您的AES密钥，例如：aa4BtZ4tspm2wnXLb1ThQA== -->";

        return config;
    }
	
	/**
	 * 当面付相关AP封装
	 * @author zhaoxin
	 *
	 */
	public static class FaceToFace {
		
		/**
		 * 交易预创建，生成正扫二维码
		 * 
		 * @param subject 订单标题, Apple iPhone11 128G
		 * @param outTradeNo 交易创建时传入的商户订单号 6823789339978248
		 * @param totalAmount 订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]
		 * 
		 * @return 当前预下单请求生成的二维码码串，可以用二维码生成工具根据该码串值生成对应的二维码 https://qr.alipay.com/bavh4wjlxf12tper3a
		 */
		public String getQrCode(String subject, String outTradeNo, String totalAmount) {
			try {
	            // 2. 发起API调用（以创建当面付收款二维码为例）
	            AlipayTradePrecreateResponse response = Payment.FaceToFace()
	                    .preCreate(subject, outTradeNo, totalAmount);
	            // 3. 处理响应或异常
	            if (ResponseChecker.success(response)) {
	                System.out.println("调用成功");
	                return response.getQrCode();
	            } else {
	                System.err.println("调用失败，原因：" + response.msg + "，" + response.subMsg);
	                throw new RuntimeException(response.msg + "，" + response.subMsg);    
	            }
	        } catch (Exception e) {
	            System.err.println("调用遭遇异常，原因：" + e.getMessage());
	            throw new RuntimeException(e.getMessage(), e);
	        }
		}
		
		/**
		 * 处理支付宝异步通知回来的内容
		 * 从http的form表单形式获取
		 * @return
		 */
		public AsyncNotification getAsyncNotification(Map<String, String> params) {
			AsyncNotification asyncNotification = new AsyncNotification();
			try {
				BeanUtils.populate(asyncNotification, params);
			} catch (IllegalAccessException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return asyncNotification;
		}
		
		
		public class AsyncNotification {
			private String notify_time;			// 通知时间	Date	是	通知的发送时间。格式为yyyy-MM-dd HH:mm:ss。	2011/12/27 06:30
			private String notify_type;         // 通知类型	String(64)	是	通知的类型。	trade_status_sync
			private String notify_id;           // 通知校验ID	String(128)	是	通知校验 ID。	ac05099524730693a8b330c5ecf72da9786
			private String sign_type;           // 签名类型	String(10)	是	商户生成签名字符串所使用的签名算法类型，目前支持 RSA2 和 RSA，推荐使用 RSA2（如果开发者手动验签，不使用 SDK 验签，可以不传此参数）。	RSA2
			private String sign;                // 签名	String(256)	是	请参考异步返回结果的验签（如果开发者手动验签，不使用 SDK 验签，可以不传此参数）。	601510b7970e52cc63db0f44997cf70e
			private String trade_no;            // 支付宝交易号	String(64)	是	支付宝交易凭证号。	2.013112011001E+27
			private String app_id;              // 开发者的app_id	String(32)	是	支付宝分配给开发者的应用 APPID。	2014072300007140
			private String out_trade_no;        // 商户订单号	String(64)	是	原支付请求的商户订单号。	6823789339978240
			private String out_biz_no;          // 商户业务号	String(64)	否	商户业务 ID，主要是退款通知中返回退款申请的流水号。	HZRF001
			private String buyer_id;            // 买家支付宝用户号	String(16)	否	买家支付宝账号对应的支付宝唯一用户号。	2088102122524330
			private String buyer_logon_id;      // 买家支付宝账号	String(100)	否	买家支付宝账号。	15901825620
			private String seller_id;           // 卖家支付宝用户号	String(30)	否	卖家支付宝用户号。	2088101106499360
			private String seller_email;        // 卖家支付宝账号	String(100)	否	卖家支付宝账号。	zhuzhanghu@alitest.com
			private String trade_status;        // 交易状态	String(32)	是	交易目前所处的状态。	TRADE_CLOSED-未付款交易超时关闭，或支付完成后全额退款;WAIT_BUYER_PAY-交易创建，等待买家付款;TRADE_SUCCESS-交易支付成功;TRADE_FINISHED-交易结束，不可退款.
			private String total_amount;        // 订单金额	Number(9,2)	是	本次交易支付的订单金额，单位为人民币（元）。	20
			private String receipt_amount;      // 实收金额	Number(9,2)	否	商家在交易中实际收到的款项，单位为人民币（元）。	15
			private String invoice_amount;      // 开票金额	Number(9,2)	否	用户在交易中支付的可开发票的金额。	10
			private String buyer_pay_amount;    // 付款金额	Number(9,2)	否	用户在交易中支付的金额。	13.88
			private String point_amount;        // 集分宝金额	Number(9,2)	否	使用集分宝支付的金额。	12
			private String refund_fee;          // 总退款金额	Number(9,2)	否	退款通知中，返回总退款金额，单位为元，支持两位小数。	2.58
			private String send_back_fee;       // 实际退款金额	Number(9,2)	否	商户实际退款给用户的金额，单位为元，支持两位小数。	2.08
			private String subject;             // 订单标题	String(256)	否	商品的标题/交易标题/订单标题/订单关键字等，是请求时对应的参数，原样通知回来。	当面付交易
			private String body;                // 商品描述	String(400)	否	该订单的备注、描述、明细等。对应请求时的 body 参数，原样通知回来。	当面付交易内容
			private String gmt_create;          // 交易创建时间	Date	否	该笔交易创建的时间。格式为 yyyy-MM-dd HH:mm:ss。	2015/4/27 15:45
			private String gmt_payment;         // 交易付款时间	Date	否	该笔交易的买家付款时间。格式为 yyyy-MM-dd HH:mm:ss。	2015/4/27 15:45
			private String gmt_refund;          // 交易退款时间	Date	否	该笔交易的退款时间。格式为 yyyy-MM-dd HH:mm:ss.S。	45:57.3
			private String gmt_close;           // 交易结束时间	Date	否	该笔交易结束时间。格式为 yyyy-MM-dd HH:mm:ss。	2015/4/29 15:45
			private String fund_bill_list;      // 支付金额信息	String(512)	否	支付成功的各个渠道金额信息，详见资金明细信息说明。	[{"amount":"15.00","fundChannel":"ALIPAYACCOUNT"}]
			
			public String getNotify_time() {
				return notify_time;
			}
			public void setNotify_time(String notify_time) {
				this.notify_time = notify_time;
			}
			public String getNotify_type() {
				return notify_type;
			}
			public void setNotify_type(String notify_type) {
				this.notify_type = notify_type;
			}
			public String getNotify_id() {
				return notify_id;
			}
			public void setNotify_id(String notify_id) {
				this.notify_id = notify_id;
			}
			public String getSign_type() {
				return sign_type;
			}
			public void setSign_type(String sign_type) {
				this.sign_type = sign_type;
			}
			public String getSign() {
				return sign;
			}
			public void setSign(String sign) {
				this.sign = sign;
			}
			public String getTrade_no() {
				return trade_no;
			}
			public void setTrade_no(String trade_no) {
				this.trade_no = trade_no;
			}
			public String getApp_id() {
				return app_id;
			}
			public void setApp_id(String app_id) {
				this.app_id = app_id;
			}
			public String getOut_trade_no() {
				return out_trade_no;
			}
			public void setOut_trade_no(String out_trade_no) {
				this.out_trade_no = out_trade_no;
			}
			public String getOut_biz_no() {
				return out_biz_no;
			}
			public void setOut_biz_no(String out_biz_no) {
				this.out_biz_no = out_biz_no;
			}
			public String getBuyer_id() {
				return buyer_id;
			}
			public void setBuyer_id(String buyer_id) {
				this.buyer_id = buyer_id;
			}
			public String getBuyer_logon_id() {
				return buyer_logon_id;
			}
			public void setBuyer_logon_id(String buyer_logon_id) {
				this.buyer_logon_id = buyer_logon_id;
			}
			public String getSeller_id() {
				return seller_id;
			}
			public void setSeller_id(String seller_id) {
				this.seller_id = seller_id;
			}
			public String getSeller_email() {
				return seller_email;
			}
			public void setSeller_email(String seller_email) {
				this.seller_email = seller_email;
			}
			public String getTrade_status() {
				return trade_status;
			}
			public void setTrade_status(String trade_status) {
				this.trade_status = trade_status;
			}
			public String getTotal_amount() {
				return total_amount;
			}
			public void setTotal_amount(String total_amount) {
				this.total_amount = total_amount;
			}
			public String getReceipt_amount() {
				return receipt_amount;
			}
			public void setReceipt_amount(String receipt_amount) {
				this.receipt_amount = receipt_amount;
			}
			public String getInvoice_amount() {
				return invoice_amount;
			}
			public void setInvoice_amount(String invoice_amount) {
				this.invoice_amount = invoice_amount;
			}
			public String getBuyer_pay_amount() {
				return buyer_pay_amount;
			}
			public void setBuyer_pay_amount(String buyer_pay_amount) {
				this.buyer_pay_amount = buyer_pay_amount;
			}
			public String getPoint_amount() {
				return point_amount;
			}
			public void setPoint_amount(String point_amount) {
				this.point_amount = point_amount;
			}
			public String getRefund_fee() {
				return refund_fee;
			}
			public void setRefund_fee(String refund_fee) {
				this.refund_fee = refund_fee;
			}
			public String getSend_back_fee() {
				return send_back_fee;
			}
			public void setSend_back_fee(String send_back_fee) {
				this.send_back_fee = send_back_fee;
			}
			public String getSubject() {
				return subject;
			}
			public void setSubject(String subject) {
				this.subject = subject;
			}
			public String getBody() {
				return body;
			}
			public void setBody(String body) {
				this.body = body;
			}
			public String getGmt_create() {
				return gmt_create;
			}
			public void setGmt_create(String gmt_create) {
				this.gmt_create = gmt_create;
			}
			public String getGmt_payment() {
				return gmt_payment;
			}
			public void setGmt_payment(String gmt_payment) {
				this.gmt_payment = gmt_payment;
			}
			public String getGmt_refund() {
				return gmt_refund;
			}
			public void setGmt_refund(String gmt_refund) {
				this.gmt_refund = gmt_refund;
			}
			public String getGmt_close() {
				return gmt_close;
			}
			public void setGmt_close(String gmt_close) {
				this.gmt_close = gmt_close;
			}
			public String getFund_bill_list() {
				return fund_bill_list;
			}
			public void setFund_bill_list(String fund_bill_list) {
				this.fund_bill_list = fund_bill_list;
			}
			
		}
		
	}
	
}

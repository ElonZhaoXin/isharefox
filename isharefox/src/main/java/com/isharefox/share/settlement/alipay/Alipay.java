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
import com.isharefox.share.settlement.alipay.entity.Order;

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
		public Order getAsyncNotification(Map<String, String> params) {
			Order order = new Order();
			try {
				BeanUtils.populate(order, params);
			} catch (IllegalAccessException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return order;
		}
		
	}
	
}

/**
 * 
 */
package com.isharefox.share.settlement.alipay;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;


import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.factory.Factory.Payment;
import com.alipay.easysdk.kernel.Config;
import com.alipay.easysdk.kernel.util.ResponseChecker;
import com.alipay.easysdk.payment.facetoface.models.AlipayTradePrecreateResponse;
import com.baomidou.mybatisplus.core.toolkit.BeanUtils;
import com.isharefox.share.kernel.util.ConvertUtils;
import com.isharefox.share.settlement.alipay.controller.OrderDTO;

/**
 * @author zhaoxin
 */
public class Alipay {
	public static void init() {
		Factory.setOptions(getOptions());
	}
	
	private static Config getOptions() {
        Config config = new Config();
        config.protocol = "https";
        config.gatewayHost = "openapi.alipay.com";
        config.signType = "RSA2";

        config.appId = "2021002110673405";

        // 为避免私钥随源码泄露，推荐从文件中读取私钥字符串而不是写入源码中
        config.merchantPrivateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCKROH5rbBSMkkYVUOezM/ZyxhAL9KkaFuDL8gBC9maTX13N9Sxyw3hmuuczdBiurKDxKj8IEtI4u6eQQD0dLibnA+08sAyBhLBCYhBqdmJDklPhfKV3pMytkEFA9soTMu4iR4e+vyFwOR16fobWuFYlGObwfMZv3uYi7j8Pt/BT9DDpi2hbARx1xthZ6L6VoQpwBVsm1XnCrH9+8UOtxh4xwQM79gTkgx0PArsnYInFUcTIl2JEAw0ut1/YE/Q3MH7TENojTKI5RZOyQWMg5BUrcoR0vkZqhQ1Lioi95t79iKsrfKEU8Y5Ft9hhyQe9gxjpLBENv+7wlR2do1M6l13AgMBAAECggEBAIWvO90vdX7DZ7DDH3H2DDBDENteYYbb7z7Tf8ijMGzj5x7ROC/+Zxlts8gTphsO73I/PjMm9iMuZg9UQhwYjjTFmtoUqoazIIbLNJZoJg9BZNgiZf1JCYDKferfY5F/TqjIyfqiKG0ZpsnqwwqXv7DpJ7V8vBJ09Ib08JHSKHy5xTpOGA0/QqZfr6FzKQso63FUApTwTj1IWDIsHFvBL4HVbpD1s7UOEv1eKC/YMD10c8muBIFM/40eEAx04Kp5sDd8hZumG7NSrZH0fwpXX7n2r2Iz8MVKJUrih3A5z1X/KRbwgIAbo3RJnrFn7LtHWW5Rhj7wuHt9CYSkxDMhywECgYEAz+aZA0xs5nMnVBAsid74YLf3GnMz24E5QsD90Db/oZra5D4f/3hB5hpBLhryiiHPPO+SCXyY9jHMGUg+5rTdcvMXtMmLMTd3T6oyVPGAG1yCM+5XjQbPcVX9NO20av3cJ34WUiccyUTpppdMP7yNyiAIq9lg9J5oje5udopWFCECgYEAqkIv6tLTYxMnFYvRYay9koob5peGI1mv7XmDICt0YnnmkDo69LLmfd0FhbSPpBNpTAiA70H/yPchAUrPdKaQnSnt8nHGhUecFq8NZs4F0vyB+b+5ubRFPLuS8kF1axd4vASVn/EE4Dh3jGzLiXfV/IY+aa2As57OnNBn2mq0vpcCgYBYzr1QDmZ6bng/TYVpA4j/77WrY8ikHGg0XrJNLiE2jCqy0uLiQyGLRG3RXwt5/AVFl2S/jqzchdsUdasA7AOopbTbsjg2NuZNbnt/k4XQqkyoGEjn4h/c3wmBgYTPK0/OwuqP/0sporlYQ0r78vRyggqSNhJXjA9HibFAC8nHYQKBgG9shYN+mgz62ddF+6+apy4h6ISOoaC871/Q6DnDdpWEBZpaIX9HHYU9goGwW1kbwwuZfRgX71gMM7NuzDohT/8rE6hZQXy0Erjdsz2Wk0UzL7TM/rVD/xPyBgc5Q6jkYYVa0rkruO8sxUBHayT6IjbzfCEZBWOICNujoHPW5ZbdAoGBAK1XgZQ3xe4sr1DaGMsz9UtvSnFf9+u9U9FYsDDToygoeFQw6h5MP86KKCvg0d/70EAwf+zWUT/zwxblHNFa3cw7B1H4+ZcJMoQjyybNwItU26JNbo3vVK/RMITlDJ+FLuYaoJDizKJHBNKe9GfPK8jsMuHT83TcKCly2TW6iG8Y";

        //注：证书文件路径支持设置为文件系统中的路径或CLASS_PATH中的路径，优先从文件系统中加载，加载失败后会继续尝试从CLASS_PATH中加载
//        config.merchantCertPath = "<-- 请填写您的应用公钥证书文件路径，例如：/foo/appCertPublicKey_2019051064521003.crt -->";
//        config.alipayCertPath = "<-- 请填写您的支付宝公钥证书文件路径，例如：/foo/alipayCertPublicKey_RSA2.crt -->";
//        config.alipayRootCertPath = "<-- 请填写您的支付宝根证书文件路径，例如：/foo/alipayRootCert.crt -->";

        //注：如果采用非证书模式，则无需赋值上面的三个证书路径，改为赋值如下的支付宝公钥字符串即可
        config.alipayPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA3vJKoHORp8CwzfeNhS9GO066KECVj3JFwNGDLP6VJZ05kZl3HZ11eu/xmSmfN9pbOHyqxwycj+ditgSKV0JorSvkf3jHSmKbCNrfmjfWucxSYyyszRCxlcds6RDmM+fjBAMZ0O3hDn8A92VVphGhZ/mQ4efGtjHxAw76WKYU/3cMTDswaX1ujAo9Tyq05qFbNifDwDfAz6LI1tv4UqvXf5QuJF9BbxK/DEXF3tIGr2xS2HsGqtkRgxS+9hXWKjciWjC5ig351PYSTtT8R4dfyAY/Qj1ptwkeFuo9lJ1oFgFkdYaAR0pIZ9tmZzLe5iIZhmzhGapV6OKAPpsjWTRQ3wIDAQAB";

        //可设置异步通知接收服务地址（可选）
//        config.notifyUrl = "https://www.ishare.com/share/alipay/facetoface/callback";
        config.notifyUrl = "http://222.129.54.196/share/alipay/facetoface/callback";
        
        //可设置AES密钥，调用AES加解密相关接口时需要（可选）
//        config.encryptKey = "<-- 请填写您的AES密钥，例如：aa4BtZ4tspm2wnXLb1ThQA== -->";

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
		public static String getQrCode(String subject, String outTradeNo, String totalAmount) {
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
		 * 当面付异步通知验签
		 * @param order
		 * @return
		 */
		public static boolean verifyNotify(OrderDTO order) {
			Map<String, Object> srcParams = BeanUtils.beanToMap(order);
			Map<String, String> parameters = new HashMap<>();
			srcParams.forEach((key, value)  -> {
				parameters.put(key, (String) value);
			});
			try {
				Factory.Payment.Common().verifyNotify(parameters);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
		
	}
	
}

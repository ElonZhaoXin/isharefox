/**
 * 
 */
package com.isharefox.share.settlement.alipay.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.PostMapping;

import com.isharefox.share.kernel.util.ConvertUtils;
import com.isharefox.share.settlement.alipay.controller.OrderDTO;

/**
 * @author zhaoxin
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class OrderTest {

	@Test
	void test() {
		OrderInfo order = new OrderInfo();
		order.setUserId("123");
		order.setOutTradeNo("123");
		order.insert();
		
		
	}
	


}
/**
 * 
 */
package com.isharefox.share.settlement.alipay.entity;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

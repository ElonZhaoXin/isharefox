package com.isharefox.share.settlement.alipay;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class AlipayTest {

	@Test
	void testGetQrCode() {
		Alipay.init();
		String qrCode = Alipay.FaceToFace.getQrCode("256G ihone", "test002", "1");
		assertNotNull(qrCode);
		System.out.println(qrCode);
	}

}

package com.isharefox.share.user.user.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserTest {

	@Test
	void test() {
		User user = new User();
		user.setEmail("test@test");
		user.setPwd("test");
		user.setUserId("test");
		user.insert();
	}

}

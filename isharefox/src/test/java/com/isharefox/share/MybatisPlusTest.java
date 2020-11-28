package com.isharefox.share;

import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.isharefox.share.orm.mapper.UserMapper;
import com.isharefox.share.user.User;


@RunWith(SpringRunner.class)
@SpringBootTest
class MybatisPlusTest {
	@Autowired
	private UserMapper userMapper;

	@Test
	public void testSelectList() {
		List<User> users = userMapper.selectList(null);
		Assert.assertEquals(0, users.size());
		users.forEach(System.out::println);
	}

}

package com.isharefox.share.item.service;


import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.extension.service.IService;
import com.isharefox.share.item.entity.Item;

@RunWith(SpringRunner.class)
@SpringBootTest
class IItemServiceTest {
	@Autowired
	private IService<Item> itemServiceImpl;

	@Test
	void test() {
		Assert.assertEquals(0, itemServiceImpl.count());
	}
	
	@Test
	public void testItemAR() {
		Item item = new Item();
		Assert.assertEquals(0, item.selectAll().size());
				
	}

}
package com.isharefox.share;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan({
		"com.isharefox.share.item.mapper",
		"com.isharefox.share.user.regist.mapper"
})
@SpringBootApplication
public class IsharefoxApplication {

	public static void main(String[] args) {
		SpringApplication.run(IsharefoxApplication.class, args);
	}

}

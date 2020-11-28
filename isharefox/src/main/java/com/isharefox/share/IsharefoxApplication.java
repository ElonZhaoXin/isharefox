package com.isharefox.share;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.isharefox.share.*.orm.mapper")
public class IsharefoxApplication {

	public static void main(String[] args) {
		SpringApplication.run(IsharefoxApplication.class, args);
	}

}

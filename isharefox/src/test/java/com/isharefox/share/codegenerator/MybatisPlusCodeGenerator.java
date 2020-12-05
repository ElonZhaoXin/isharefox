package com.isharefox.share.codegenerator;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisPlusCodeGenerator {
	
	@Test
	public void test() {
//		testItemCodeGenerator();
//		testUserCodeGenerator();
//		testTradeCodeGenerator();
		testOrderCodeGenerator();
	}
	
	/**
	 * 商品模块
	 */
	public void testItemCodeGenerator() {
		generateCode("item", "item");
	}

	/**
	 * 用户模块
	 */
	public void testUserCodeGenerator() {
		generateCode("user.user", "user");
	}
	

	/**
	 * 交易模块
	 */
	public void testTradeCodeGenerator() {
		generateCode("user.account", "user_account");
	}
	

	/**
	 * 阿里结算支付模块
	 */
	public void testOrderCodeGenerator() {
		generateCode("settlement.alipay", "order_info");
	}
	
	/**
	 * 
	 * @param moduleName 写入模块顶级包名，例如：user
	 * @param tableName 待处理的表名，例如：user
	 */
	public void generateCode(String moduleName, String tableName) {
		//1. 全局配置
	    GlobalConfig config = new GlobalConfig();
	    //是否支持AR模式
	    config.setActiveRecord(true)
	        .setAuthor("zhaoxin") //作者
	        .setFileOverride(true)
	        .setOutputDir(System.getProperty("user.dir") + "/src/main/java");  //生成路径
	        
	     //2. 数据源配置
	    DataSourceConfig dsConfig = new DataSourceConfig();
	    dsConfig.setDbType(DbType.MYSQL)
	        .setUrl("jdbc:mysql://localhost:3306/isharefox?useUnicode=true&characterEncoding=utf8")
	        .setDriverName("com.mysql.cj.jdbc.Driver")
	        .setUsername("root")
	        .setPassword("zx2021@startup");
	    
	    //3.包名策略
	    PackageConfig pc = new PackageConfig();
        pc.setModuleName(moduleName);
        pc.setParent("com.isharefox.share");
//        pc.setController("controller");
//        pc.setEntity("domain");
//        pc.setMapper("mapper");
//        pc.setService("service");
//        pc.setServiceImpl("service.impl");
//        pc.setXml("mapper.xml");
	    
	    //4.策略配置
	    StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setSuperEntityClass("com.isharefox.share.kernel.BaseEntity");
        strategy.setSuperEntityColumns("id", "status", "create_time", "update_time");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 公共父类
//        strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
        // 写于父类中的公共字段
        strategy.setInclude(tableName); 
        strategy.setControllerMappingHyphenStyle(true);
//        strategy.setTablePrefix(pc.getModuleName() + "_");
	    
	    //5.整合配置
	    AutoGenerator generator = new AutoGenerator().setGlobalConfig(config)
	         .setDataSource(dsConfig)
	         .setStrategy(strategy)
	         .setPackageInfo(pc);
	    generator.execute();
	}

}

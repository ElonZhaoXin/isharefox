-- MySQL dump 10.13  Distrib 8.0.22, for macos10.15 (x86_64)
--
-- Host: 127.0.0.1    Database: isharefox
-- ------------------------------------------------------
-- Server version	8.0.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item` (
  `id` int NOT NULL AUTO_INCREMENT,
  `resource_id` varchar(4) NOT NULL COMMENT '资源编号，在支付URL中：http://isharefox.com/share/userId/resourceId',
  `zip_pwd` varchar(6) NOT NULL COMMENT '解压密码',
  `description` varchar(60) DEFAULT NULL COMMENT '商品描述',
  `amount` decimal(10,0) NOT NULL COMMENT '收费金额',
  `user_id` varchar(4) NOT NULL COMMENT '客户编号',
  `status` varchar(1) NOT NULL COMMENT '资源状态，0-创建，未发布；1=上架，正常售卖；2-下架',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `resource_id_UNIQUE` (`resource_id`),
  KEY `UK` (`resource_id`,`user_id`) COMMENT '联合索引，唯一确认一个支付资源情况'
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `order_info`
--

DROP TABLE IF EXISTS `order_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(4) NOT NULL COMMENT '客户编号',
  `notify_time` datetime DEFAULT NULL COMMENT '通知时间 格式为yyyy-MM-dd HH:mm:ss。',
  `notify_type` varchar(64) DEFAULT NULL COMMENT '通知类型	 trade_status_sync',
  `trade_no` varchar(64) DEFAULT NULL COMMENT '支付宝交易号',
  `app_id` varchar(32) DEFAULT NULL COMMENT '开发者的app_id',
  `out_trade_no` varchar(64) NOT NULL COMMENT '商户订单号原支付请求的商户订单号',
  `out_biz_no` varchar(64) DEFAULT NULL COMMENT '商户业务号商户业务 ID，主要是退款通知中返回退款申请的流水号。	HZRF001',
  `buyer_id` varchar(16) DEFAULT NULL COMMENT '买家支付宝用户号 买家支付宝账号对应的支付宝唯一用户号。	2088102122524330',
  `buyer_logon_id` varchar(100) DEFAULT NULL COMMENT '买家支付宝账号',
  `seller_id` varchar(30) DEFAULT NULL COMMENT '卖家支付宝用户号',
  `seller_email` varchar(100) DEFAULT NULL COMMENT '卖家支付宝账号',
  `trade_status` varchar(32) DEFAULT NULL COMMENT '交易状态	 TRADE_CLOSED-未付款交易超时关闭，或支付完成后全额退款;WAIT_BUYER_PAY-交易创建，等待买家付款;TRADE_SUCCESS-交易支付成功;TRADE_FINISHED-交易结束，不可退款.',
  `total_amount` decimal(9,2) DEFAULT NULL COMMENT '订单金额	',
  `receipt_amount` decimal(9,2) DEFAULT NULL COMMENT '实收金额',
  `invoice_amount` decimal(9,2) DEFAULT NULL COMMENT '开票金额用户在交易中支付的可开发票的金额。	10',
  `buyer_pay_amount` decimal(9,2) DEFAULT NULL COMMENT '付款金额用户在交易中支付的金额。	13.88',
  `point_amount` decimal(9,2) DEFAULT NULL COMMENT '集分宝金额使用集分宝支付的金额。	12',
  `refund_fee` decimal(9,2) DEFAULT NULL COMMENT '总退款金额退款通知中，返回总退款金额，单位为元，支持两位小数。	2.58 ',
  `send_back_fee` decimal(9,2) DEFAULT NULL COMMENT '实际退款金额商户实际退款给用户的金额，单位为元，支持两位小数。	2.08 ',
  `subject` varchar(256) DEFAULT NULL COMMENT '订单标题	商品的标题/交易标题/订单标题/订单关键字等，是请求时对应的参数，原样通知回来。',
  `body` varchar(400) DEFAULT NULL COMMENT '商品描述	该订单的备注、描述、明细等。对应请求时body 参数，原样通知回来。',
  `gmt_create` datetime DEFAULT NULL COMMENT '交易创建时间该笔交易创建的时间。格式为 yyyy-MM-dd HH:mm:ss',
  `gmt_payment` datetime DEFAULT NULL COMMENT '交易付款时间该笔交易的买家付款时间。格式为 yyyy-MM-dd HH:mm:ss',
  `gmt_refund` datetime DEFAULT NULL COMMENT '交易退款时间该笔交易的退款时间。格式为 yyyy-MM-dd HH:mm:ss.S。',
  `gmt_close` datetime DEFAULT NULL COMMENT '交易结束时间该笔交易结束时间。格式为 yyyy-MM-dd HH:mm:ss。',
  `fund_bill_list` varchar(512) DEFAULT NULL COMMENT '支付金额信息支付成功的各个渠道金额信息，详见资金明细信息说明。	[{"amount":"15.00","fundChannel":"ALIPAYACCOUNT"}]',
  `status` varchar(1) NOT NULL COMMENT '0-异常；1-正常',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `out_trade_no_UNIQUE` (`out_trade_no`),
  KEY `UK` (`out_trade_no`) COMMENT '订单编号',
  KEY `userid` (`user_id`) COMMENT '用户编号'
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(4) NOT NULL COMMENT '客户编号',
  `nick_name` varchar(15) DEFAULT NULL COMMENT '昵称',
  `email` varchar(255) NOT NULL COMMENT '登录邮箱',
  `pwd` varchar(9) NOT NULL COMMENT '登录密码',
  `cell_phone_num` varchar(11) DEFAULT NULL COMMENT '手机号',
  `status` varchar(1) NOT NULL COMMENT '0-异常；1-正常',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `UK` (`user_id`) COMMENT '客户编号唯一'
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_account`
--

DROP TABLE IF EXISTS `user_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_account` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(4) NOT NULL COMMENT '客户编号',
  `type` varchar(1) NOT NULL COMMENT '账户类型，0-微信；1-支付宝',
  `account_no` varchar(255) NOT NULL COMMENT '1-支付宝：手机号/账号；',
  `status` varchar(1) NOT NULL COMMENT '0-异常；1-正常',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  KEY `UK` (`user_id`) COMMENT '客户编号唯一'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-05 21:46:56

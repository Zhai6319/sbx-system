/*
 Navicat Premium Data Transfer

 Source Server         : zjc-person
 Source Server Type    : MySQL
 Source Server Version : 50718 (5.7.18-20170830-log)
 Source Host           : cdb-dsd9ddou.bj.tencentcdb.com:10009
 Source Schema         : sbx-system-dev

 Target Server Type    : MySQL
 Target Server Version : 50718 (5.7.18-20170830-log)
 File Encoding         : 65001

 Date: 12/12/2022 10:58:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` bigint(20) NOT NULL COMMENT '账户id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `account_type` tinyint(2) NOT NULL COMMENT '账户类型',
  `available_balance` bigint(20) NOT NULL COMMENT '可用余额',
  `reward_balance` bigint(20) NOT NULL COMMENT '奖励余额',
  `frozen_balance` bigint(20) NOT NULL COMMENT '冻结余额',
  `points_balance` int(11) NOT NULL COMMENT '积分余额',
  `total_withdraw` bigint(20) NOT NULL COMMENT '提现总额',
  `enable_flag` tinyint(1) NOT NULL COMMENT '启用状态',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `delete_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户账户';

-- ----------------------------
-- Records of account
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for account_bill
-- ----------------------------
DROP TABLE IF EXISTS `account_bill`;
CREATE TABLE `account_bill` (
  `id` bigint(20) NOT NULL COMMENT '账户账单id',
  `account_id` bigint(20) NOT NULL COMMENT '账户id',
  `bill_sn` varchar(32) NOT NULL COMMENT '账单编号',
  `order_sn` varchar(32) NOT NULL COMMENT '订单编号',
  `trade` varchar(32) NOT NULL COMMENT '交易单号',
  `bill_amount` bigint(20) NOT NULL COMMENT '账单金额',
  `bill_points` int(11) NOT NULL COMMENT '账单积分',
  `bill_type` tinyint(2) NOT NULL COMMENT '账单类型 1下单支付 2用户充值 3积分兑换 4获取积分 5获取奖励金额 6退款进账 7提现申请 8提现成功',
  `income_expend_type` tinyint(2) NOT NULL COMMENT '收支类型',
  `available_amount` bigint(20) NOT NULL COMMENT '可用金额',
  `reward_amount` bigint(20) NOT NULL COMMENT '奖励金额',
  `frozen_amount` bigint(20) NOT NULL COMMENT '冻结金额',
  `available_balance` bigint(20) NOT NULL COMMENT '可用余额',
  `reward_balance` bigint(20) NOT NULL COMMENT '奖励余额',
  `frozen_balance` bigint(20) NOT NULL COMMENT '冻结余额',
  `points_balance` int(11) NOT NULL COMMENT '积分余额',
  `remarks` varchar(255) NOT NULL COMMENT '备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `delete_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='账户账单';

-- ----------------------------
-- Records of account_bill
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for app
-- ----------------------------
DROP TABLE IF EXISTS `app`;
CREATE TABLE `app` (
  `id` bigint(20) NOT NULL COMMENT '应用ID',
  `app_name` varchar(32) NOT NULL COMMENT '应用名称',
  `app_logo` varchar(255) NOT NULL COMMENT '应用logo',
  `app_host` varchar(255) NOT NULL COMMENT '应用跳转地址',
  `outline` varchar(255) NOT NULL COMMENT '应用描述',
  `provider` varchar(20) NOT NULL COMMENT '提供者',
  `provider_mobile` varchar(20) NOT NULL COMMENT '提供者手机号',
  `audit_status` tinyint(2) NOT NULL COMMENT '应用状态0待审核 10审核通过 20审核不通过',
  `enable_flag` tinyint(1) NOT NULL COMMENT '启用标识',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `delete_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of app
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for enterpise
-- ----------------------------
DROP TABLE IF EXISTS `enterpise`;
CREATE TABLE `enterpise` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `set_meal_id` bigint(20) NOT NULL COMMENT '套餐ID',
  `enterprise_name` varchar(20) NOT NULL COMMENT '企业名称',
  `enterprise_logo` varchar(255) NOT NULL COMMENT '企业logo',
  `outline` varchar(255) NOT NULL DEFAULT '' COMMENT '简介',
  `expire_time` datetime NOT NULL COMMENT '过期时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `delete_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of enterpise
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for product_declare
-- ----------------------------
DROP TABLE IF EXISTS `product_declare`;
CREATE TABLE `product_declare` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '声明id',
  `declare_type` tinyint(2) NOT NULL COMMENT '声明类型 1产品声明 2退款声明 3安全须知 4物资准备',
  `declare_name` varchar(16) NOT NULL COMMENT '声明名称',
  `content` longtext NOT NULL COMMENT '声明内容',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `delete_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='产品声明';

-- ----------------------------
-- Records of product_declare
-- ----------------------------
BEGIN;
INSERT INTO `product_declare` (`id`, `declare_type`, `declare_name`, `content`, `create_time`, `update_time`, `delete_flag`) VALUES (1, 1, '测试声明', '测试声明', '2021-07-25 15:06:11', '2021-07-31 16:12:25', 0);
INSERT INTO `product_declare` (`id`, `declare_type`, `declare_name`, `content`, `create_time`, `update_time`, `delete_flag`) VALUES (2, 2, '阿斯顿发', '<p>水电费阿斯顿发</p>', '2021-07-25 19:04:42', '2021-07-31 16:12:27', 0);
INSERT INTO `product_declare` (`id`, `declare_type`, `declare_name`, `content`, `create_time`, `update_time`, `delete_flag`) VALUES (3, 3, '通用声明', '<h3 style=\"text-align: center;\"><span style=\"font-family: \'Microsoft Yahei\';\"><img src=\"https://jingdan-devtest.oss-cn-shanghai.aliyuncs.com/dev/2021/07/29/4ba1fdccab1948cab5116d6213bafc5a.png\" alt=\"\" width=\"100%\" height=\"300\" />哈哈</span></h3>\n<pre class=\"language-java\"><code>public class User {\n    private Long id;\n    private String userName;\n}</code></pre>\n<p>&nbsp;</p>\n<p><img src=\"https://jingdan-devtest.oss-cn-shanghai.aliyuncs.com/dev/2021/07/29/2b9ed1acdedc48fab44f29ba136947c6.jpg\" alt=\"\" width=\"100%\" height=\"1000\" /></p>', '2021-07-25 19:04:45', '2021-07-31 16:12:28', 0);
COMMIT;

-- ----------------------------
-- Table structure for product_info
-- ----------------------------
DROP TABLE IF EXISTS `product_info`;
CREATE TABLE `product_info` (
  `id` bigint(20) NOT NULL COMMENT '产品id',
  `region_id` bigint(20) NOT NULL COMMENT '区域id',
  `destination_id` bigint(20) NOT NULL COMMENT '目的地id',
  `product_name` varchar(32) NOT NULL COMMENT '产品名称',
  `description` varchar(64) NOT NULL COMMENT '产品描述',
  `price` bigint(20) NOT NULL COMMENT '产品价格',
  `max_people` int(3) NOT NULL COMMENT '最大人数',
  `min_age` int(3) NOT NULL COMMENT '最小年龄',
  `max_age` int(3) NOT NULL COMMENT '最大年龄',
  `days` int(2) NOT NULL COMMENT '出行天数',
  `gather_place` varchar(255) NOT NULL COMMENT '集合地点',
  `gather_place_longitude` decimal(10,6) NOT NULL COMMENT '集合地点经度',
  `gather_place_latitude` decimal(10,6) NOT NULL COMMENT '集合地点纬度',
  `dissolution_place` varchar(255) NOT NULL COMMENT '解散地点',
  `dissolution_place_longitude` decimal(10,6) NOT NULL COMMENT '解散地点经度',
  `dissolution_place_latitude` decimal(10,6) NOT NULL COMMENT '解散地点纬度',
  `main_image` varchar(512) NOT NULL DEFAULT '' COMMENT '主图/封面',
  `images` json NOT NULL COMMENT '轮播图',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `delete_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='产品信息';

-- ----------------------------
-- Records of product_info
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for product_region
-- ----------------------------
DROP TABLE IF EXISTS `product_region`;
CREATE TABLE `product_region` (
  `id` bigint(20) NOT NULL COMMENT '区域id',
  `region_name` varchar(20) NOT NULL COMMENT '区域名称',
  `region_type` tinyint(2) NOT NULL COMMENT '区域类型 1国内 2国际',
  `description` varchar(255) NOT NULL COMMENT '描述',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `delete_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='区域信息';

-- ----------------------------
-- Records of product_region
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for product_region_destination
-- ----------------------------
DROP TABLE IF EXISTS `product_region_destination`;
CREATE TABLE `product_region_destination` (
  `id` bigint(20) NOT NULL COMMENT '目的地id',
  `region_id` bigint(20) NOT NULL COMMENT '区域id',
  `destination_name` varchar(20) NOT NULL COMMENT '目的地名称',
  `hot_flag` tinyint(1) NOT NULL COMMENT '是否热门',
  `sort` int(11) NOT NULL COMMENT '排序',
  `enable_flag` tinyint(1) NOT NULL COMMENT '是否启用 1启用 0未启用',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `delete_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='区域目的地';

-- ----------------------------
-- Records of product_region_destination
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for product_trip
-- ----------------------------
DROP TABLE IF EXISTS `product_trip`;
CREATE TABLE `product_trip` (
  `id` bigint(20) NOT NULL COMMENT '行程id',
  `product_id` bigint(20) NOT NULL COMMENT '产品id',
  `day_sn` int(2) NOT NULL COMMENT '第几天',
  `attribute` json NOT NULL COMMENT '拓展属性',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `delete_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='每日行程介绍';

-- ----------------------------
-- Records of product_trip
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for set_meal
-- ----------------------------
DROP TABLE IF EXISTS `set_meal`;
CREATE TABLE `set_meal` (
  `id` bigint(20) NOT NULL COMMENT '套餐ID',
  `set_meal_name` varchar(20) NOT NULL COMMENT '套餐名称',
  `set_meal_content` json NOT NULL COMMENT '套餐内容',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `delete_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of set_meal
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_authorities
-- ----------------------------
DROP TABLE IF EXISTS `sys_authorities`;
CREATE TABLE `sys_authorities` (
  `id` bigint(20) NOT NULL COMMENT '权限id',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单id',
  `authority_type` tinyint(2) NOT NULL COMMENT '权限类型',
  `authority` varchar(64) NOT NULL COMMENT '权限',
  `method` varchar(10) NOT NULL COMMENT '方法',
  `module` varchar(20) NOT NULL COMMENT '所属模块',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `delete_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统权限';

-- ----------------------------
-- Records of sys_authorities
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL COMMENT '菜单id',
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '父级id',
  `menu_type` tinyint(2) NOT NULL COMMENT '菜单类型',
  `ascription` tinyint(2) NOT NULL COMMENT '归属系统',
  `menu_title` varchar(10) NOT NULL COMMENT '菜单标题',
  `router` varchar(64) NOT NULL DEFAULT '' COMMENT '前端路由',
  `menu_code` varchar(20) NOT NULL DEFAULT '' COMMENT '菜单编码',
  `component` varchar(64) NOT NULL DEFAULT '' COMMENT '前端组件地址',
  `redirect` varchar(64) NOT NULL DEFAULT '' COMMENT '转发路由',
  `icon` varchar(64) NOT NULL DEFAULT '' COMMENT '图标',
  `menu_level` tinyint(2) NOT NULL COMMENT '菜单等级',
  `show_flag` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否展示',
  `sort` int(11) NOT NULL COMMENT '排序',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `delete_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统菜单';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` (`id`, `parent_id`, `menu_type`, `ascription`, `menu_title`, `router`, `menu_code`, `component`, `redirect`, `icon`, `menu_level`, `show_flag`, `sort`, `create_time`, `update_time`, `delete_flag`) VALUES (1, 0, 0, 0, '系统管理', '', '', '', '', 'sbx-shezhi', 1, 1, 1, '2021-07-14 01:15:54', '2021-07-14 01:15:54', 0);
INSERT INTO `sys_menu` (`id`, `parent_id`, `menu_type`, `ascription`, `menu_title`, `router`, `menu_code`, `component`, `redirect`, `icon`, `menu_level`, `show_flag`, `sort`, `create_time`, `update_time`, `delete_flag`) VALUES (2, 1, 0, 0, '权限管理', '', '', '', '', '', 2, 1, 2, '2021-07-14 01:16:53', '2021-07-14 01:16:53', 0);
INSERT INTO `sys_menu` (`id`, `parent_id`, `menu_type`, `ascription`, `menu_title`, `router`, `menu_code`, `component`, `redirect`, `icon`, `menu_level`, `show_flag`, `sort`, `create_time`, `update_time`, `delete_flag`) VALUES (3, 2, 0, 0, '角色管理', '/system/authority/role', 'page-role', 'system/role/Role.vue', '', '', 3, 1, 3, '2021-07-14 01:18:42', '2021-07-14 01:18:42', 0);
INSERT INTO `sys_menu` (`id`, `parent_id`, `menu_type`, `ascription`, `menu_title`, `router`, `menu_code`, `component`, `redirect`, `icon`, `menu_level`, `show_flag`, `sort`, `create_time`, `update_time`, `delete_flag`) VALUES (4, 3, 1, 0, '添加角色', 'top', 'add', '', '', '', 4, 1, 4, '2021-07-14 01:19:16', '2021-07-14 01:19:16', 0);
INSERT INTO `sys_menu` (`id`, `parent_id`, `menu_type`, `ascription`, `menu_title`, `router`, `menu_code`, `component`, `redirect`, `icon`, `menu_level`, `show_flag`, `sort`, `create_time`, `update_time`, `delete_flag`) VALUES (5, 3, 1, 0, '编辑角色', 'list', 'edit', '', '', '', 4, 1, 5, '2021-07-14 01:19:53', '2021-07-14 01:19:53', 0);
INSERT INTO `sys_menu` (`id`, `parent_id`, `menu_type`, `ascription`, `menu_title`, `router`, `menu_code`, `component`, `redirect`, `icon`, `menu_level`, `show_flag`, `sort`, `create_time`, `update_time`, `delete_flag`) VALUES (6, 3, 1, 0, '删除角色', 'list', 'del', '', '', '', 4, 1, 6, '2021-07-14 01:20:21', '2021-07-14 01:20:21', 0);
INSERT INTO `sys_menu` (`id`, `parent_id`, `menu_type`, `ascription`, `menu_title`, `router`, `menu_code`, `component`, `redirect`, `icon`, `menu_level`, `show_flag`, `sort`, `create_time`, `update_time`, `delete_flag`) VALUES (7, 2, 0, 0, '菜单管理', '/system/authority/menu', 'page-menu', 'system/menu/Menu.vue', '', '', 3, 1, 7, '2021-07-14 01:21:46', '2021-07-14 01:24:09', 0);
INSERT INTO `sys_menu` (`id`, `parent_id`, `menu_type`, `ascription`, `menu_title`, `router`, `menu_code`, `component`, `redirect`, `icon`, `menu_level`, `show_flag`, `sort`, `create_time`, `update_time`, `delete_flag`) VALUES (8, 7, 1, 0, '菜单页面权限', 'page', 'page-query', '', '', '', 4, 1, 8, '2021-07-14 01:24:05', '2021-07-14 01:29:46', 0);
INSERT INTO `sys_menu` (`id`, `parent_id`, `menu_type`, `ascription`, `menu_title`, `router`, `menu_code`, `component`, `redirect`, `icon`, `menu_level`, `show_flag`, `sort`, `create_time`, `update_time`, `delete_flag`) VALUES (9, 7, 1, 0, '添加菜单', 'top', 'add', '', '', '', 4, 1, 9, '2021-07-14 01:24:45', '2021-07-14 01:24:45', 0);
INSERT INTO `sys_menu` (`id`, `parent_id`, `menu_type`, `ascription`, `menu_title`, `router`, `menu_code`, `component`, `redirect`, `icon`, `menu_level`, `show_flag`, `sort`, `create_time`, `update_time`, `delete_flag`) VALUES (10, 7, 1, 0, '设置权限', 'list-authority', 'setup-authority', '', '', '', 4, 1, 10, '2021-07-14 01:26:51', '2021-07-14 01:26:51', 0);
INSERT INTO `sys_menu` (`id`, `parent_id`, `menu_type`, `ascription`, `menu_title`, `router`, `menu_code`, `component`, `redirect`, `icon`, `menu_level`, `show_flag`, `sort`, `create_time`, `update_time`, `delete_flag`) VALUES (11, 7, 1, 0, '编辑', 'list', 'edit', '', '', '', 4, 1, 11, '2021-07-14 01:28:04', '2021-07-14 01:28:04', 0);
INSERT INTO `sys_menu` (`id`, `parent_id`, `menu_type`, `ascription`, `menu_title`, `router`, `menu_code`, `component`, `redirect`, `icon`, `menu_level`, `show_flag`, `sort`, `create_time`, `update_time`, `delete_flag`) VALUES (12, 3, 1, 0, '角色页面权限', 'page', 'page-query', '', '', '', 4, 1, 12, '2021-07-14 01:29:35', '2021-07-14 01:29:35', 0);
INSERT INTO `sys_menu` (`id`, `parent_id`, `menu_type`, `ascription`, `menu_title`, `router`, `menu_code`, `component`, `redirect`, `icon`, `menu_level`, `show_flag`, `sort`, `create_time`, `update_time`, `delete_flag`) VALUES (13, 1, 0, 0, '系统用户', '/system/admin', 'page-admin', 'system/admin/Admin.vue', '', '', 2, 1, 13, '2021-07-14 01:31:36', '2021-07-14 01:31:36', 0);
INSERT INTO `sys_menu` (`id`, `parent_id`, `menu_type`, `ascription`, `menu_title`, `router`, `menu_code`, `component`, `redirect`, `icon`, `menu_level`, `show_flag`, `sort`, `create_time`, `update_time`, `delete_flag`) VALUES (14, 13, 1, 0, '系统用户页面权限', 'page', 'page-query', '', '', '', 3, 1, 14, '2021-07-14 01:32:36', '2021-07-14 01:32:36', 0);
INSERT INTO `sys_menu` (`id`, `parent_id`, `menu_type`, `ascription`, `menu_title`, `router`, `menu_code`, `component`, `redirect`, `icon`, `menu_level`, `show_flag`, `sort`, `create_time`, `update_time`, `delete_flag`) VALUES (15, 13, 1, 0, '添加用户', 'top', 'add', '', '', '', 3, 1, 15, '2021-07-14 01:33:23', '2021-07-14 01:33:23', 0);
INSERT INTO `sys_menu` (`id`, `parent_id`, `menu_type`, `ascription`, `menu_title`, `router`, `menu_code`, `component`, `redirect`, `icon`, `menu_level`, `show_flag`, `sort`, `create_time`, `update_time`, `delete_flag`) VALUES (16, 13, 1, 0, '编辑', 'list', 'edit', '', '', '', 3, 1, 16, '2021-07-14 01:33:48', '2021-07-14 01:33:48', 0);
INSERT INTO `sys_menu` (`id`, `parent_id`, `menu_type`, `ascription`, `menu_title`, `router`, `menu_code`, `component`, `redirect`, `icon`, `menu_level`, `show_flag`, `sort`, `create_time`, `update_time`, `delete_flag`) VALUES (17, 0, 0, 0, '产品管理', '', '', '', '', 'sbx-shezhi', 1, 1, 17, '2021-07-24 23:41:49', '2021-07-24 23:44:23', 0);
INSERT INTO `sys_menu` (`id`, `parent_id`, `menu_type`, `ascription`, `menu_title`, `router`, `menu_code`, `component`, `redirect`, `icon`, `menu_level`, `show_flag`, `sort`, `create_time`, `update_time`, `delete_flag`) VALUES (18, 17, 0, 0, '产品信息', '/product/info', 'page-product', 'product/productInfo/ProductInfo.vue', '', '', 2, 1, 18, '2021-07-24 23:44:21', '2021-07-24 23:44:21', 0);
INSERT INTO `sys_menu` (`id`, `parent_id`, `menu_type`, `ascription`, `menu_title`, `router`, `menu_code`, `component`, `redirect`, `icon`, `menu_level`, `show_flag`, `sort`, `create_time`, `update_time`, `delete_flag`) VALUES (19, 17, 0, 0, '产品声明', '/product/declare', 'page-declare', 'product/productDeclare/ProductDeclare.vue', '', '', 2, 1, 19, '2021-07-24 23:49:44', '2021-07-24 23:49:44', 0);
INSERT INTO `sys_menu` (`id`, `parent_id`, `menu_type`, `ascription`, `menu_title`, `router`, `menu_code`, `component`, `redirect`, `icon`, `menu_level`, `show_flag`, `sort`, `create_time`, `update_time`, `delete_flag`) VALUES (20, 19, 1, 0, '添加声明', 'top', 'add', '', '', '', 3, 1, 20, '2021-07-25 13:47:08', '2021-07-25 13:47:08', 0);
INSERT INTO `sys_menu` (`id`, `parent_id`, `menu_type`, `ascription`, `menu_title`, `router`, `menu_code`, `component`, `redirect`, `icon`, `menu_level`, `show_flag`, `sort`, `create_time`, `update_time`, `delete_flag`) VALUES (21, 19, 1, 0, '修改声明', 'list', 'edit', '', '', '', 3, 1, 21, '2021-07-25 13:49:05', '2021-07-25 13:49:05', 0);
INSERT INTO `sys_menu` (`id`, `parent_id`, `menu_type`, `ascription`, `menu_title`, `router`, `menu_code`, `component`, `redirect`, `icon`, `menu_level`, `show_flag`, `sort`, `create_time`, `update_time`, `delete_flag`) VALUES (22, 19, 1, 0, '声明页面权限', 'page', 'page-query', '', '', '', 3, 1, 22, '2021-07-25 13:49:58', '2021-07-25 13:49:58', 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(20) NOT NULL COMMENT '角色名称',
  `remarks` varchar(32) NOT NULL DEFAULT '' COMMENT '备注',
  `super_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否超级角色',
  `role_type` tinyint(2) NOT NULL COMMENT '角色类型',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `delete_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='系统角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` (`id`, `role_name`, `remarks`, `super_flag`, `role_type`, `create_time`, `update_time`, `delete_flag`) VALUES (1, '超级管理员', '拥有所有权限', 1, 0, '2021-07-14 01:12:34', '2021-07-14 01:12:34', 0);
INSERT INTO `sys_role` (`id`, `role_name`, `remarks`, `super_flag`, `role_type`, `create_time`, `update_time`, `delete_flag`) VALUES (3, '运营管理', '', 0, 0, '2021-09-29 22:40:35', '2021-09-29 22:40:42', 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色关联菜单表id',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单id',
  `half_flag` tinyint(1) NOT NULL COMMENT '是否半选择',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `delete_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COMMENT='角色关联菜单';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `half_flag`, `create_time`, `update_time`, `delete_flag`) VALUES (1, 1, 1, 0, '2021-07-14 01:34:47', '2021-07-14 01:43:05', 0);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `half_flag`, `create_time`, `update_time`, `delete_flag`) VALUES (2, 1, 2, 0, '2021-07-14 01:34:56', '2021-07-14 01:43:08', 0);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `half_flag`, `create_time`, `update_time`, `delete_flag`) VALUES (3, 1, 3, 0, '2021-07-14 01:35:01', '2021-07-14 01:43:11', 0);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `half_flag`, `create_time`, `update_time`, `delete_flag`) VALUES (4, 1, 4, 0, '2021-07-14 01:35:06', '2021-07-14 01:43:14', 0);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `half_flag`, `create_time`, `update_time`, `delete_flag`) VALUES (5, 1, 5, 0, '2021-07-14 01:35:11', '2021-07-14 01:43:17', 0);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `half_flag`, `create_time`, `update_time`, `delete_flag`) VALUES (6, 1, 6, 0, '2021-07-14 01:35:18', '2021-07-14 01:43:20', 0);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `half_flag`, `create_time`, `update_time`, `delete_flag`) VALUES (7, 1, 7, 0, '2021-07-14 01:35:25', '2021-07-14 01:43:23', 0);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `half_flag`, `create_time`, `update_time`, `delete_flag`) VALUES (8, 1, 8, 0, '2021-07-14 01:35:34', '2021-07-14 01:43:26', 0);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `half_flag`, `create_time`, `update_time`, `delete_flag`) VALUES (9, 1, 9, 0, '2021-07-14 01:35:42', '2021-07-14 01:43:29', 0);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `half_flag`, `create_time`, `update_time`, `delete_flag`) VALUES (10, 1, 10, 0, '2021-07-14 01:35:50', '2021-07-14 01:43:32', 0);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `half_flag`, `create_time`, `update_time`, `delete_flag`) VALUES (11, 1, 11, 0, '2021-07-14 01:35:55', '2021-07-14 01:43:35', 0);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `half_flag`, `create_time`, `update_time`, `delete_flag`) VALUES (12, 1, 12, 0, '2021-07-14 01:36:00', '2021-07-14 01:43:37', 0);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `half_flag`, `create_time`, `update_time`, `delete_flag`) VALUES (13, 1, 13, 0, '2021-07-14 01:36:05', '2021-07-14 01:43:40', 0);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `half_flag`, `create_time`, `update_time`, `delete_flag`) VALUES (14, 1, 14, 0, '2021-07-14 01:36:11', '2021-07-14 01:43:42', 0);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `half_flag`, `create_time`, `update_time`, `delete_flag`) VALUES (15, 1, 15, 0, '2021-07-14 01:36:16', '2021-07-14 01:43:45', 0);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `half_flag`, `create_time`, `update_time`, `delete_flag`) VALUES (16, 1, 16, 0, '2021-07-14 01:36:24', '2021-07-14 01:43:47', 0);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `half_flag`, `create_time`, `update_time`, `delete_flag`) VALUES (17, 1, 17, 0, '2021-07-24 23:45:39', '2021-07-24 23:45:39', 0);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `half_flag`, `create_time`, `update_time`, `delete_flag`) VALUES (18, 1, 18, 0, '2021-07-24 23:45:51', '2021-07-24 23:45:51', 0);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `half_flag`, `create_time`, `update_time`, `delete_flag`) VALUES (19, 1, 19, 0, '2021-07-24 23:49:59', '2021-07-24 23:49:59', 0);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `half_flag`, `create_time`, `update_time`, `delete_flag`) VALUES (20, 1, 20, 0, '2021-07-25 13:47:22', '2021-07-25 13:47:22', 0);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `half_flag`, `create_time`, `update_time`, `delete_flag`) VALUES (21, 1, 21, 0, '2021-07-25 13:50:09', '2021-07-25 13:50:09', 0);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `half_flag`, `create_time`, `update_time`, `delete_flag`) VALUES (22, 1, 22, 0, '2021-07-25 13:50:14', '2021-07-25 13:50:14', 0);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `half_flag`, `create_time`, `update_time`, `delete_flag`) VALUES (23, 3, 17, 0, '2021-09-29 22:40:35', '2021-09-29 22:40:42', 1);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `half_flag`, `create_time`, `update_time`, `delete_flag`) VALUES (24, 3, 18, 0, '2021-09-29 22:40:35', '2021-09-29 22:40:42', 1);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `half_flag`, `create_time`, `update_time`, `delete_flag`) VALUES (25, 3, 19, 0, '2021-09-29 22:40:35', '2021-09-29 22:40:42', 1);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `half_flag`, `create_time`, `update_time`, `delete_flag`) VALUES (26, 3, 20, 0, '2021-09-29 22:40:35', '2021-09-29 22:40:42', 1);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `half_flag`, `create_time`, `update_time`, `delete_flag`) VALUES (27, 3, 21, 0, '2021-09-29 22:40:35', '2021-09-29 22:40:42', 1);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `half_flag`, `create_time`, `update_time`, `delete_flag`) VALUES (28, 3, 22, 0, '2021-09-29 22:40:35', '2021-09-29 22:40:42', 1);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `half_flag`, `create_time`, `update_time`, `delete_flag`) VALUES (29, 3, 17, 0, '2021-09-29 22:40:43', '2021-09-29 22:40:43', 0);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `half_flag`, `create_time`, `update_time`, `delete_flag`) VALUES (30, 3, 18, 0, '2021-09-29 22:40:43', '2021-09-29 22:40:43', 0);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `half_flag`, `create_time`, `update_time`, `delete_flag`) VALUES (31, 3, 19, 0, '2021-09-29 22:40:43', '2021-09-29 22:40:43', 0);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `half_flag`, `create_time`, `update_time`, `delete_flag`) VALUES (32, 3, 20, 0, '2021-09-29 22:40:43', '2021-09-29 22:40:43', 0);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `half_flag`, `create_time`, `update_time`, `delete_flag`) VALUES (33, 3, 21, 0, '2021-09-29 22:40:43', '2021-09-29 22:40:43', 0);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `half_flag`, `create_time`, `update_time`, `delete_flag`) VALUES (34, 3, 22, 0, '2021-09-29 22:40:43', '2021-09-29 22:40:43', 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_tag
-- ----------------------------
DROP TABLE IF EXISTS `sys_tag`;
CREATE TABLE `sys_tag` (
  `id` bigint(20) NOT NULL COMMENT '标签id',
  `tag_name` varchar(20) NOT NULL COMMENT '标签名称',
  `tag_type` tinyint(2) NOT NULL COMMENT '标签类型 1用户标签 2产品标签',
  `enable_flag` tinyint(1) NOT NULL COMMENT '启用标识',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `delete_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统标签';

-- ----------------------------
-- Records of sys_tag
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(64) NOT NULL DEFAULT '' COMMENT '用户名',
  `nickname` varchar(64) NOT NULL DEFAULT '' COMMENT '昵称',
  `gender` tinyint(2) NOT NULL DEFAULT '0' COMMENT '性别',
  `mobile` varchar(128) NOT NULL DEFAULT '' COMMENT '手机号',
  `email` varchar(32) NOT NULL DEFAULT '' COMMENT '邮箱',
  `birthday` varchar(20) NOT NULL DEFAULT '' COMMENT '生日',
  `portrait` varchar(255) NOT NULL DEFAULT '' COMMENT '头像',
  `user_type` tinyint(2) NOT NULL COMMENT '用户类型 1管理员 2c端用户',
  `super_admin` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否超级管理员',
  `enable_flag` tinyint(1) NOT NULL DEFAULT '1' COMMENT '启用状态',
  `source` int(2) NOT NULL COMMENT '来源',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `delete_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='用户信息';

-- ----------------------------
-- Records of user_info
-- ----------------------------
BEGIN;
INSERT INTO `user_info` (`id`, `username`, `nickname`, `gender`, `mobile`, `email`, `birthday`, `portrait`, `user_type`, `super_admin`, `enable_flag`, `source`, `create_time`, `update_time`, `delete_flag`) VALUES (1, 'admin', '超级管理员', 0, 'H187LHrIO1YgrEUmhjmBtg==', '', '', '', 0, 1, 1, 0, '2021-07-14 01:09:05', '2021-09-29 22:26:36', 0);
INSERT INTO `user_info` (`id`, `username`, `nickname`, `gender`, `mobile`, `email`, `birthday`, `portrait`, `user_type`, `super_admin`, `enable_flag`, `source`, `create_time`, `update_time`, `delete_flag`) VALUES (2, 'yunying', '运营', 0, 'xJQ4W03uR+N7/O6zErVOpg==', '', '', '', 0, 0, 1, 0, '2021-09-29 22:25:37', '2021-09-29 22:43:28', 0);
COMMIT;

-- ----------------------------
-- Table structure for user_login
-- ----------------------------
DROP TABLE IF EXISTS `user_login`;
CREATE TABLE `user_login` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `login_type` tinyint(2) NOT NULL COMMENT '登录类型',
  `union_id` varchar(128) NOT NULL DEFAULT '' COMMENT '微信联盟id',
  `secret_key` varchar(255) NOT NULL COMMENT '密钥',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `delete_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='用户登录信息';

-- ----------------------------
-- Records of user_login
-- ----------------------------
BEGIN;
INSERT INTO `user_login` (`id`, `user_id`, `login_type`, `union_id`, `secret_key`, `create_time`, `update_time`, `delete_flag`) VALUES (1, 1, 0, '', '$2a$10$Yu9Bia8AI/WpiD1EaesrBefS/56bRPy/55UwIKGBOFrdX7sQK/jXi', '2021-07-14 01:11:32', '2021-07-14 01:11:32', 0);
INSERT INTO `user_login` (`id`, `user_id`, `login_type`, `union_id`, `secret_key`, `create_time`, `update_time`, `delete_flag`) VALUES (2, 2, 0, '', '$2a$10$eKYRb9/bvCETpG4Ns/N2tut0ay.y9ta8bRzUMbvZSlCb0NNVSKUt2', '2021-09-29 22:25:37', '2021-09-29 22:25:37', 0);
COMMIT;

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户角色id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `delete_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COMMENT='用户角色关联';

-- ----------------------------
-- Records of user_role
-- ----------------------------
BEGIN;
INSERT INTO `user_role` (`id`, `user_id`, `role_id`, `create_time`, `update_time`, `delete_flag`) VALUES (1, 1, 1, '2021-07-14 01:12:07', '2021-09-29 22:20:04', 1);
INSERT INTO `user_role` (`id`, `user_id`, `role_id`, `create_time`, `update_time`, `delete_flag`) VALUES (2, 1, 1, '2021-09-29 22:20:04', '2021-09-29 22:20:47', 1);
INSERT INTO `user_role` (`id`, `user_id`, `role_id`, `create_time`, `update_time`, `delete_flag`) VALUES (3, 1, 1, '2021-09-29 22:20:47', '2021-09-29 22:22:27', 1);
INSERT INTO `user_role` (`id`, `user_id`, `role_id`, `create_time`, `update_time`, `delete_flag`) VALUES (4, 1, 1, '2021-09-29 22:22:27', '2021-09-29 22:23:03', 1);
INSERT INTO `user_role` (`id`, `user_id`, `role_id`, `create_time`, `update_time`, `delete_flag`) VALUES (5, 1, 1, '2021-09-29 22:23:03', '2021-09-29 22:23:57', 1);
INSERT INTO `user_role` (`id`, `user_id`, `role_id`, `create_time`, `update_time`, `delete_flag`) VALUES (6, 1, 1, '2021-09-29 22:23:57', '2021-09-29 22:26:36', 1);
INSERT INTO `user_role` (`id`, `user_id`, `role_id`, `create_time`, `update_time`, `delete_flag`) VALUES (7, 2, 1, '2021-09-29 22:25:37', '2021-09-29 22:27:00', 1);
INSERT INTO `user_role` (`id`, `user_id`, `role_id`, `create_time`, `update_time`, `delete_flag`) VALUES (8, 1, 1, '2021-09-29 22:26:36', '2021-09-29 22:26:36', 0);
INSERT INTO `user_role` (`id`, `user_id`, `role_id`, `create_time`, `update_time`, `delete_flag`) VALUES (9, 2, 1, '2021-09-29 22:27:00', '2021-09-29 22:27:23', 1);
INSERT INTO `user_role` (`id`, `user_id`, `role_id`, `create_time`, `update_time`, `delete_flag`) VALUES (10, 2, 1, '2021-09-29 22:27:23', '2021-09-29 22:27:51', 1);
INSERT INTO `user_role` (`id`, `user_id`, `role_id`, `create_time`, `update_time`, `delete_flag`) VALUES (11, 2, 1, '2021-09-29 22:27:51', '2021-09-29 22:28:16', 1);
INSERT INTO `user_role` (`id`, `user_id`, `role_id`, `create_time`, `update_time`, `delete_flag`) VALUES (12, 2, 1, '2021-09-29 22:28:16', '2021-09-29 22:28:58', 1);
INSERT INTO `user_role` (`id`, `user_id`, `role_id`, `create_time`, `update_time`, `delete_flag`) VALUES (13, 2, 1, '2021-09-29 22:28:58', '2021-09-29 22:30:04', 1);
INSERT INTO `user_role` (`id`, `user_id`, `role_id`, `create_time`, `update_time`, `delete_flag`) VALUES (14, 2, 1, '2021-09-29 22:30:04', '2021-09-29 22:31:59', 1);
INSERT INTO `user_role` (`id`, `user_id`, `role_id`, `create_time`, `update_time`, `delete_flag`) VALUES (15, 2, 1, '2021-09-29 22:31:59', '2021-09-29 22:33:57', 1);
INSERT INTO `user_role` (`id`, `user_id`, `role_id`, `create_time`, `update_time`, `delete_flag`) VALUES (16, 2, 1, '2021-09-29 22:33:57', '2021-09-29 22:34:16', 1);
INSERT INTO `user_role` (`id`, `user_id`, `role_id`, `create_time`, `update_time`, `delete_flag`) VALUES (17, 2, 1, '2021-09-29 22:34:17', '2021-09-29 22:40:55', 1);
INSERT INTO `user_role` (`id`, `user_id`, `role_id`, `create_time`, `update_time`, `delete_flag`) VALUES (18, 2, 1, '2021-09-29 22:40:55', '2021-09-29 22:43:28', 1);
INSERT INTO `user_role` (`id`, `user_id`, `role_id`, `create_time`, `update_time`, `delete_flag`) VALUES (19, 2, 1, '2021-09-29 22:43:28', '2021-09-29 22:43:28', 0);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

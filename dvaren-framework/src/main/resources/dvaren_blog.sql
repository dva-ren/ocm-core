/*
Navicat MySQL Data Transfer

Source Server         : 本地mysql
Source Server Version : 80023
Source Host           : localhost:3306
Source Database       : dvaren_blog

Target Server Type    : MYSQL
Target Server Version : 80023
File Encoding         : 65001

Date: 2023-08-11 14:39:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_article
-- ----------------------------
DROP TABLE IF EXISTS `t_article`;
CREATE TABLE `t_article` (
  `id` varchar(255) NOT NULL,
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标题',
  `summary` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '摘要，简介',
  `content` longtext NOT NULL COMMENT '文章内容',
  `category_id` varchar(255) DEFAULT NULL COMMENT '分类id',
  `label` varchar(255) DEFAULT NULL COMMENT '标签',
  `cover` varchar(255) DEFAULT NULL COMMENT '封面图',
  `is_top` int DEFAULT '0' COMMENT '是否置顶(0否 1是)',
  `status` int NOT NULL DEFAULT '0' COMMENT '状态(0已发布, 1草稿)',
  `comment_count` int DEFAULT '0' COMMENT '评论数',
  `view_count` bigint DEFAULT '0' COMMENT '浏览量',
  `allow_comment` int DEFAULT '1' COMMENT '是否允许评论（1是，0否）',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` int NOT NULL DEFAULT '0' COMMENT '逻辑删除字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for t_category
-- ----------------------------
DROP TABLE IF EXISTS `t_category`;
CREATE TABLE `t_category` (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(255) DEFAULT NULL COMMENT '分类名',
  `description` varchar(512) DEFAULT NULL COMMENT '描述',
  `pid` varchar(255) DEFAULT NULL COMMENT '父分类id',
  `status` int DEFAULT '0' COMMENT '状态(0已发布, 1草稿)',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` int NOT NULL DEFAULT '0' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment` (
  `id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `ref` varchar(100) DEFAULT '' COMMENT '评论id',
  `ref_type` varchar(20) DEFAULT NULL COMMENT '评论类型',
  `avatar` varchar(255) DEFAULT NULL COMMENT '评论者头像',
  `author` varchar(50) DEFAULT NULL COMMENT '评论者',
  `mail` varchar(50) DEFAULT NULL COMMENT '评论者邮箱',
  `parent` varchar(100) NOT NULL COMMENT '父评论id',
  `url` varchar(255) DEFAULT NULL COMMENT '评论者网站',
  `content` text,
  `status` int NOT NULL DEFAULT '0' COMMENT '评论状态 0已发布 1已读 2未读 3垃圾箱',
  `is_whispers` int DEFAULT '0' COMMENT '悄悄话 0否 1是',
  `send_ip` varchar(100) DEFAULT NULL COMMENT '评论ip',
  `location` varchar(100) DEFAULT NULL COMMENT '评论者定位',
  `comments_index` int DEFAULT NULL COMMENT '评论index (评论楼层)',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` int NOT NULL DEFAULT '0' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for t_friends
-- ----------------------------
DROP TABLE IF EXISTS `t_friends`;
CREATE TABLE `t_friends` (
  `id` varchar(100) NOT NULL,
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '名称',
  `type` varchar(255) DEFAULT NULL COMMENT '类型',
  `url` varchar(255) DEFAULT NULL COMMENT '网址',
  `state` int NOT NULL DEFAULT '1',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` int NOT NULL DEFAULT '0' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for t_log
-- ----------------------------
DROP TABLE IF EXISTS `t_log`;
CREATE TABLE `t_log` (
  `id` varchar(100) NOT NULL DEFAULT '' COMMENT 'id',
  `path` varchar(100) DEFAULT '' COMMENT '请求路径',
  `ip` varchar(255) DEFAULT '' COMMENT '请求ip',
  `ua` varchar(255) DEFAULT NULL COMMENT 'UA',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `is_delete` int NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for t_note
-- ----------------------------
DROP TABLE IF EXISTS `t_note`;
CREATE TABLE `t_note` (
  `id` varchar(255) NOT NULL,
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标题',
  `summary` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '摘要，简介',
  `category_id` varchar(255) DEFAULT NULL COMMENT '分类/专栏id',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文章内容',
  `position` varchar(255) DEFAULT NULL COMMENT '位置',
  `mood` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '心情',
  `weather` varchar(50) DEFAULT NULL COMMENT '天气',
  `cover` varchar(255) DEFAULT NULL COMMENT '封面图',
  `music_id` varchar(100) DEFAULT NULL COMMENT '音乐 id',
  `is_top` int DEFAULT '0' COMMENT '是否置顶(0否 1是)',
  `status` int NOT NULL DEFAULT '0' COMMENT '状态(0已发布, 1草稿)',
  `comment_count` int DEFAULT '0' COMMENT '评论数',
  `view_count` bigint DEFAULT '0' COMMENT '浏览量',
  `allow_comment` int DEFAULT '1' COMMENT '是否允许评论（1是，0否）',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` int NOT NULL DEFAULT '0' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for t_picture
-- ----------------------------
DROP TABLE IF EXISTS `t_picture`;
CREATE TABLE `t_picture` (
  `id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'id',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '图片地址',
  `nickname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `position` varchar(255) DEFAULT NULL COMMENT '位置',
  `labels` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '标签',
  `description` text COMMENT '图片描述',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` int NOT NULL DEFAULT '0' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for t_say
-- ----------------------------
DROP TABLE IF EXISTS `t_say`;
CREATE TABLE `t_say` (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `content` varchar(255) DEFAULT NULL COMMENT '内容',
  `author` varchar(50) DEFAULT NULL COMMENT '作者',
  `origin` varchar(50) DEFAULT NULL COMMENT '来源',
  `is_top` int DEFAULT '0' COMMENT '是否置顶(0否 1是)',
  `status` int NOT NULL DEFAULT '0' COMMENT '状态(0已发布, 1草稿)',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` int NOT NULL DEFAULT '0' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for t_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_tag`;
CREATE TABLE `t_tag` (
  `id` varchar(100) NOT NULL,
  `name` varchar(100) DEFAULT NULL COMMENT '标签名',
  `type` varchar(255) NOT NULL COMMENT '标签类型',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` int NOT NULL DEFAULT '0' COMMENT '逻辑删除字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
  `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `site_name` varchar(255) DEFAULT '' COMMENT '网站名称',
  `nickname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户昵称',
  `roles` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '角色',
  `avatar` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '头像地址',
  `icp` varchar(255) DEFAULT NULL COMMENT 'icp备案号',
  `url` varchar(255) DEFAULT NULL COMMENT '面板地址',
  `mail` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '邮箱地址',
  `introduce` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '签名',
  `last_login_time` datetime DEFAULT NULL COMMENT '上次登录时间',
  `last_login_ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '登录Ip',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_delete` int NOT NULL DEFAULT '0' COMMENT '是否删除 0正常 1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

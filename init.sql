/*
Navicat MySQL Data Transfer

Source Server         : zkkk
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : alcyone

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2017-01-19 17:03:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `advertisement`
-- ----------------------------
DROP TABLE IF EXISTS `advertisement`;
CREATE TABLE `advertisement` (
  `id` int(11) NOT NULL,
  `img` varchar(200) DEFAULT NULL,
  `page` int(11) DEFAULT NULL,
  `position` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `summary` longtext,
  `title` varchar(200) DEFAULT NULL,
  `update_time` varchar(20) DEFAULT NULL,
  `url` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of advertisement
-- ----------------------------
INSERT INTO `advertisement` VALUES ('1', null, '1', '1', '1', null, '首页1', null, null);
INSERT INTO `advertisement` VALUES ('2', null, '1', '2', '1', null, '首页2', null, null);
INSERT INTO `advertisement` VALUES ('3', null, '1', '3', '1', null, '首页3', null, null);
INSERT INTO `advertisement` VALUES ('4', null, '1', '4', '1', null, '首页4', null, null);
INSERT INTO `advertisement` VALUES ('5', null, '1', '5', '1', null, '首页5', null, null);
INSERT INTO `advertisement` VALUES ('6', null, '1', '6', '1', null, '首页6', null, null);
INSERT INTO `advertisement` VALUES ('7', null, '1', '7', '1', null, '首页7', null, null);
INSERT INTO `advertisement` VALUES ('8', null, '1', '8', '1', null, '首页8', null, null);
INSERT INTO `advertisement` VALUES ('9', null, '1', '9', '1', null, '首页9', null, null);
INSERT INTO `advertisement` VALUES ('10', null, '1', '10', '1', null, '首页10', null, null);
INSERT INTO `advertisement` VALUES ('11', null, '2', '1', '1', null, '播放页1', null, null);
INSERT INTO `advertisement` VALUES ('12', null, '2', '2', '1', null, '播放页2', null, null);
INSERT INTO `advertisement` VALUES ('13', null, '2', '3', '1', null, '播放页3', null, null);
INSERT INTO `advertisement` VALUES ('14', null, '2', '4', '1', null, '播放页4', null, null);
INSERT INTO `advertisement` VALUES ('15', null, '2', '5', '1', null, '播放页5', null, null);
INSERT INTO `advertisement` VALUES ('16', null, '2', '6', '1', null, '播放页6', null, null);
INSERT INTO `advertisement` VALUES ('17', null, '2', '7', '1', null, '播放页7', null, null);
INSERT INTO `advertisement` VALUES ('18', null, '2', '8', '1', null, '播放页8', null, null);
INSERT INTO `advertisement` VALUES ('19', null, '2', '9', '1', null, '播放页9', null, null);
INSERT INTO `advertisement` VALUES ('20', null, '2', '10', '1', null, '播放页10', null, null);
INSERT INTO `advertisement` VALUES ('21', null, '2', '11', '1', null, '播放页11', null, null);
INSERT INTO `advertisement` VALUES ('22', null, '2', '12', '1', null, '播放页12', null, null);
INSERT INTO `advertisement` VALUES ('23', null, '2', '13', '1', null, '播放页13', null, null);
INSERT INTO `advertisement` VALUES ('24', null, '2', '14', '1', null, '播放页14', null, null);
INSERT INTO `advertisement` VALUES ('25', null, '2', '15', '1', null, '播放页15', null, null);
INSERT INTO `advertisement` VALUES ('26', null, '2', '16', '1', null, '播放页16', null, null);
INSERT INTO `advertisement` VALUES ('27', null, '2', '17', '1', null, '播放页17', null, null);
INSERT INTO `advertisement` VALUES ('28', null, '2', '18', '1', null, '播放页18', null, null);
INSERT INTO `advertisement` VALUES ('29', null, '2', '19', '1', null, '播放页19', null, null);
INSERT INTO `advertisement` VALUES ('30', null, '2', '20', '1', null, '播放页20', null, null);

-- ----------------------------
-- Table structure for `pay_config`
-- ----------------------------
DROP TABLE IF EXISTS `pay_config`;
CREATE TABLE `pay_config` (
  `id` int(11) NOT NULL,
  `amount` float DEFAULT NULL,
  `name` varchar(10) DEFAULT NULL,
  `month_lenth` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pay_config
-- ----------------------------
INSERT INTO `pay_config` VALUES ('11', '30', '两个月', '2', '1');
INSERT INTO `pay_config` VALUES ('12', '30', '一季', '3', '1');
INSERT INTO `pay_config` VALUES ('13', '30', '半年', '6', '1');
INSERT INTO `pay_config` VALUES ('14', '30', '一年', '12', '1');
INSERT INTO `pay_config` VALUES ('15', '30', '任意', null, '2');

/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : student

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2019-11-18 18:08:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for students
-- ----------------------------
DROP TABLE IF EXISTS `students`;
CREATE TABLE `students` (
  `uid` varchar(20) NOT NULL DEFAULT '',
  `name` varchar(20) NOT NULL,
  `nation` varchar(20) NOT NULL,
  `sex` varchar(4) NOT NULL,
  `age` int(11) DEFAULT NULL,
  `school` varchar(40) NOT NULL,
  `major` varchar(20) NOT NULL,
  `detail` varchar(255) DEFAULT NULL,
  `isPoor` tinyint(1) DEFAULT NULL,
  `tel` varchar(11) NOT NULL,
  `parentTel` varchar(11) NOT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `cv` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of students
-- ----------------------------
INSERT INTO `students` VALUES ('11602070823', '李四', '汉', '男', '2000', '理学院', '数学', '目前从彩排来看，有三组领队，每组三人，均由陆军执旗；服装为绿蓝白三种颜色；同时有男兵和女兵。10年前，三军仪仗队是执八一军旗出场，其他的徒步方队领队是2人。但2019年国庆大阅兵从彩排现场的视频看，是由三组士兵执三面旗，分别是党旗、国旗、军旗。', '0', '13302583691', '13323456789', 'http://127.0.0.1:8080/image/1573972079073.jpg', 'http://127.0.0.1:8080/image/1573972094960.docx');
INSERT INTO `students` VALUES ('11602070826', '李四', '汉', '男', '2016', '会计学院', '会计', '无', '0', '13385209631', '15523456789', 'http://127.0.0.1:8080/image/1573981169903.jpg', '');
INSERT INTO `students` VALUES ('11602070829', '李四', '汉', '男', '2016', '理学院', '数学', '无', '0', '13385209631', '15523456789', 'http://127.0.0.1:8080/image/1574068996163.jpg', '');
INSERT INTO `students` VALUES ('11602070832', '李四', '汉', '男', '2016', '会计学院', '会计学', '无', '0', '13385209631', '15523456789', '', null);
INSERT INTO `students` VALUES ('11603080117', '张三', '回', '男', '2016', '计算机科学与工程学院', '软件工程', '无', '0', '13385209631', '15523456789', null, null);
INSERT INTO `students` VALUES ('11603080120', '张三', '汉', '男', '2013', '计算机科学与工程学院', '软件工程', '无', '0', '13385209631', '15523456789', null, null);
INSERT INTO `students` VALUES ('11603080123', '张三', '汉', '男', '2016', '计算机科学与工程学院', '软件工程', '无', '0', '13385209631', '15523456789', '', null);
INSERT INTO `students` VALUES ('11603080126', '张三', '汉', '男', '2016', '计算机科学与工程学院', '软件工程', '无', '0', '13385209631', '15523456789', null, null);
INSERT INTO `students` VALUES ('11606080404', '赵六', '汉', '男', '2015', '理学院', '数学', '无', '1', '13385209631', '15523456789', null, null);
INSERT INTO `students` VALUES ('11606080407', '赵六', '汉', '男', '2016', '理学院', '数学', '无', '0', '13385209631', '15523456789', null, null);
INSERT INTO `students` VALUES ('11606080410', '赵六', '汉', '男', '2016', '理学院', '数学', '无', '0', '13385209631', '15523456789', null, null);
INSERT INTO `students` VALUES ('11606080413', '赵六', '汉', '男', '2016', '理学院', '数学', '无', '0', '13385209631', '15523456789', null, null);
INSERT INTO `students` VALUES ('11703080110', '丽丽', '汉', '女', '2000', '计算机科学与工程学院', '计算机', '', '0', '13323456789', '13323456789', null, null);

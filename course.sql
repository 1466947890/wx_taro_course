-- MySQL dump 10.13  Distrib 5.7.44, for Win64 (x86_64)
--
-- Host: localhost    Database: course
-- ------------------------------------------------------
-- Server version	5.7.44

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `c_chapter`
--

DROP TABLE IF EXISTS `c_chapter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `c_chapter` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '章节ID',
  `name` varchar(100) NOT NULL COMMENT '章节名称',
  `course_id` int(11) NOT NULL COMMENT '所属课程',
  PRIMARY KEY (`id`),
  KEY `fk_chapter_course` (`course_id`),
  CONSTRAINT `fk_chapter_course` FOREIGN KEY (`course_id`) REFERENCES `c_course` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100002 DEFAULT CHARSET=utf8 COMMENT='章节表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `c_chapter`
--

LOCK TABLES `c_chapter` WRITE;
/*!40000 ALTER TABLE `c_chapter` DISABLE KEYS */;
INSERT INTO `c_chapter` VALUES (100000,'ACCA规章',100004),(100001,'测试',100004);
/*!40000 ALTER TABLE `c_chapter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `c_course`
--

DROP TABLE IF EXISTS `c_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `c_course` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '课程编号',
  `name` varchar(100) NOT NULL COMMENT '课程名称',
  `image` varchar(100) NOT NULL COMMENT '课程封面',
  `credit` int(11) DEFAULT NULL COMMENT '课程学分',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100005 DEFAULT CHARSET=utf8 COMMENT='课程表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `c_course`
--

LOCK TABLES `c_course` WRITE;
/*!40000 ALTER TABLE `c_course` DISABLE KEYS */;
INSERT INTO `c_course` VALUES (100004,'航空理论','http://localhost:9092/file/40f3bc724f32413a9b8a800766afc86c.jpg',5);
/*!40000 ALTER TABLE `c_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `c_details`
--

DROP TABLE IF EXISTS `c_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `c_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '资料ID',
  `name` varchar(50) NOT NULL COMMENT '资料文件名',
  `url` varchar(100) NOT NULL COMMENT '资料地址',
  `course_id` int(11) NOT NULL COMMENT '所属课程',
  PRIMARY KEY (`id`),
  UNIQUE KEY `url` (`url`),
  KEY `fk_details_course_id` (`course_id`),
  CONSTRAINT `fk_details_course_id` FOREIGN KEY (`course_id`) REFERENCES `c_course` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='课程资料表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `c_details`
--

LOCK TABLES `c_details` WRITE;
/*!40000 ALTER TABLE `c_details` DISABLE KEYS */;
INSERT INTO `c_details` VALUES (2,'测试PPt.pptx','http://localhost:9092/file/dce8fce42b12479b9983f2de87835977.pptx',100004);
/*!40000 ALTER TABLE `c_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `c_examine`
--

DROP TABLE IF EXISTS `c_examine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `c_examine` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `title` varchar(100) NOT NULL COMMENT '题目',
  `e_options` varchar(50) DEFAULT NULL COMMENT '选项',
  `type` int(10) NOT NULL COMMENT '题目类型',
  `answer` varchar(100) DEFAULT NULL COMMENT '答案',
  `chapter_id` int(11) NOT NULL COMMENT '所属章节',
  PRIMARY KEY (`id`),
  KEY `fk_examine_chapter` (`chapter_id`),
  KEY `fk_examine_type` (`type`),
  CONSTRAINT `fk_examine_chapter` FOREIGN KEY (`chapter_id`) REFERENCES `c_chapter` (`id`),
  CONSTRAINT `fk_examine_type` FOREIGN KEY (`type`) REFERENCES `c_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='章节试题表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `c_examine`
--

LOCK TABLES `c_examine` WRITE;
/*!40000 ALTER TABLE `c_examine` DISABLE KEYS */;
INSERT INTO `c_examine` VALUES (2,'测试1','1#1#1#c',1,'A',100000),(3,'测试题目2','测试选项1#测试选项2#测试选项3#测试选项4',1,'D',100000),(4,'测试3','122#2#2#2',1,'A',100000);
/*!40000 ALTER TABLE `c_examine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `c_major`
--

DROP TABLE IF EXISTS `c_major`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `c_major` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(20) NOT NULL COMMENT '专业名称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  UNIQUE KEY `name_2` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='专业表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `c_major`
--

LOCK TABLES `c_major` WRITE;
/*!40000 ALTER TABLE `c_major` DISABLE KEYS */;
INSERT INTO `c_major` VALUES (1,'光电信息科学与工程');
/*!40000 ALTER TABLE `c_major` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `c_message`
--

DROP TABLE IF EXISTS `c_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `c_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `value` varchar(100) NOT NULL COMMENT '留言内容',
  `m_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '留言时间',
  `pid` int(11) DEFAULT NULL COMMENT '回复的内容ID',
  `userid` int(11) NOT NULL COMMENT '所属用户',
  `chapter_id` int(11) NOT NULL COMMENT '所属章节',
  PRIMARY KEY (`id`),
  KEY `fk_message_userid_user_id` (`userid`),
  KEY `fk_message_chapter_id_chapter_id` (`chapter_id`),
  CONSTRAINT `fk_message_chapter_id_chapter_id` FOREIGN KEY (`chapter_id`) REFERENCES `c_chapter` (`id`),
  CONSTRAINT `fk_message_userid_user_id` FOREIGN KEY (`userid`) REFERENCES `c_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='章节留言表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `c_message`
--

LOCK TABLES `c_message` WRITE;
/*!40000 ALTER TABLE `c_message` DISABLE KEYS */;
INSERT INTO `c_message` VALUES (1,'测试留言1','2023-11-28 07:05:07',NULL,1,100000),(2,'测试留言2','2023-11-28 07:06:21',NULL,1,100000),(3,'测试回复1','2023-11-28 08:25:12',1,1,100000),(4,'测试回复2','2023-11-28 11:58:59',1,1,100000),(5,'测试回复3','2023-11-28 11:59:18',2,1,100000),(6,'测试','2023-12-13 03:13:59',NULL,1,100000),(7,'测试2','2023-12-13 03:17:21',NULL,1,100000),(8,'从2','2024-01-04 05:37:21',NULL,1,100000);
/*!40000 ALTER TABLE `c_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `c_notes`
--

DROP TABLE IF EXISTS `c_notes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `c_notes` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '笔记ID',
  `title` varchar(100) DEFAULT NULL COMMENT '笔记标题',
  `content` varchar(1000) NOT NULL COMMENT '笔记内容',
  `userid` int(11) DEFAULT NULL COMMENT '所属用户',
  `chapter_id` int(11) DEFAULT NULL COMMENT '所属章节',
  PRIMARY KEY (`id`),
  KEY `fk_notes_userid` (`userid`),
  KEY `fk_notes_chapter_id` (`chapter_id`),
  CONSTRAINT `fk_notes_chapter_id` FOREIGN KEY (`chapter_id`) REFERENCES `c_chapter` (`id`),
  CONSTRAINT `fk_notes_userid` FOREIGN KEY (`userid`) REFERENCES `c_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='章节笔记';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `c_notes`
--

LOCK TABLES `c_notes` WRITE;
/*!40000 ALTER TABLE `c_notes` DISABLE KEYS */;
INSERT INTO `c_notes` VALUES (1,'测试标题123','测试内容2222222111',1,100000);
/*!40000 ALTER TABLE `c_notes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `c_role`
--

DROP TABLE IF EXISTS `c_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `c_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(50) NOT NULL COMMENT '角色名称',
  `flag` varchar(50) NOT NULL COMMENT '唯一标识',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  UNIQUE KEY `flag` (`flag`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `c_role`
--

LOCK TABLES `c_role` WRITE;
/*!40000 ALTER TABLE `c_role` DISABLE KEYS */;
INSERT INTO `c_role` VALUES (1,'管理员','ROLE_ADMIN'),(2,'普通用户','ROLE_USER');
/*!40000 ALTER TABLE `c_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `c_student`
--

DROP TABLE IF EXISTS `c_student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `c_student` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '学号',
  `userid` int(11) NOT NULL COMMENT '所属用户',
  PRIMARY KEY (`id`),
  UNIQUE KEY `userid` (`userid`),
  CONSTRAINT `fk_student_user_id` FOREIGN KEY (`userid`) REFERENCES `c_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='学生表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `c_student`
--

LOCK TABLES `c_student` WRITE;
/*!40000 ALTER TABLE `c_student` DISABLE KEYS */;
INSERT INTO `c_student` VALUES (2,1);
/*!40000 ALTER TABLE `c_student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `c_student_course`
--

DROP TABLE IF EXISTS `c_student_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `c_student_course` (
  `course_id` int(11) NOT NULL COMMENT '课程ID',
  `student_id` int(11) NOT NULL COMMENT '学生ID',
  `grade` decimal(10,0) NOT NULL DEFAULT '0',
  KEY `fk_student_course_course_id` (`course_id`),
  KEY `fk_student_course_student_id` (`student_id`),
  CONSTRAINT `fk_student_course_course_id` FOREIGN KEY (`course_id`) REFERENCES `c_course` (`id`),
  CONSTRAINT `fk_student_course_student_id` FOREIGN KEY (`student_id`) REFERENCES `c_student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学生课程关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `c_student_course`
--

LOCK TABLES `c_student_course` WRITE;
/*!40000 ALTER TABLE `c_student_course` DISABLE KEYS */;
INSERT INTO `c_student_course` VALUES (100004,2,25);
/*!40000 ALTER TABLE `c_student_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `c_student_process`
--

DROP TABLE IF EXISTS `c_student_process`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `c_student_process` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `video_process` decimal(10,0) NOT NULL DEFAULT '0' COMMENT '视频进度',
  `examine_grade` decimal(10,0) NOT NULL DEFAULT '0' COMMENT '测验分数',
  `student_id` int(11) NOT NULL COMMENT '所属学生ID',
  `chapter_id` int(11) NOT NULL COMMENT '所属章节',
  PRIMARY KEY (`id`),
  KEY `fk_process_chapter` (`chapter_id`),
  KEY `c_student_process_c_student_FK` (`student_id`),
  CONSTRAINT `c_student_process_c_student_FK` FOREIGN KEY (`student_id`) REFERENCES `c_student` (`id`),
  CONSTRAINT `fk_process_chapter` FOREIGN KEY (`chapter_id`) REFERENCES `c_chapter` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='学生章节学习进度表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `c_student_process`
--

LOCK TABLES `c_student_process` WRITE;
/*!40000 ALTER TABLE `c_student_process` DISABLE KEYS */;
INSERT INTO `c_student_process` VALUES (1,50,50,2,100000);
/*!40000 ALTER TABLE `c_student_process` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `c_teacher`
--

DROP TABLE IF EXISTS `c_teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `c_teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `avatar` varchar(100) DEFAULT NULL COMMENT '教师头像',
  `information` varchar(255) DEFAULT NULL COMMENT '基本信息',
  `experience` varchar(255) DEFAULT NULL COMMENT '教师经历',
  `userid` int(11) NOT NULL COMMENT '所属用户',
  PRIMARY KEY (`id`),
  UNIQUE KEY `useid` (`userid`),
  CONSTRAINT `fk_teacher_user_id` FOREIGN KEY (`userid`) REFERENCES `c_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='老师名单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `c_teacher`
--

LOCK TABLES `c_teacher` WRITE;
/*!40000 ALTER TABLE `c_teacher` DISABLE KEYS */;
INSERT INTO `c_teacher` VALUES (1,'http://localhost:9092/file/59e23e073dec413bbf6f96a4f958a375.jpg','测试基本信息','测试学习经历',1);
/*!40000 ALTER TABLE `c_teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `c_teacher_course`
--

DROP TABLE IF EXISTS `c_teacher_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `c_teacher_course` (
  `course_id` int(11) NOT NULL COMMENT '课程ID',
  `teacher_id` int(11) NOT NULL COMMENT '老师ID',
  KEY `fk_course_id` (`course_id`),
  KEY `fk_teacher_id` (`teacher_id`),
  CONSTRAINT `fk_course_id` FOREIGN KEY (`course_id`) REFERENCES `c_course` (`id`),
  CONSTRAINT `fk_teacher_id` FOREIGN KEY (`teacher_id`) REFERENCES `c_teacher` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='老师课程关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `c_teacher_course`
--

LOCK TABLES `c_teacher_course` WRITE;
/*!40000 ALTER TABLE `c_teacher_course` DISABLE KEYS */;
INSERT INTO `c_teacher_course` VALUES (100004,1);
/*!40000 ALTER TABLE `c_teacher_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `c_type`
--

DROP TABLE IF EXISTS `c_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `c_type` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(15) NOT NULL COMMENT '类型名称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='题目类型表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `c_type`
--

LOCK TABLES `c_type` WRITE;
/*!40000 ALTER TABLE `c_type` DISABLE KEYS */;
INSERT INTO `c_type` VALUES (1,'单选题');
/*!40000 ALTER TABLE `c_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `c_user`
--

DROP TABLE IF EXISTS `c_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `c_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(9) DEFAULT NULL COMMENT '姓名',
  `role` varchar(100) DEFAULT NULL COMMENT '用户角色',
  `nickname` varchar(100) DEFAULT NULL COMMENT '微信昵称',
  `avatar` varchar(255) DEFAULT NULL COMMENT '微信头像',
  `open_id` varchar(100) NOT NULL COMMENT '用户小程序唯一标识',
  `session_key` varchar(100) NOT NULL COMMENT '微信小程序认证密匙',
  `phone` varchar(20) DEFAULT NULL COMMENT '用户电话号码',
  `sex` char(1) DEFAULT NULL COMMENT '性别',
  `register_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `is_real` int(11) NOT NULL DEFAULT '-1' COMMENT '是否已认证，-1：未认证，0：待审核，1：已认证',
  `major_id` int(11) DEFAULT NULL COMMENT '专业ID',
  `password` varchar(100) DEFAULT NULL COMMENT '用户密码',
  PRIMARY KEY (`id`),
  UNIQUE KEY `open_id` (`open_id`),
  UNIQUE KEY `session_key` (`session_key`),
  UNIQUE KEY `name` (`name`),
  UNIQUE KEY `avatar` (`avatar`),
  UNIQUE KEY `phone` (`phone`),
  KEY `major_id` (`major_id`),
  KEY `c_user_FK` (`role`),
  CONSTRAINT `c_user_FK` FOREIGN KEY (`role`) REFERENCES `c_role` (`flag`),
  CONSTRAINT `c_user_ibfk_1` FOREIGN KEY (`major_id`) REFERENCES `c_major` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `c_user`
--

LOCK TABLES `c_user` WRITE;
/*!40000 ALTER TABLE `c_user` DISABLE KEYS */;
INSERT INTO `c_user` VALUES (1,'韦长佳','ROLE_ADMIN','北稚','http://localhost:9092/file/9a44ead34eea402497f08a1ee1ddd2ea.jpeg','odSdV5Qv1TI9uIr4kG7kyM2Im-9M','kpYbDX81wBuaTloudkBGKw==','17623135650','男','2023-11-22 06:44:21',1,1,'$2a$10$0IuOHv5AlY5NGzk3Won.iuIhwCMtSiw/rc07G3MkrXd5w8U/ByCxe');
/*!40000 ALTER TABLE `c_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `c_video`
--

DROP TABLE IF EXISTS `c_video`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `c_video` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `flag` varchar(50) NOT NULL COMMENT '视频获取地址ID',
  `is_real` int(11) DEFAULT '-1' COMMENT '视频是否已经审核，-1代表未审核，1代表已审核',
  `chapter_id` int(11) NOT NULL COMMENT '所属章节',
  PRIMARY KEY (`id`),
  UNIQUE KEY `flag` (`flag`),
  UNIQUE KEY `chapter_id` (`chapter_id`),
  CONSTRAINT `fk_video_chapter_id` FOREIGN KEY (`chapter_id`) REFERENCES `c_chapter` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='章节视频表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `c_video`
--

LOCK TABLES `c_video` WRITE;
/*!40000 ALTER TABLE `c_video` DISABLE KEYS */;
INSERT INTO `c_video` VALUES (1,'80a4fcb4babf71eead5b6723a78f0102',1,100000);
/*!40000 ALTER TABLE `c_video` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'course'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-28  0:10:43

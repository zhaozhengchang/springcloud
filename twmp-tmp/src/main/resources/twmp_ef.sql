/*
Navicat MySQL Data Transfer

Source Server         : dev--Electronic anklet 
Source Server Version : 50615
Source Host           : 192.168.19.145:3306
Source Database       : twmp_ef

Target Server Type    : MYSQL
Target Server Version : 50615
File Encoding         : 65001

Date: 2019-03-05 09:36:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for twmp_bs_person_criminal_ef
-- ----------------------------
DROP TABLE IF EXISTS `twmp_bs_person_criminal_ef`;
CREATE TABLE `twmp_bs_person_criminal_ef` (
  `criminal_id` bigint(20) NOT NULL,
  `person_id` bigint(20) NOT NULL COMMENT '被监控人员id',
  `criminal_type` tinyint(4) NOT NULL COMMENT '违法类型（1财产侵犯？后续询问用户）',
  `criminal_time` datetime DEFAULT NULL COMMENT '违法时间',
  `criminal_site` varchar(1000) DEFAULT NULL COMMENT '违法地点',
  `criminal_act` varchar(1000) DEFAULT NULL COMMENT '违法行为',
  `law_enforcement_agency` varchar(128) DEFAULT NULL COMMENT '执法机关',
  `criminal_number` varchar(128) DEFAULT NULL COMMENT '违法代码',
  `dispose_time` datetime DEFAULT NULL COMMENT '处理时间',
  `dispose_result` varchar(1000) DEFAULT NULL COMMENT '处理结果',
  `creator` varchar(64) NOT NULL,
  `creator_id` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL,
  `updater` varchar(64) DEFAULT NULL,
  `updater_id` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` tinyint(4) NOT NULL DEFAULT '1' COMMENT '删除标志（1未删除 0删除）',
  PRIMARY KEY (`criminal_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of twmp_bs_person_criminal_ef
-- ----------------------------

-- ----------------------------
-- Table structure for twmp_bs_person_ef
-- ----------------------------
DROP TABLE IF EXISTS `twmp_bs_person_ef`;
CREATE TABLE `twmp_bs_person_ef` (
  `person_id` bigint(20) NOT NULL,
  `person_number` varchar(64) NOT NULL COMMENT '人员编号',
  `person_id_card` varchar(64) NOT NULL COMMENT '身份证号',
  `person_name` varchar(128) NOT NULL COMMENT '被监控人姓名',
  `person_former_name` varchar(128) DEFAULT NULL COMMENT '曾用名',
  `person_url` varchar(2000) DEFAULT NULL COMMENT '照片url(多张图片地址，以，分割)',
  `birth_date` datetime NOT NULL COMMENT '出生日期',
  `gender` tinyint(4) NOT NULL COMMENT '性别（1男，2女，3其他）',
  `marital_status` tinyint(4) NOT NULL COMMENT '婚姻状态（1，已婚 2，未婚）',
  `career` varchar(64) DEFAULT NULL COMMENT '职业',
  `phone` varchar(64) DEFAULT NULL COMMENT '联系电话',
  `email` varchar(64) DEFAULT NULL COMMENT '联系邮件',
  `address` varchar(64) DEFAULT NULL COMMENT '联系家庭地址',
  `comment` varchar(1000) DEFAULT NULL COMMENT '备注',
  `department_id` bigint(20) NOT NULL COMMENT '组织机构id',
  `creator` varchar(64) NOT NULL,
  `creator_id` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL,
  `updater` varchar(64) DEFAULT NULL,
  `updater_id` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` tinyint(4) NOT NULL DEFAULT '1' COMMENT '删除标志（1未删除 0删除）',
  PRIMARY KEY (`person_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of twmp_bs_person_ef
-- ----------------------------

-- ----------------------------
-- Table structure for twmp_dev_check_service
-- ----------------------------
DROP TABLE IF EXISTS `twmp_dev_check_service`;
CREATE TABLE `twmp_dev_check_service` (
  `service_id` bigint(20) NOT NULL,
  `service_name` varchar(64) NOT NULL COMMENT '校验服务名',
  `sort_id` bigint(20) NOT NULL COMMENT '设备类别id',
  `comment` varchar(1000) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`service_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of twmp_dev_check_service
-- ----------------------------

-- ----------------------------
-- Table structure for twmp_dev_device
-- ----------------------------
DROP TABLE IF EXISTS `twmp_dev_device`;
CREATE TABLE `twmp_dev_device` (
  `device_id` bigint(20) NOT NULL,
  `device_number` varchar(64) NOT NULL,
  `factory_time` datetime DEFAULT NULL,
  `type_id` bigint(20) NOT NULL,
  `ope_status` tinyint(4) NOT NULL,
  `online_status` tinyint(4) NOT NULL,
  `device_power` varchar(32) DEFAULT NULL,
  `version_id` bigint(20) DEFAULT NULL,
  `attribute_extend` text COMMENT '扩展属性',
  `creator` varchar(64) NOT NULL,
  `creator_id` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL,
  `updater` varchar(64) DEFAULT NULL,
  `updater_id` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` tinyint(4) NOT NULL DEFAULT '1' COMMENT '删除标志（1未删除 0删除）',
  PRIMARY KEY (`device_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of twmp_dev_device
-- ----------------------------

-- ----------------------------
-- Table structure for twmp_dev_inspect_ef
-- ----------------------------
DROP TABLE IF EXISTS `twmp_dev_inspect_ef`;
CREATE TABLE `twmp_dev_inspect_ef` (
  `inspect_id` bigint(20) NOT NULL,
  `device_id` bigint(20) NOT NULL COMMENT '设备id',
  `device_number` varchar(64) NOT NULL COMMENT '设备编号',
  `type_id` bigint(20) NOT NULL,
  `inspect_time` datetime NOT NULL COMMENT '自检时间',
  `function_extend` text NOT NULL COMMENT '扩展属性(json格式)',
  PRIMARY KEY (`inspect_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of twmp_dev_inspect_ef
-- ----------------------------

-- ----------------------------
-- Table structure for twmp_dev_lifecycle
-- ----------------------------
DROP TABLE IF EXISTS `twmp_dev_lifecycle`;
CREATE TABLE `twmp_dev_lifecycle` (
  `lifecycle_id` bigint(20) NOT NULL,
  `device_id` bigint(20) NOT NULL COMMENT '设备id',
  `lifecycle_event_type` tinyint(4) NOT NULL COMMENT '事件类型（1，新建，2修改，3删除，4安装，5拆机，6报废）',
  `title` varchar(64) NOT NULL COMMENT '事件标题',
  `event` varchar(500) NOT NULL COMMENT '事件',
  `creator` varchar(64) NOT NULL,
  `creator_id` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`lifecycle_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of twmp_dev_lifecycle
-- ----------------------------

-- ----------------------------
-- Table structure for twmp_dev_sort
-- ----------------------------
DROP TABLE IF EXISTS `twmp_dev_sort`;
CREATE TABLE `twmp_dev_sort` (
  `sort_id` bigint(20) NOT NULL,
  `sort_name` varchar(64) NOT NULL COMMENT '类别名',
  `thumbnail_url` varchar(128) DEFAULT NULL COMMENT '缩略图url',
  `comment` varchar(1000) DEFAULT NULL COMMENT '类别备注',
  PRIMARY KEY (`sort_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of twmp_dev_sort
-- ----------------------------

-- ----------------------------
-- Table structure for twmp_dev_type
-- ----------------------------
DROP TABLE IF EXISTS `twmp_dev_type`;
CREATE TABLE `twmp_dev_type` (
  `type_id` bigint(20) NOT NULL,
  `sort_id` bigint(20) NOT NULL COMMENT '类别id',
  `type_name` varchar(64) NOT NULL COMMENT '类型名称',
  `photo_url` varchar(2000) DEFAULT NULL COMMENT '安装示意图url',
  `thumbnail_url` varchar(128) DEFAULT NULL COMMENT '缩略图',
  `comment` varchar(1000) DEFAULT NULL COMMENT '设备类型备注',
  `attribute_extend` text COMMENT '扩展属性',
  `check_service_ids` varchar(1000) DEFAULT NULL COMMENT '安装校验服务串，已“，”串起来',
  `file_rule` text COMMENT '文件上传校验规则（一个字段json格式）',
  `function_extend` text COMMENT '自检属性',
  `creator` varchar(64) NOT NULL,
  `creator_id` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL,
  `updater` varchar(64) DEFAULT NULL,
  `updater_id` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` tinyint(4) NOT NULL DEFAULT '1' COMMENT '删除标志（1未删除 0删除）',
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of twmp_dev_type
-- ----------------------------

-- ----------------------------
-- Table structure for twmp_gis_address
-- ----------------------------
DROP TABLE IF EXISTS `twmp_gis_address`;
CREATE TABLE `twmp_gis_address` (
  `address_id` bigint(20) NOT NULL,
  `address_name` varchar(128) NOT NULL COMMENT '名称',
  `point` varchar(64) NOT NULL COMMENT '地址经纬度，格式：经度，纬度11.861,-15.79986',
  `deleted` tinyint(4) NOT NULL DEFAULT '1' COMMENT '删除标志(1未删除0删除)',
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of twmp_gis_address
-- ----------------------------

-- ----------------------------
-- Table structure for twmp_log_login
-- ----------------------------
DROP TABLE IF EXISTS `twmp_log_login`;
CREATE TABLE `twmp_log_login` (
  `login_id` bigint(20) NOT NULL,
  `token` varchar(128) NOT NULL,
  `user_name` varchar(64) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  `role_name` varchar(64) NOT NULL,
  `login_time` datetime NOT NULL,
  `logout_time` datetime DEFAULT NULL,
  `dpartment_id` bigint(20) NOT NULL,
  `department_name` varchar(64) NOT NULL,
  PRIMARY KEY (`login_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of twmp_log_login
-- ----------------------------

-- ----------------------------
-- Table structure for twmp_log_operate
-- ----------------------------
DROP TABLE IF EXISTS `twmp_log_operate`;
CREATE TABLE `twmp_log_operate` (
  `operate_id` bigint(20) NOT NULL,
  `operate_name` varchar(64) NOT NULL COMMENT '操作对象名称',
  `operate_type` tinyint(4) NOT NULL COMMENT '操作类型，1，新增；2，修改；3，删除；4，导入 5审批变更 6撤回 7驳回 8审批通过',
  `operate_object_type` tinyint(4) NOT NULL COMMENT '操作对象类型',
  `operate_time` datetime NOT NULL COMMENT '操作时间',
  `operator` varchar(64) NOT NULL COMMENT '操作人',
  `operator_id` bigint(20) NOT NULL COMMENT '操作人id',
  `comment` varchar(1000) DEFAULT NULL COMMENT '操作备注',
  `department_id` bigint(20) NOT NULL COMMENT '组织机构id',
  `department_name` varchar(64) NOT NULL COMMENT '组织机构名称',
  PRIMARY KEY (`operate_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of twmp_log_operate
-- ----------------------------

-- ----------------------------
-- Table structure for twmp_monitor_task_approval_ef
-- ----------------------------
DROP TABLE IF EXISTS `twmp_monitor_task_approval_ef`;
CREATE TABLE `twmp_monitor_task_approval_ef` (
  `approval_id` bigint(20) NOT NULL,
  `task_id` bigint(20) NOT NULL COMMENT '监控任务id',
  `task_change_id` bigint(20) DEFAULT NULL COMMENT '监控任务参数变更id（只有在审批类型为4时有值）',
  `approval_type` tinyint(4) NOT NULL COMMENT '审批类型(1, 创建监控任务审批 2，结束监控任务审批3，设备变更审批 4，监控任务参数变更审批5，终止任务的审批)',
  `change_reason` varchar(1000) NOT NULL COMMENT '变更原因（设备变更审批需填写）',
  `submit_id` bigint(20) NOT NULL COMMENT '提交人id',
  `submiter` varchar(64) DEFAULT NULL COMMENT '提交人',
  `submit_time` datetime NOT NULL COMMENT '提交时间',
  `approval_user_id` bigint(20) DEFAULT NULL COMMENT '审批人id',
  `approval_user` varchar(64) DEFAULT NULL COMMENT '审批人',
  `approval_time` datetime DEFAULT NULL COMMENT '审批时间',
  `approval_status` tinyint(4) NOT NULL,
  PRIMARY KEY (`approval_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of twmp_monitor_task_approval_ef
-- ----------------------------

-- ----------------------------
-- Table structure for twmp_monitor_task_change_ef
-- ----------------------------
DROP TABLE IF EXISTS `twmp_monitor_task_change_ef`;
CREATE TABLE `twmp_monitor_task_change_ef` (
  `task_change_id` bigint(20) NOT NULL,
  `device_id` bigint(20) NOT NULL COMMENT '绑定的设备id',
  `device_number` varchar(64) NOT NULL COMMENT '设备编号',
  `person_id` bigint(20) NOT NULL,
  `task_code` varchar(64) NOT NULL COMMENT '监控任务编号',
  `task_name` varchar(128) NOT NULL COMMENT '监控任务名称',
  `task_level` tinyint(4) NOT NULL COMMENT '监控任务等级（1、高；2、中；3、低）',
  `task_status` tinyint(4) NOT NULL COMMENT '任务状态（1未开始、2 已开始、3 已完成、4 已结束5已终止）',
  `document_url` varchar(5000) DEFAULT NULL COMMENT '监控文书路径，多个下载路径已“，”串起来',
  `start_time` datetime NOT NULL COMMENT '任务开始时间',
  `end_time` datetime NOT NULL COMMENT '任务结束时间',
  `task_reason` varchar(1000) DEFAULT NULL COMMENT '监控原因',
  `report_location` varchar(1000) DEFAULT NULL COMMENT '定期汇报地点',
  `report_start_time` datetime DEFAULT NULL COMMENT '定期汇报开始时间',
  `report_end_time` datetime DEFAULT NULL COMMENT '定期汇报结束时间',
  `report_time` datetime DEFAULT NULL COMMENT '定期汇报时间',
  `report_time_interval` datetime DEFAULT NULL COMMENT '定期汇报频率（每隔几天，单位天）',
  `department_id` bigint(20) NOT NULL COMMENT '组织机构id',
  `department_name` varchar(64) NOT NULL COMMENT '组织机构名称',
  `creator` varchar(64) NOT NULL,
  `creator_id` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL,
  `updater` varchar(64) DEFAULT NULL,
  `updater_id` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` tinyint(4) NOT NULL DEFAULT '1' COMMENT '删除标志（1未删除 0删除）',
  PRIMARY KEY (`task_change_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of twmp_monitor_task_change_ef
-- ----------------------------

-- ----------------------------
-- Table structure for twmp_monitor_task_criminal_ef
-- ----------------------------
DROP TABLE IF EXISTS `twmp_monitor_task_criminal_ef`;
CREATE TABLE `twmp_monitor_task_criminal_ef` (
  `task_criminal_id` bigint(20) NOT NULL,
  `task_id` bigint(20) NOT NULL COMMENT '监控任务id',
  `task_change_id` bigint(20) DEFAULT NULL,
  `criminal_id` bigint(20) NOT NULL COMMENT '犯罪记录id',
  PRIMARY KEY (`task_criminal_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of twmp_monitor_task_criminal_ef
-- ----------------------------

-- ----------------------------
-- Table structure for twmp_monitor_task_ef
-- ----------------------------
DROP TABLE IF EXISTS `twmp_monitor_task_ef`;
CREATE TABLE `twmp_monitor_task_ef` (
  `task_id` bigint(20) NOT NULL,
  `device_id` bigint(20) NOT NULL COMMENT '绑定的设备id',
  `device_number` varchar(64) NOT NULL COMMENT '设备编号',
  `person_id` bigint(20) NOT NULL,
  `task_code` varchar(64) NOT NULL COMMENT '监控任务编号',
  `task_name` varchar(128) NOT NULL COMMENT '监控任务名称',
  `task_level` tinyint(4) NOT NULL COMMENT '监控任务等级（1、高；2、中；3、低）',
  `task_status` tinyint(4) NOT NULL COMMENT '任务状态（1未开始、2 已开始、3 已完成、4 已结束5已终止）',
  `document_url` varchar(5000) DEFAULT NULL COMMENT '监控文书路径，多个下载路径已“，”串起来',
  `start_time` datetime NOT NULL COMMENT '任务开始时间',
  `end_time` datetime NOT NULL COMMENT '任务结束时间',
  `task_reason` varchar(1000) DEFAULT NULL COMMENT '监控原因',
  `report_location` varchar(1000) DEFAULT NULL COMMENT '定期汇报地点',
  `report_start_time` datetime DEFAULT NULL COMMENT '定期汇报开始时间',
  `report_end_time` datetime DEFAULT NULL COMMENT '定期汇报结束时间',
  `report_time` datetime DEFAULT NULL COMMENT '定期汇报时间',
  `report_time_interval` datetime DEFAULT NULL COMMENT '定期汇报频率（每隔几天，单位天）',
  `department_id` bigint(20) NOT NULL COMMENT '组织机构id',
  `department_name` varchar(64) NOT NULL COMMENT '组织机构名称',
  `creator` varchar(64) NOT NULL,
  `creator_id` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL,
  `updater` varchar(64) DEFAULT NULL,
  `updater_id` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` tinyint(4) NOT NULL DEFAULT '1' COMMENT '删除标志（1未删除 0删除）',
  PRIMARY KEY (`task_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of twmp_monitor_task_ef
-- ----------------------------

-- ----------------------------
-- Table structure for twmp_monitor_task_fence_ef
-- ----------------------------
DROP TABLE IF EXISTS `twmp_monitor_task_fence_ef`;
CREATE TABLE `twmp_monitor_task_fence_ef` (
  `fence_id` bigint(20) NOT NULL,
  `task_id` bigint(20) NOT NULL COMMENT '监控任务id',
  `task_change_id` bigint(20) DEFAULT NULL COMMENT '监控任务参数变更id',
  `fence_status` tinyint(4) NOT NULL COMMENT '启动状态(1,启动 2，停用)',
  `fence_type` tinyint(4) NOT NULL COMMENT '围栏类型(1,禁止入内 2，禁止外出)',
  `times` datetime NOT NULL COMMENT '围栏时间段',
  `comment` varchar(1000) DEFAULT NULL COMMENT '围栏描述',
  `creator` varchar(64) NOT NULL,
  `creator_id` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL,
  `updater` varchar(64) DEFAULT NULL,
  `updater_id` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` tinyint(4) NOT NULL DEFAULT '1' COMMENT '删除标志（1未删除 0删除）',
  PRIMARY KEY (`fence_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of twmp_monitor_task_fence_ef
-- ----------------------------

-- ----------------------------
-- Table structure for twmp_monitor_task_fence_space_ef
-- ----------------------------
DROP TABLE IF EXISTS `twmp_monitor_task_fence_space_ef`;
CREATE TABLE `twmp_monitor_task_fence_space_ef` (
  `shape_id` bigint(20) NOT NULL,
  `fence_id` bigint(20) NOT NULL COMMENT '监控任务id',
  `space` varchar(4000) NOT NULL COMMENT '围栏范围, xy之间逗号分隔，坐标时间空格分隔。用如下形式表示x,y x,y x,y x,y…….',
  PRIMARY KEY (`shape_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of twmp_monitor_task_fence_space_ef
-- ----------------------------

-- ----------------------------
-- Table structure for twmp_monitor_task_outside_record_ef
-- ----------------------------
DROP TABLE IF EXISTS `twmp_monitor_task_outside_record_ef`;
CREATE TABLE `twmp_monitor_task_outside_record_ef` (
  `task_outside_record_id` bigint(20) NOT NULL,
  `task_id` bigint(20) NOT NULL COMMENT '监控任务id',
  `task_outside_record_type` tinyint(4) NOT NULL COMMENT '类型（1定期汇报，2社区服务，3教育学习，4违规记录，5其他）',
  `event_address` varchar(1000) NOT NULL COMMENT '事件发生地址',
  `event_time` datetime NOT NULL COMMENT '事件发生事件',
  `event_comment` varchar(2000) NOT NULL COMMENT '事件情况描述',
  PRIMARY KEY (`task_outside_record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of twmp_monitor_task_outside_record_ef
-- ----------------------------

-- ----------------------------
-- Table structure for twmp_monitor_task_team_ef
-- ----------------------------
DROP TABLE IF EXISTS `twmp_monitor_task_team_ef`;
CREATE TABLE `twmp_monitor_task_team_ef` (
  `team_id` int(11) NOT NULL,
  `task_id` int(11) NOT NULL COMMENT '监控任务id',
  `task_change_id` int(11) DEFAULT NULL COMMENT '监控任务参数变更id',
  `name` varchar(255) NOT NULL COMMENT '姓名',
  `photo_url` varchar(255) DEFAULT NULL COMMENT '头像照片url',
  `relation_type` varchar(255) NOT NULL COMMENT '与被监控人的关系(1父母，2子女，3亲戚，4朋友，5社区主任，6邻居，7其他)',
  `phone` varchar(255) NOT NULL COMMENT '联系电话',
  `email` varchar(255) NOT NULL COMMENT '联系邮件',
  `address` varchar(255) DEFAULT NULL COMMENT '联系地址',
  `creator` varchar(64) NOT NULL,
  `creator_id` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL,
  `updater` varchar(64) DEFAULT NULL,
  `updater_id` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` tinyint(4) NOT NULL DEFAULT '1' COMMENT '删除标志（1未删除 0删除）',
  PRIMARY KEY (`team_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of twmp_monitor_task_team_ef
-- ----------------------------

-- ----------------------------
-- Table structure for twmp_ope_change
-- ----------------------------
DROP TABLE IF EXISTS `twmp_ope_change`;
CREATE TABLE `twmp_ope_change` (
  `change_id` bigint(20) NOT NULL,
  `old_device_id` bigint(20) NOT NULL COMMENT '旧的设备id',
  `old_device_number` varchar(64) NOT NULL COMMENT '旧的设备编号',
  `device_id` bigint(20) NOT NULL,
  `device_number` varchar(64) NOT NULL,
  `person_id` bigint(20) NOT NULL,
  `task_id` bigint(20) NOT NULL,
  `department_id` bigint(20) NOT NULL,
  `department_name` varchar(64) NOT NULL,
  `file_url` varchar(128) DEFAULT NULL COMMENT '变更上传文件包',
  `function_extend` text COMMENT '校验通过功能扩展属性(json格式)',
  `ops` varchar(64) NOT NULL COMMENT '运维人员',
  `ops_id` bigint(20) NOT NULL COMMENT '运维人员id',
  `change_time` datetime NOT NULL COMMENT '变更时间',
  PRIMARY KEY (`change_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of twmp_ope_change
-- ----------------------------

-- ----------------------------
-- Table structure for twmp_ope_dismantle
-- ----------------------------
DROP TABLE IF EXISTS `twmp_ope_dismantle`;
CREATE TABLE `twmp_ope_dismantle` (
  `dismantle_id` bigint(20) NOT NULL,
  `person_id` bigint(20) NOT NULL COMMENT '被监控人员id',
  `task_id` bigint(20) NOT NULL COMMENT '监控任务id',
  `device_id` bigint(20) NOT NULL,
  `device_number` varchar(64) NOT NULL,
  `department_id` bigint(20) NOT NULL,
  `department_name` varchar(64) NOT NULL,
  `file_url` varchar(128) DEFAULT NULL COMMENT '拆机上传文件包',
  `ops` varchar(64) NOT NULL COMMENT '运维人员',
  `ops_id` bigint(20) NOT NULL COMMENT '运维人员id',
  `dismantle_time` datetime NOT NULL COMMENT '拆机时间',
  PRIMARY KEY (`dismantle_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of twmp_ope_dismantle
-- ----------------------------

-- ----------------------------
-- Table structure for twmp_ope_install
-- ----------------------------
DROP TABLE IF EXISTS `twmp_ope_install`;
CREATE TABLE `twmp_ope_install` (
  `install_id` bigint(20) NOT NULL,
  `device_id` bigint(20) NOT NULL,
  `device_number` varchar(64) NOT NULL,
  `person_id` bigint(20) NOT NULL,
  `task_id` bigint(20) NOT NULL,
  `department_id` bigint(20) NOT NULL,
  `department_name` varchar(64) NOT NULL,
  `file_url` varchar(128) DEFAULT NULL COMMENT '安装上传文件包',
  `function_extend` text COMMENT '校验通过功能扩展属性(json格式)',
  `ops` varchar(64) NOT NULL COMMENT '运维人员',
  `ops_id` bigint(20) NOT NULL COMMENT '运维人员id',
  `install_time` datetime NOT NULL COMMENT '安装时间',
  PRIMARY KEY (`install_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of twmp_ope_install
-- ----------------------------

-- ----------------------------
-- Table structure for twmp_sm_alarm_dispose_ef
-- ----------------------------
DROP TABLE IF EXISTS `twmp_sm_alarm_dispose_ef`;
CREATE TABLE `twmp_sm_alarm_dispose_ef` (
  `alarm_dispose_id` bigint(20) NOT NULL,
  `alarm_id` bigint(20) NOT NULL COMMENT '告警信息id',
  `dispose_type` tinyint(4) NOT NULL COMMENT 'dispose_type',
  `dispose_time` datetime DEFAULT NULL COMMENT '处置时间',
  `user_id` bigint(20) NOT NULL COMMENT '处置该条报警信息的用户id',
  `user_name` varchar(64) NOT NULL COMMENT '处置该条报警信息的账号',
  `comment` varchar(1000) DEFAULT NULL COMMENT '备注说明',
  PRIMARY KEY (`alarm_dispose_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of twmp_sm_alarm_dispose_ef
-- ----------------------------

-- ----------------------------
-- Table structure for twmp_sm_alarm_ef
-- ----------------------------
DROP TABLE IF EXISTS `twmp_sm_alarm_ef`;
CREATE TABLE `twmp_sm_alarm_ef` (
  `alarm_id` bigint(20) NOT NULL,
  `task_id` bigint(20) NOT NULL COMMENT '监控任务id',
  `alarm_number` varchar(64) DEFAULT NULL COMMENT '告警编号',
  `last_dispose_time` datetime DEFAULT NULL,
  `device_number` varchar(64) NOT NULL COMMENT '设备编号',
  `alarm_type` tinyint(4) NOT NULL COMMENT '告警类型（1暴力拆卸告警、2越界告警、3低电量告警、4设备离线告警、5手动创建）',
  `alarm_time` datetime NOT NULL COMMENT '告警时间',
  `alarm_status` tinyint(4) NOT NULL COMMENT '告警状态（1：未分配；2：待处置；3：已处置）',
  `incident_appeal_time` datetime DEFAULT NULL COMMENT '接警开始时间',
  `start_time` datetime DEFAULT NULL COMMENT '处警开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '处警结束时间',
  `incident_disposal_person_name` varchar(64) DEFAULT NULL COMMENT '处置人',
  `incident_address` varchar(2000) DEFAULT NULL COMMENT '事发地',
  `alarm_flag` tinyint(4) DEFAULT NULL COMMENT '真假警（0假警情，1真警情）',
  `tag_value` varchar(256) DEFAULT NULL COMMENT '报警相关值',
  `department_id` bigint(20) NOT NULL COMMENT '组织机构id',
  `department_name` varchar(64) NOT NULL COMMENT '组织机构名称',
  `address` varchar(256) DEFAULT NULL COMMENT '告警地址',
  `longitude` varchar(64) DEFAULT NULL COMMENT '经度',
  `latitude` varchar(64) DEFAULT NULL COMMENT '纬度',
  `speed` varchar(64) DEFAULT NULL COMMENT '速度',
  `direction` varchar(64) DEFAULT NULL COMMENT '方向',
  `gps_time` datetime DEFAULT NULL COMMENT 'gps时间',
  `used_user_id` varchar(2000) DEFAULT NULL COMMENT '用逗号串联所有的曾经分配后不能再把该条消息分配到该报警信息的用户id eg： 111,222,333 ',
  `handle_user_id` bigint(20) DEFAULT NULL COMMENT '当前处理该条报警信息的用户id',
  `handle_user` varchar(64) DEFAULT NULL COMMENT '当前处理该条报警信息的用户名',
  `creator` varchar(64) NOT NULL,
  `creator_id` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL,
  `updater` varchar(64) DEFAULT NULL,
  `updater_id` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` tinyint(4) NOT NULL DEFAULT '1' COMMENT '删除标志（1未删除 0删除）',
  PRIMARY KEY (`alarm_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of twmp_sm_alarm_ef
-- ----------------------------

-- ----------------------------
-- Table structure for twmp_sm_device_alarm_person_report
-- ----------------------------
DROP TABLE IF EXISTS `twmp_sm_device_alarm_person_report`;
CREATE TABLE `twmp_sm_device_alarm_person_report` (
  `report_id` bigint(20) NOT NULL,
  `person_id` bigint(20) NOT NULL COMMENT '被监控人员id',
  `num` bigint(20) NOT NULL COMMENT '告警信息数量',
  `department_id` bigint(20) NOT NULL COMMENT '组织机构id',
  `alarm_time` datetime NOT NULL COMMENT '告警时间（按天分隔）',
  PRIMARY KEY (`report_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of twmp_sm_device_alarm_person_report
-- ----------------------------

-- ----------------------------
-- Table structure for twmp_sm_device_alarm_report
-- ----------------------------
DROP TABLE IF EXISTS `twmp_sm_device_alarm_report`;
CREATE TABLE `twmp_sm_device_alarm_report` (
  `report_id` bigint(20) NOT NULL,
  `num` bigint(20) NOT NULL COMMENT '告警信息数量',
  `department_id` bigint(20) NOT NULL COMMENT '组织机构id',
  `alarm_type` tinyint(4) NOT NULL COMMENT '告警类型',
  `alarm_time` datetime NOT NULL COMMENT '告警时间（按天分隔）',
  PRIMARY KEY (`report_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of twmp_sm_device_alarm_report
-- ----------------------------

-- ----------------------------
-- Table structure for twmp_sm_device_used_report
-- ----------------------------
DROP TABLE IF EXISTS `twmp_sm_device_used_report`;
CREATE TABLE `twmp_sm_device_used_report` (
  `report_id` bigint(20) NOT NULL,
  `utilization_rate` double NOT NULL COMMENT '使用率0-100',
  `department_id` bigint(20) NOT NULL COMMENT '组织机构id',
  `utility_time` datetime NOT NULL COMMENT '使用时间',
  PRIMARY KEY (`report_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of twmp_sm_device_used_report
-- ----------------------------

-- ----------------------------
-- Table structure for twmp_sys_authority
-- ----------------------------
DROP TABLE IF EXISTS `twmp_sys_authority`;
CREATE TABLE `twmp_sys_authority` (
  `authority_id` bigint(20) NOT NULL,
  `sort` int(11) NOT NULL COMMENT '排序',
  `authority_type` tinyint(4) NOT NULL COMMENT '权限类型 1菜单 2按钮',
  `authority_name` varchar(64) NOT NULL COMMENT '权限名称（用于国际化）',
  `zh_name` varchar(64) NOT NULL COMMENT '权限中文名',
  `node` varchar(64) NOT NULL COMMENT '权限代码',
  `parent_id` bigint(20) NOT NULL COMMENT '父id',
  `authority_url` varchar(256) NOT NULL COMMENT '权限的URL',
  `icon` varchar(64) NOT NULL COMMENT '权限图标',
  `comment` varchar(1000) DEFAULT NULL COMMENT '备注',
  `deleted` tinyint(4) NOT NULL,
  PRIMARY KEY (`authority_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of twmp_sys_authority
-- ----------------------------
INSERT INTO `twmp_sys_authority` VALUES ('1', '1', '1', 'monitor_index', '监控', '1', '0', '/monitor', 'icon-Vehicle_monitoring', null, '1');
INSERT INTO `twmp_sys_authority` VALUES ('2', '2', '1', 'information_index', '信息管理', '2', '0', '/information', 'icon-Vehicle_monitoring', null, '1');

-- ----------------------------
-- Table structure for twmp_sys_department
-- ----------------------------
DROP TABLE IF EXISTS `twmp_sys_department`;
CREATE TABLE `twmp_sys_department` (
  `department_id` bigint(20) NOT NULL,
  `department_name` varchar(64) NOT NULL COMMENT '组织机构名称',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父级组织机构id',
  `contacts` varchar(64) DEFAULT NULL COMMENT '联系人',
  `phone` varchar(64) DEFAULT NULL COMMENT '联系电话',
  `fax` varchar(64) DEFAULT NULL COMMENT '传真',
  `address` varchar(1000) DEFAULT NULL COMMENT '地址',
  `map_center` varchar(128) DEFAULT NULL COMMENT '组织机构地图中心区域',
  `creator` varchar(64) NOT NULL,
  `creator_id` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL,
  `updater` varchar(64) DEFAULT NULL,
  `updater_id` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` tinyint(4) NOT NULL DEFAULT '1' COMMENT '删除标志（1未删除 0删除）',
  PRIMARY KEY (`department_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of twmp_sys_department
-- ----------------------------
INSERT INTO `twmp_sys_department` VALUES ('1', 'Root organization', '0', null, null, null, null, null, 'system', '1', '2019-03-01 17:51:56', null, null, null, '1');

-- ----------------------------
-- Table structure for twmp_sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `twmp_sys_dict`;
CREATE TABLE `twmp_sys_dict` (
  `dict_id` bigint(20) NOT NULL,
  `dict_type` varchar(32) NOT NULL COMMENT 'dic_type',
  `dict_name` varchar(64) DEFAULT NULL COMMENT '字典名称',
  `dict_name_code` varchar(64) DEFAULT NULL COMMENT '字典名称国际化（如果有此值，则使用此值）',
  `dict_value` tinyint(4) NOT NULL COMMENT '字典值(-127-127)',
  `comment` varchar(1000) DEFAULT NULL COMMENT '字典备注',
  `change_enable` tinyint(255) NOT NULL DEFAULT '1' COMMENT '1可以变更，2不可以变更',
  `creator` varchar(64) NOT NULL,
  `creator_id` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL,
  `updater` varchar(64) DEFAULT NULL,
  `updater_id` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` tinyint(4) NOT NULL DEFAULT '1' COMMENT '删除标志（1未删除 0删除）',
  PRIMARY KEY (`dict_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of twmp_sys_dict
-- ----------------------------
INSERT INTO `twmp_sys_dict` VALUES ('1', 'marital_status', '已婚', null, '1', null, '2', 'system', '0', '2019-02-28 14:39:21', null, null, null, '1');
INSERT INTO `twmp_sys_dict` VALUES ('2', 'marital_status', '未婚', null, '2', null, '2', 'system', '0', '2019-02-28 14:39:44', null, null, null, '1');
INSERT INTO `twmp_sys_dict` VALUES ('3', 'gender', '男', null, '1', null, '2', 'system', '0', '2019-02-28 14:40:05', null, null, null, '1');
INSERT INTO `twmp_sys_dict` VALUES ('4', 'gender', '女', null, '2', null, '2', 'system', '0', '2019-02-28 14:40:27', null, null, null, '1');
INSERT INTO `twmp_sys_dict` VALUES ('5', 'gender', '其他', null, '3', null, '2', 'system', '0', '2019-02-28 14:40:54', null, null, null, '1');
INSERT INTO `twmp_sys_dict` VALUES ('6', 'relation_type', '父母', null, '1', null, '2', 'system', '0', '2019-02-28 14:44:54', null, null, null, '1');
INSERT INTO `twmp_sys_dict` VALUES ('7', 'relation_type', '子女', null, '2', null, '2', 'system', '0', '2019-02-28 14:45:20', null, null, null, '1');
INSERT INTO `twmp_sys_dict` VALUES ('8', 'relation_type', '亲戚', null, '3', null, '2', 'system', '0', '2019-02-28 14:45:45', null, null, null, '1');
INSERT INTO `twmp_sys_dict` VALUES ('9', 'relation_type', '朋友', null, '4', null, '2', 'system', '0', '2019-02-28 14:48:21', null, null, null, '1');
INSERT INTO `twmp_sys_dict` VALUES ('10', 'relation_type', '社区主任', null, '5', null, '2', 'system', '0', '2019-02-28 14:48:37', null, null, null, '1');
INSERT INTO `twmp_sys_dict` VALUES ('11', 'relation_type', '邻居', null, '6', null, '2', 'system', '0', '2019-02-28 14:49:23', null, null, null, '1');
INSERT INTO `twmp_sys_dict` VALUES ('12', 'relation_type', '其他', null, '7', null, '2', 'system', '0', '2019-02-28 14:50:13', null, null, null, '1');
INSERT INTO `twmp_sys_dict` VALUES ('13', 'task_outside_record_type', '定期汇报', null, '1', null, '2', 'system', '0', '2019-02-28 14:51:09', null, null, null, '1');
INSERT INTO `twmp_sys_dict` VALUES ('14', 'task_outside_record_type', '社区服务', null, '2', null, '2', 'system', '0', '2019-02-28 14:51:35', null, null, null, '1');
INSERT INTO `twmp_sys_dict` VALUES ('15', 'task_outside_record_type', '教育学习', null, '3', null, '2', 'system', '0', '2019-02-28 14:51:52', null, null, null, '1');
INSERT INTO `twmp_sys_dict` VALUES ('16', 'task_outside_record_type', '违规记录', null, '4', null, '2', 'system', '0', '2019-02-28 14:52:08', null, null, null, '1');
INSERT INTO `twmp_sys_dict` VALUES ('17', 'task_outside_record_type', '其他', null, '5', null, '2', 'system', '0', '2019-02-28 14:52:28', null, null, null, '1');
INSERT INTO `twmp_sys_dict` VALUES ('18', 'criminal_type', '财产侵犯', null, '6', null, '2', 'system', '0', '2019-02-28 14:52:57', null, null, null, '1');

-- ----------------------------
-- Table structure for twmp_sys_file
-- ----------------------------
DROP TABLE IF EXISTS `twmp_sys_file`;
CREATE TABLE `twmp_sys_file` (
  `file_id` bigint(20) NOT NULL,
  `file_type` tinyint(4) NOT NULL COMMENT '文件类型',
  `link_id` bigint(20) DEFAULT NULL COMMENT '关联的id',
  `link_table_name` varchar(64) DEFAULT NULL COMMENT '关联表的名',
  `link_table_column` varchar(64) DEFAULT NULL COMMENT '关联表的关联字段',
  `file_name` varchar(64) NOT NULL COMMENT '文件名',
  `file_path` varchar(128) NOT NULL COMMENT '文件存放地址',
  `creator` varchar(64) NOT NULL,
  `creator_id` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL,
  `deleted` tinyint(4) NOT NULL DEFAULT '1' COMMENT '删除标志（1未删除 0删除）',
  PRIMARY KEY (`file_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of twmp_sys_file
-- ----------------------------

-- ----------------------------
-- Table structure for twmp_sys_message
-- ----------------------------
DROP TABLE IF EXISTS `twmp_sys_message`;
CREATE TABLE `twmp_sys_message` (
  `message_id` bigint(20) NOT NULL,
  `business_id` bigint(20) NOT NULL COMMENT '业务id',
  `message_comment` varchar(1000) NOT NULL COMMENT '消息内容',
  `message_type` tinyint(255) NOT NULL COMMENT '消息类型',
  `message_sub_type` tinyint(255) NOT NULL COMMENT '消息子类型',
  `message_status` tinyint(255) NOT NULL COMMENT '消息状态',
  `department_id` bigint(20) NOT NULL COMMENT '组织机构id',
  `message_time` datetime NOT NULL COMMENT '消息时间',
  `reader` varchar(64) DEFAULT NULL COMMENT '已读人',
  `reader_id` bigint(20) DEFAULT NULL COMMENT '已读人id',
  `read_time` datetime DEFAULT NULL COMMENT '已读时间',
  PRIMARY KEY (`message_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of twmp_sys_message
-- ----------------------------

-- ----------------------------
-- Table structure for twmp_sys_paperwork
-- ----------------------------
DROP TABLE IF EXISTS `twmp_sys_paperwork`;
CREATE TABLE `twmp_sys_paperwork` (
  `paperwork_id` bigint(20) NOT NULL,
  `paperwork_name` varchar(64) NOT NULL,
  `paperwork_url` varchar(5000) NOT NULL,
  `comment` varchar(1000) DEFAULT NULL,
  `creator` varchar(64) NOT NULL,
  `create_time` datetime NOT NULL,
  `creator_id` bigint(20) NOT NULL,
  `deleted` tinyint(4) NOT NULL DEFAULT '1' COMMENT '删除标志(1未删除0删除)',
  PRIMARY KEY (`paperwork_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of twmp_sys_paperwork
-- ----------------------------

-- ----------------------------
-- Table structure for twmp_sys_parameter
-- ----------------------------
DROP TABLE IF EXISTS `twmp_sys_parameter`;
CREATE TABLE `twmp_sys_parameter` (
  `parameter_id` bigint(20) NOT NULL,
  `parameter_name` varchar(64) NOT NULL COMMENT '参数名称',
  `parameter_range` varchar(64) NOT NULL COMMENT '参数范围',
  `parameter_unit` varchar(64) NOT NULL COMMENT '参数单位',
  `parameter_value` varchar(64) NOT NULL COMMENT '参数取值',
  `deleted` tinyint(255) NOT NULL COMMENT '删除标志(1未删除0删除)',
  PRIMARY KEY (`parameter_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of twmp_sys_parameter
-- ----------------------------

-- ----------------------------
-- Table structure for twmp_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `twmp_sys_role`;
CREATE TABLE `twmp_sys_role` (
  `role_id` bigint(20) NOT NULL,
  `role_name` varchar(64) NOT NULL COMMENT '角色名称',
  `creator` varchar(64) NOT NULL,
  `creator_id` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL,
  `updater` varchar(64) DEFAULT NULL,
  `updater_id` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` tinyint(4) NOT NULL DEFAULT '1' COMMENT '删除标志（1未删除 0删除）',
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `u_role_name` (`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of twmp_sys_role
-- ----------------------------
INSERT INTO `twmp_sys_role` VALUES ('1', 'Super Administrator', 'system', '0', '2019-03-01 17:51:17', null, null, null, '1');

-- ----------------------------
-- Table structure for twmp_sys_role_authority
-- ----------------------------
DROP TABLE IF EXISTS `twmp_sys_role_authority`;
CREATE TABLE `twmp_sys_role_authority` (
  `id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  `authority_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of twmp_sys_role_authority
-- ----------------------------

-- ----------------------------
-- Table structure for twmp_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `twmp_sys_user`;
CREATE TABLE `twmp_sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(64) NOT NULL COMMENT '帐号',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `photo_url` varchar(128) DEFAULT NULL COMMENT '头像路径',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `department_id` bigint(20) NOT NULL COMMENT '组织机构id',
  `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(64) DEFAULT NULL COMMENT '电话',
  `fax` varchar(64) DEFAULT NULL COMMENT '传真',
  `comment` varchar(1000) DEFAULT NULL COMMENT '备注',
  `topic` tinyint(4) DEFAULT NULL COMMENT '主题',
  `language` varchar(32) DEFAULT NULL COMMENT '语言',
  `map_center` varchar(128) DEFAULT NULL COMMENT '用户地图中心区域',
  `login_status` tinyint(4) DEFAULT NULL COMMENT '登录状态 1登录 2退出',
  `creator` varchar(64) NOT NULL,
  `creator_id` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL,
  `updater` varchar(64) DEFAULT NULL,
  `updater_id` bigint(20) DEFAULT NULL,
  `update_time` date DEFAULT NULL,
  `deleted` tinyint(4) NOT NULL DEFAULT '1' COMMENT '删除标志（1未删除 0删除）',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `u_user_name` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=561619505250305 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of twmp_sys_user
-- ----------------------------
INSERT INTO `twmp_sys_user` VALUES ('1', 'superAdmin', 'f6fdffe48c908deb0f4c3bd36c032e72', null, '1', '1', null, null, null, null, null, null, null, null, 'system', '0', '2019-03-01 17:50:14', null, null, null, '1');
INSERT INTO `twmp_sys_user` VALUES ('2', 'hello2222', 'Q7C+K9NlFKs=', null, '1', '1', null, null, null, null, null, null, null, null, 'system', '0', '2019-03-01 17:58:57', null, null, null, '1');
INSERT INTO `twmp_sys_user` VALUES ('561235388088320', 'zzc', 'Q7C+K9NlFKs=', null, '1', '1', null, null, null, null, null, null, null, null, 'hello2222', '2', '2019-03-04 11:18:10', null, null, null, '1');
INSERT INTO `twmp_sys_user` VALUES ('561615756034048', 'zzc2', 'Q7C+K9NlFKs=', null, '1', '1', null, null, null, null, null, null, null, null, 'zzc', '561235388088320', '2019-03-04 17:45:05', null, null, null, '1');
INSERT INTO `twmp_sys_user` VALUES ('561619505250304', 'zzc3', 'Q7C+K9NlFKs=', null, '1', '1', null, null, null, null, null, null, null, null, 'zzc', '561235388088320', '2019-03-04 17:48:54', null, null, null, '1');

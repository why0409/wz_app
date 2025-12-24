CREATE TABLE `assessment_activity_report` (
  `report_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '报告ID',
  `activity_id` bigint(20) NOT NULL COMMENT '活动ID',
  `stats_json` longtext COMMENT '统计结果JSON',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`report_id`),
  UNIQUE KEY `uk_activity_id` (`activity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='测评活动报告表';

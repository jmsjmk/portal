-- ----------------------------
-- Table structure for `template`
-- ----------------------------
DROP TABLE IF EXISTS `m_group`;
CREATE TABLE `m_group` (
  `id`         INT(11)      NOT NULL AUTO_INCREMENT,
  `name`       VARCHAR(100) NOT NULL
  COMMENT '用户组名',
  `note`       VARCHAR(200) COMMENT '用户组说明',
  `status`     INT(3)       NOT NULL DEFAULT '0'
  COMMENT '状态,默认正常',
  `created_at` DATETIME     NOT NULL
  COMMENT '创建时间',
  `created_by` VARCHAR(100) NOT NULL
  COMMENT '创建人',
  `changed_at` DATETIME              DEFAULT NULL
  COMMENT '修改时间',
  `changed_by` VARCHAR(100)          DEFAULT NULL
  COMMENT '修改人',
  `version`    INT(10)      NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNI_ID` (`id`) USING BTREE,
  UNIQUE KEY `UNI_RNAME` (`name`) USING BTREE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8;
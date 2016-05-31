-- ----------------------------
-- Table structure for `template`
-- ----------------------------
DROP TABLE IF EXISTS `m_permission`;
CREATE TABLE `m_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '权限名',
  `note` varchar(200) COMMENT '权限说明',
  `code` varchar(200) COMMENT '权限结构码',
  `unique_key` varchar(200) COMMENT '权限唯一key',
  `parent_id` int(11) DEFAULT 0 COMMENT '上一级权限Id',
  `link` varchar(200) COMMENT '权限对应的功能连接',
  `type` int(3) NOT NULL DEFAULT 0 COMMENT '权限类型,0-菜单,1-按钮',
  `icon` varchar(200) COMMENT '权限个性化ICON',
  `weight` int(3) COMMENT '权重,排序使用',
  `status` int(3) NOT NULL DEFAULT '0' COMMENT '状态,默认正常',
  `created_at` datetime NOT NULL COMMENT '创建时间',
  `created_by` varchar(100) NOT NULL COMMENT '创建人',
  `changed_at` datetime DEFAULT NULL COMMENT '修改时间',
  `changed_by` varchar(100) DEFAULT NULL COMMENT '修改人',
  `version` int(10) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNI_ID` (`id`) USING BTREE,
  UNIQUE KEY `UNI_CODE` (`code`) USING BTREE,
  UNIQUE KEY `UNI_KEY` (`unique_key`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
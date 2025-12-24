ALTER TABLE `assessment_cadre` ADD COLUMN `id_card` VARCHAR(20) DEFAULT NULL COMMENT '身份证号' AFTER `cadre_id`;
ALTER TABLE `assessment_cadre` ADD UNIQUE INDEX `uk_id_card` (`id_card`);

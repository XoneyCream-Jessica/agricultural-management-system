INSERT INTO `sys_dict` (`id`, `dict_name`, `dict_code`, `desc`, `del_flag`, `create_date`, `create_user`, `update_date`, `update_user`) VALUES ('98949e7c26925862d76b3daab8ee7813', '传感器类型', 'sensor_type', '传感器类型', '0', '2022-05-12 10:19:32', '1', NULL, NULL);
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `label`, `val`, `desc`, `sort`, `del_flag`) VALUES ('9527fec51679786b1cc65607d213fd48', '98949e7c26925862d76b3daab8ee7813', '害虫识别传感器', 'PestRecognition', '害虫识别传感器', '1', '0');


INSERT INTO `sys_menu` VALUES ('ff4e712a56cc27b70dfd94cfcb6961a1', 'f876a0ecab0261c796617e13e5762f6e', '传感器列表', 'el-icon-view', 'SENSOR_LIST', '/bus/sensor/list.html', '0', 5);
INSERT INTO `sys_menu_role` VALUES ('d318828b2e695bb0312486c9a6a68e81', '1', 'ff4e712a56cc27b70dfd94cfcb6961a1');


update sys_dict_item set val = 'PEST_RECOGNITION' where id = '9527fec51679786b1cc65607d213fd48';
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `label`, `val`, `desc`, `sort`, `del_flag`) VALUES ('9527fec51679786b1cc65607d213f148', '98949e7c26925862d76b3daab8ee7813', '湿度传感器', 'HUMITURE', '湿度传感器', '2', '0');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `label`, `val`, `desc`, `sort`, `del_flag`) VALUES ('9527fec51679786b1ac65607d213f148', '98949e7c26925862d76b3daab8ee7813', '温度传感器', 'TEMPERATURE', '温度传感器', '3', '0');



INSERT INTO `sys_user` (`id`, `username`, `password`, `name`, `phone`, `sex`, `create_date`, `create_user`, `update_date`, `update_user`) VALUES ('1c3a5eb4ccfe3d636743ae56e2286f3b', '系统', '$2a$10$M99mzavXhA.N8Fj4QmMrWe03IhijU5KQRwJc.KC24tcOM4KR3LU.a', '系统', '0', '01', '2022-05-16 14:10:10', '1', NULL, NULL);
INSERT INTO `sys_user_role` (`id`, `role_id`, `user_id`) VALUES ('bef1a753970e46b9bc3df6a7be01b5ee', '1', '1c3a5eb4ccfe3d636743ae56e2286f3b');


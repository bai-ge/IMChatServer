-- 聊天记录表 chat_message
DROP TABLE if exists chat_message;
CREATE TABLE chat_message (
  id int auto_increment,
  sender_id int, -- 发送者ID
  receive_id int, -- 接收者ID
  send_time bigint(20), -- 发送时间
  receive_time bigint(20), -- 接收时间
  context varchar(1000), -- 内容
  context_type int,	-- 内容类型
  context_state int, -- 已读、未读消息
  remark varchar(255),	-- 备注
  PRIMARY KEY(id)
) DEFAULT CHARSET=UTF8;

-- 用户表 users
DROP TABLE if exists users;
CREATE TABLE users (
  id int auto_increment,
  user_name varchar(100) UNIQUE,	-- 用户名(手机号码或邮箱)
  user_password varchar(100) NOT NULL,	-- 密码
  user_alias varchar(100),	-- 用户别名
  user_device_id varchar(100),	-- 设备ID
  user_login_time bigint(20), -- 登录时间
  user_login_ip varchar(255), -- 登录IP
  user_register_time bigint(20),
  user_verification varchar(500), -- 验证字段
  user_img_name varchar(100),
  user_remark varchar(255),	-- 备注
  PRIMARY KEY(id)
) DEFAULT CHARSET=UTF8;




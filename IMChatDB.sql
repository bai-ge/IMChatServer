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



-- 聊天记录表 chat_message
DROP TABLE if exists chat_message;
CREATE TABLE chat_message (
  id int auto_increment,
  sender_id int, -- 发送者ID
  receive_id int, -- 接收者ID
  send_time bigint(20), -- 发送时间
  context varchar(1000), -- 内容
  context_type int,	-- 内容类型
  context_state int, -- 已读、未读消息
  remark varchar(255),	-- 备注
  PRIMARY KEY(id),
  foreign key(sender_id) references users(id),
  foreign key(receive_id) references users(id)
) DEFAULT CHARSET=UTF8;



-- 好友表 friends
DROP TABLE if exists friends;
CREATE TABLE friends (
  id int auto_increment,
  user_id int ,	              -- 用户ID
  friend_id int,	            -- 朋友ID
  user_alias varchar(100),	  -- 用户别名 (朋友设置的别名)
  friend_alias varchar(100),	-- 朋友别名(用户设置的别名)
  relate_time bigint(20),     -- 绑定时间
  state int,                  -- 状态(申请中，同意，拒绝，删除，拉黑)
  user_read_state int,        -- 用户是否已读
  friend_read_state int,      -- 好友是否已读
  remark varchar(255),	      -- 备注
  PRIMARY KEY(id),
  foreign key(user_id) references users(id),
  foreign key(friend_id) references users(id)
) DEFAULT CHARSET=UTF8;


-- 文件表 files
DROP TABLE if exists files;
CREATE TABLE files (
  id int auto_increment,
  user_id int ,	              -- 用户ID
  file_name varchar(255),	    -- 文件名
  file_path varchar(255),	    -- 文件路径
  file_type int,              -- 文件类型
  file_size bigint(20),       -- 文件大小
  file_describe varchar(255), -- 文件描述
  upload_time bigint(20),     -- 上传时间
  download_count int,         -- 下载量
  remark varchar(255),	      -- 备注
  PRIMARY KEY(id),
  foreign key(user_id) references users(id)
) DEFAULT CHARSET=UTF8;





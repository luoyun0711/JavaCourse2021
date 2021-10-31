CREATE TABLE demo_t_order (
                              `id` BIGINT NOT NULL COMMENT '主键id',
                              `order_no` VARCHAR(100) NOT NULL COMMENT '订单号',
                              `user_id` BIGINT NOT NULL COMMENT '用户id',
                              `item_id` BIGINT NOT NULL COMMENT '商品id',
                              `amount` decimal(20,5)  NOT NULL COMMENT '订单金额',
                              `status` VARCHAR(50) NOT NULL COMMENT '状态',
                              `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                              `update_ime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                              PRIMARY KEY (order_id),
                              UNIQUE KEY `order_no` (`order_no`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin comment='订单表';


CREATE TABLE demo_t_user (
                             `id` BIGINT NOT NULL COMMENT '主键id',
                             `name` VARCHAR(100) NOT NULL COMMENT '用户名',
                             `password` VARCHAR(50) NOT NULL COMMENT '密码',
                             `nick_name` VARCHAR(100) COMMENT '昵称',
                             `id_card` VARCHAR(50) COMMENT '身份证',
                             `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                             `update_ime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin comment='用户表';


CREATE TABLE demo_t_item (
                             `id` BIGINT NOT NULL  COMMENT '主键id',
                             `name` VARCHAR(100) COMMENT '商品名',
                             `description` text COMMENT '商品描述',
                             `price` decimal(20,5)  NOT NULL COMMENT '商品价格',
                             `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                             `update_ime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin comment='商品表';


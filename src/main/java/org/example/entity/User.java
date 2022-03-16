package org.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("s_client")
public class User {
	@TableId
	private Integer id;

	@TableField("user_name")
	private String username;

	@TableField("user_pwd")
	private String password;

	@TableField(value = "UUID")
	private String UUID;

	@TableLogic
	private Integer isDelete;

	public User(Integer id, String username, String password, Integer isDelete) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.isDelete = isDelete;
	}

	public User(String username, String password, Integer isDelete,String UUID) {
		this.username = username;
		this.password = password;
		this.isDelete = isDelete;
		this.UUID = UUID;
	}
}

package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.entity.User;

@Mapper
public interface ClientMapper extends BaseMapper<User> {
	public User selectUserByUsername(@Param("username") String username);

	public int insertUser(User user);
}

package org.example.service;

import org.example.entity.ResultInfo;
import org.example.entity.User;
import org.example.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientMapperService {
	@Autowired
	ClientMapper clientMapper;

	public User selectUserName(String username) {
		return clientMapper.selectUserByUsername(username);
	}

	public int register(String username,String password,String UUID){
		return clientMapper.insertUser(new User(username,password,0,UUID));
	}
}

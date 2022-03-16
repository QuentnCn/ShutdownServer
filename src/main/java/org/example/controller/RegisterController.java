package org.example.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.example.entity.ResultInfo;
import org.example.service.ClientMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

@Controller
public class RegisterController {
	@Autowired
	ClientMapperService clientMapperService;
	@Autowired
	PasswordEncoder passwordEncoder;

	@RequestMapping("/register")
	public String toRegister() {
		return "redirect:/register.html";
	}

	@RequestMapping("/reg")
	@ResponseBody
	public ResultInfo Reg(@RequestParam("username") String username, @RequestParam("password") String password) {
		ResultInfo resultInfo = new ResultInfo();
		if (StringUtils.isBlank(username))
			resultInfo.setMsg("用户名不能为空");
		else if (StringUtils.isBlank(password))
			resultInfo.setMsg("密码不能为空");
		else clientMapperService.register(username,passwordEncoder.encode(password),UUID.randomUUID().toString().substring(6,11));
		return resultInfo;
	}
}

package org.example.exception;


import org.example.entity.ResultInfo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@ControllerAdvice
public class exceptionresolver {
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public ResultInfo exception01(Exception e){
		ResultInfo resultInfo = new ResultInfo();
		if (e instanceof NullPointerException) {
			resultInfo.setMsg("无指针异常");
		}
		if (e instanceof IOException) {
			resultInfo.setMsg("网络异常");
		}
		return resultInfo;
	}
}

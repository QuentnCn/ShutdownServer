package org.example.controller;

import org.example.entity.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class ShutdownController {
	Logger logger = LoggerFactory.getLogger(ShutdownController.class);

	@Autowired
	ConcurrentHashMap<String, Client> concurrentHashMap;

	@RequestMapping("/shutdown")
	@ResponseBody
	public ModelAndView queryAlltarget() {
		ModelAndView modelAndView = new ModelAndView();
		List<String> list = new ArrayList<>();
		for (String o: concurrentHashMap.keySet()) {
			if (o.startsWith("target")) {
				list.add(o);
			}

		}
		modelAndView.addObject("target",list);
		modelAndView.setViewName("shutdownList");
		return modelAndView;
	}

	@RequestMapping("/shutdown/{name}")
	public void shutTarget(@PathVariable String name) {
		try {
			logger.info("请求对"+ name + "进行关机...");
			String remark = "target" + name;
			System.out.println(remark);
			Socket socket = concurrentHashMap.get(remark).getSocket();
			concurrentHashMap.remove(remark);
			PrintWriter pw = new PrintWriter(new BufferedOutputStream(socket.getOutputStream()));
			pw.println("shutdown");
			pw.flush();
			pw.close();
			socket.close();
			logger.info("操作成功...");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

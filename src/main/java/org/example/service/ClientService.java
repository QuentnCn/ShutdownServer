package org.example.service;

import org.example.dao.ClientDao;
import org.example.entity.Client;
import org.example.entity.User;
import org.example.mapper.ClientMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ClientService implements ClientDao {
	Logger logger = LoggerFactory.getLogger(ClientService.class);

	@Autowired
	ServerSocket serverSocket;
	@Resource
	ConcurrentHashMap<String,Client> concurrentHashMap;
	@Autowired
	ClientMapper clientMapper;

	@Override
	public void addTarget() throws IOException {
		Logger logger = LoggerFactory.getLogger(ClientService.class);
		new Thread(() -> {
			while (true) {
				try {
					Socket socket = serverSocket.accept();
					BufferedReader bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					String remark = bf.readLine();
					String info = bf.readLine();
					User user = clientMapper.selectUserByUsername(info.split(" ")[0]);
					if (!user.getUUID().equals(info.split(" ")[1])){
						logger.info("非法访问");
						continue;
					}
					if (remark.equals("target")) {
						if (socket.getInetAddress() != null) {
							Client client = new Client(remark+" " +socket.getInetAddress().getHostName(),socket);
							concurrentHashMap.put(remark+socket.getInetAddress().getHostName(),client);
							logger.info("200" + " " + client.getRemark() + "已连接");
						}else {
							logger.info("500 连接失败");
						}
					}else logger.info("未知类型");
				} catch (IOException | NullPointerException e) {
					logger.info(e.getMessage());
				}
			}
		}).start();
	}


}

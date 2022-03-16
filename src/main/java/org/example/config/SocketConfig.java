package org.example.config;

import org.example.entity.Client;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class SocketConfig {
	@Bean
	public ServerSocket getserverSocket() throws IOException {
		return new ServerSocket(38562);
	}

	/**
	 *  用于储存连接过来的socket对象
	 * @return
	 */
	@Bean
	public ConcurrentHashMap<String, Client> getconcurrentHashMap() {
		return new ConcurrentHashMap<>();
	}
}

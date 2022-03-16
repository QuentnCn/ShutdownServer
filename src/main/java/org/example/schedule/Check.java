package org.example.schedule;

import org.example.entity.Client;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class Check implements Job {
	Logger logger = LoggerFactory.getLogger(Check.class);

	@Autowired
	ConcurrentHashMap<String, Client> concurrentHashMap;

	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		logger.info("检查连接开始...");
		if (!concurrentHashMap.isEmpty()) {
			Iterator<Map.Entry<String,Client>> it = concurrentHashMap.entrySet().iterator();
			while (it.hasNext()) {
				String key = it.next().getKey();
				Client client = concurrentHashMap.get(key);
				if (!client.getSocket().isConnected()) {
					it.remove();
				}
			}
		}
		logger.info("检查连接结束...");
	}
}

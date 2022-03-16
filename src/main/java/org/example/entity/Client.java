package org.example.entity;

import java.io.Serializable;
import java.net.Socket;

public class Client implements Serializable {
	Socket socket;
	String remark;

	public Client() {
	}

	public Client(String remark,Socket socket) {
		this.socket = socket;
		this.remark = remark;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}

package net.send;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.NetData;

public class Send implements Runnable{
	private static DatagramSocket socket=new NetData().getMainSocket();
	private DatagramPacket packet;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	public Send(DatagramPacket packet){
		this.packet=packet;
	}
	
	@Override
	public void run() {
		try {
			socket.send(packet);
		} catch (IOException e) {
			logger.warn(e.toString()); 
		}
	}
}

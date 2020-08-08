package net.accept;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import main.MainData;
import main.ServerStatusEnum;
import net.NetData;
import treat.accept.AcceptTreat;
import treat.user.Position;

public class Accept extends Thread{
	private DatagramSocket socket=new NetData().getMainSocket();
	public void run(){
		byte[] receiveData;
		DatagramPacket packet;
		while(!MainData.getServerStatus().equals(ServerStatusEnum.CLOSEING)) {
			receiveData=new byte[128];
			packet=new DatagramPacket(receiveData,128);
			try {
				socket.receive(packet);
			} catch (IOException e) {
				
			}
			AcceptThreadPool.getAcceptThreadPool().execute(new AcceptTreat(receiveData,new Position(packet)));
		}
	}
}

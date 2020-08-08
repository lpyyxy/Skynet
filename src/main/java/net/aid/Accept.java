package net.aid;

import java.io.IOException;
import main.MainData;
import main.ServerStatusEnum;
import net.NetData;
import net.accept.AcceptThreadPool;

public class Accept extends Thread{
	public void run(){
		byte[] receiveData;
		while(!MainData.getServerStatus().equals(ServerStatusEnum.CLOSEING)) {
			receiveData=new byte[6];
			try {
				NetData.getAidInputStream().read(receiveData);
			} catch (IOException e) {
				
			}
			AcceptThreadPool.getAcceptThreadPool().execute(new AcceptTreat(receiveData));
		}
	}
}

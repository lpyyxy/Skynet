package treat.clear;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import data.config.Config;
import data.config.ConfigPath;
import main.MainData;
import main.ServerStatusEnum;
import treat.UserData;


public class Clear extends Thread{
	Logger logger = LoggerFactory.getLogger(this.getClass());
	public void run(){
		int intervalTime=(int)Config.getConfigData(new ConfigPath("treat","clear","intervalTime"));
		while(!MainData.getServerStatus().equals(ServerStatusEnum.CLOSEING)) {
			UserData.clearExpiredID();
			try {
				Thread.sleep(intervalTime);
			} catch (InterruptedException e) {
				logger.error(e.toString());
			}
		}
	}
}

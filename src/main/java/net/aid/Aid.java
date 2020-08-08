package net.aid;


import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import data.config.Config;
import data.config.ConfigPath;
import net.NetData;

public class Aid {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	public boolean start() {
		Object position = null;
		Object port = null;
		if((position=Config.getConfigData(new ConfigPath("net","aid","position")))==null&
				(port=Config.getConfigData(new ConfigPath("net","aid","port"))
				)==null) {
			return false;
		}
		logger.info("连接协服务器中");
		try {
			new NetData().setAidSocket(new Socket((String) position,(int) port));
		}catch (Exception e) {
			logger.error("协服务器连接失败[]",e.toString());
			return false;
		}
		new Accept().start();
		return true;
	}
}

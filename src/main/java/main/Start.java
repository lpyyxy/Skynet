package main;

import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import data.Data;
import net.Net;

public class Start {
	
	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger(new Start().getClass());
		MainData.setServerStatusEnum(ServerStatusEnum.STARTING);
		logger.info("查找配置文件");
		File folder=new File(System.getProperty("user.dir"));
		if(!new File(folder,"logback.xml").exists()&new File(folder,"config.yml").exists()&new File(folder,"user").exists()) {
			logger.info("文件不存在");
			return;
		}
		if(new Data().start()&new Net().start()) {
			Run.main();
		}else {
			Close.main();
		}
	}
}

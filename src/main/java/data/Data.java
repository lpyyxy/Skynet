package data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import data.config.Config;
import data.database.AatabasePool;

public class Data {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	public boolean start() {
		logger.info("������������");
		return new Config().Start()&new AatabasePool().Start();
	}
	public void close() {
		new AatabasePool().close();
	}
}

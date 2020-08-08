package data.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;
public final class Config {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	public boolean Start() {
		File configFile=new File(System.getProperty("user.dir"),"config.yml");
		if(!configFile.exists()) {
			logger.error("配置文件不存在"); 
			return false;
		}
		Yaml config=new Yaml();
		try {
			new ConfigData().setConfigData(config.load(new FileInputStream(configFile)));
		} catch (FileNotFoundException e) {
			logger.error("配置文件不存在",e.toString()); 
			return false;
		}
		config=null;
		return true; 
	}
	@SuppressWarnings("unchecked")
	public static Object getConfigData(ConfigPath path) {
		Object temporaryData=new ConfigData().getConfigData();
		while(path.getpath().hasNext()) {
			if((temporaryData=((HashMap<String, Object>) temporaryData).get(path.getpath().next())) == null) {
				return null;
			}
		}
		return temporaryData;
	}
}

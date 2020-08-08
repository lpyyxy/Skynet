package data.config;

import java.util.HashMap;


@SuppressWarnings("rawtypes")
final class ConfigData {
	private static HashMap configData=null;
	void setConfigData(HashMap configData) {
		ConfigData.configData=configData;
	}
	
	HashMap getConfigData() {
		return configData;
	}
}

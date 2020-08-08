package data.config;

import java.util.Arrays;
import java.util.Iterator;

public final class ConfigPath {
	private Iterator<String> path=null;
	public ConfigPath(String...path){
		this.path=Arrays.asList(path).iterator();
	}
	Iterator<String> getpath(){
		return path;
	}
}

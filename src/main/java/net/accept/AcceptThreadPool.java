package net.accept;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import data.config.Config;
import data.config.ConfigPath;

public final class AcceptThreadPool {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	private static ThreadPoolExecutor acceptThreadPool = null;
	public boolean start() {
		
		Object corePoolSize=0;
		Object maximumPoolSize=0;
		Object keepAliveTime=0L;
		Object workQueueSize=0;
		
		if((corePoolSize=Config.getConfigData(new ConfigPath("net","accept","acceptThreadPool","corePoolSize")))==null&
			(maximumPoolSize=Config.getConfigData(new ConfigPath("net","accept","acceptThreadPool","maximumPoolSize")))==null&	
			(keepAliveTime=Config.getConfigData(new ConfigPath("net","accept","acceptThreadPool","keepAliveTime")))==null&
			(workQueueSize=Config.getConfigData(new ConfigPath("net","accept","acceptThreadPool","workQueueSize")))==null
			) {
			return false;
		}
		logger.info("启动接收线程池");
		acceptThreadPool=new ThreadPoolExecutor((int) corePoolSize, 
				(int) maximumPoolSize, 
				(int)keepAliveTime, 
				TimeUnit.MILLISECONDS,
				new ArrayBlockingQueue<Runnable>((int) workQueueSize));
		return true;
	}
	public static ThreadPoolExecutor getAcceptThreadPool() {
		return acceptThreadPool;
	}
	public void close() {
		acceptThreadPool.shutdown();
	}
}

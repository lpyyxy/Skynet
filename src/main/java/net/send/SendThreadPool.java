package net.send;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import data.config.Config;
import data.config.ConfigPath;

public final class SendThreadPool {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	private static ThreadPoolExecutor sendThreadPool = null;
	public boolean start() {
		
		Object corePoolSize=0;
		Object maximumPoolSize=0;
		Object keepAliveTime=0L;
		Object workQueueSize=0;
		
		if((corePoolSize=Config.getConfigData(new ConfigPath("net","send","sendThreadPool","corePoolSize")))==null&
			(maximumPoolSize=Config.getConfigData(new ConfigPath("net","send","sendThreadPool","maximumPoolSize")))==null&	
			(keepAliveTime=Config.getConfigData(new ConfigPath("net","send","sendThreadPool","keepAliveTime")))==null&
			(workQueueSize=Config.getConfigData(new ConfigPath("net","send","sendThreadPool","workQueueSize")))==null
			) {
			return false;
		}
		logger.info("启动发送线程池");
		sendThreadPool=new ThreadPoolExecutor((int) corePoolSize, 
				(int) maximumPoolSize, 
				(int) keepAliveTime, 
				TimeUnit.MILLISECONDS,
				new ArrayBlockingQueue<Runnable>((int) workQueueSize));
		return true;
	}
	public static ThreadPoolExecutor getSendThreadPool() {
		return sendThreadPool;
	}
	public void close() {
		sendThreadPool.shutdown();
	}
}

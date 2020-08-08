package net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramSocket;
import java.net.Socket;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import treat.accept.AcceptStatement;

public class NetData {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static DatagramSocket mainSocket=null;
	
	private static HashMap<Byte,AcceptStatement> acceptStatements=new HashMap<Byte,AcceptStatement>();
	
	private static Socket aidSocket=null;
	
	private static OutputStream aidOutputStream=null;
	
	private static InputStream aidInputStream =null;
	
	private static Thread clearThread=null;
	
	public DatagramSocket getMainSocket() {
		return mainSocket;
	}
	void setMainSocket(DatagramSocket mainSocket) {
		if(NetData.mainSocket==null) {
			NetData.mainSocket=mainSocket;
		}
	}
	public void setAidSocket(Socket aidSocket) {
		if(NetData.aidSocket==null) {
			NetData.aidSocket=aidSocket;
			try {
				aidOutputStream=aidSocket.getOutputStream();
				aidInputStream=aidSocket.getInputStream();
			} catch (IOException e) {
				logger.error(e.toString());
			}
			
		}
	}
	
	public static OutputStream getAidOutputStream() {
		return NetData.aidOutputStream;
	}
	public static InputStream getAidInputStream() {
		return NetData.aidInputStream;
	}
	
	public static AcceptStatement getAcceptStatements(byte instruction) {
		return acceptStatements.get(instruction);
	}
	void setAcceptStatements(AcceptStatement acceptStatement) {
		NetData.acceptStatements.put(acceptStatement.getDetection(), acceptStatement);
	}
	
	public void close() {
		mainSocket.close();
		try {
			aidInputStream.close();
			aidOutputStream.close();
			aidSocket.close();
		} catch (IOException e) {
			logger.error(e.toString());
		}
	}
	public Thread getClearThread() {
		return clearThread;
	}
	public void setClearThread(Thread clearThread) {
		NetData.clearThread = clearThread;
	}
}

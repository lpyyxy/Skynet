package net;

import java.net.DatagramSocket;
import java.net.SocketException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import data.config.Config;
import data.config.ConfigPath;
import net.accept.Accept;
import net.accept.AcceptThreadPool;
import net.aid.Aid;
import net.send.SendThreadPool;
import treat.accept.achieve.AloneConnectionAccept;
import treat.accept.achieve.EnterAccept;
import treat.accept.achieve.MaintainAccept;
import treat.accept.achieve.StatusAccept;
import treat.clear.Clear;

public class Net {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	public boolean start() {
		logger.info("���������������");
		if(new AcceptThreadPool().start()&new SendThreadPool().start()&/*new Email().start()*/new Aid().start()) {
			logger.info("��ȡ�˿�");
			try {
				new NetData().setMainSocket(new DatagramSocket((int)Config.getConfigData(new ConfigPath("net","port"))));
			} catch (SocketException e) {
				logger.error("�������˿��쳣\n{}",e.toString());
				return false;
			}
			logger.info("����ָ��");
			new NetData().setAcceptStatements(new EnterAccept());
			new NetData().setAcceptStatements(new AloneConnectionAccept());
			new NetData().setAcceptStatements(new MaintainAccept());
			new NetData().setAcceptStatements(new StatusAccept());
			logger.info("�������շ���");
			new Accept().start();
			logger.info("�����������");
			new NetData().setClearThread(new Clear());
			new NetData().getClearThread().start();
			return true;
		}
		return false;
	}
	public void close() {
		new NetData().close();
		new AcceptThreadPool().close();
		new SendThreadPool().close();
		new NetData().getClearThread().interrupt();
	}
}

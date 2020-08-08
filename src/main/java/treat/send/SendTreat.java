package treat.send;


import net.send.Send;
import net.send.SendThreadPool;

public class SendTreat{
	public SendTreat(AbstractSendStatement sendStatement) {
		SendThreadPool.getSendThreadPool().execute(new Send(sendStatement.getPosition().getDatagramPacket(sendStatement.getCommand())));
	}
}

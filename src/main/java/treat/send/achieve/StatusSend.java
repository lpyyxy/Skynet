package treat.send.achieve;

import treat.send.AbstractSendStatement;
import treat.user.Position;

public class StatusSend extends AbstractSendStatement {
	byte[] data;
	public StatusSend(Position position,byte type) {
		super(position);
		data=new byte[] {-2, type};
		// TODO �Զ����ɵĹ��캯�����
	}
	
	@Override
	public byte[] getCommand() {
		return data;
	}

}

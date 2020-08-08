package treat.send.achieve;

import treat.send.AbstractSendStatement;
import treat.user.Position;

public class StatusSend extends AbstractSendStatement {
	byte[] data;
	public StatusSend(Position position,byte type) {
		super(position);
		data=new byte[] {-2, type};
		// TODO 自动生成的构造函数存根
	}
	
	@Override
	public byte[] getCommand() {
		return data;
	}

}

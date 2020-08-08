package treat.send.achieve;

import treat.send.AbstractSendStatement;
import treat.user.Position;

public class MaintainSend extends AbstractSendStatement {

	public MaintainSend(Position position) {
		super(position);
		
	}

	@Override
	public byte[] getCommand() {
		return new byte[] {-1};
	}
}

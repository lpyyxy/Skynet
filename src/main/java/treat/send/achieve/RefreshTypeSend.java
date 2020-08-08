package treat.send.achieve;

import treat.send.AbstractSendStatement;
import treat.user.Position;

public class RefreshTypeSend extends AbstractSendStatement {

	public RefreshTypeSend(Position position) {
		super(position);
	}

	@Override
	public byte[] getCommand() {
		return new byte[] {-3};
	}

}

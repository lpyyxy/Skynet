package treat.send.achieve;

import treat.Tools;
import treat.send.AbstractSendStatement;
import treat.user.Position;
import treat.user.TemporaryID;

public class EnterSend extends AbstractSendStatement {
	private byte[] data;
	public EnterSend(Position position,TemporaryID temporaryID) {
		super(position, temporaryID);
		data =Tools.byteArrayMerge(new byte[] {0},temporaryID.gettemporaryID());
	}
	@Override
	public byte[] getCommand() {
		return data;
	}
}

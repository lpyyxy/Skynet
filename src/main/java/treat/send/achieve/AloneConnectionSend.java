package treat.send.achieve;

import treat.Tools;
import treat.UserData;
import treat.send.AbstractSendStatement;
import treat.user.Position;
import treat.user.TemporaryID;

public class AloneConnectionSend extends AbstractSendStatement {
	private byte[] data;
	public AloneConnectionSend(Position position,byte b) {
		super(position);
		data=new byte[] {1,b};
	}
	public AloneConnectionSend(Position position,TemporaryID temporaryID,byte[] code) {
		super(position);
		data=Tools.byteArrayMerge(new byte[] {1,1},UserData.getPosition(temporaryID).getByteArray(),temporaryID.gettemporaryID(),code);
	}
	public AloneConnectionSend(Position initiativePosition,Position followPosition,TemporaryID initiativeTemporaryID) {
		super(initiativePosition);
		data=Tools.byteArrayMerge(new byte[] {1,2},followPosition.getByteArray(),initiativeTemporaryID.gettemporaryID());
	}
	@Override
	public byte[] getCommand() {
		return data;
	}

}

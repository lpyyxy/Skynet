package treat.accept.achieve;

import treat.UserData;
import treat.accept.AcceptStatement;
import treat.send.SendTreat;
import treat.send.achieve.MaintainSend;
import treat.send.achieve.RefreshTypeSend;
import treat.user.Position;
import treat.user.TemporaryID;

public class MaintainAccept implements AcceptStatement {

	@Override
	public byte getDetection() {
		return -1;
	}

	@Override
	public void run(Position position, byte[] commend) {
		TemporaryID temporaryID=new TemporaryID(commend);
		UserData.insertMarkTemporaryIDs(temporaryID);
		new SendTreat(new MaintainSend(position));
		if(!UserData.updateTemporaryID(temporaryID, position).equals(position)) {
			new SendTreat(new RefreshTypeSend(position));
		}
	}

}

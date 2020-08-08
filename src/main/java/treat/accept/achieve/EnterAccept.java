package treat.accept.achieve;

import treat.UserData;
import treat.accept.AcceptStatement;
import treat.send.SendTreat;
import treat.send.achieve.EnterSend;
import treat.user.Position;
import treat.user.TemporaryID;

public class EnterAccept implements AcceptStatement {

	@Override
	public byte getDetection() {
		return 0;
	}
	@Override
	public void run(Position position, byte[] commend) {
		TemporaryID temporaryID=new TemporaryID();
		while(!UserData.insertTemporaryID(temporaryID, position)) {
			temporaryID=new TemporaryID();
		}
		new SendTreat(new EnterSend(position,temporaryID));
	}

}

package treat.accept.achieve;

import net.aid.Send;
import treat.UserData;
import treat.accept.AcceptStatement;
import treat.user.Position;
import treat.user.TemporaryID;

public class StatusAccept implements AcceptStatement {

	@Override
	public byte getDetection() {
		return -2;
	}

	@Override
	public void run(Position position, byte[] commend) {
		switch(commend[4]) {
			case 0:
				new Send(position);
			case 1:
				if(commend[5]!=-1) {
					UserData.getPosition(new TemporaryID(commend)).setType(commend[5]);
				}
			default:
				return;
		}
	}

}

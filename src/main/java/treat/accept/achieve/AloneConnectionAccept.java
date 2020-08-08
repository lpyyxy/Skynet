package treat.accept.achieve;

import treat.Tools;
import treat.UserData;
import treat.accept.AcceptStatement;
import treat.send.SendTreat;
import treat.send.achieve.AloneConnectionSend;
import treat.user.Position;
import treat.user.TemporaryID;

public class AloneConnectionAccept implements AcceptStatement {

	@Override
	public byte getDetection() {
		return 1;
	}
	@Override
	public void run(Position position, byte[] commend) {
		TemporaryID initiativeTemporaryID=new TemporaryID(commend);
		TemporaryID followTemporaryID=new TemporaryID(Tools.byteArrayCutSubscript(5, commend));
		switch(commend[4]) {
			case 0:
				if(UserData.IstemporaryIDExist(followTemporaryID)) {
					new SendTreat(new AloneConnectionSend(UserData.getPosition(followTemporaryID),initiativeTemporaryID,Tools.byteArrayCut(9,2,commend)));
					UserData.insertMarkAloneConnection(initiativeTemporaryID);
				}else{
					new SendTreat(new AloneConnectionSend(position,(byte) -1));
				}
			case -2:
				if(UserData.IsMarkAloneConnectionExist(followTemporaryID)) {
					new SendTreat(new AloneConnectionSend(UserData.getPosition(followTemporaryID),position,initiativeTemporaryID));
					UserData.deleteMarkAloneConnection(followTemporaryID);
				}
			default:
				return;
		}
	}

}

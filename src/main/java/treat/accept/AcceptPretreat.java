package treat.accept;

import treat.UserData;
import treat.user.Position;
import treat.user.TemporaryID;

public class AcceptPretreat {
	byte commend;
	TemporaryID temporaryID;
	Position position;
	public AcceptPretreat(byte commend,TemporaryID temporaryID,Position position) {
		this.commend=commend;
		this.position=position;
		this.temporaryID=temporaryID;
	}
	public boolean IsVia() {
		if(commend==0) {
			return true;
		}
		return UserData.IstemporaryIDExist(temporaryID);
	}
}

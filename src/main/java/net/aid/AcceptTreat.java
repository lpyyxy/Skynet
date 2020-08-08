package net.aid;

import treat.Tools;
import treat.UserData;
import treat.send.SendTreat;
import treat.send.achieve.StatusSend;
import treat.user.Position;
import treat.user.TemporaryID;

public class AcceptTreat implements Runnable{
	byte[] data;
	public AcceptTreat(byte[] data) {
		this.data=data;
	}
	@Override
	public void run() {
		Position position=UserData.getPosition(new TemporaryID(data));
		if(UserData.getPosition(new TemporaryID(data)).isPortSame(Tools.byteArrayCutSubscript(4, data))) {
			new Send(position);
		}else {
			new SendTreat(new StatusSend(position,(byte)3));
			position.setType((byte)3);
		}
	}
}

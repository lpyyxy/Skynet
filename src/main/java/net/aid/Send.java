package net.aid;

import java.io.IOException;
import net.NetData;
import treat.user.Position;

public class Send {
	public Send(Position position) {
		try {
			NetData.getAidOutputStream().write(position.getByteArray());
		} catch (IOException e) {}
	} 
}

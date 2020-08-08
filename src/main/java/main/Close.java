package main;

import data.Data;
import net.Net;

public class Close {
	public static void main() {
		MainData.setServerStatusEnum(ServerStatusEnum.CLOSEING);
		new Net().close();
		new Data().close();
	}
}

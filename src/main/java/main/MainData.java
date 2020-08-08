package main;



public class MainData {
	private static ServerStatusEnum serverStatus;
	public static ServerStatusEnum getServerStatus() {
		return serverStatus;
	}
	static void setServerStatusEnum(ServerStatusEnum serverStatus) {
		MainData.serverStatus=serverStatus;
	}
}

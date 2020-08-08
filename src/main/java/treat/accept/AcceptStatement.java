package treat.accept;

import treat.user.Position;

public interface AcceptStatement{
	public byte getDetection();
	public void run(Position position,byte[] commend);
}

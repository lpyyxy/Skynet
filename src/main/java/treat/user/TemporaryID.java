package treat.user;

import treat.Tools;

public class TemporaryID {
	byte[] temporaryID=new byte[4];
	int hashCode;
	public TemporaryID() {
		Start(Tools.intToByteArray(Tools.random()));
	}
	public TemporaryID(byte[] temporaryID) {
		Start(Tools.byteArrayCutLength(4,temporaryID));
	}
	public TemporaryID(int temporaryID) {
		Start(Tools.intToByteArray(temporaryID));
	}
	private void Start(byte[] temporaryID) {
		this.temporaryID=temporaryID;
		this.hashCode=Tools.byteArrayToInt(temporaryID);
	}
	public byte[] gettemporaryID() {
		return temporaryID;
	}
	public boolean equals(Object temporaryID) {
		return temporaryID.hashCode()==this.hashCode;
	}
	public int hashCode(){
		return hashCode;
	}
}

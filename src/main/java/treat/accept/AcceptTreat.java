package treat.accept;

import net.NetData;
import treat.Tools;
import treat.user.Position;
import treat.user.TemporaryID;

public final class AcceptTreat implements Runnable{
	private byte[] commend;
	private Position position;
	public AcceptTreat(byte[] commend,Position position) {
		this.commend=commend;
		this.position=position;
	}
	@Override
	public void run() {
		if(new AcceptPretreat(commend[0],new TemporaryID(Tools.byteArrayCut(1,4,commend)),position).IsVia()) {
			NetData.getAcceptStatements(commend[0]).run(position,Tools.byteArrayCutSubscript(1,commend));
		}
	}
	
}

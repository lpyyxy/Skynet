package treat.send;

import treat.user.Position;

public abstract class AbstractSendStatement {
	protected Position position;
	public AbstractSendStatement(Position position,Object...array) {
		this.position=position;
	}
	public Position getPosition() {
		return position;
	}
	public abstract byte[] getCommand();
}

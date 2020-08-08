package treat;

import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

import treat.user.Position;
import treat.user.TemporaryID;

public class UserData {
	private static ConcurrentHashMap<TemporaryID,Position> temporaryIDs=new ConcurrentHashMap<TemporaryID,Position>();
	
	private static CopyOnWriteArraySet<TemporaryID> markTemporaryIDs=new CopyOnWriteArraySet<TemporaryID>();
	
	private static CopyOnWriteArraySet<TemporaryID> markAloneConnections=new CopyOnWriteArraySet<TemporaryID>();
	
	public static Position getPosition(TemporaryID temporaryID) {
		return temporaryIDs.get(temporaryID);
	}
	public static Position updateTemporaryID(TemporaryID temporaryID,Position position) {
		return temporaryIDs.put(temporaryID,position);
	}
	public static boolean insertTemporaryID(TemporaryID temporaryID,Position position) {
		if(!IstemporaryIDExist(temporaryID)) {
			temporaryIDs.put(temporaryID,position);
			return true;
		}
		return false;
	}
	public static boolean IstemporaryIDExist(TemporaryID temporaryID) {
		return temporaryIDs.containsKey(temporaryID);
	}
	public static void deleteTemporaryID(TemporaryID temporaryID) {
		temporaryIDs.remove(temporaryID);
	}
	
	public static void insertMarkTemporaryIDs(TemporaryID temporaryID) {
		markTemporaryIDs.add(temporaryID);
	}
	
	public static void deleteMarkAloneConnection(TemporaryID temporaryID) {
		markAloneConnections.remove(temporaryID);
	}
	public static boolean IsMarkAloneConnectionExist(TemporaryID temporaryID) {
		return markAloneConnections.contains(temporaryID);
	}
	public static void insertMarkAloneConnection(TemporaryID temporaryID) {
		markAloneConnections.add(temporaryID);
	}
	
	public static void clearExpiredID() {
		for(Entry<TemporaryID, Position> entry:temporaryIDs.entrySet()) {
			if(!markTemporaryIDs.remove(entry.getKey())) {
				deleteTemporaryID(entry.getKey());
			}
		}
		markAloneConnections=new CopyOnWriteArraySet<TemporaryID>();
	}
}

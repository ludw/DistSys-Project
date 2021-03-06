package gcom;

import gcom.interfaces.Message;
import gcom.interfaces.*;
import gcom.HashVectorClock;

import java.io.Serializable;
import java.util.Vector;

public class momCausal extends momNonOrdered {

	public momCausal (String id) {
		super(id);
	}

	private void sendMessage(Message message) {
		this.clock.tickKey(message.getSource().getID());
		sendToListeners(message);
	}

	@Override
	public void queueMessage(Message m) {
		Debug.log(this, Debug.TRACE, "Queued message: " + m.toString() + " Clocks: " + clock.toString());
		if(m.bypass() || m.getSource().getID().equals(myID)) { 
			Debug.log(this, Debug.DEBUG, "Bypasses message " + m);
			sendToListeners(m);
			return;
		}
		if(this.clock.getValue(m.getSource().getID()) == null) {
			String id = m.getSource().getID();
			Integer val = m.getClock().getValue(id);
			this.clock.put(id, val-1);
		}
		Debug.log(this, Debug.TRACE, "After Clocks: " + clock.toString());
		messages.add(m);
		checkMessages();
	}
	
	private void checkMessages() {
		Vector<Message> remove = new Vector<Message>();
		
		for(Message m : messages) {
			String id = m.getSource().getID();
			HashVectorClock m_clock = m.getClock();
			if(m_clock.getValue(id) == this.clock.getValue(id)+1 && this.clock.excludedCompareTo(m_clock, id) >= 0) {
				sendMessage(m);
				remove.add(m);
			}
		}

		for(Message m : remove) { messages.remove(m); }
		if(remove.size() > 0) { checkMessages(); }
	}

}

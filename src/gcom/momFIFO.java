package gcom;

import gcom.interfaces.Message;
import gcom.interfaces.*;
import gcom.HashVectorClock;

import java.util.Hashtable;
import java.io.Serializable;
import java.util.Vector;

public class momFIFO extends momNonOrdered {
	private Vector<GComMessageListener> listeners;
	private Vector<Message> messages;

	public momFIFO(String id) {
		super(id);
		listeners = new Vector<GComMessageListener>();
		messages = new Vector<Message>();
	}

	@Override
	public void tick() {
		this.clock.tick();
	}

	private void sendMessage(Message message) {
		String key = message.getSource().getID();
		Integer value = message.getClock().getValue(key);
		clock.put(key, value);
		sendToListeners(message);
	}
	
	@Override
	public void queueMessage(Message m) {
		String key = m.getSource().getID();
		Integer value = m.getClock().getValue(key);
		Integer last = clock.getValue(key);
		if(last == null) {
			sendMessage(m);
			return;
		}
		if(value < last) { return; }
		messages.add(m);
		checkMessages();
	}
	
	private void checkMessages() {
		Vector<Message> remove = new Vector<Message>();

		for(Message m : messages) {
			String key = m.getSource().getID();
			Integer value = m.getClock().getValue(key);
			Integer last = clock.getValue(key);
			if(value == last+1) {
				sendMessage(m);
				remove.add(m);
			}
		}

		for(Message m : remove) {
			messages.remove(m);
		}
		if(remove.size() > 0) { checkMessages(); }
	}

}

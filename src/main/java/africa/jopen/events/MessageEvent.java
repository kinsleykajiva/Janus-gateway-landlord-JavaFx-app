package africa.jopen.events;


import javafx.event.Event;
import javafx.event.EventType;

public class MessageEvent extends Event {
	public static final EventType<MessageEvent> MESSAGE_EVENT_UPDATE_CONFIGS = new EventType<>(Event.ANY, "MESSAGE_EVENT_UPDATE_CONFIGS");


	String message = null;
	Object Object  = null;

	public MessageEvent (EventType<MessageEvent> eventType) {
		super(eventType);


	}

	public MessageEvent (EventType<MessageEvent> eventType, String message) {
		super(eventType);
		this.message = message;

	}

	public MessageEvent (EventType<MessageEvent> eventType, Object message) {
		super(eventType);
		this.Object = message;

	}

	public String getMessage () {
		return message;
	}

	public java.lang.Object getObject () {
		return Object;
	}
}

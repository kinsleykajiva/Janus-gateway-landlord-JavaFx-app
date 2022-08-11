package africa.jopen.events;


import javafx.event.Event;
import javafx.event.EventType;

public class MessageEvent extends Event {
    public static final EventType<MessageEvent> MESSAGE_ANY_EVENT = new EventType<>(Event.ANY,"MESSAGE_EVENT");
    public static final EventType<MessageEvent> MESSAGE_NEW_MESSAGE_EVENT = new EventType<>(Event.ANY,"MESSAGE_NEW_MESSAGE_EVENT");
    public static final EventType<MessageEvent> MESSAGE_NOT_CONNECTED_TO_SERVER_EVENT = new EventType<>(Event.ANY,"MESSAGE_NOT_CONNECTED_TO_SERVER_EVENT");
    public static final EventType<MessageEvent> MESSAGE_CONNECTED_TO_SERVER_EVENT = new EventType<>(Event.ANY,"MESSAGE_CONNECTED_TO_SERVER_EVENT");
    public static final EventType<MessageEvent> MESSAGE_EVENT_NEW_USER_ALERT = new EventType<>(Event.ANY,"MESSAGE_EVENT_NEW_USER_ALERT");
    public static final EventType<MessageEvent> MESSAGE_EVENT_MAKE_OUT_GOING_CALL_ALERT = new EventType<>(Event.ANY,"MESSAGE_EVENT_MAKE_OUT_GOING_CALL_ALERT");

    String message = null;
    Object Object = null;

    public MessageEvent(EventType<MessageEvent> eventType,String message) {
        super(eventType);
        this.message = message;

    }
    public MessageEvent(EventType<MessageEvent> eventType,Object message) {
        super(eventType);
        this.Object = message;

    }

    public String getMessage() {
        return message;
    }

    public java.lang.Object getObject() {
        return Object;
    }
}

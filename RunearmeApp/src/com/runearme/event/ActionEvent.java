package com.runearme.event;

import com.google.web.bindery.event.shared.Event;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;

/**
 * Fired when the user wants the app to do something. Action events are fired
 * with a string name, and handlers monitor the event bus for them based on that
 * name.
 */
public class ActionEvent extends Event<ActionEvent.Handler> {

	/**
	 * Implemented by objects that handle {@link ActionEvent}.
	 */
	public interface Handler {
		void onAction(ActionEvent event);
	}

	/**
	 * The event type.
	 */
	private static final Type<ActionEvent.Handler> TYPE = new Type<ActionEvent.Handler>();

	public static void fire(EventBus eventBus, String sourceName) {
		eventBus.fireEventFromSource(new ActionEvent(), sourceName);
	}

	public static HandlerRegistration register(EventBus eventBus,
			String sourceName, Handler handler) {
		return eventBus.addHandlerToSource(TYPE, sourceName, handler);
	}

	/**
	 * Protected contructor to encourage the use of
	 * {@link #fire(EventBus, String)}.
	 */
	protected ActionEvent() {
	}

	@Override
	public final Type<ActionEvent.Handler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ActionEvent.Handler handler) {
		handler.onAction(this);
	}
}

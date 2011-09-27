package com.runearme.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

/**
 * Fired when the user wants to return to the main screen of the app.
 */
public class GoHomeEvent extends GwtEvent<GoHomeEvent.Handler> {

	/**
	 * Implemented by objects that handle {@link GoHomeEvent}.
	 */
	public interface Handler extends EventHandler {
		void onGoHome(GoHomeEvent event);
	}

	/**
	 * The event type.
	 */
	public static final Type<GoHomeEvent.Handler> TYPE = new Type<GoHomeEvent.Handler>();

	@Override
	public final Type<GoHomeEvent.Handler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(GoHomeEvent.Handler handler) {
		handler.onGoHome(this);
	}
}

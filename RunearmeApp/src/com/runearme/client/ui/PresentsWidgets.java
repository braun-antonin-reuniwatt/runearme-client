package com.runearme.client.ui;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.web.bindery.event.shared.EventBus;

/**
 * Implemented by specialized widget drivers that can answer
 * {@link com.google.gwt.activity.shared.Activity#mayStop() mayStop()} calls for
 * {@link com.google.gwt.activity.shared.Activity Activities}.
 * <p>
 * Note that this interface extends {@link IsWidget}. This is to make it easier
 * to evolve app code in MVP directions piecemeal, just where it is useful. If
 * code that assembles widgets thinks of them strictly as IsWidget instances, it
 * doesn't need to notice as they get refactored in to Presenter / View pairs.
 * Or as they don't.
 */
public interface PresentsWidgets extends IsWidget {
	/**
	 * Called (probably from
	 * {@link com.google.gwt.activity.shared.Activity#mayStop Activity.mayStop})
	 * to see if it's safe to stop this presenter.
	 * 
	 * @return null if it's okay to stop, else a message to ask the user if
	 *         she's sure
	 */
	String mayStop();

	void start(EventBus eventBus);

	void stop();
}

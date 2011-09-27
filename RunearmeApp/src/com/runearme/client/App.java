package com.runearme.client;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.UmbrellaException;
import com.runearme.event.ActionEvent;
import com.runearme.event.ActionNames;
import com.runearme.place.HomePlace;

/**
 * The heart of the applicaiton, mainly concerned with bootstrapping.
 */
public class App {
	private static final Logger log = Logger.getLogger(App.class.getName());

	private final Storage storage;

	/**
	 * Where components of the app converse by posting and monitoring events.
	 */
	private final EventBus eventBus;

	/**
	 * Owns the current {@link Place} in the app. A Place is the embodiment of
	 * any bookmarkable state.
	 */
	private final PlaceController placeController;

	/**
	 * The top of our UI.
	 */
	private final RunearmeAppShell shell;

	private final ActivityManager activityManager;

	public App(EventBus eventBus, PlaceController placeController,
			ActivityManager activityManager, RunearmeAppShell shell) {
		this(null, eventBus, placeController, activityManager, shell);
	}

	public App(Storage storage, EventBus eventBus,
			PlaceController placeController, ActivityManager activityManager,
			RunearmeAppShell shell) {

		this.storage = storage;
		this.eventBus = eventBus;
		this.placeController = placeController;
		this.activityManager = activityManager;
		this.shell = shell;
	}

	/**
	 * Given a parent view to show itself in, start this App.
	 * 
	 * @param parentView
	 *            where to show the app's widget
	 */
	public void run(HasWidgets.ForIsWidget parentView) {
		activityManager.setDisplay(shell);

		parentView.add(shell);

		ActionEvent.register(eventBus, ActionNames.GO_HOME,
				new ActionEvent.Handler() {
					@Override
					public void onAction(ActionEvent event) {
						placeController.goTo(new HomePlace());
					}
				});

		GWT.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
			@Override
			public void onUncaughtException(Throwable e) {
				while (e instanceof UmbrellaException) {
					e = ((UmbrellaException) e).getCauses().iterator().next();
				}

				String message = e.getMessage();
				if (message == null) {
					message = e.toString();
				}
				log.log(Level.SEVERE, "Uncaught exception", e);
				Window.alert("An unexpected error occurred: " + message);
			}
		});
	}
}

package com.runearme.client;

import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;
import com.runearme.presenter.HomeView;

/**
 * The factory responsible for instantiating everything interesting in this
 * application.
 */
public interface ClientFactory {
	/**
	 * Create the App.
	 * 
	 * @return a new instance of the {@link App}
	 */
	App getApp();

	/**
	 * Get the {@link EventBus}
	 * 
	 * @return the event bus used through the app
	 */
	EventBus getEventBus();

	/**
	 * Get the {@link PlaceController}.
	 * 
	 * @return the place controller
	 */
	PlaceController getPlaceController();

	/**
	 * Get the UI shell.
	 * 
	 * @return the shell
	 */
	RunearmeAppShell getShell();

	/**
	 * Get an implementation of {@link HomeView}.
	 */
	HomeView getHomeView();
}

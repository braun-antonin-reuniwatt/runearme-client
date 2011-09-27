package com.runearme.client;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.runearme.activity.NullActivityMapper;
import com.runearme.client.desktop.DesktopHomeView;
import com.runearme.client.desktop.RunearmeAppShellDesktop;
import com.runearme.presenter.HomeView;

/**
 * Default implementation of {@link ClientFactory}. Used by desktop version.
 */
class ClientFactoryImpl implements ClientFactory {
	private final EventBus eventBus = new SimpleEventBus();
	private final PlaceController placeController = new PlaceController(
			eventBus);
	private ActivityManager activityManager;
	private RunearmeAppShell shell;
	private HomeView homeView;

	public ClientFactoryImpl() {
	}

	public App getApp() {
		return new App(eventBus, getPlaceController(), getActivityManager(),
				getShell());
	}

	@Override
	public EventBus getEventBus() {
		return eventBus;
	}

	public PlaceController getPlaceController() {
		return placeController;
	}

	public RunearmeAppShell getShell() {
		if (shell == null) {
			shell = createShell();
		}
		return shell;
	}

	public HomeView getHomeView() {
		if (homeView == null) {
			homeView = createHomeView();
		}
		return homeView;
	}

	/**
	 * Create the application UI shell.
	 * 
	 * @return the UI shell
	 */
	protected RunearmeAppShell createShell() {
		return new RunearmeAppShellDesktop(eventBus, placeController,
				getHomeView());
	}

	/**
	 * Create a {@link HomeView}.
	 * 
	 * @return a new {@link HomeView}
	 */
	protected HomeView createHomeView() {
		return new DesktopHomeView();
	}

	protected ActivityManager getActivityManager() {
		if (activityManager == null) {
			activityManager = new ActivityManager(createActivityMapper(),
					eventBus);
		}
		return activityManager;
	}

	/**
	 * ActivityMapper determines an Activity to run for a particular place,
	 * configures the {@link #getActivityManager()}
	 */
	protected ActivityMapper createActivityMapper() {
		return new NullActivityMapper();
	}
}

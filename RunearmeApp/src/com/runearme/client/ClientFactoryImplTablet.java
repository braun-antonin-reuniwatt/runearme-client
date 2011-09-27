package com.runearme.client;

import com.runearme.client.tablet.RunearmeAppShellTablet;
import com.runearme.client.tablet.TabletHomeView;
import com.runearme.client.ui.OrientationHelper;
import com.runearme.client.ui.WindowBasedOrientationHelper;
import com.runearme.presenter.HomeView;

/**
 * Tablet version of {@link ClientFactory}.
 */
public class ClientFactoryImplTablet extends ClientFactoryImplMobile {
	private final OrientationHelper orientationHelper = new WindowBasedOrientationHelper();

	@Override
	protected RunearmeAppShell createShell() {
		return new RunearmeAppShellTablet(this, orientationHelper,
				getHomeView());
	}

	@Override
	protected HomeView createHomeView() {
		return new TabletHomeView();
	}
}

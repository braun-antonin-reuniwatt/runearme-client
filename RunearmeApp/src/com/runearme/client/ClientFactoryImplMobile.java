package com.runearme.client;

import com.runearme.client.mobile.MobileHomeView;
import com.runearme.client.mobile.RunearmeAppShellMobile;
import com.runearme.client.ui.OrientationHelper;
import com.runearme.client.ui.WindowBasedOrientationHelper;
import com.runearme.presenter.HomeView;

/**
 * Mobile version of {@link ClientFactory}.
 */
public class ClientFactoryImplMobile extends ClientFactoryImpl {
	private final OrientationHelper orientationHelper = new WindowBasedOrientationHelper();

	@Override
	protected RunearmeAppShell createShell() {
		return new RunearmeAppShellMobile(getEventBus(), orientationHelper,
				getHomeView());
	}

	@Override
	protected HomeView createHomeView() {
		return new MobileHomeView();
	}
}

package com.runearme.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootLayoutPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class RunearmeApp implements EntryPoint {
	public void onModuleLoad() {
		ClientFactory clientFactory = GWT.create(ClientFactory.class);
		clientFactory.getApp().run(RootLayoutPanel.get());
	}
}

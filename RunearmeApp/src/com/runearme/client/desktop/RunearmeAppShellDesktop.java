package com.runearme.client.desktop;

import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;
import com.runearme.client.RunearmeAppShell;
import com.runearme.presenter.HomeView;

public class RunearmeAppShellDesktop extends ResizeComposite implements
		RunearmeAppShell {

	private static RunearmeAppShellDesktopUiBinder uiBinder = GWT
			.create(RunearmeAppShellDesktopUiBinder.class);

	interface RunearmeAppShellDesktopUiBinder extends
			UiBinder<Widget, RunearmeAppShellDesktop> {
	}

	/**
	 * A boolean indicating that we have not yet seen the first content widget.
	 */
	private boolean firstContentWidget = true;

	/**
	 * The container that holds content.
	 */
	@UiField
	SimpleLayoutPanel contentContainer;

	/**
	 * Construct a new {@link MobileWebAppShellDesktop}.
	 */
	public RunearmeAppShellDesktop(EventBus bus,
			final PlaceController placeController, HomeView homeView) {
		// Initialize this widget.
		initWidget(uiBinder.createAndBindUi(this));

		contentContainer.add(homeView);
	}

	/**
	 * Set the widget to display in content area.
	 * 
	 * @param content
	 *            the {@link Widget} to display
	 */
	@Override
	public void setWidget(IsWidget content) {
		System.out.println("setWidget : " + content);

		// Do not animate the first time we show a widget.
		if (firstContentWidget) {
			firstContentWidget = false;
		}
	}
}

package com.runearme.client.tablet;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;
import com.runearme.client.ClientFactory;
import com.runearme.client.RunearmeAppShell;
import com.runearme.client.ui.OrientationHelper;
import com.runearme.event.ActionEvent;
import com.runearme.event.ActionNames;
import com.runearme.presenter.HomeView;

public class RunearmeAppShellTablet extends ResizeComposite implements
		RunearmeAppShell {

	interface RunearmeAppShellTabletUiBinder extends
			UiBinder<Widget, RunearmeAppShellTablet> {
	}

	private static RunearmeAppShellTabletUiBinder uiBinder = GWT
			.create(RunearmeAppShellTabletUiBinder.class);

	private final ClientFactory clientFactory;

	/**
	 * The container that holds content.
	 */
	@UiField
	SimpleLayoutPanel contentContainer;

	/**
	 * A boolean indicating that we have not yet seen the first content widget.
	 */
	private boolean firstContentWidget = true;

	private final HomeView homeView;

	private final EventBus eventBus;

	/**
	 * Construct a new {@link RunearmeAppShellTablet}.
	 */
	public RunearmeAppShellTablet(ClientFactory clientFactory,
			OrientationHelper orientationHelper, HomeView homeView) {
		this.clientFactory = clientFactory;
		this.eventBus = clientFactory.getEventBus();
		this.homeView = homeView;

		// Initialize this widget.
		initWidget(uiBinder.createAndBindUi(this));

		orientationHelper.setCommands(this, new Command() {
			@Override
			public void execute() {
				onShiftToPortrait();
			}
		}, new Command() {
			@Override
			public void execute() {
				onShiftToLandscape();
			}
		});
	}

	/**
	 * Set the widget to display in content area.
	 * 
	 * @param content
	 *            the {@link Widget} to display
	 */
	public void setWidget(IsWidget content) {
		// TODO : if content is null, insert twitter widget
		contentContainer.setWidget(content);

		// Do not animate the first time we show a widget.
		if (firstContentWidget) {
			firstContentWidget = false;
		}
	}

	private void onShiftToLandscape() {
		// Ensure that the task list view is not displayed as content.
		// TODO : if content is null, insert twitter widget
		if (!contentContainer.isVisible()) {
			// contentContainer.setWidget(twitterMessage);
		}
	}

	private void onShiftToPortrait() {
		contentContainer.add(homeView);

		// Ensure that something is displayed.
		Widget curWidget = contentContainer.getWidget();
		// TODO : if (curWidget == null || curWidget == twitterMessage) {
		if (curWidget == null) {
			ActionEvent.fire(eventBus, ActionNames.GO_HOME);
		}
	}
}

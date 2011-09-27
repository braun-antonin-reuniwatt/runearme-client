package com.runearme.client.mobile;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;
import com.runearme.client.RunearmeAppShell;
import com.runearme.client.ui.OrientationHelper;
import com.runearme.presenter.HomeView;

/**
 * Mobile version of the UI shell.
 */
public class RunearmeAppShellMobile extends ResizeComposite implements
		RunearmeAppShell {

	interface RunearmeAppShellMobileUiBinder extends
			UiBinder<Widget, RunearmeAppShellMobile> {
	}

	private static RunearmeAppShellMobileUiBinder uiBinder = GWT
			.create(RunearmeAppShellMobileUiBinder.class);

	/**
	 * The panel that holds the current content.
	 */
	@UiField
	SimpleLayoutPanel contentContainer;

	/**
	 * A boolean indicating that we have not yet seen the first content widget.
	 */
	private boolean firstContentWidget = true;

	/**
	 * Construct a new {@link RunearmeAppShellMobile}.
	 */
	public RunearmeAppShellMobile(final EventBus eventBus,
			OrientationHelper orientationHelper, HomeView homeView) {

		initWidget(uiBinder.createAndBindUi(this));

		contentContainer.add(homeView);

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
		contentContainer.setWidget(content);

		// Do not animate the first time we show a widget.
		if (firstContentWidget) {
			firstContentWidget = false;
		}
	}

	private void onShiftToLandscape() {
		// TODO : Landscape
	}

	private void onShiftToPortrait() {
		// TODO : Portrait
	}
}

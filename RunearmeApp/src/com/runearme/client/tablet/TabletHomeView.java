package com.runearme.client.tablet;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.runearme.presenter.HomeView;

public class TabletHomeView extends Composite implements HomeView {

	private static TabletHomeViewUiBinder uiBinder = GWT
			.create(TabletHomeViewUiBinder.class);

	interface TabletHomeViewUiBinder extends UiBinder<Widget, TabletHomeView> {
	}

	public TabletHomeView() {
		initWidget(uiBinder.createAndBindUi(this));
	}
}

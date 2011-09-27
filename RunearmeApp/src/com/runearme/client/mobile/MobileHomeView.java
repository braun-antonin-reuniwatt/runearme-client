package com.runearme.client.mobile;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.runearme.presenter.HomeView;

public class MobileHomeView extends Composite implements HomeView {

	private static MobileHomeViewUiBinder uiBinder = GWT
			.create(MobileHomeViewUiBinder.class);

	interface MobileHomeViewUiBinder extends UiBinder<Widget, MobileHomeView> {
	}

	public MobileHomeView() {
		initWidget(uiBinder.createAndBindUi(this));
	}
}

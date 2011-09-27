package com.runearme.activity;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

/**
 * A mapping of places to activities used by this application.
 */
public class NullActivityMapper implements ActivityMapper {
	public Activity getActivity(final Place place) {
		return null;
	}
}

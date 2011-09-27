package com.runearme.client;

/**
 * Provides the presenter to run a given view for a new session.
 * 
 * @param <P>
 *            the presenter type
 * @param <V>
 *            the view type
 */
public interface ProvidesPresenter<P, V> {
	P getPresenter(V view);
}

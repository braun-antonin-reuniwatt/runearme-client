package com.runearme.client.ui;

import com.google.gwt.event.logical.shared.HasAttachHandlers;
import com.google.gwt.user.client.Command;
import com.google.web.bindery.event.shared.HandlerRegistration;

/**
 * Accepts {@link Command}s to be run when the browser window or device's
 * orientation changes. Commands operate only while the associated widget is
 * attached.
 */
public interface OrientationHelper {
	/**
	 * Gives efficient on demand access to the orientation of the window or
	 * device.
	 * 
	 * @return true for portrait, false for landscape
	 */
	boolean isPortrait();

	/**
	 * Set commands to run on orientation change, one for portrait and one for
	 * landscape. The appropriate command is fired immediately if this method is
	 * called while the widget is attached.
	 * <p>
	 * Commands are also fired each time the widget is attached. If that is not
	 * desired a widget should call {@link HandlerRegistration#removeHandler()}
	 * on the returned object when it detaches.
	 * 
	 * @param widget
	 *            the widget to help
	 * @param forPortrait
	 *            command to run when on shift to portrait
	 * @param forLandscape
	 *            command to run when on shift to landscape
	 * @return registration object to be used to stop and dereference the
	 *         commands
	 */
	HandlerRegistration setCommands(HasAttachHandlers widget,
			Command forPortrait, Command forLandscape);
}

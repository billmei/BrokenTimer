/*
 *
 *   This file is part of jSevenBar. Copyright 2011 Alex Volodko
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */
package de.xhelp.jsevenbar.core;

import java.awt.Component;

import javax.swing.JFrame;

/**
 * Main class of this library. The class provides the functionality of the
 * Windows 7 taskbar
 * 
 * @author Alex Volodko
 * 
 */
public class JSevenBar {

	static {
		// load native library
		try {
			Class.forName("de.xhelp.jsevenbar.jni.NativeLibLoader");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Native access to the window handle of a window with the given title
	 * 
	 * @param title
	 *            Window title
	 * @return HWND
	 */
	private static native int getWindowHandle(String title);

	public static final int S_OK = 0x00000000;

	private int hwnd = 0;

	private final ProgressBar progressBar;
	private final Tabs tabs;
	private final Thumbnail thumbnail;

	/**
	 * Getter
	 * 
	 * @return the thumbnail
	 */
	public Thumbnail getThumbnail() {
		return thumbnail;
	}

	/**
	 * Getter
	 * 
	 * @return the tabs
	 */
	public Tabs getTabs() {
		return tabs;
	}

	/**
	 * Getter
	 * 
	 * @return the progressBar
	 */
	public ProgressBar getProgressBar() {
		return progressBar;
	}

	/**
	 * Constructor
	 * 
	 * @param component
	 *            Main container that should owns the bar
	 */
	public JSevenBar(Component component) {
		hwnd = getHandle(component);
		progressBar = new ProgressBar(hwnd);
		tabs = new Tabs(hwnd);
		thumbnail = new Thumbnail(hwnd);
	}

	protected static int getHandle(Component component) {
		// TODO change this crap to get HWND not by the title
		if (component.isLightweight()) {
			throw new IllegalArgumentException(
					"The component must be heavyweight in order to have a window handler");
		}
		JFrame frame = (JFrame) component;
		int hwnd = JSevenBar.getWindowHandle(frame.getTitle());
		checkHwnd(hwnd);
		return hwnd;
	}

	/**
	 * Validate window handler
	 */
	private static void checkHwnd(int hwnd) {
		if (hwnd <= 0) {
			throw new IllegalStateException("Illegal window handwer");
		}
	}

}

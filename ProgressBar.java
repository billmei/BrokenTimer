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

/**
 * This class managed the access to the progress bar functions
 * @author Alex Volodko
 *
 */
public class ProgressBar {
	public static final int STATE_NOPROGRESS = 0x00000000;
	public static final int STATE_INDETERMINATE = 0x00000001;
	public static final int STATE_NORMAL = 0x00000002;
	public static final int STATE_ERROR = 0x00000004;
	public static final int STATE_PAUSED = 0x00000008;


	private static native int setProgressState(int hwnd, int state);

	private static native int setProgressValue(int hwnd, int value, int maximum);

	private final int hwnd;
	private int maximum;
	private int value;
	private ProgressBarState state;

	/**
	 * Getter
	 * 
	 * @return the maximum
	 */
	public int getMaximum() {
		return maximum;
	}

	/**
	 * Setter
	 * 
	 * @param maximum
	 *            the maximum to set
	 */
	public void setMaximum(int maximum) {
		this.maximum = maximum;
		updateValue();
	}

	/**
	 * Getter
	 * 
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Setter
	 * 
	 * @param value
	 *            the value to set
	 */
	public void setValue(int value) {
		this.value = value;
		updateValue();
	}

	/**
	 * Getter
	 * 
	 * @return the state
	 */
	public ProgressBarState getState() {
		return state;
	}

	/**
	 * Setter
	 * 
	 * @param state
	 *            the state to set
	 */
	public void setState(ProgressBarState state) {
		this.state = state;
		updateState();
	}

	public ProgressBar(int hwnd) {
		this.hwnd = hwnd;
		this.value = 0;
		this.maximum = 100;
		this.state = ProgressBarState.NOPROGRESS;
	}

	/**
	 * Call the native method to update the current state of the taskbar
	 */
	private void updateState() {
		int result = setProgressState(hwnd, state.getValue());
		if (result!=JSevenBar.S_OK) {
			throw new IllegalStateException("Return code: "+result);
		}
	}

	/**
	 * Call the native method to update the current value of the taskbar
	 */
	private void updateValue() {
		int result = setProgressValue(hwnd, value, maximum);
		if (result!=JSevenBar.S_OK) {
			throw new IllegalStateException("Return code: "+result);
		}
	}

}

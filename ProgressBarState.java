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
 * @author Alex Volodko
 * 
 */
public enum ProgressBarState {
	NOPROGRESS(0x00000000), INDETERMINATE(0x00000001), NORMAL(0x00000002), ERROR(
			0x00000004), PAUSED(0x00000008);
	private final int value;

	/**
	 * Getter
	 * 
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	private ProgressBarState(int value) {
		this.value = value;
	}
}

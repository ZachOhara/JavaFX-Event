/* Copyright (C) 2016 Zach Ohara
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package io.github.zachohara.eventfx.window;

import io.github.zachohara.eventfx.EventListener;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

public class WindowEventListener extends EventListener<WindowListenable, WindowHandler> implements EventHandler<WindowEvent> {
	
	public WindowEventListener(WindowListenable listenable) {
		super();
		listenable.setOnCloseRequest(this);
		listenable.setOnHidden(this);
		listenable.setOnHiding(this);
		listenable.setOnShown(this);
		listenable.setOnShowing(this);
	}

	@Override
	public void handle(WindowEvent event) {
		for (WindowHandler handler : this.getHandlerList()) {
			handler.handleWindowEvent(event, event.getEventType());
		}
	}
	
	public static WindowEventListener createSelfHandler(WindowSelfHandler handler) {
		WindowEventListener listener = new WindowEventListener(handler);
		listener.addHandler(handler);
		return listener;
	}
	
}
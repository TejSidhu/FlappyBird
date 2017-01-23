package com.monte.enterprise.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.monte.enterprise.Flappy;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new Flappy(), config);//This is the main laucher that starts the game and everything related to it
		config.width = Flappy.Width;
		config.height = Flappy.Height;
		config.resizable = false;
		config.title = Flappy.title;
	}
}

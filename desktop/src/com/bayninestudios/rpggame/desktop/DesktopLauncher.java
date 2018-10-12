package com.bayninestudios.rpggame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.bayninestudios.rpggame.RpgGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 640;
		config.height = 400;
		config.forceExit = true;
		config.fullscreen = false;
		new LwjglApplication(new RpgGame(), config);
	}
}

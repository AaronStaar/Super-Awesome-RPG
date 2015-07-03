package com.nathan.funGame;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class Main {

	public static void main(String[] args) {
		try {
			AppGameContainer app = new AppGameContainer(new Game("Super Awesome Game"));
			app.setDisplayMode(960, 540, false);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}

package com.nathan.funGame.entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.nathan.funGame.events.EventSystem;

public abstract class BaseEntity extends EventSystem {
	private final int zIndex;
	
	public BaseEntity(int zIndex) {
		this.zIndex = zIndex;
	}
	
	public abstract void update(GameContainer container, int delta) throws SlickException;
	
	public abstract void render(GameContainer container, Graphics g) throws SlickException;
	
	public int getZIndex() {
		return this.zIndex;
	}
}


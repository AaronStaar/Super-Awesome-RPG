package com.nathan.funGame.entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.nathan.funGame.EntityManager;



public class SpawnerLayer1 extends BaseEntity {
	
	public SpawnerLayer1() {
		super(1);
		EntityManager.getInstance().spawn(new RectangleLayer1(500, 500));
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

}

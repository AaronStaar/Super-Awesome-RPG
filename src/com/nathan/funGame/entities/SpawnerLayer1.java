package com.nathan.funGame.entities;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.nathan.funGame.EntityManager;

public class SpawnerLayer1 extends BaseEntity {

	private static final int SPAWN_RATE = 250;
	
	Random r = new Random();
	int timer = 0;
	
	public SpawnerLayer1() {
		super(1); 
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		if(timer >= SPAWN_RATE) {
			EntityManager.getInstance().spawn(new RectangleLayer1(r.nextInt(4)));
			EntityManager.getInstance().spawn(new RectangleLayer2(r.nextInt(4)));
			EntityManager.getInstance().spawn(new RectangleLayer3(r.nextInt(4)));
			timer = 0;
		} else {
			timer += delta;
		}
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		
	}

}

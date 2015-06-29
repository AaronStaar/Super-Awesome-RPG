package com.nathan.funGame.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Player extends BaseEntity {
	public int x = 0;
	public int y = 0;
	public int width = 25;
	public int height = 25;
	public static int location = -1;
	
	public Player(int rectHeight) {
		super(location);
		y =  y + (480 - rectHeight);
	}
	
	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		// TODO Auto-generated method stub
		if (container.getInput().isKeyDown(Input.KEY_RIGHT)) {
			x = x + 3;
		}
		if (container.getInput().isKeyDown(Input.KEY_LEFT)) {
			x = x - 3;
		}
		if (container.getInput().isKeyDown(Input.KEY_UP)) {
			y = y - 3;
		}
		if (container.getInput().isKeyDown(Input.KEY_DOWN)) {
			y = y + 3;
		}
	}

	@Override
	public void render(GameContainer container, Graphics g)	throws SlickException {
		// TODO Auto-generated method stub
		g.fillRect(x, y, width, height);
		g.setColor(Color.green);
		
	}

}

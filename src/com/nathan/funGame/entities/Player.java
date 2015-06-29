package com.nathan.funGame.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Player extends BaseEntity {
	public int x = 0;
	public int y = 0;
	public int width = 50;
	public int height = 100;
	
	public Player(int contHeight) {
		super(1);
		y =  y - contHeight;
	}
	
	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		// TODO Auto-generated method stub
		if (container.getInput().isKeyDown(Input.KEY_RIGHT)) {
			x = x + 1;
		} else if ((container.getInput().isKeyDown(Input.KEY_LEFT))) {
			x = x - 1;
		}
	}

	@Override
	public void render(GameContainer container, Graphics g)	throws SlickException {
		// TODO Auto-generated method stub
		g.fillRect(x, y, width, height);
		g.setColor(Color.green);
		
	}

}

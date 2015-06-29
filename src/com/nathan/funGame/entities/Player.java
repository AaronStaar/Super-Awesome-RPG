package com.nathan.funGame.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.nathan.funGame.EntityManager;

public class Player extends BaseEntity {
	private int x = 0;
	private int y = 0;
	private int width = 25;
	private int height = 25;
	
	public Player(int zIndex) {
		super(zIndex);
	}
	
	public Player(int zIndex, int x, int y, int width, int height) {
		super(zIndex);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
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
		
		if (container.getInput().isKeyDown(Input.KEY_SPACE)) {
			EntityManager.getInstance().remove(this);
			EntityManager.getInstance().spawn(new Player(getZIndex() + 1, x, y, width + 20, height + 20));
		} if (getZIndex() > 2)
			 Player(2, x, y, 70, 70);
	}

	@Override
	public void render(GameContainer container, Graphics g)	throws SlickException {
		// TODO Auto-generated method stub
		g.fillRect(x, y, width, height);
		g.setColor(Color.green);
		
	}

}

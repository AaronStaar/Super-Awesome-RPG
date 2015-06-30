package com.nathan.funGame.entities;

import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class RectangleLayer1 extends BaseEntity {
	private Random r = new Random();
	private int x;
	private int y;
	private int width;
	private int height;
		
	public RectangleLayer1() {
		super(1);
		this.x = r.nextInt(641);
		this.y = r.nextInt(481);
		this.width = r.nextInt(150);
		this.height = r.nextInt(150);
	}

	
	
	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		if () {
			
		}
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		g.setColor(Color.green);
		g.fillRect(x, 0 - height, width, height);
		g.fillRect(x, container.getHeight(), width, height);
		g.fillRect(0 - width, y, width, height);
		g.fillRect(container.getWidth(), y, width, height);
	}

}

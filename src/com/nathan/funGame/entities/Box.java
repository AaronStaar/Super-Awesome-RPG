package com.nathan.funGame.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;

public class Box extends BaseEntity{
	
	private int xVelocity = 1;
	private int yVelocity = 1;
	
	public Box(Point point, int w, int h, int rot, int xVelocity, int yVelocity) {
		super(point, w, h, rot);
		this.xVelocity = xVelocity;
		this.yVelocity = yVelocity;
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		// TODO Auto-generated method stub
		if (position.getX() + width >= container.getWidth())
			xVelocity *= -1;
		if (position.getX() <= 0)
			xVelocity *= -1;
		if (position.getY() + height >= container.getHeight())
			yVelocity *= -1;
		if (position.getY() < 0)
			yVelocity *= -1;
		
		position.setX(position.getX() +  xVelocity);
		position.setY(position.getY() +  yVelocity);
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		g.setColor(Color.green);
		g.drawRect(position.getX(), position.getY(), width, height);
	}

}

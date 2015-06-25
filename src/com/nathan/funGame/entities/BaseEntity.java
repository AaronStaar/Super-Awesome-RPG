package com.nathan.funGame.entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;

public abstract class BaseEntity {
	protected Point position;
	protected int length;
	protected int height;
	protected double rotation;
	
	public BaseEntity() {
		this.position = new Point(0, 0);
		this.length =  0;
		this.height = 0;
		this.rotation = 0;
	}
	
	public BaseEntity(Point p) {
		this.position = p;
	}
	
	public BaseEntity (Point p, int l, int h, double rot) {
		this.position = p;
		this.length = l;
		this.height = h;
		this.rotation = rot;
	}
	
	public abstract void update(GameContainer container, int delta) throws SlickException;
	
	public abstract void render(GameContainer container, Graphics g) throws SlickException;
}


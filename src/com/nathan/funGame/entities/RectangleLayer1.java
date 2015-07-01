package com.nathan.funGame.entities;

import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import com.nathan.funGame.collision.Collidable;
import com.nathan.funGame.collision.CollisionSystem;

public class RectangleLayer1 extends BaseEntity implements Collidable {
	private Random r = new Random();
	private Rectangle sprite;
		
	public RectangleLayer1(int x, int y) {
		super(1);
		x = r.nextInt(1920);
		y = r.nextInt(1080);
		CollisionSystem.getInstance().register(this);
		this.sprite = new Rectangle(x, y, r.nextFloat() * 100, r.nextFloat() * 100);
	}
	
	
	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		// TODO Auto-generated method stub
		sprite.setX(sprite.getX() + 100 * 0.001f * delta);
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		g.setColor(Color.green);
		g.fill(sprite);
	}



	@Override
	public Shape getCollisionBouds() {
		// TODO Auto-generated method stub
		return sprite;
	}



	@Override
	public boolean collidesWith(Collidable c) {
		return sprite.intersects(c.getCollisionBouds());
	}

}

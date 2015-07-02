package com.nathan.funGame.entities;

import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import com.nathan.funGame.Game;
import com.nathan.funGame.collision.Collidable;
import com.nathan.funGame.collision.CollisionSystem;
import com.nathan.funGame.events.Event;
import com.nathan.funGame.events.EventHandler;

public class RectangleLayer2 extends BaseEntity implements Collidable {
	private static final int SPEED = 400;
	
	private Rectangle sprite;
	private Random r = new Random();
	private int direction;
	private static boolean isOutlined = true;
	
	public RectangleLayer2(int direction) {
		super(2);
		this.direction = direction;
		CollisionSystem.getInstance().register(this);
		
		if(direction == 0)
			this.sprite = new Rectangle(r.nextInt(1920), -100, r.nextFloat() * 100 + 50, r.nextFloat() * 100 + 50);
		else if (direction == 1)
			this.sprite = new Rectangle(r.nextInt(1920), 1180, r.nextFloat() * 100 + 50, r.nextFloat() * 100 + 50);
		else if (direction == 2)
			this.sprite = new Rectangle(-100, r.nextInt(1080), r.nextFloat() * 100 + 50, r.nextFloat() * 100 + 50);
		else
			this.sprite = new Rectangle(2020, r.nextInt(1080), r.nextFloat() * 100 + 50, r.nextFloat() * 100 + 50);
		
		Game.addEventListener("ChangeLayer", new EventHandler() {
			
			@Override
			public void handleEvent(Event e) {
				int new_layer = (int) e.data[0];
				if(new_layer == 2)
					isOutlined = false;
				else
					isOutlined = true;
			}
			
		});
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		if (direction == 0) 
			sprite.setY(sprite.getY() + SPEED * 0.001f * delta);
		if (direction == 1)
			sprite.setY(sprite.getY() - SPEED * 0.001f * delta);
		if (direction == 2)
			sprite.setX(sprite.getX() + SPEED * 0.001f * delta);
		if(direction == 3)
			sprite.setX(sprite.getX() - SPEED * 0.001f * delta);
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		g.setColor(Color.blue);
		if(!isOutlined)
			g.fill(sprite);
		else
			g.draw(sprite);
	}

	@Override
	public Shape getCollisionBouds() {
		return sprite;
	}
	

	@Override
	public boolean collidesWith(Collidable c) {
		return sprite.intersects(c.getCollisionBouds());
	}

}

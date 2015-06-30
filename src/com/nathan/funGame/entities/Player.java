package com.nathan.funGame.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import com.nathan.funGame.EntityManager;
import com.nathan.funGame.collision.Collidable;
import com.nathan.funGame.collision.CollisionSystem;

public class Player extends BaseEntity implements Collidable {
	
	private Rectangle sprite;
	private float speed = 350;
	
	public Player(int zIndex) {
		super(zIndex);
		sprite = new Rectangle(0, 0, 20, 20);
	}
	
	public Player(int zIndex, float centerX, float centerY, float width, float height, float speed) {
		super(zIndex);
		sprite = new Rectangle(0, 0, width, height);
		sprite.setCenterX(centerX);
		sprite.setCenterY(centerY);
		this.speed = speed;
		CollisionSystem.getInstance().register(this);
	}
	
	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		if (container.getInput().isKeyDown(Input.KEY_RIGHT)) 
			sprite.setCenterX(sprite.getCenterX() + (speed * 0.001f * delta));
		if (container.getInput().isKeyDown(Input.KEY_LEFT)) 
			sprite.setCenterX(sprite.getCenterX() - (speed * 0.001f * delta));
		if (container.getInput().isKeyDown(Input.KEY_UP)) 
			sprite.setCenterY(sprite.getCenterY() - (speed * 0.001f * delta));
		if (container.getInput().isKeyDown(Input.KEY_DOWN)) 
			sprite.setCenterY(sprite.getCenterY() + (speed * 0.001f * delta));
		
		if (sprite.getX() > 640 - sprite.getWidth()) {
			sprite.setX(640 - sprite.getWidth());
		}
		if (sprite.getX() < 1) {
			sprite.setX(0);
		}
		if (sprite.getY() > 480 - sprite.getHeight()) {
			sprite.setY(480 -  sprite.getHeight());
		}
		if (sprite.getY() < 1) {
			sprite.setY(0);
		}
		
		if (container.getInput().isKeyPressed(Input.KEY_A)) {
			if(getZIndex() < 3) {
				EntityManager.getInstance().remove(this);
				EntityManager.getInstance().spawn(new Player(getZIndex() + 1, sprite.getCenterX(), sprite.getCenterY(), 
															sprite.getWidth() + 7, sprite.getHeight() + 7, speed - 70));
			}
		} 
		
		if (container.getInput().isKeyPressed(Input.KEY_D)) {
			if (getZIndex() > 1) {
				EntityManager.getInstance().remove(this);
				EntityManager.getInstance().spawn(new Player(getZIndex() - 1, sprite.getCenterX(), sprite.getCenterY(), 
						sprite.getWidth() - 7, sprite.getHeight() - 7, speed + 70));
			}
		}
			
	}

	@Override
	public void render(GameContainer container, Graphics g)	throws SlickException {
		// TODO Auto-generated method stub
		if(getZIndex() == 1)
			g.setColor(Color.green);
		else if(getZIndex() == 2)
			g.setColor(Color.blue);
		else if(getZIndex() == 3)
			g.setColor(Color.red);
		g.fill(sprite);
	}

	@Override
	public Shape getCollisionBouds() {
		// TODO Auto-generated method stub
		return sprite;
	}

	@Override
	public boolean collidesWith(Collidable c) {
		// TODO Auto-generated method stub
		return sprite.intersects(c.getCollisionBouds());
	}

}

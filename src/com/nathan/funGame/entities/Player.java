package com.nathan.funGame.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import com.nathan.funGame.EntityManager;
import com.nathan.funGame.Game;
import com.nathan.funGame.collision.Collidable;
import com.nathan.funGame.collision.CollisionSystem;
import com.nathan.funGame.events.Event;
import com.nathan.funGame.events.EventHandler;

public class Player extends BaseEntity implements Collidable {
	
	private Rectangle sprite;
	private float speed = 350;
	private static int yourScore = 0;
	private static boolean isDead = false;
	private int timer = 0;
	
	public Player(int zIndex) {
		super(zIndex);
		sprite = new Rectangle(480, 270, 20, 20);
		setupCollision();
	}
	
	public Player (int zIndex, float centerX, float centerY, float width, float height, float speed) {
		super(zIndex);
		sprite = new Rectangle(0, 0, width, height);
		sprite.setCenterX(centerX);
		sprite.setCenterY(centerY);
		this.speed = speed;
		setupCollision();
	}
	
	private void setupCollision() {
		CollisionSystem.getInstance().register(this);

		addEventListener("CollisionEvent", new EventHandler() {

			@Override
			public void handleEvent(Event e) {
				if(e.data[0] instanceof RectangleLayer1 & getZIndex() == 1) {
					isDead = true;
				}
				if(e.data[0] instanceof RectangleLayer2 & getZIndex() == 2) {
					isDead = true;
				}
				if(e.data[0] instanceof RectangleLayer3 & getZIndex() == 3) {
					isDead = true;
				}
			}

		});
	}
	
	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		if(!isDead) {
			if (container.getInput().isKeyDown(Input.KEY_RIGHT)) 
				sprite.setCenterX(sprite.getCenterX() + (speed * 0.001f * delta));
			if (container.getInput().isKeyDown(Input.KEY_LEFT)) 
				sprite.setCenterX(sprite.getCenterX() - (speed * 0.001f * delta));
			if (container.getInput().isKeyDown(Input.KEY_UP)) 
				sprite.setCenterY(sprite.getCenterY() - (speed * 0.001f * delta));
			if (container.getInput().isKeyDown(Input.KEY_DOWN)) 
				sprite.setCenterY(sprite.getCenterY() + (speed * 0.001f * delta));

			if (sprite.getX() > 1920 - sprite.getWidth()) {
				sprite.setX(1920 - sprite.getWidth());
			}
			if (sprite.getX() < 1) {
				sprite.setX(0);
			}
			if (sprite.getY() > 1080 - sprite.getHeight()) {
				sprite.setY(1080 -  sprite.getHeight());
			}
			if (sprite.getY() < 1) {
				sprite.setY(0);
			}

			if (container.getInput().isKeyPressed(Input.KEY_A)) {
				if(getZIndex() < 3) {
					EntityManager.getInstance().remove(this);
					CollisionSystem.getInstance().unregister(this);
					EntityManager.getInstance().spawn(new Player(getZIndex() + 1, sprite.getCenterX(), sprite.getCenterY(), 
							sprite.getWidth() + 7, sprite.getHeight() + 7, speed - 70));
					Game.invokeEvent(new Event("ChangeLayer", new Object[] {getZIndex() + 1}));
				}
			} 

			if (container.getInput().isKeyPressed(Input.KEY_D)) {
				if (getZIndex() > 1) {
					EntityManager.getInstance().remove(this);
					CollisionSystem.getInstance().unregister(this);
					EntityManager.getInstance().spawn(new Player(getZIndex() - 1, sprite.getCenterX(), sprite.getCenterY(), 
							sprite.getWidth() - 7, sprite.getHeight() - 7, speed + 70));
					Game.invokeEvent(new Event("ChangeLayer", new Object[] {getZIndex() - 1}));
				}
			}

			if(timer >= 1000) {
				yourScore += Math.ceil(2) + (sprite.getHeight()) / 2;
				timer = 0;
			} else {
				timer += delta;
			}
		}
	}

	@Override
	public void render(GameContainer container, Graphics g)	throws SlickException {
		// TODO Auto-generated method stub
		if(getZIndex() == 1)
			g.setColor(Color.green);
		else if(getZIndex() == 2)
			g.setColor(Color.cyan);
		else if(getZIndex() == 3)
			g.setColor(Color.red);
		g.fill(sprite);
		
		g.drawString("Score: " + yourScore, 8, 50);
		
		if (isDead) {
			g.drawString("GAME OVER, your score is " + yourScore, 420, 270);
			g.setColor(Color.orange);
		}
		
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

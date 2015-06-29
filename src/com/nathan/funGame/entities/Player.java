package com.nathan.funGame.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import com.nathan.funGame.EntityManager;

public class Player extends BaseEntity {
	
	private Rectangle sprite;
	private float speed = 350;
	
	public Player(int zIndex) {
		super(zIndex);
		sprite = new Rectangle(0, 0, 35, 35);
	}
	
	public Player(int zIndex, float centerX, float centerY, float width, float height, float speed) {
		super(zIndex);
		sprite = new Rectangle(0, 0, width, height);
		sprite.setCenterX(centerX);
		sprite.setCenterY(centerY);
		this.speed = speed;
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

}

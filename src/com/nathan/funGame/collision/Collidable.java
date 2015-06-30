package com.nathan.funGame.collision;

import org.newdawn.slick.geom.Shape;

import com.nathan.funGame.events.Event;

public interface Collidable {
	public Shape getCollisionBouds();
	
	public boolean collidesWith(Collidable c);
	
	public void invokeEvent(Event e);
}

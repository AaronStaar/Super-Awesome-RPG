package com.nathan.funGame.collision;

import com.nathan.funGame.events.Event;

public class CollisionEvent extends Event{
	
	public CollisionEvent(Object collider) {
		super("CollisionEvent", new Object[] {collider});
	}
}

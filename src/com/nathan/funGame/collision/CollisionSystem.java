package com.nathan.funGame.collision;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CollisionSystem {
	private static CollisionSystem instance = new CollisionSystem();
	
	private ArrayList<Collidable> collisionList = new ArrayList<>();
	
	private CollisionSystem() {}
	
	public static CollisionSystem getInstance() {
		return instance;
	}
	
	public void register(Collidable c) {
		collisionList.add(c);
		Logger.getLogger(CollisionSystem.class.getSimpleName()).log(Level.INFO, "Registered new entity: " + c.getClass().getSimpleName());
	}
	
	public void unregister(Collidable c) {
		collisionList.remove(c);
	}
	
	public void update() {
		Collidable c1;
		Collidable c2;
		
		for(int j=0; j < collisionList.size(); j++) {
			for(int k=j+1; k < collisionList.size(); k++) {
				c1 = collisionList.get(j);
				c2 = collisionList.get(k);
				if(c1.collidesWith(c2)) {
					c1.invokeEvent(new CollisionEvent(c2));
					c2.invokeEvent(new CollisionEvent(c1));
				}
			}
		}
	}
}

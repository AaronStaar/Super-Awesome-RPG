package com.nathan.funGame;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.nathan.funGame.entities.BaseEntity;
import com.nathan.funGame.events.EventSystem;
import com.nathan.funGame.events.SpawnEvent;

/** 
 * Singleton (only one instance allowed) class in charge of managing entities.
 * @author Isaac
 *
 */
public class EntityManager extends EventSystem {
	
	private static EntityManager instance = new EntityManager();
	
	//Ordering it this way so we can render the entities based on z-index
	private TreeMap<Integer, ArrayList<BaseEntity>> entityList = new TreeMap<Integer, ArrayList<BaseEntity>>();
	private ArrayList<BaseEntity> despawnList = new ArrayList<>();
	private ArrayList<BaseEntity> spawnList = new ArrayList<>();
	
	private EntityManager() {}
	
	/**
	 * Gets the instance of this class. This method will return the exact same instance each time it is called.
	 */
	public static EntityManager getInstance() {
		return instance;
	}
	
	/**
	 * Updates the entities. Package-protected because only the Game class should call this function.
	 * 
	 * Updates functions based on z-index, but that really doesn't matter
	 * @param container
	 * @param delta
	 * @throws SlickException
	 */
	void __updateEntities(GameContainer container, int delta) throws SlickException {
		for(Map.Entry<Integer, ArrayList<BaseEntity>> es : entityList.entrySet()) {
			for(BaseEntity ent : es.getValue())
				ent.update(container, delta);
		}
		
		//Depsawn entities
		for(BaseEntity d : despawnList) {
			__remove(d);
		}
		despawnList.clear();
		
		for(BaseEntity s : spawnList) {
			__spawn(s);
		}
		spawnList.clear();
	}
	
	/**
	 * Renders the entities. Package-protected because only the Game class should call this function. 
	 * 
	 * Renders entities based on their z-index.
	 * @param container
	 * @param delta
	 * @throws SlickException
	 */
	void __renderEntities(GameContainer container, Graphics g) throws SlickException {
		for(Map.Entry<Integer, ArrayList<BaseEntity>> es : entityList.entrySet()) {
			for(BaseEntity ent : es.getValue())
				ent.render(container, g);
		}
	}
	
	/**
	 * Spawns a new entity and add it to entList
	 * @param ent The entity to spawn
	 */
	public void spawn(BaseEntity ent) {
		spawnList.add(ent);
	}
	
	private void __spawn(BaseEntity ent) {
		int index;
		
		if(entityList.get(ent.getZIndex()) == null) {
			//ArrayList doesn't exist so 
			ArrayList<BaseEntity> newList = new ArrayList<BaseEntity>();
			newList.add(ent);
			index = newList.size() - 1;
			entityList.put(ent.getZIndex(), newList);
		} else {
			ArrayList<BaseEntity> updateList = entityList.get(ent.getZIndex());
			updateList.add(ent);
			index = updateList.size() - 1;
			entityList.put(ent.getZIndex(), updateList);
		}
		
		Logger.getLogger(this.getClass().getSimpleName()).log(Level.INFO, "Spawned a new entity, " + ent.getClass().getSimpleName());
		ent.invokeEvent(new SpawnEvent(index));
		this.invokeEvent(new SpawnEvent(index));
	}
	
	private void __remove(BaseEntity ent) {
		ArrayList<BaseEntity> updateList = entityList.get(ent.getZIndex());
		updateList.remove(ent);
		entityList.put(ent.getZIndex(), updateList);
	}
	
	public void remove(BaseEntity ent) {
		despawnList.add(ent);
	}
}

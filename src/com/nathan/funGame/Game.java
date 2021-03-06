package com.nathan.funGame;


import java.util.Random;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.nathan.funGame.collision.CollisionSystem;
import com.nathan.funGame.entities.Player;
import com.nathan.funGame.entities.RectangleLayer1;
import com.nathan.funGame.entities.SpawnerLayer1;
import com.nathan.funGame.events.Event;
import com.nathan.funGame.events.EventHandler;
import com.nathan.funGame.events.EventSystem;

/**
 * Contains all the code that makes events work. It should also be used to invoke and bind to "global" or "miscellaneous" events.
 * @author Isaac
 *
 */
public class Game extends BasicGame {
	
	//Since we can't extend two classes
	private static EventSystem es = new EventSystem();
	private InputBindings bindings;
	
	public Game(String title) {
		super(title);
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		EntityManager.getInstance().__renderEntities(container, g);
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		bindings = new InputBindings(container.getInput());
		
		EntityManager.getInstance().spawn(new Player(1));
		EntityManager.getInstance().spawn(new SpawnerLayer1());
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		EntityManager.getInstance().__updateEntities(container, delta);
		CollisionSystem.getInstance().update();
	}
	
	/**
	 * Adds an EventLister to this object. The listener will be invoked whenever this entity, or other objects, calls it's invokeEvent method.
	 * @param e The event to listen for
	 * @param handler The EventHandler to handle the event
	 */
	public static void addEventListener(Event e, EventHandler handler) {
		es.addEventListener(e, handler);
	}
	
	public static void addEventListener(String eventName, EventHandler handler) {
		es.addEventListener(eventName, handler);
	}
	
	/**
	 * Invokes an event in relation to the entity. It will call the EventHandler of other objects that have attached themselves to this event.
	 * @param e The event to invoke
	 */
	public static void invokeEvent(Event e) {
		es.invokeEvent(e);
	}
}

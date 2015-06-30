package com.nathan.funGame.events;

public class Event {
	public String name;
	public Object[] data;
	
	public Event(String name) {
		this.name = name;
	}
	
	public Event(String name, Object[] data) {
		this.data = data;
		this.name = name;
	}
}

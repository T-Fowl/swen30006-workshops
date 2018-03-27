package com.tfowl.lms.commands;

import com.tfowl.lms.State;

public abstract class Command {

	private String name;
	private State state;

	public Command(String name, State state) {
		this.name = name;
		this.state = state;
	}

	public abstract boolean exec(String[] args);

	protected State getState() {
		return state;
	}

	public String getName() {
		return name;
	}
}

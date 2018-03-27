package com.tfowl.lms.commands;

import com.tfowl.lms.State;

import java.util.concurrent.Callable;

public abstract class Command implements Callable<Boolean> {

	private String name;
	private State state;

	public Command(String name, State state) {
		this.name = name;
		this.state = state;
	}

	protected State getState() {
		return state;
	}

	public String getName() {
		return name;
	}
}

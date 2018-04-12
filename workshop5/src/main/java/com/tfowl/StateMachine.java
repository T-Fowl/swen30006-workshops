package com.tfowl;

public abstract class StateMachine {

	private State state;

	public StateMachine(State initialState) {
		become(initialState);
	}

	public State getCurrentState() {
		return state;
	}

	public void handle(String message) {
		state.handle(this::become, message);
	}

	private void become(State state) {
		System.out.println("Changing state " + this.state + " -> " + state);
		this.state = state;
	}
}

package com.tfowl.states;

import com.tfowl.State;

import java.util.function.Consumer;

public class ClosedState implements State {
	@Override
	public void handle(Consumer<State> become, String message) {

	}
}

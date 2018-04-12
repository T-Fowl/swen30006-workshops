package com.tfowl.states;

import com.tfowl.State;

import java.util.function.Consumer;

public class InactiveState implements State {

	@Override
	public void handle(Consumer<State> become, String message) {
		if (message.equalsIgnoreCase("newproduct"))
			become.accept(new ActiveState());
	}
}

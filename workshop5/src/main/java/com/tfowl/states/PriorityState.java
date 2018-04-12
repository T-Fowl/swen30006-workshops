package com.tfowl.states;

import com.tfowl.State;

import java.util.function.Consumer;

public class PriorityState implements State {

	@Override
	public void handle(Consumer<State> become, String message) {
		if (message.equalsIgnoreCase("resolved"))
			become.accept(new ActiveState());
	}
}

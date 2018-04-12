package com.tfowl.states;

import com.tfowl.State;

import java.util.function.Consumer;

public class PendingState implements State {

	@Override
	public void handle(Consumer<State> become, String message) {
		if(message.equalsIgnoreCase("approve"))
			become.accept(new ActiveState());
	}
}

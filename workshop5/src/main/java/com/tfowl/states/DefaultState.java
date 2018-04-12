package com.tfowl.states;

import com.tfowl.State;

import java.util.function.Consumer;

public class DefaultState implements State {

	@Override
	public void handle(Consumer<State> become, String message) {
		if (message.equalsIgnoreCase("pay"))
			become.accept(new ActiveState());
		else if (message.equalsIgnoreCase("noresponse"))
			become.accept(new ClosedState());
	}
}

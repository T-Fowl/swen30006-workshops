package com.tfowl.states;

import com.tfowl.State;

import java.util.function.Consumer;

public class ActiveState implements State {

	@Override
	public void handle(Consumer<State> become, String message) {
		if (message.equalsIgnoreCase("noproducts"))
			become.accept(new InactiveState());
		else if (message.equalsIgnoreCase("complain"))
			become.accept(new PriorityState());
		else if (message.equalsIgnoreCase("failbill"))
			become.accept(new DefaultState());
	}
}

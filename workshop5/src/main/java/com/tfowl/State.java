package com.tfowl;

import java.util.function.Consumer;

@FunctionalInterface
public interface State {

	public void handle(Consumer<State> become, String message);
}

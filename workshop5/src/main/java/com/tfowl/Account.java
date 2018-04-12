package com.tfowl;

import com.tfowl.states.PendingState;

public class Account extends StateMachine {

	public Account() {
		super(new PendingState());
	}
}

package com.tfowl;

import com.tfowl.states.ClosedState;

import java.util.Scanner;

public class Controller {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		Account account = new Account();

		while (!(account.getCurrentState() instanceof ClosedState)) {
			String transition = scanner.nextLine();
			account.handle(transition);
		}
	}
}

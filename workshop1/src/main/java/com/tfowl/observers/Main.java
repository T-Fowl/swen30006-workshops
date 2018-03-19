package com.tfowl.observers;

import java.time.LocalTime;

public class Main {

	public static void main(String[] args) {
		SimpleSubject<Integer> currentTimeSeconds = new SimpleSubject<>(LocalTime.now().getSecond());
		SimpleSubject<Integer> currentTimeMinutes = new SimpleSubject<>(LocalTime.now().getMinute());

		Observer<Integer> timeObserver = ((subject, oldValue, newValue) -> {
			if (subject == currentTimeSeconds) {
				System.out.println("Seconds changed from " + oldValue + " to " + newValue);
			} else if (subject == currentTimeMinutes) {
				System.out.println("Minutes changed from " + oldValue + " to " + newValue);
			}
		});

		currentTimeSeconds.registerObserver(timeObserver);
		currentTimeMinutes.registerObserver(timeObserver);

		for(int i = 0; i < 65; i++) {
			LocalTime time = LocalTime.now().plusSeconds(i);
			currentTimeSeconds.setValue(time.getSecond());
			currentTimeMinutes.setValue(time.getMinute());
		}

	}
}

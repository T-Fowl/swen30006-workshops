package com.tfowl.observers;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;

import java.time.LocalTime;

public class UsingFX {

	public static void main(String[] args) {


		ObjectProperty<Integer> secondsProperty = new SimpleObjectProperty<>(LocalTime.now().getSecond());
		ObjectProperty<Integer> minutesProperty = new SimpleObjectProperty<>(LocalTime.now().getMinute());

		ChangeListener<Integer> listener = (observavable, oldval, newval) -> {
			if (observavable == secondsProperty)
				System.out.println("Seconds changed from " + oldval + " to " + newval);
			else if (observavable == minutesProperty)
				System.out.println("Minutes changed from " + oldval + " to " + newval);
		};

		secondsProperty.addListener(listener);
		minutesProperty.addListener(listener);

		for (int i = 0; i < 10; i++) {
			LocalTime time = LocalTime.now().plusSeconds(i * 5);
			secondsProperty.setValue(time.getSecond());
			minutesProperty.setValue(time.getMinute());
		}
	}
}

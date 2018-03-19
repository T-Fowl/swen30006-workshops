package com.tfowl.observers;

import java.util.*;

public class SimpleSubject<T> implements Subject<T> {

	private T value;
	private Set<Observer<T>> observers = new HashSet<>();

	public SimpleSubject(T value) {
		this.value = value;
	}

	@Override
	public void registerObserver(Observer<T> observer) {
		observers.add(observer);
	}

	@Override
	public void deregisterObserver(Observer<T> observer) {
		observers.remove(observer);
	}

	private void tellAll(T oldValue, T newValue) {
		observers.forEach(o -> o.onChange(this, oldValue, newValue));
	}

	public void setValue(T value) {
		if (!value.equals(this.value)) {
			T old = this.value;
			this.value = value;
			tellAll(old, this.value);
		}
	}

	public T getValue() {
		return this.value;
	}
}

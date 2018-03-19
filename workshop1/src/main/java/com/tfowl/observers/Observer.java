package com.tfowl.observers;

@FunctionalInterface
public interface Observer<T> {

	public void onChange(Subject<T> subject, T oldValue, T newValue);
}

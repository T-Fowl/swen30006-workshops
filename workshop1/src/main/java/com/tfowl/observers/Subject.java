package com.tfowl.observers;

public interface Subject<T> {

	public void registerObserver(Observer<T> observer);

	public void deregisterObserver(Observer<T> observer);
}

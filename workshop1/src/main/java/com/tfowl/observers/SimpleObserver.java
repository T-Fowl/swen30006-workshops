package com.tfowl.observers;

public class SimpleObserver<T> implements Observer<T> {

	@Override
	public void onChange(Subject<T> subject, T oldValue, T newValue) {
		System.out.println(this.toString() + ": subject = [" + subject + "], oldValue = [" + oldValue + "], newValue = [" + newValue + "]");
	}
}

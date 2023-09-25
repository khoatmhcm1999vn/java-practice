package com.mk.examples.refactor.collection;

public class ArrayListIterator<T> implements Iterator<T> {

	private ArrayList<T> arrayList;
	
	private int currentIndex;
	
	public ArrayListIterator(ArrayList<T> arrayList) {
		this.arrayList = arrayList;
		this.currentIndex = 0;
	}
	
	@Override
	public boolean hasNext() {		
		return (this.currentIndex < this.arrayList.getCurrentIndex());
	}

	@Override
	public T next() {		
		return (T) (this.arrayList.getAt(this.currentIndex++));
	}
}

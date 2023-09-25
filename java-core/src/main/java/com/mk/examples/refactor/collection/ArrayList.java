package com.mk.examples.refactor.collection;

import java.util.HashMap;
import java.util.Map;

public class ArrayList<T> extends AbstractList<T> {

    private static final int SCALE_CONSTANT = 2;

    private static final int DEFAULT_LENGTH = 10;

    private Object[] array;

    private int length;

    private int currentIndex;

    public ArrayList() {
        this.currentIndex = 0;
        this.length = DEFAULT_LENGTH;
        this.array = new Object[this.length];
    }

    @Override
    public T get(int index) {
        return null;
    }

    public ArrayList(int length) {
        this.currentIndex = 0;
        this.length = length;
        this.array = new Object[this.length];
    }

    public ArrayList(Object[] inArray) {
        this.setArray(inArray);
        this.currentIndex = inArray.length;
        this.setLength(inArray.length * SCALE_CONSTANT);
    }

    public ArrayList(List<T> inList) {
        this.currentIndex = 0;
        this.length = inList.size();
        this.array = new Object[this.length];

        Iterator<T> iterator = inList.iterator();
        while (iterator.hasNext()) {
            this.insert(iterator.next());
            this.currentIndex++;
        }
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Object[] getArray() {
        return array;
    }

    public void setArray(Object[] array) {
        this.array = array;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    @Override
    public boolean insert(T inValue) {
        if (this.currentIndex == this.length) {
            Object[] tempArr = this.array;
            this.length = (this.length * SCALE_CONSTANT);
            this.array = new Object[this.length];
            this.scaleUpMainArray(tempArr);
        }
        this.array[currentIndex++] = inValue;
        return true;
    }

    public boolean removeAt(int index) {
        if (index >= this.length || index < 0) {
            return false;
        }
        this.switchElementPositionToLeft(index);
        return true;
    }

    public T getAt(int index) {
        if (index >= this.length || index < 0) {
            return null;
        }
        return (T) (this.array[index]);
    }

    private Object[] switchElementPositionToLeft(int startIndex) {
        for (int i = startIndex; i < this.currentIndex - 1; i++) {
            this.array[i] = this.array[i + 1];
        }
        this.currentIndex--;
        return this.array;
    }

    private Object[] switchElementPositionToRight(int startIndex) {
        for (int i = this.currentIndex; i > startIndex; i--) {
            this.array[i] = this.array[i - 1];
        }
        return this.array;
    }

    private Object[] scaleUpMainArray(Object[] oldArray) {
        int i = 0;
        for (Object item : oldArray) {
            this.array[i] = item;
            i++;
        }
        return this.array;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");
        for (int i = 0; i < this.currentIndex; i++) {
            stringBuilder.append(this.array[i]);
            stringBuilder.append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public boolean contain(T inValue) {
        for (Object item : this.array) {
            T value = (T) (item);
            if (value.equals(inValue)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return (this.currentIndex == 0);
    }

    @Override
    public void clear() {
        for (int i = 0; i < this.length; i++) {
            this.array[i] = null;
        }
        this.currentIndex = 0;
        this.length = DEFAULT_LENGTH;
        this.array = new Object[this.length];
    }

    /**
     * This method maybe be removed in the future - I consider for creating the AbstractList class in the near future which contains
     * all common functions of List classes
     * This method is used to insert a collection of T value to our current Array List
     *
     * @param collection which is the collection we want to insert to our current Array List
     */
    public void insertAll(Collection<T> collection) {
        Iterator<T> iterator = collection.iterator();
        if (iterator.hasNext()) {
            T currentValue = iterator.next();
            this.insert(currentValue);
        }
    }

    @Override
    public int size() {
        return this.length;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator<>(this);
    }

    @Override
    public boolean retainAll(java.util.Collection<?> c) {
        return false;
    }

    public T max() {
        // TODO Auto-generated method stub
        return null;
    }

    public ArrayList<T> intersect(ArrayList<T> opponent) {
        Map<Object, Integer> intersectMap = new HashMap<>();
        int firstLength = this.getCurrentIndex();
        int secondLength = opponent.getCurrentIndex();
        int finalLength = Math.max(firstLength, secondLength);

        for (int i = 0; i < finalLength; i++) {
            Object mainItem = this.getAt(i);
            Object opponentItem = opponent.getAt(i);

            if (intersectMap.containsKey(mainItem)) {
                Integer currentValue = intersectMap.get(mainItem);
                intersectMap.put(mainItem, currentValue + 1);
            } else {
                intersectMap.put(mainItem, 1);
            }

            if (intersectMap.containsKey(opponentItem)) {
                Integer currentValue = intersectMap.get(opponentItem);
                intersectMap.put(opponentItem, currentValue + 1);
            } else {
                intersectMap.put(opponentItem, 1);
            }
        }

        ArrayList<T> output = new ArrayList<>();
        for (Map.Entry<Object, Integer> item : intersectMap.entrySet()) {
            if (item.getValue() > 1) {
                output.insert((T) item.getKey());
            }
        }
        return output;
    }

    @Override
    public void reverse() {
        Object[] reverseArray = new Object[this.size()];
        int reverseIndex = 0;
        for (int i = this.getCurrentIndex() - 1; i >= 0; i--) {
            Object item = this.array[i];
            reverseArray[reverseIndex++] = item;
        }
        this.array = reverseArray;
    }

    @Override
    public boolean insertAt(T value, int index) {
        if (index >= this.size() || index < 0) {
            return false;
        }
        if (this.getCurrentIndex() == this.size()) {
            this.insert(null);
        }
        switchElementPositionToRight(index);
        this.array[index] = value;
        return true;
    }

    @Override
    public int indexOf(Object value) {
        // TODO Auto-generated method stub
        return 0;
    }
}

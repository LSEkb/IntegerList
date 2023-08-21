package org.laserova;

import java.util.Arrays;

public class IntegerListImpl implements IntegerList {

    private int size;

    private Integer[] storage;

    private final int DEFAULT_INITIAL_CAPACITY = 10;

    public IntegerListImpl() {
        this.storage = new Integer[DEFAULT_INITIAL_CAPACITY];
        size = 0;
    }

    public IntegerListImpl(int length) {
        this.storage = new Integer[length];
        size = 0;
    }

    public IntegerListImpl(Integer[] integers) {
        this.storage = new Integer[integers.length];
        for (int i = 0; i < integers.length; i++) {
            this.storage[i] = integers[i];
        }
        this.size = integers.length;
    }

//    @Override
//    public String toString() {
//        String a = "[ ";
//
//        StringBuilder values = new StringBuilder();
//        for (int i = 0; i < this.storage.length; i++) {
//            if (storage[i] != null) {
//                values.append(storage[i] + " ");
//            }
//        }
//        String b = values.toString();
//        String c = "]";
//        return a + b + c;
//    }

    @Override
    public Integer add(Integer item) {
        validateItem(item);
        if (size == storage.length) {
            this.grow(storage.length);
        }
//        storage[size] = item;
//        size++;
//        return item;
        size++;
        return storage[size];
    }

    @Override
    public Integer add(int index, Integer item) {
        validateIndex(index);
        validateItem(item);
        if (size == storage.length) {
            this.grow(storage.length);
        }
//        for (int i = size; i > index; i--) {
//            storage[i] = storage[i - 1];
//        }
//        size++;
//        return storage[index] = item;
        if (index==size){
            storage[size++]=item;
            return item;
        }
        System.arraycopy(storage,index,storage,index+1,size-index);
        storage[index]=item;
        size++;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        validateIndex(index);
        validateItem(item);
        return storage[index]=item;
    }

    @Override
    public Integer remove(Integer item) {
        validateItem(item);
        int index = indexOf(item);
        return remove(index);
    }

    @Override
    public Integer remove(int index) {
        validateIndex(index);
        Integer item =storage[index];
        if (index!=size){
            System.arraycopy(storage,index+1,storage,index,size-index);
        }
        size--;
        return item;
    }

    @Override
    public boolean contains(Integer item) {
        return indexOf(item)!=-1;
    }

    @Override
    public int indexOf(Integer item) {
        validateItem(item);
        for (int i = 0; i < size; i++) {
            if (storage[i].equals(item)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        validateItem(item);
        for (int i = size; i >=0 ; i--) {
            if (storage[i].equals(item)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        validateIndex(index);
        return storage[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        if (otherList == this) {
            return true;
        }
        if (otherList == null) {
            throw new RuntimeException("Null is not accepted");
        } else {
            if (this.size() == otherList.size()) {
                for (int i = 0; i < size; i++) {
                    if (!this.storage[i].equals(otherList.get(i))) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(storage, size);
    }

    private void validateItem(Integer item) {
        if (item == null) {
            throw new NullItemException();
        }
    }

    private void validateIndex(int index) {
        if (index < 0 || index > size) {
            throw new InvalidIndexException();
        }
    }

    private void grow(int length) {
        length = length * 3 / 2 + 1;
        Integer[] newStorage = new Integer[length];
        if (size >= 0) System.arraycopy(storage, 0, newStorage, 0, size);
        storage = newStorage;
    }
}

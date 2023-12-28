package ru.skypro.course2.valiev.StringList.Impl;

import ru.skypro.course2.valiev.StringList.StringList;
import ru.skypro.course2.valiev.exception.StringListIndexOutOfRangeException;
import ru.skypro.course2.valiev.exception.StringListNullException;
import ru.skypro.course2.valiev.exception.StringListNullItemException;

import java.util.Arrays;

public class StringListImpl implements StringList {
    private String[] stringArray;
    private int listSize;

    public StringListImpl() {
        this.listSize = 0;
        this.stringArray = new String[10];
    }

    @Override
    public String add(String item) {
        validateItem(item);
        if(listSize >= stringArray.length) {
            increaseArray();
        }
        listSize++;
        return stringArray[listSize - 1] = item;
    }

    @Override
    public String add(int index, String item) {
        validateIndex(index);
        validateItem(item);
        if (listSize >= stringArray.length) {
            increaseArray();
        }
        if (!(index == listSize - 1)) {
            for (int i = listSize; i > index; i--) {
                stringArray[i] = stringArray[i - 1];
            }
        }
        listSize++;
        return stringArray[index] = item;
    }

    @Override
    public String set(int index, String item) {
        validateIndex(index);
        validateItem(item);
        return stringArray[index] = item;
    }

    @Override
    public String remove(String item) {
        validateItem(item);
        int index = indexOf(item);
        return remove(index);
    }

    @Override
    public String remove(int index) {
        validateIndex(index);
        String tmp = stringArray[index];
        for (int i = index; i < listSize; i++) {
            stringArray[i] = stringArray[i + 1];
        }
        listSize--;
        if (stringArray.length > 10 && stringArray.length > listSize * 1.3F) {
            decreaseArray();
        }
        return tmp;
    }

    @Override
    public boolean contains(String item) {
        validateItem(item);
        for (int i = 0; i < listSize; i++) {
            if (stringArray[i].equals(item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(String item) {
        validateItem(item);
        for (int i = 0; i < listSize; i++) {
            if (stringArray[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        validateItem(item);
        for (int i = listSize - 1; i >= 0; i--) {
            if (stringArray[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        validateIndex(index);
        return stringArray[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        validateStringList(otherList);
        return Arrays.equals(this.toArray(), otherList.toArray());
    }

    @Override
    public int size() {
        return listSize;
    }

    @Override
    public boolean isEmpty() {
        return listSize == 0;
    }

    @Override
    public void clear() {
        listSize = 0;
    }

    @Override
    public String[] toArray() {
        return Arrays.copyOf(stringArray, listSize);
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= listSize) {
            throw new StringListIndexOutOfRangeException("Index out of range!");
        }
    }

    private void validateItem(String item) {
        if (item == null) {
            throw new StringListNullItemException("Item cannot be null!");
        }
    }

    private void validateStringList(StringList stringList) {
        if (stringList == null) {
            throw new StringListNullException("StringList cannot be null!");
        }
    }

    /**
     * Creating a new String[] array with a size 30% larger than the previous one
     */
    private void increaseArray() {
        stringArray = Arrays.copyOf(stringArray, (int) (listSize * 1.3F));
    }

    /**
     * Creating a new String[] array with a size 30% smaller than the previous one
     */
    private void decreaseArray() {
        stringArray = Arrays.copyOf(stringArray, listSize + 10);
    }
}


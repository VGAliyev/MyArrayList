package ru.skypro.course2.valiev.StringList.Impl;

import ru.skypro.course2.valiev.StringList.StringList;
import ru.skypro.course2.valiev.exception.StringListElementNotFound;
import ru.skypro.course2.valiev.exception.StringListIndexOutOfRangeException;
import ru.skypro.course2.valiev.exception.StringListNullException;
import ru.skypro.course2.valiev.exception.StringListNullItemException;

import java.util.Arrays;

public class StringListImpl implements StringList {
    private String[] stringArray;
    private int arraySize;
    private int listSize;

    public StringListImpl() {
        this.arraySize = 10;
        this.listSize = 0;
        this.stringArray = new String[arraySize];
    }

    @Override
    public String add(String item) {
        if (item == null) {
            throw new StringListNullItemException("Item must not be null!");
        }
        if(listSize + 1 < arraySize) {
            stringArray[listSize] = item;
            listSize++;
        } else {
            increaseArray();
            this.add(item);
        }
        return stringArray[listSize - 1];
    }

    @Override
    public String add(int index, String item) {
        if (item == null) {
            throw new StringListNullItemException("Item must not be null!");
        }
        if (index < 0 || index >= listSize) {
            throw new StringListIndexOutOfRangeException("Index out of range!");
        }
        if (listSize + 1 > arraySize) {
            increaseArray();
        }
        for (int i = listSize; i >= index; i--) {
            stringArray[i] = stringArray[i - 1];
            stringArray[i - 1] = null;
        }
        stringArray[index] = item;
        listSize++;
        return stringArray[index];
    }

    @Override
    public String set(int index, String item) {
        if (item == null) {
            throw new StringListNullItemException("Item must not be null!");
        }
        if (index < 0 || index >= listSize) {
            throw new StringListIndexOutOfRangeException("Index out of range!");
        } else {
            stringArray[index] = item;
        }
        return stringArray[index];
    }

    @Override
    public String remove(String item) {
        if (item == null) {
            throw new StringListNullItemException("Item must not be null!");
        }
        int index = this.indexOf(item);
        if (index != -1) {
            return this.remove(index);
        } else {
            throw new StringListElementNotFound("Element item = '" + item + "' not found!");
        }
    }

    @Override
    public String remove(int index) {
        if (index < 0 || index >= listSize) {
            throw new StringListIndexOutOfRangeException("Index out of range!");
        }
        String item;
        item = stringArray[index];
        stringArray[index] = null;
        for (int i = index + 1; i <= listSize; i++) {
            stringArray[i - 1] = stringArray[i];
            stringArray[i] = null;
        }
        listSize--;
        if ((listSize < (int) (arraySize / 1.3F)) && (arraySize > 10)) {
            decreaseArray();
        }
        return item;
    }

    @Override
    public boolean contains(String item) {
        if (item == null) {
            throw new StringListNullItemException("Item must not be null!");
        }
        for (int i = 0; i < listSize; i++) {
            if (stringArray[i].equals(item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(String item) {
        if (item == null) {
            throw new StringListNullItemException("Item must not be null!");
        }
        for (int i = 0; i < listSize; i++) {
            if (stringArray[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        if (item == null) {
            throw new StringListNullItemException("Item must not be null!");
        }
        for (int i = listSize - 1; i >= 0; i--) {
            if (stringArray[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        if (index < 0 || index >= listSize) {
            throw new StringListIndexOutOfRangeException("Index out of range!");
        } else {
            return stringArray[index];
        }
    }

    @Override
    public boolean equals(StringList otherList) {
        if (otherList == null) {
            throw new StringListNullException("StringList must not be null!");
        }
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
        if (!this.isEmpty()) {
            for (int i = 0; i <= listSize; i++) {
                stringArray[i] = null;
            }
            arraySize = 10;
            listSize = 0;
            this.stringArray = new String[arraySize];
        }
    }

    @Override
    public String[] toArray() {
        String[] strArray = new String[listSize];
        System.arraycopy(stringArray, 0, strArray, 0, listSize);
        return strArray;
    }

    /**
     * Creating a new String[] array with a size 30% larger than the previous one
     */
    private void increaseArray() {
        arraySize = (int) (arraySize * 1.3F);
        String[] newStringArray = new String[arraySize];
        System.arraycopy(stringArray, 0, newStringArray, 0, stringArray.length);
        stringArray = newStringArray;
    }

    /**
     * Creating a new String[] array with a size 30% smaller than the previous one
     */
    private void decreaseArray() {
        arraySize = (int) (listSize * 1.3F);
        String[] newStringArray = new String[arraySize];
        System.arraycopy(stringArray, 0, newStringArray, 0, stringArray.length);
        stringArray = newStringArray;
    }
}


package ru.skypro.course2.valiev.StringList.Impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.skypro.course2.valiev.StringList.StringList;
import ru.skypro.course2.valiev.exception.StringListIndexOutOfRangeException;
import ru.skypro.course2.valiev.exception.StringListNullException;
import ru.skypro.course2.valiev.exception.StringListNullItemException;

import static org.junit.jupiter.api.Assertions.*;
import static ru.skypro.course2.valiev.StringList.Impl.Constants.TestConstants.*;

class StringListImplTest {
    private final StringList stringList = new StringListImpl();
    private final StringList stringList2 = new StringListImpl();

    @BeforeEach
    void setUp() {
        // Заполним первоначально встроенный массив до 8 значений (его начальный размер 10)
        for (int i = 0; i < 8; i++) {
            if ( i == 6) {
                stringList.add(ITEM_LAST_5);
                stringList2.add(ITEM_LAST_5);
            }
            stringList.add("Test string item " + i);
            stringList2.add("Test string item " + i);
        }
    }

    @Test
    void shouldAddStringItem() {
        assertEquals(ITEM_8, stringList.add(ITEM_8));
        assertEquals(ITEM_9, stringList.add(ITEM_9));
        assertEquals(ITEM_10, stringList.add(ITEM_10));
    }

    @Test
    void shouldAddStringItemStringListNullItemException() {
        assertThrows(StringListNullItemException.class, () -> stringList.add(null));
    }

    @Test
    void shouldAddIntIndexStringItem() {
        int index8 = 8;
        int index5 = 5;

        assertEquals(ITEM_8, stringList.add(index8, ITEM_8));
        assertEquals(ITEM_NEW_5, stringList.add(index5, ITEM_NEW_5));
    }

    @Test
    void shouldAddIntIndexStringItemStringListNullItemException() {
        assertThrows(StringListNullItemException.class, () -> stringList.add(1, null));
    }

    @Test
    void shouldAddIntIndexStringItemStringListIndexOutOfRangeException() {
        assertThrows(StringListIndexOutOfRangeException.class, () -> stringList.add(-1, ITEM_5));
    }

    @Test
    void shouldSet() {
        int index = 5;

        assertEquals(ITEM_5, stringList.set(index, ITEM_5));
    }

    @Test
    void shouldSetItemStringListNullItemException() {
        assertThrows(StringListNullItemException.class, () -> stringList.set(1, null));
    }

    @Test
    void shouldSetStringListIndexOutOfRangeException() {
        assertThrows(StringListIndexOutOfRangeException.class, () -> stringList.set(-1, ITEM_5));
    }

    @Test
    void shouldRemoveStringItem() {
        assertEquals(ITEM_5, stringList.remove(ITEM_5));
    }

    @Test
    void shouldRemoveStringItemStringListNullItemException() {
        assertThrows(StringListNullItemException.class, () -> stringList.remove(null));
    }

    @Test
    void shouldRemoveIntIndex() {
        assertEquals(ITEM_5, stringList.remove(5));
    }

    @Test
    void shouldRemoveIntIndexStringListIndexOutOfRangeException() {
        assertThrows(StringListIndexOutOfRangeException.class, () -> stringList.remove(-1));
    }

    @Test
    void shouldContainsStringItem() {
        assertTrue(stringList.contains(ITEM_5));
        assertFalse(stringList.contains(ITEM_10));
    }

    @Test
    void shouldContainsStringItemStringListNullItemException() {
        assertThrows(StringListNullItemException.class, () -> stringList.contains(null));
    }

    @Test
    void shouldIndexOfStringItem() {
        assertEquals(5, stringList.indexOf(ITEM_5));
    }

    @Test
    void shouldIndexOfStringItemStringListNullItemException() {
        assertThrows(StringListNullItemException.class, () -> stringList.indexOf(null));
    }

    @Test
    void shouldLastIndexOfStringItem() {
        assertEquals(6, stringList.lastIndexOf(ITEM_LAST_5));
    }

    @Test
    void shouldLastIndexOfStringItemStringListNullItemException() {
        assertThrows(StringListNullItemException.class, () -> stringList.lastIndexOf(null));
    }

    @Test
    void shouldGetIntIndex() {
        int index = 5;

        assertEquals(ITEM_5, stringList.get(index));
    }

    @Test
    void shouldGetIntIndexStringListIndexOutOfRangeException() {
        assertThrows(StringListIndexOutOfRangeException.class, () -> stringList.get(-1));
    }

    @Test
    void shouldEqualsStringList() {
        assertTrue(stringList.equals(stringList2));
    }

    @Test
    void shouldEqualsStringListNullException() {
        assertThrows(StringListNullException.class, () -> stringList.equals(null));
    }

    @Test
    void shouldSize() {
        assertEquals(9, stringList.size());
    }

    @Test
    void shouldIsEmptyTrue() {
        stringList.clear();
        assertTrue(stringList.isEmpty());
    }

    @Test
    void shouldIsEmptyFalse() {
        assertFalse(stringList.isEmpty());
    }

    @Test
    void clear() {
        stringList.clear();
        assertTrue(stringList.isEmpty());
    }

    @Test
    void toArray() {
        assertArrayEquals(STRING_ARRAY, stringList.toArray());
    }
}
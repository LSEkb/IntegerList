package org.laserova;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntegerListImplTest {
    IntegerListImpl testIntegers10 = new IntegerListImpl(new Integer[]{0,1,2,3,4,5,6,7,8,9});
    IntegerListImpl testIntegers09 = new IntegerListImpl(new Integer[]{0,1,2,3,4,5,6,7,8});
    IntegerListImpl testIntegers11 = new IntegerListImpl(new Integer[]{0,1,2,3,4,1,6});
    IntegerListImpl testIntegers9 = new IntegerListImpl(new Integer[]{0,1,2,3,4,5,6,7,8});
    IntegerListImpl testIntegers99 = new IntegerListImpl(new Integer[]{0,1,2,3,4,5,6,7,9});

    @Test
    void add_sizeLessCapacity_addWithoutGrowCapacity() {
        Integer result = testIntegers09.add(88);
        assertTrue(testIntegers09.contains(88));
        assertEquals(88, result);
        assertEquals(10, testIntegers09.size());
    }

    @Test
    void add_sizeEqualsCapacity_addWithGrowCapacity() {
        Integer result = testIntegers10.add(88);
        assertTrue(testIntegers10.contains(88));
        assertEquals(88, result);
        assertEquals(11, testIntegers10.size());
    }

    @Test
    void addByIndex_indexMoreSize_thrownException() {
        assertThrows(InvalidIndexException.class, () -> testIntegers09.add(11, 88));
    }

    @Test
    void addByIndex_indexNoMoreSize_addByIndexAndIncreaseSize() {
        Integer result = testIntegers09.add(7, 88);
        assertTrue(testIntegers09.contains(88));
        assertEquals(88, result);
        assertEquals(88, testIntegers09.get(7));
        assertEquals(10, testIntegers09.size());
    }

    @Test
    void set_indexMoreSize_thrownException() {
        assertThrows(InvalidIndexException.class, () -> testIntegers09.set(11, 88));
    }

    @Test
    void set_indexNoMoreSize_replacedItemsByIndex() {
        Integer result = testIntegers09.set(7, 88);
        assertTrue(testIntegers09.contains(88));
        assertEquals(88, result);
        assertEquals(88, testIntegers09.get(7));
        assertEquals(9, testIntegers09.size());
    }

    @Test
    void removeByItem_noHasItemInIntegerList_thrownException() {
        assertThrows(InvalidIndexException.class, () -> testIntegers09.remove((Integer)88));
    }

    @Test
    void removeByItem_haveItemInIntegerList_deleteAndDecreaseSize() {
        Integer result = testIntegers09.remove((Integer)4);
        assertFalse(testIntegers09.contains(4));
        assertEquals(4, result);
        assertEquals(8, testIntegers09.size());
    }

    @Test
    void removeByIndex_indexMoreSize_thrownException() {
        assertThrows(InvalidIndexException.class, () -> testIntegers09.remove(11));
    }

    @Test
    void removeByIndex_indexNoMoreSize_deleteItemAndDecreaseSize() {
        Integer result = testIntegers09.remove(4);
        assertFalse(testIntegers09.contains(4));
        assertEquals(4, result);
        assertEquals(8, testIntegers09.size());
    }

    @Test
    void contains_noHasItemInIntegerList_returnFalse() {
       assertFalse(testIntegers09.contains(88));
    }

    @Test
    void contains_haveItemInIntegerList_returnTrue() {
        assertTrue(testIntegers09.contains(4));
    }

    @Test
    void indexOf_noHasItemInIntegerList_return_minus1() {
        assertEquals(-1, testIntegers11.indexOf(88));
    }

    @Test
    void indexOf_haveItemInIntegerList_returnIndex() {
        assertEquals(1, testIntegers11.indexOf(1));
    }

    @Test
    void lastIndexOf_noHasItemInIntegerList_return_minus1() {
        assertEquals(-1, testIntegers11.indexOf(88));
    }

    @Test
    void lastIndexOf_haveItemInIntegerList_returnIndex() {
        assertEquals(5, testIntegers11.lastIndexOf(1));
    }

    @Test
    void get_indexMoreSize_thrownException() {
        assertThrows(InvalidIndexException.class, () -> testIntegers09.remove(11));
    }

    @Test
    void get_indexNoMoreSize_returnItem() {
        assertEquals(2, testIntegers09.get(2));
    }

    @Test
    void equals_stringListsNotEqualToEachOtherBySize_returnFalse() {
        assertFalse(testIntegers09.equals(testIntegers10));
    }

    @Test
    void equals_stringListsNotEqualToEachOtherByItems_returnFalse() {
        assertFalse(testIntegers09.equals(testIntegers99));
    }

    @Test
    void equals_stringListsEqualToEachOther_returnTrue() {
        assertTrue(testIntegers09.equals(testIntegers9));
    }

    @Test
    void equals_stringListsEqualToSelf_returnTrue() {
        assertTrue(testIntegers09.equals(testIntegers09));
    }

    @Test
    void size__returnedSizeIsCorrect() {
        assertEquals(9, testIntegers09.size());
    }

    @Test
    void isEmpty_stringListIsEmpty_returnTrue() {
        IntegerListImpl emptyStrList = new IntegerListImpl();
        assertTrue(emptyStrList.isEmpty());
    }

    @Test
    void isEmpty_stringListNoEmpty_returnFalse() {
        assertFalse(testIntegers09.isEmpty());
    }

    @Test
    void toArray__returnedArrayIsCorrect() {
        Integer [] expect = new Integer []{0,1,2,3,4,5,6,7,8,9};
        assertArrayEquals(expect, testIntegers10.toArray());
    }

}
package complib.collections


import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName


class LowerBoundTests {

    @Test
    fun lowerBoundTest() {
        val list = listOf<Int>(1, 1, 2, 2, 3, 5, 6, 7, 8, 8, 9)
        assertEquals(0, list.lowerBound(0))
        assertEquals(0, list.lowerBound(1))
        assertEquals(2, list.lowerBound(2))
        assertEquals(4, list.lowerBound(3))
        assertEquals(8, list.lowerBound(8))
        assertEquals(10, list.lowerBound(9))
        assertEquals(11, list.lowerBound(10))
    }
}

class UpperBoundTests {

    @Test
    fun upperBoundTest() {
        val list = listOf<Int>(1, 1, 2, 2, 3, 5, 6, 7, 8, 8, 9)
        assertEquals(0, list.upperBound(0))
        assertEquals(2, list.upperBound(1))
        assertEquals(4, list.upperBound(2))
        assertEquals(5, list.upperBound(3))
        assertEquals(10, list.upperBound(8))
        assertEquals(11, list.upperBound(9))
        assertEquals(11, list.upperBound(10))
    }
}


class BinarySearchTest {

    @DisplayName("same as lowerBound when used for element search in List")
    @Test
    fun listSearchTest() {
        val list = listOf<Int>(1, 1, 2, 2, 3, 5, 6, 7, 8, 8, 9)
        val range = 0 to list.size
        assertEquals(0, binarySearch(range){ list[it] >= 0 })
        assertEquals(0, binarySearch(range){ list[it] >= 1 })
        assertEquals(2, binarySearch(range){ list[it] >= 2 })
        assertEquals(4, binarySearch(range){ list[it] >= 3 })
        assertEquals(8, binarySearch(range){ list[it] >= 8 })
        assertEquals(10, binarySearch(range){ list[it] >= 9 })
    }
}

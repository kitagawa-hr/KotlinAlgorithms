package complib

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LowerBoundTests {

    @Test
    fun gcdIntTest() {
        val list = listOf<Int>(1, 1, 2, 2, 3, 5, 6, 7, 8, 8, 9)
        assertEquals(list.lowerBound(0), 0)
        assertEquals(list.lowerBound(1), 0)
        assertEquals(list.lowerBound(2), 2)
        assertEquals(list.lowerBound(3), 4)
        assertEquals(list.lowerBound(8), 8)
        assertEquals(list.lowerBound(9), 10)
        assertEquals(list.lowerBound(10), 11)
    }
}

class UpperBoundTests {

    @Test
    fun gcdIntTest() {
        val list = listOf<Int>(1, 1, 2, 2, 3, 5, 6, 7, 8, 8, 9)
        assertEquals(list.upperBound(0), 0)
        assertEquals(list.upperBound(1), 2)
        assertEquals(list.upperBound(2), 4)
        assertEquals(list.upperBound(3), 5)
        assertEquals(list.upperBound(8), 10)
        assertEquals(list.upperBound(9), 11)
        assertEquals(list.upperBound(10), 11)
    }
}

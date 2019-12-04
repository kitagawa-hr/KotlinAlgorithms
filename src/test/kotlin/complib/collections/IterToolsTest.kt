package complib.collections

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class DotTest {

    @DisplayName("Dot of [1, 2, 3] and [1, 3, 5] is 22")
    @Test
    fun dotInt() {
        val v1 = listOf(1, 2, 3)
        val v2 = listOf(1, 3, 5)
        assertEquals(1 * 1 + 2 * 3 + 3 * 5, v1.dot(v2))
    }

    @DisplayName("Dot of [1L, 2L, 3L] and [1L, 3L, 5L] is 22L")
    @Test
    fun dotLong() {
        val v1 = listOf(1L, 2L, 3L)
        val v2 = listOf(1L, 3L, 5L)
        assertEquals(1L * 1L + 2L * 3L + 3L * 5L, v1.dot(v2))
    }
}

internal class ProductTests {
    @Test
    fun productTests() {
        val res = product(listOf(1, 2), listOf(3, 4), listOf(5, 6)).iterator()
        assertEquals(listOf(1, 3, 5), res.next())
        assertEquals(listOf(1, 3, 6), res.next())
        assertEquals(listOf(1, 4, 5), res.next())
        assertEquals(listOf(1, 4, 6), res.next())
        assertEquals(listOf(2, 3, 5), res.next())
        assertEquals(listOf(2, 3, 6), res.next())
        assertEquals(listOf(2, 4, 5), res.next())
        assertEquals(listOf(2, 4, 6), res.next())
    }
}

internal class CombinationsTests {
    @Test
    fun combinationsTest1() {
        val res1 = setOf(1, 2, 3).combinations(2)
        val expected1 = setOf(
            setOf(1, 2),
            setOf(1, 3),
            setOf(2, 3)
        )
        assertEquals(expected1, res1)
    }

    @Test
    fun combinationsTest2() {
        val res2 = setOf(1, 2, 3, 4).combinations(3)
        val expected2 = setOf(
            setOf(1, 2, 3),
            setOf(1, 2, 4),
            setOf(1, 3, 4),
            setOf(2, 3, 4)
        )
        assertEquals(expected2, res2)
    }
}

internal class PowerSetTest {
    @Test
    fun powerSetTest() {
        val res = setOf(1, 2, 3).powerSet()
        val expected = setOf(
            setOf(),
            setOf(1),
            setOf(2),
            setOf(3),
            setOf(1, 2),
            setOf(1, 3),
            setOf(2, 3),
            setOf(1, 2, 3)
        )
        assertEquals(expected, res)
    }
}

internal class InsertTest {
    @Test
    fun insertToHead() {
        val res = mutableListOf(2, 3, 4).insert(0, 1)
        val expected = mutableListOf(1, 2, 3, 4)
        assertEquals(expected, res)
    }

    @Test
    fun insertToTail() {
        val res = mutableListOf(2, 3, 4).insert(3, 1)
        val expected = mutableListOf(2, 3, 4, 1)
        assertEquals(expected, res)
    }

    @Test
    fun insertToBody() {
        val res = mutableListOf(2, 3, 4).insert(1, 1)
        val expected = mutableListOf(2, 1, 3, 4)
        assertEquals(expected, res)
    }
}

internal class PermutationsTests {
    @Test
    fun permutationsTest() {
        val res2 = listOf(1, 2, 3).permutations()
        val expected2 = setOf(
            listOf(1, 2, 3),
            listOf(1, 3, 2),
            listOf(2, 1, 3),
            listOf(2, 3, 1),
            listOf(3, 1, 2),
            listOf(3, 2, 1)
        )
        assertEquals(expected2, res2)
    }
}

internal class ScanLeftTest {

    @DisplayName("accumulative sum of (1..10)")
    @Test
    fun accumulativeSum() {
        val res = (1..10).scanLeft(0) { a, b -> a + b }.toList()
        val expected = listOf(1, 3, 6, 10, 15, 21, 28, 36, 45, 55)
        assertEquals(expected, res)
    }
}

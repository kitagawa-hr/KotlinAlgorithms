package complib.collections

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class ProductTests {
    @Test
    fun productTests() {
        val res = product(listOf(1, 2), listOf(3, 4), listOf(5, 6)).iterator()
        assertEquals(res.next(), listOf(1, 3, 5))
        assertEquals(res.next(), listOf(1, 3, 6))
        assertEquals(res.next(), listOf(1, 4, 5))
        assertEquals(res.next(), listOf(1, 4, 6))
        assertEquals(res.next(), listOf(2, 3, 5))
        assertEquals(res.next(), listOf(2, 3, 6))
        assertEquals(res.next(), listOf(2, 4, 5))
        assertEquals(res.next(), listOf(2, 4, 6))
    }
}

internal class DotTest {

    @Test
    fun dotInt() {
        val v1 = listOf(1, 2, 3)
        val v2 = listOf(1, 3, 5)
        assertEquals(v1.dot(v2), 1 * 1 + 2 * 3 + 3 * 5)
    }

    @Test
    fun dotLong() {
        val v1 = listOf(1L, 2L, 3L)
        val v2 = listOf(1L, 3L, 5L)
        assertEquals(v1.dot(v2), 1L * 1L + 2L * 3L + 3L * 5L)
    }
}

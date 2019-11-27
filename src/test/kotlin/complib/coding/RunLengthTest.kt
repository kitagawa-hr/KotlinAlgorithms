package complib.coding

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class RunLengthTest {
    @Test
    fun intCase() {
        val target = listOf(1, 1, 1, 2, 2, 3, 3, 3, 4, 4, 5, 6, 6, 7)
        val result = target.runLengthCoding()
        val expected = listOf(1 to 3, 2 to 2, 3 to 3, 4 to 2, 5 to 1, 6 to 2, 7 to 1)
        assertEquals(expected, result)
    }

    @Test
    fun charCase() {
        val target = "aaaabbbbccccddde".toList()
        val result = target.runLengthCoding()
        val expected = listOf('a' to 4, 'b' to 4, 'c' to 4, 'd' to 3, 'e' to 1)
        assertEquals(expected, result)
    }
}
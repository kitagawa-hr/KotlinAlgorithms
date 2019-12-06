package complib.query

import complib.math.IntMonoid
import java.lang.IllegalArgumentException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class SqrtDecompositionTest {
    private val array = arrayOf(1, 3, 5, 2, 9, 12, 8, 13, 15)
    private val sumQuery = SqrtDecomposition(array, IntMonoid)

    @ParameterizedTest(name = "sum of [{0}, {1}) = {2}")
    @CsvSource(
        "0,    1,   1",
        "0,    2,   4",
        "0,    3,   9",
        "1,    3,   8",
        "0,    9,  68",
        "3,    8,   44"
    )
    fun queryTest(from: Int, to: Int, min: Int) {
        assertEquals(min, sumQuery.query(from, to))
    }

    @ParameterizedTest(name = "invalid query range should throw Exception")
    @CsvSource(
        "0,    0",
        "-1,    2",
        "0,    11"
    )
    fun queryRaisesTest(from: Int, to: Int) {
        assertThrows(IllegalArgumentException::class.java) {
            sumQuery.query(from, to)
        }
    }

    @Test
    fun updateTest() {
        sumQuery.update(0, 10)
        // 10, 3, 5, 2, 9, 12, 8, 13, 15
        assertEquals(18, sumQuery.query(0, 3))
        sumQuery.update(3, -8)
        // 10, 3, 5, -8, 9, 12, 8, 13, 15
        assertEquals(18, sumQuery.query(0, 3))
        assertEquals(10, sumQuery.query(0, 4))
    }
    @Test
    fun updateRangeTest() {
        sumQuery.updateRange(0, 3, 10)
        // 10, 10, 10, 2, 9, 12, 8, 13, 15
        assertEquals(30, sumQuery.query(0, 3))
        sumQuery.updateRange(3, 8, -8)
        // 10, 10, 10, -8, -8, -8 , -8 , -8 , 15
        assertEquals(30, sumQuery.query(0, 3))
        assertEquals(5, sumQuery.query(0, 9))
    }
}

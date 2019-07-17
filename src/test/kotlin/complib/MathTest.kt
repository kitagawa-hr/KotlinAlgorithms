package complib

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class GcdTests {

    @ParameterizedTest(name = "gcd({0}, {1}) = {2}")
    @CsvSource(
            "0,    1,   1",
            "1,    0,   1",
            "49,  7, 7",
            "10,  100, 10",
            "24,  32, 8"
    )
    fun gcdIntTest(first: Int, second: Int, expectedResult: Int) {
        assertEquals(expectedResult, gcd(first, second)) {
            "gcd($first, $second) should equal $expectedResult"
        }
    }

    @ParameterizedTest(name = "gcd({0}, {1}) = {2}")
    @CsvSource(
            "962886691382436, 171597041488476, 36",
            "665557188103603, 421350285050291, 79"
    )
    fun gcdLongTest(first: Long, second: Long, expectedResult: Long) {
        assertEquals(expectedResult, gcd(first, second)) {
            "gcd($first, $second) should equal $expectedResult"
        }
    }
}

internal class PowTest {

    @ParameterizedTest(name = "{0}.pow{1} = {2}")
    @CsvSource(
            "2, 3.0, 8.0",
            "2, 4.0, 16.0",
            "12, 2.0, 144.0",
            "2, -1.0, 0.5"
    )
    fun powIntTest(first: Int, second: Double, expectedResult: Double) {
        assertEquals(first.pow(second), expectedResult)
    }

    @ParameterizedTest(name = "{0}.pow{1} = {2}")
    @CsvSource(
            "2, 3.0, 8.0",
            "2, 4.0, 16.0",
            "12, 2.0, 144.0",
            "2, -1.0, 0.5"
    )
    fun powLongTest(first: Long, second: Double, expectedResult: Double) {
        assertEquals(first.pow(second), expectedResult)
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

internal class IsSquareTest {
    @ParameterizedTest(name = "{0} is square = {1}")
    @CsvSource(
            "1, true",
            "4, true",
            "9, true",
            "25, true",
            "144, true",
            "143, false",
            "24, false",
            "2, false",
            "3, false",
            "80, false"
    )
    fun isSquareTest(num: Long, expectedResult: Boolean) {
        assertEquals(isSquare(num), expectedResult)
    }
}

internal class IsPrimeTest {
    @ParameterizedTest(name = "{0} is prime number = {1}")
    @CsvSource(
            "2, true",
            "3, true",
            "5, true",
            "7, true",
            "11, true",
            "3571, true",
            "3559, true",
            "91, false",
            "1, false",
            "4, false",
            "16, false",
            "81, false",
            "2619, false",
            "3553, false"
    )
    fun isPrimeTest(num: Long, expectedResult: Boolean) {
        assertEquals(isPrime(num), expectedResult)
    }
}

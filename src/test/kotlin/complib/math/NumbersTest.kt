package complib.math

import org.junit.jupiter.api.Assertions.*
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
        assertEquals(expectedResult, first.pow(second))
    }

    @ParameterizedTest(name = "{0}.pow{1} = {2}")
    @CsvSource(
            "2, 3.0, 8.0",
            "2, 4.0, 16.0",
            "12, 2.0, 144.0",
            "2, -1.0, 0.5"
    )
    fun powLongTest(first: Long, second: Double, expectedResult: Double) {
        assertEquals(expectedResult, first.pow(second))
    }
}


class BinomialTest {
    @ParameterizedTest(name = "{0}C{1} = {2}")
    @CsvSource(
            "10, 0, 1",
            "10, 1, 10",
            "10, 2, 45",
            "10, 3, 120",
            "28, 14, 40116600",
            "30, 15, 155117520"
    )
    fun binomialTest(n: Int, k: Int, result: Long) {
        assertEquals(result, binomial(n, k))
    }
}

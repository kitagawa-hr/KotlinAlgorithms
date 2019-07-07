package complib

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class GcdTests {

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

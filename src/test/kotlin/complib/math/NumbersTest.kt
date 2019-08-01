package complib.math

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
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

internal class ModPowTest {
    @ParameterizedTest(name = "{0}.pow{1} % {2} = {3}")
    @CsvSource(
            "2, 3, 100, 8",
            "2, 4, 100, 16",
            "12, 2, 100, 44"
    )
    fun easyCase(n: Long, x: Long, mod: Long, expectedResult: Long) {
        assertEquals(expectedResult, n.modPow(x, mod))
    }
    @ParameterizedTest(name = "{0}.pow{1} % {2} = {3}")
    @CsvSource(
            "12, 7, 15, 3",
            "123456789, 6574837563712, 234567894, 120678297"
    )
    fun largeNumCase(n: Long, x: Long, mod: Long, expectedResult: Long) {
        // from https://atcoder.jp/contests/atc002/submissions/6631978
        assertEquals(expectedResult, n.modPow(x, mod))
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
        assertEquals(expectedResult, isSquare(num))
    }
}

class PrimeUtilsTest {
    @Nested
    inner class IsPrimeTest {
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
                "25, false",
                "81, false",
                "2619, false",
                "3553, false"
        )
        fun isPrimeTest(num: Long, expectedResult: Boolean) {
            assertEquals(expectedResult, PrimeUtils.isPrime(num))
        }
    }

    @Nested
    inner class GeneratePrimesBelowTest {
        @Test
        fun generatePrimesTest() {
            val primes = PrimeUtils.generatePrimesBelow(100).asIterable()
            val expected = listOf(
                    2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41,
                    43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97
            ).map { it.toLong() }
            assertIterableEquals(expected, primes)
        }
    }

    @Nested
    inner class PrimeFactorizeTest {
        @DisplayName("10 = 2 * 5")
        @Test
        fun Factorize10Test() {
            val res = PrimeUtils.primeFactorize(10)
            assertEquals(1, res[2])
            assertEquals(1, res[5])
            assertEquals(2, res.size)
        }

        @DisplayName("59089028305980235 = 5 * 29 * 31^3 * 13678981573")
        @Test
        fun FactorizeTest() {
            val res = PrimeUtils.primeFactorize(59089028305980235)
            assertEquals(1, res[5])
            assertEquals(1, res[29])
            assertEquals(3, res[31])
            assertEquals(1, res[13678981573])
            assertEquals(4, res.size)
        }
    }
}

class IterateDivideTest {
    @ParameterizedTest(name = "{0} = {1}^{2} * {3}")
    @CsvSource(
            "24, 2,    3,   3",
            "24, 3,    1,   8",
            "0, 4,    0,   0",
            "1, 4,    0,   1",
            "81, 3,    4,   1",
            "144, 4,    2,   9",
            "1024, 2,    10,   1"
    )
    fun iterateDivideTest(num: Long, divisor: Long, count: Int, rest: Long) {
        assertEquals(Pair(count, rest), iterateDivide(num, divisor))
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

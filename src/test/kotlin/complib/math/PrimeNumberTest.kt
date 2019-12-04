package complib.math

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class IsPrimeTest {
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
        assertEquals(expectedResult, isPrime(num))
    }
}

class GeneratePrimesBelowTest {
    @Test
    fun generatePrimesBelow100() {
        val primes = generatePrimesBelow(100).asIterable()
        val expected = listOf(
                2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41,
                43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97
        ).map { it.toLong() }
        assertIterableEquals(expected, primes)
    }
}

class PrimeFactorizeTest {
    @DisplayName("10 = 2 * 5")
    @Test
    fun factorize10() {
        val res = primeFactorize(10L)
        assertEquals(1, res[2])
        assertEquals(1, res[5])
        assertEquals(2, res.size)
    }

    @DisplayName("59089028305980235 = 5 * 29 * 31^3 * 13678981573")
    @Test
    fun factorizeBigInt() {
        val res = primeFactorize(59089028305980235L)
        assertEquals(1, res[5])
        assertEquals(1, res[29])
        assertEquals(3, res[31])
        assertEquals(1, res[13678981573])
        assertEquals(4, res.size)
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

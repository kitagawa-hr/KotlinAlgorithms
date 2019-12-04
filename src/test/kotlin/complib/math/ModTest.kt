package complib.math

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

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

internal class ModInvTest {
    @ParameterizedTest(name = "{0} ^ -1 % {1} = {2}")
    @CsvSource(
        "1, 13, 1",
        "2, 13, 7",
        "3, 13, 9",
        "4, 13, 10",
        "5, 13, 8",
        "6, 13, 11",
        "7, 13, 2",
        "8, 13, 5",
        "9, 13, 3",
        "10, 13, 4",
        "11, 13, 6",
        "12, 13, 12"
    )
    fun `modInv of mod 13`(n: Long, mod: Long, expectedResult: Long) {
        assertEquals(expectedResult, n.modInv(mod))
    }
}

internal class BinomialModTest {
    @ParameterizedTest(name = "{0} C {1} % {2} == {3}")
    @CsvSource(
        "4, 2, 10007, 6",
        "10, 2, 10007, 45",
        "10, 8, 10007, 45",
        "10, 3, 10007, 120",
        "10, 7, 10007, 120",
        "4, 0, 10007, 1",
        "4, 4, 10007, 1"
    )
    fun `nCk % bigmod == nCk`(n: Int, k: Int, mod: Long, expectedResult: Long) {
        assertEquals(expectedResult, binomialMod(n, k, mod))
    }

    @ParameterizedTest(name = "{0} C {1} % {2} == {3}")
    @CsvSource(
        "4, 2, 13, 6",
        "10, 2, 13, 6",
        "10, 3, 13, 3"
    )
    fun `nCk % 13`(n: Int, k: Int, mod: Long, expectedResult: Long) {
        assertEquals(expectedResult, binomialMod(n, k, mod))
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

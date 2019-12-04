package complib.graph

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

// verified at http://judge.u-aizu.ac.jp/onlinejudge/review.jsp?rid=3793601#1

internal class WeightedUnionFindTest {
    @Nested
    inner class UniteTest {
        @Test
        fun unite() {
            val uf = WeightedUnionFind(10)
            // 1 -> (2, 3)
            assertTrue(uf.unite(1, 2, 10L))
            assertTrue(uf.unite(1, 3, 10L))
            // 1 -> (2, 3, 4, 5)
            assertTrue(uf.unite(4, 5, 10L))
            assertTrue(uf.unite(5, 1, 10L))
            assertFalse(uf.unite(5, 1, 10L))
            assertFalse(uf.unite(4, 1, 10L))
        }
    }

    @Nested
    inner class FindTest {
        private val uf = WeightedUnionFind(10)
        @BeforeEach
        fun setUp() {
            """
                1
             /(2) \(1)
            8       3
                 /(4) \(32)
                6      5
                     /(8) \(16)
                    7      9
                    
            0 -(64) 2
            
            4

            """.trimIndent()
            uf.unite(1, 3, 1L)
            uf.unite(1, 8, 2L)
            uf.unite(3, 6, 4L)
            uf.unite(5, 7, 8L)
            uf.unite(5, 9, 16L)
            uf.unite(3, 5, 32L)
            uf.unite(0, 2, 64L)
        }

        @ParameterizedTest(name = "size of {0} is {1}")
        @CsvSource(
            "0, 2",
            "1, 7",
            "2, 2",
            "3, 7",
            "4, 1",
            "5, 7",
            "6, 7",
            "7, 7",
            "8, 7",
            "9, 7"
        )
        fun getSizeTest(node: Int, expectedSize: Int) {
            assertEquals(expectedSize, uf.getSize(node))
        }

        @ParameterizedTest(name = "root of {0} is {1}")
        @CsvSource(
            "1, 1",
            "3, 1",
            "8, 1",
            "6, 1",
            "5, 1",
            "7, 1",
            "9, 1",
            "4, 4"
        )
        fun findRootTest(node: Int, expectedRoot: Int) {
            assertEquals(expectedRoot, uf.findRoot(node))
        }

        @ParameterizedTest(name = "({0}, {1}) are same group = {2}")
        @CsvSource(
            "1, 8, true",
            "1, 3, true",
            "1, 6, true",
            "1, 5, true",
            "1, 7, true",
            "1, 9, true",
            "8, 9, true",
            "0, 2, true",
            "1, 4, false",
            "0, 4, false",
            "1, 2, false",
            "7, 4, false"
        )
        fun isSameTest(first: Int, second: Int, isSame: Boolean) {
            assertEquals(isSame, uf.isSame(first, second))
        }

        @ParameterizedTest(name = "weight diff of ({0}, {1}) is {2}")
        @CsvSource(
            "1, 8, 2",
            "1, 3, 1",
            "1, 6, 5",
            "1, 5, 33",
            "1, 7, 41",
            "1, 9, 49",
            "8, 9, 47",
            "0, 2, 64",
            "3, 7, 40",
            "6, 9, 44"
        )
        fun getDiffTest(first: Int, second: Int, diff: Long) {
            assertEquals(diff, uf.getDiff(first, second))
        }
        @ParameterizedTest(name = "weight of {0} is {1}")
        @CsvSource(
            "0, 0",
            "1, 0",
            "2, 64",
            "3, 1",
            "4, 0",
            "5, 33",
            "6, 5",
            "7, 41",
            "8, 2",
            "9, 49"
        )
        fun getWeightTest(index: Int, weight: Long) {
            """
                1
             /(2) \(1)
            8       3
                 /(4) \(32)
                6      5
                     /(8) \(16)
                    7      9
                    
            0 -(64) 2
            
            4

            """.trimIndent()
            assertEquals(weight, uf.getWeight(index))
        }
    }
}

package complib.graph

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class UnionFindTest {
    @Nested
    inner class UniteTest {
        @Test
        fun unite() {
            val uf = UnionFind(10)
            // 1 -> (2, 3)
            assertTrue(uf.unite(1, 2))
            assertTrue(uf.unite(1, 3))
            // 1 -> (2, 3, 4, 5)
            assertTrue(uf.unite(4, 5))
            assertTrue(uf.unite(5, 1))
            assertFalse(uf.unite(5, 1))
            assertFalse(uf.unite(4, 1))
        }
    }

    @Nested
    inner class FindTest {
        private val uf = UnionFind(10)
        @BeforeEach
        fun setUp() {
            """
              1
             / \
            8   3
               /  \
              6    5
                   / \
                  7   9
            0 - 2
            4

            """.trimIndent()
            uf.unite(1, 3)
            uf.unite(1, 8)
            uf.unite(3, 6)
            uf.unite(5, 7)
            uf.unite(5, 9)
            uf.unite(3, 5)
            uf.unite(0, 2)
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
            assertEquals(uf.getSize(node), expectedSize)
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
            assertEquals(uf.findRoot(node), expectedRoot)
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
            "1, 3, true",
            "0, 2, true",
            "1, 4, false",
            "0, 4, false",
            "1, 2, false",
            "7, 4, false"
        )
        fun isSameTest(first: Int, second: Int, isSame: Boolean) {
            assertEquals(uf.isSame(first, second), isSame)
        }
    }
}

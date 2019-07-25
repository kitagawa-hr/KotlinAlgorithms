package complib.math

fun gcd(x: Int, y: Int): Int = if (y == 0) x else gcd(y, x % y)
fun gcd(x: Long, y: Long): Long = if (y == 0L) x else gcd(y, x % y)

fun Int.pow(x: Double): Double = Math.pow(this.toDouble(), x)
fun Long.pow(x: Double): Double = Math.pow(this.toDouble(), x)

fun isSquare(num: Long): Boolean {
    val sqrt = Math.sqrt(num.toDouble()).toLong()
    return sqrt * sqrt == num || (sqrt + 1L) * (sqrt + 1L) == num
}

object PrimeUtils {
    fun isPrime(num: Long): Boolean {
        if (num == 1L) return false
        if (num in listOf(2L, 3L)) return true
        if (num % 6 in listOf(0L, 2L, 3L, 4L)) return false
        val sqrt = Math.sqrt(num.toDouble()).toLong()
        for (n in 5..sqrt step 6) {
            if (num % n == 0L) return false
        }
        for (n in 7..sqrt step 6) {
            if (num % n == 0L) return false
        }
        return true
    }

    fun generatePrimesBelow(sup: Long): Sequence<Long> {
        if (sup < 2L) return sequenceOf()
        if (sup < 3L) return sequenceOf(2L)
        val seq = (0..sup / 6L)
                .asSequence()
                .flatMap { sequenceOf(6 * it + 5L, 6 * it + 7L) }
                .filter { it < sup }
        return sequenceOf(2L, 3L) + seq.filter { isPrime(it) }
    }

    fun primeFactorize(num: Long): Map<Long, Int> {
        var n = num
        val result = mutableMapOf<Long, Int>()
        val sqrt = Math.sqrt(num.toDouble()).toLong()
        val trialDivs = sequenceOf(2L) + (3..sqrt step 2).asSequence()
        for (trialDiv in trialDivs) {
            if (trialDiv > n) break
            val divideRes = iterateDivide(n, trialDiv)
            if (divideRes.first > 0) {
                result[trialDiv] = divideRes.first
                n = divideRes.second
            }
        }
        if (n != 1L) result[n] = 1
        return result
    }
}


fun iterateDivide(num: Long, divisor: Long): Pair<Int, Long> {
    var n = num
    var count = 0
    while (num != 0L && n % divisor == 0L) {
        n /= divisor
        count++
    }
    return Pair(count, n)
}

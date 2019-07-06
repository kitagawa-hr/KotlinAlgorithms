package complib

fun gcd(x: Int, y: Int): Int = if (y == 0) x else gcd(y, x % y)
fun gcd(x: Long, y: Long): Long = if (y == 0L) x else gcd(y, x % y)

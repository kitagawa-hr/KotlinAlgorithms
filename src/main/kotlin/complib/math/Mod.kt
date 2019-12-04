package complib.math

fun Long.modPow(x: Long, mod: Long): Long {
    // a ^ (2^(exp)) % mod
    tailrec fun binModPow(a: Long, exp: Long): Long = when (exp) {
        0L -> a % mod
        else -> binModPow(a * a % mod, exp - 1)
    }
    return (0..63).asSequence()
            .map { (x shr it) and 1 }
            .withIndex()
            .filter { it.value > 0 }
            .map { binModPow(this, it.index.toLong()) }
            .reduce { a, b -> a * b % mod }
}

// 1 / x % mod (x ⊥ mod)
fun Long.modInv(mod: Long): Long = this.modPow(mod - 2, mod)

fun binomialMod(n: Int, k: Int, mod: Long): Long = when (k) {
    // nCk % mod
    0, n -> 1L
    else -> {
        val numer = (n downTo n - k + 1)
                .map { it.toLong() }
                .reduce { a, b -> (a * b) % mod }
        val denom = (1..k)
                .map { it.toLong() }
                .reduce { a, b -> (a * b) % mod }
        (numer * denom.modInv(mod)) % mod
    }
}

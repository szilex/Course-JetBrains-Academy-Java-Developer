public static BigInteger calcDoubleFactorial(int n) {
    BigInteger result = BigInteger.ONE;

    for (int i = n % 2 == 0 ? 2 : 1; i <= n; i += 2) {
        BigInteger tmp = result.multiply(BigInteger.valueOf(i));
        result = tmp;
    }

    return result;
}
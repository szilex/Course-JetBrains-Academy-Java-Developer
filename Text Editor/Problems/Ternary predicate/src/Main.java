class Predicate {

    @FunctionalInterface
    public interface TernaryIntPredicate {
        boolean test(int firstValue, int secondValue, int thirdValue);
    }

    public static final TernaryIntPredicate allValuesAreDifferentPredicate = (x, y, z) -> x != y && y != z && x != z ? true : false;
}
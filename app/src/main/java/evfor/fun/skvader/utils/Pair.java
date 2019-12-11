package evfor.fun.skvader.utils;

public class Pair<L, R> {
    private R right;
    private L left;

    public Pair(L left, R right) {
        this.right = right;
        this.left = left;
    }

    public R getRight() {
        return right;
    }

    public L getLeft() {
        return left;
    }
}

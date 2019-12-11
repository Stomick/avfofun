package evfor.fun.skvader.exceptions;

public class AgeLimitException extends Exception {
    private int difference = 0;

    public AgeLimitException() {
    }

    public AgeLimitException(int difference) {
        this.difference = difference;
    }

    public int getDifference() {
        return difference;
    }

    public void setDifference(int difference) {
        this.difference = difference;
    }
}

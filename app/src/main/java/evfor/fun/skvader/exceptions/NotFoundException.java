package evfor.fun.skvader.exceptions;

public class NotFoundException extends Exception {
    private String what;

    public NotFoundException(String what) {
        this.what = what;
    }

    public String getWhat() {
        return what;
    }
}

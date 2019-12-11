package evfor.fun.skvader.exceptions;

public class EventCompleteException extends Exception {
    public String id;

    public EventCompleteException(String id) {
        this.id = id;
    }
}

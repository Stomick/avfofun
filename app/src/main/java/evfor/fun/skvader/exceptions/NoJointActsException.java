package evfor.fun.skvader.exceptions;

public class NoJointActsException extends Exception {
    @Override
    public String getMessage() {
        return "you can not write to this user";
    }
}

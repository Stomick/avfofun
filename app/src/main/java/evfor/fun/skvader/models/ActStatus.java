package evfor.fun.skvader.models;

public enum ActStatus {
    ENABLED, BLOCKED, COMPLETED, SENT, PASSED, NOPLACES;

    public static ActStatus valueFrom(String s){
        if (s != null) {
            if (s.equalsIgnoreCase(BLOCKED.name()))
                return BLOCKED;
            if (s.equalsIgnoreCase(COMPLETED.name()))
                return COMPLETED;
            if (s.equalsIgnoreCase(SENT.name()))
                return SENT;
        }
        return ENABLED;
    }

}

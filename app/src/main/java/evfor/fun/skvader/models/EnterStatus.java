package evfor.fun.skvader.models;

public enum EnterStatus {
    FALSE, REQUEST, CONFIRMED, DISMISSED, COMPLETED;

    public static EnterStatus valueFrom(String nameIgnoreCase) {
        if (nameIgnoreCase != null) {
            if (nameIgnoreCase.toLowerCase().contains(REQUEST.name().substring(0, 3).toLowerCase()))
                return REQUEST;
            if (nameIgnoreCase.toLowerCase().contains(CONFIRMED.name().substring(0, 3).toLowerCase()))
                return CONFIRMED;
            if (nameIgnoreCase.toLowerCase().contains(DISMISSED.name().substring(0, 3).toLowerCase()))
                return DISMISSED;
            if (nameIgnoreCase.toLowerCase().contains(COMPLETED.name().substring(0, 3).toLowerCase()))
                return COMPLETED;
        }
        return FALSE;
    }
}

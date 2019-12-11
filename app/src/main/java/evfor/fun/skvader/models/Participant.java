package evfor.fun.skvader.models;

import static evfor.fun.skvader.models.Participant.Status.CONFIRMED;
import static evfor.fun.skvader.models.Participant.Status.DISMISSED;
import static evfor.fun.skvader.models.Participant.Status.REQUEST;

public class Participant extends User {
    public Status status;

    public enum Status {
        REQUEST, CONFIRMED, DISMISSED
    }
        public static Status fromString(String status) {
            if (status.equalsIgnoreCase(REQUEST.name()))
                return REQUEST;
            if (status.equalsIgnoreCase(CONFIRMED.name()))
                return CONFIRMED;
            return DISMISSED;
        }

    public Status getStatus() {
        return status;
    }
}

package evfor.fun.skvader.utils.socket.models;


import evfor.fun.skvader.repository.Identified;

public class Writer implements Identified {
    public String userId;

    public Writer(String id) {
        this.userId = id;
    }

    @Override
    public String id() {
        return userId;
    }
}

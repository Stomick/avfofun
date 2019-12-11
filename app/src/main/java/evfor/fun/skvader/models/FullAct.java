package evfor.fun.skvader.models;

import java.util.List;

public class FullAct {
    public Act act;
    public User user;
    public List<User> users;

    public FullAct(Act act, User user, List<User> users) {
        this.act = act;
        this.user = user;
        this.users = users;
    }
}

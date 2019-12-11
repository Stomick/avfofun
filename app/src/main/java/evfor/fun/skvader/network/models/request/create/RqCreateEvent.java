package evfor.fun.skvader.network.models.request.create;

public class RqCreateEvent extends RqEventCom {

    public String id;
    public String date;
    public String time;

    @Override
    public String path() {
        return "evfor.fun.skvader";
    }

}

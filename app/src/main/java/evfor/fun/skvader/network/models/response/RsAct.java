package evfor.fun.skvader.network.models.response;

import java.util.List;

public class RsAct {
    public String id;
    public String category_id;
    public String user_id;
    public String title;
    public String description;
    public String place;
    public String start_time;
    public String end_time;
    public List<String> visiting_days;
    public Integer price;
    public String video;
    public Integer max_count;
    public Integer age_limit;
    public String logo;
    public int enter_count;
    public double latitude;
    public double longitude;
    public String date;
    public String time;
    public List<String> joined;
    public double rating;
    public String enter_status;
    public String status;
    public int moderation;

    public String getID() {
        return id;
    }

    public boolean isEvent() {
        return start_time == null && end_time == null && visiting_days == null;
    }

}

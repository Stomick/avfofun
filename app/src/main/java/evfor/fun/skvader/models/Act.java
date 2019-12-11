package evfor.fun.skvader.models;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;
import evfor.fun.skvader.utils.Predicatable;

import java.util.List;

public abstract class Act extends ActId implements Predicatable, Comparable<Act>{

    public static final String TAG = Act.class.getSimpleName();
    public static final String BAN = "ban";

    public String category_id;
    public String user_id;
    public String title;
    public String description;
    public String video;
    public String imageUrl;
    public String address;
    public List<String> users;
    @SerializedName("price")
    public int price;
    public double rate;
    public int age_limit;
    public int count_peoples;
    public int max_count;
    public boolean mod;
    public double latitude;
    public double longitude;
    public EnterStatus enterStatus;
    private ActStatus status;

    public ActStatus getStatus() {
        if (count_peoples == max_count)
            return ActStatus.NOPLACES;
        if (isPassed())
            return ActStatus.PASSED;
        return status;
    }

    public void setStatus(ActStatus status) {
        this.status = status;
    }

    public Act(String id) {
        this.id = id;
    }

    public Act() {
    }

    abstract public String displayDate();
    abstract protected String nextDate();
    abstract protected String beginTime();

    abstract public boolean isPassed();

    @Override
    public boolean contain(String word) {
        return title.toLowerCase()
                .contains(word.toLowerCase());
    }

    @Override
    public int compareTo(@NonNull Act act) {
        int dateCompare = String.valueOf(nextDate()).compareTo(String.valueOf(act.nextDate()));
        if (dateCompare == 0)
            return -String.valueOf(beginTime()).compareTo(String.valueOf(act.beginTime()));
        else return dateCompare;
    }
}

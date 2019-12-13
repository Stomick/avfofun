package evfor.fun.skvader.ui.models;

import com.google.android.gms.maps.model.LatLng;
import evfor.fun.skvader.models.ActStatus;

import java.util.ArrayList;
import java.util.List;

public class UiFullEvent{
    public String id;
    public String title;
    public String video_id;
    public String image;
    public String when;
    public String where;
    public String count;
    public String price;
    public String room_id;
    public String description;
    public String organizerId;
    public ActStatus status;
    public LatLng location = new LatLng(0, 0);
    public List<String> visiting_days = new ArrayList<>();
    public List<String> participantsId = new ArrayList<>();

    public boolean isPassed(){
        return status == ActStatus.COMPLETED;
    }
}

package com.team.noty.event.models;

import android.support.annotation.NonNull;

import com.team.noty.event.R;
import com.team.noty.event.repository.Identified;
import com.team.noty.event.ui.models.UiSoc;
import com.team.noty.event.utils.ObjUtils;
import com.team.noty.event.utils.social.Social;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class User implements Identified, Comparable<User> {
    public static final String TAG = User.class.getSimpleName();
    public static final String ME = "me";

    public int id;
    public String gender;
    public Integer rating;
    public Integer level;
    public int created, visited;
    public String imageUrl;
    public String firstname;
    public String lastname;
    public String date;
    public String reg;
    public String phone;
    public String email;
    public String info;
    public String city;
    public List<String> categories;
    public List<String> events;
    public List<String> communities;
    public List<Comment> reviews;
    public int sendMail;
    public int eventAccess;
    public int commAccess;
    public String vk_id;
    public String fb_id;
    public String tw_id;
    public String inst_id;

    @Override
    public String id() {
        return String.valueOf(id);
    }

    public void update(User user) {
        gender = ObjUtils.defaultIfNull(user.gender, gender);
        date = ObjUtils.defaultIfNull(user.date, date);
        imageUrl = ObjUtils.defaultIfNull(user.imageUrl, imageUrl);
        firstname = ObjUtils.defaultIfNull(user.firstname, firstname);
        lastname = ObjUtils.defaultIfNull(user.lastname, lastname);
        phone = ObjUtils.defaultIfNull(user.phone, phone);
        email = ObjUtils.defaultIfNull(user.email, email);
        info = ObjUtils.defaultIfNull(user.info, info);
        city = ObjUtils.defaultIfNull(user.city, city);
        categories = ObjUtils.defaultIfNull(user.categories, categories);
        sendMail = ObjUtils.defaultIfNull(user.sendMail, sendMail);
        eventAccess = ObjUtils.defaultIfNull(user.eventAccess, eventAccess);
        commAccess = ObjUtils.defaultIfNull(user.commAccess, commAccess);
    }

    public void addAct(ActId id) {
        if (id.isEvent) {
            if (events == null)
                events = new ArrayList<>();
            if (!events.contains(id.id))
                events.add(id.id);
        } else if (communities == null) {
            communities = new ArrayList<>();
            if (!communities.contains(id.id))
                communities.add(id.id);
        }
    }

    public void removeAct(ActId id) {
        if (id.isEvent) {
            if (events != null && !events.contains(id.id))
                events.remove(id.id);
        } else if (communities != null && !communities.contains(id.id))
            communities.remove(id.id);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User && id >= 0 && ((User) obj).id >= 0)
            return id == ((User) obj).id;
        return super.equals(obj);
    }

    @Override
    public int compareTo(@NonNull User user) {
        return -Integer.compare(rating * level, user.rating * user.level);
    }

    public List<UiSoc> createList() {
        List<UiSoc> list = new ArrayList<>();
        list.add(new UiSoc(Social.FACEBOOK.getUrl(fb_id), R.drawable.ic_fb, fb_id, R.string.fb));
        list.add(new UiSoc(Social.VK.getUrl(vk_id), R.drawable.ic_vk, vk_id, R.string.vkontakte));
        list.add(new UiSoc(Social.TW.getUrl(tw_id), R.drawable.ic_tw, tw_id, R.string.tw));
        list.add(new UiSoc(Social.INST.getUrl(inst_id), R.drawable.ic_inst, inst_id, R.string.instagram));
        return list;
    }

    public Calendar getBirthday() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        if (date != null)
            calendar.setTime(dateFormat.parse(date));
        return calendar;
    }
}

package evfor.fun.skvader.utils;

import android.util.Log;

import evfor.fun.skvader.models.Act;
import evfor.fun.skvader.models.City;
import evfor.fun.skvader.models.Community;
import evfor.fun.skvader.models.Event;
import evfor.fun.skvader.models.Location;
import evfor.fun.skvader.models.Message;
import evfor.fun.skvader.models.User;
import evfor.fun.skvader.network.URLS;
import evfor.fun.skvader.network.models.request.create.RqEventCom;
import evfor.fun.skvader.network.models.response.RsAct;
import evfor.fun.skvader.network.models.response.RsCategory;
import evfor.fun.skvader.network.models.response.RsCities;
import evfor.fun.skvader.network.models.response.RsMessage;
import evfor.fun.skvader.network.models.response.RsNotification;
import evfor.fun.skvader.network.models.response.RsProfile;
import evfor.fun.skvader.ui.activities.TabsActivity;
import evfor.fun.skvader.ui.models.UiCategory;
import evfor.fun.skvader.ui.models.UiPopularEvent;
import evfor.fun.skvader.ui.models.UiUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;

import static evfor.fun.skvader.utils.RequestMapUtil.input;

public class Converter {
    public static UiCategory toUI(RsCategory.Category category) {
        return new UiCategory(category.category_id, category.name, URLS.imgdomain + category.urlimg);
    }

    public static RsNotification.Notification toRs(Map<String, String> data) {
        RsNotification.Notification notification = new RsNotification.Notification();
        notification.cause_id = StringUtils.toIntOr0(get(data, "cause_id"));
        notification.created = get(data, "created");
        notification.message = get(data, "message");
        notification.title = get(data, "title");
        if(notification.title.equals("Шкипер"))
            Log.e("sdf","fsd");
        notification.notification_id = StringUtils.toIntOr0(get(data, "notification_id"));
        notification.object_type = get(data, "object_type");

        notification.object_id = get(data, "object_id");
        try {
            notification.sender_id = notification.createObjId().senderId;
        } catch (Exception e) {
        }
        notification.status = get(data, "status");
        if (notification.sender_id != null)
            TabsActivity.setNotyCount(notification.sender_id);
        return notification;
    }

    private static String get(Map<String, String> map, String field) {
        if (map.containsKey(field))
            return map.get(field);
        else return "";
    }

    public static Act toDB(RsAct rsAct) {
        Act eventComm = rsAct.isEvent() ? new Event() : new Community();
        eventComm.longitude = rsAct.longitude;
        eventComm.latitude = rsAct.latitude;
        eventComm.address = rsAct.place;
        eventComm.title = rsAct.title;
        eventComm.age_limit = rsAct.age_limit;
        eventComm.category_id = rsAct.category_id;
        eventComm.count_peoples = rsAct.enter_count;
        eventComm.max_count = rsAct.max_count > 9999 ? 0 : rsAct.max_count;
        eventComm.id = rsAct.getID();
        eventComm.description = rsAct.description;
        eventComm.imageUrl = URLS.imgdomain + rsAct.logo;
        eventComm.mod = false;
        eventComm.video = rsAct.video;
        eventComm.user_id = rsAct.user_id;
        eventComm.rate = rsAct.rating;
        eventComm.mod = rsAct.moderation != 0;
        if (eventComm instanceof Event) {
            ((Event) eventComm).time = rsAct.time;
            ((Event) eventComm).date = rsAct.date;
        } else {
            ((Community) eventComm).days = rsAct.visiting_days;
            ((Community) eventComm).bTime = String.valueOf(rsAct.start_time);
            ((Community) eventComm).eTime = String.valueOf(rsAct.end_time);
        }
        return eventComm;
    }

    public static UiPopularEvent toUI(RsAct eventComm) {
        UiPopularEvent event = new UiPopularEvent();
        event.id = eventComm.getID();
        event.logo = URLS.imgdomain + eventComm.logo;
        event.title = eventComm.title;
        event.isComm = !eventComm.isEvent();
        return event;
    }

    private static String getCount(int count_peoples, int max_count_peoples) {
        return String.valueOf(count_peoples) + ((max_count_peoples != 9999) ? ("/" + String.valueOf(max_count_peoples)) : "");
    }

    private static String getBody(RsMessage message) {
        if (message.photoUrl != null)
            return message.photoUrl;
        else if (message.audioUrl != null)
            return message.audioUrl;
        else return message.message;
    }

    private static Message.Type getType(RsMessage message) {
        if (message.photoUrl != null)
            return Message.Type.IMAGE;
        else if (message.audioUrl != null)
            return Message.Type.VOICE;
        else return Message.Type.TEXT;
    }

    public static UiUser toUi(RsProfile.Account account) {
        return toUi(toDB(account));
    }

    public static User toDB(RsProfile.Account account) {
        User user = new User();
        user.categories = account.userCategories == null ? new ArrayList<>() : account.userCategories;
        user.events = account.events;
        user.communities = account.communities;
        user.reviews = account.reviews;
        user.rating = account.userRating;
        user.level = account.typeRank;
        user.gender = account.gender;
        user.date = account.birthday;
        user.email = account.email;
        user.created = account.makedCommunities + account.makedEvents;
        user.visited = account.makedCommunities + account.makedEvents;
        user.email = account.email;
        user.reg = account.dateReg;
        user.phone = account.userPhone;
        user.firstname = account.username;
        user.lastname = account.surename;
        user.id = account.user_id;
        user.city = account.userCity;
        user.info = account.userInfo;
        user.sendMail = account.notificationEmail != 0 ? 1 : 0;
        ;
        user.commAccess = account.AccessCommunity != 0 ? 1 : 0;
        ;
        user.eventAccess = account.AccessEvent != 0 ? 1 : 0;
        ;
        user.imageUrl = URLS.imgdomain + account.userAvatar;

        user.vk_id = account.vk;
        user.fb_id = account.fb;
        user.tw_id = account.tw;
        user.inst_id = account.inst;
        return user;
    }


    public static UiUser toUi(User user) {
        return new UiUser(user);
    }


    private static int maxCount(int count) {
        return count == 0 || count > 9999 ? 9999 : count;
    }

    private static Map<String, RequestBody> createBody(RqEventCom eventCom) {
        Map<String, RequestBody> requestBodyMap = new HashMap<>();
        input(requestBodyMap, "title", eventCom.title);
        input(requestBodyMap, "category_id", eventCom.category_id);
        input(requestBodyMap, "description", eventCom.description);
        input(requestBodyMap, "place", eventCom.place);
        input(requestBodyMap, "price", eventCom.price);
        input(requestBodyMap, "age_limit", eventCom.age_limit);
        input(requestBodyMap, "count_peoples", eventCom.count_peoples);
        input(requestBodyMap, "moderation", eventCom.moderation);
        input(requestBodyMap, "video", eventCom.video);
        input(requestBodyMap, "latitude", eventCom.latitude);
        input(requestBodyMap, "longitude", eventCom.longitude);
        return requestBodyMap;
    }


    static public City toUi(RsCities.City rsCity) {
        City uiCity = new City();
        uiCity.name = rsCity.name;
        uiCity.location = new Location(rsCity.latitude, rsCity.longitude);
        return uiCity;
    }
}

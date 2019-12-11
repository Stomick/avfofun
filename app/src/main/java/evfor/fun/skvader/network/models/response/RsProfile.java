package evfor.fun.skvader.network.models.response;

import evfor.fun.skvader.models.Comment;

import java.util.List;

public class RsProfile extends RsBase {

    public Account answer;

    public class Account{
        public int user_id;
        public String userAvatar;
        public int userRating, typeRank;
        public String username;
        public String gender;
        public String surename;
        public String userPhone;
        public String dateReg;
        public String birthday;
        public String userCity;
        public String email;
        public String userInfo;
        public int notificationEmail;
        public int AccessEvent;
        public int AccessCommunity;
        public int makedEvents, makedCommunities;
        public List<String> userCategories;
        public List<String> communities;
        public List<String> events;
        public List<Comment> reviews;
        public String vk;
        public String fb;
        public String tw;
        public String inst;
    }
}

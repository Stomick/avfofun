package com.team.noty.event.network.models.response;

import java.util.List;

public class RsAdminUserList extends RsBase {
    public List<RsAdminUser> userlist;

    public class RsAdminUser {
        public String userAvatar;
        public String userPhone;
        public String userCity;
        public int userRating;
        public String birthday;
        public int typeRank;
        public String username;
        public String usId;
        public String status;

        public boolean isRequest() {
            return status.equals("request");
        }

        public boolean isConfirmed() {
            return status.equals("confirmed");
        }

        public boolean isDissmised() {
            return status.equals("dissmised");
        }

        @Override
        public boolean equals(Object obj) {
            if (usId == null || obj == null)
                return super.equals(obj);
            if (obj instanceof String)
                return usId.equalsIgnoreCase(String.valueOf(obj));
            if (obj instanceof RsAdminUser)
                return String.valueOf(usId).equalsIgnoreCase(String.valueOf(((RsAdminUser) obj).usId));
            return super.equals(obj);
        }
    }
}

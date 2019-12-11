package com.team.noty.event.models;

import java.util.ArrayList;
import java.util.List;

public class BanEventCommList {
    public List<String> eventsIds;
    public List<String> communitiesIds;

    public BanEventCommList() {
        eventsIds = new ArrayList<>();
        communitiesIds = new ArrayList<>();
    }
}

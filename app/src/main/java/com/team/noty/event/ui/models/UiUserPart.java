package com.team.noty.event.ui.models;

import com.team.noty.event.network.models.response.RsAdminUserList;

public class UiUserPart {
    public RsAdminUserList.RsAdminUser model;

    public UiUserPart(RsAdminUserList.RsAdminUser model) {
        this.model = model;
    }

    public UiUserPart() {
    }
}

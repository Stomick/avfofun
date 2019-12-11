package evfor.fun.skvader.ui.models;

import evfor.fun.skvader.network.models.response.RsAdminUserList;

public class UiUserPart {
    public RsAdminUserList.RsAdminUser model;

    public UiUserPart(RsAdminUserList.RsAdminUser model) {
        this.model = model;
    }

    public UiUserPart() {
    }
}

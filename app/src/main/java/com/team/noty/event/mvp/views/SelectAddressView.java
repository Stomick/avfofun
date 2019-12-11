package com.team.noty.event.mvp.views;

import com.team.noty.event.models.Address;

import java.util.List;

public interface SelectAddressView extends BaseView {
    void setAddressList(List<Address> addresses);
}

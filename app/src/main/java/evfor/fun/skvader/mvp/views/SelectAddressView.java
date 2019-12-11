package evfor.fun.skvader.mvp.views;

import evfor.fun.skvader.models.Address;

import java.util.List;

public interface SelectAddressView extends BaseView {
    void setAddressList(List<Address> addresses);
}

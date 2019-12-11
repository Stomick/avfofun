package com.team.noty.event.mvp.presenters;

import com.team.noty.event.R;
import com.team.noty.event.exceptions.DeterminationLocation;
import com.team.noty.event.exceptions.PermissionException;
import com.team.noty.event.mvp.views.BaseView;

public class LocationPresenter<T extends BaseView> extends BasePresenter<T> {
    @Override
    void onError(Throwable throwable) {
        if (throwable instanceof PermissionException)
            getViewState().showInfo(R.string.need_permission_location);
        else if (throwable instanceof DeterminationLocation)
            getViewState().showInfo(R.string.need_enable_gps);
        else super.onError(throwable);
    }
}

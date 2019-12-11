package evfor.fun.skvader.mvp.presenters;

import evfor.fun.skvader.R;
import evfor.fun.skvader.exceptions.DeterminationLocation;
import evfor.fun.skvader.exceptions.PermissionException;
import evfor.fun.skvader.mvp.views.BaseView;

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

package com.team.noty.event.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.team.noty.event.app.Injector;
import com.team.noty.event.repository.data_observe.DataObserver;
import com.team.noty.event.models.Location;
import com.team.noty.event.mvp.views.SelectLocationView;
import com.team.noty.event.utils.PermissionController;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

@InjectViewState
public class SelectLocationPresenter extends LocationPresenter<SelectLocationView> {

    @Inject
    DataObserver<Location> locationController;

    @Inject
    PermissionController permissionController;

    private Disposable disposable;
    private Location current;

    public SelectLocationPresenter() {
        Injector.get().getApp().inject(this);
        disposable = permissionController.myLocation()
                .andThen(locationController.observe())
                .subscribe(this::setCurrent);
    }

    public void moveToMyLocation(){
        if(current!=null)
            getViewState().setCurrentLoc(current);
    }

    private void setCurrent(Location current) {
        this.current = current;
    }

    @Override
    public void detachView(SelectLocationView view) {
        super.detachView(view);
        if (disposable != null)
            disposable.dispose();
    }
}

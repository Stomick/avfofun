package com.team.noty.event.ui.fragments.main.create;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.view.View;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.team.noty.event.R;
import com.team.noty.event.models.Address;
import com.team.noty.event.models.Category;
import com.team.noty.event.models.Event;
import com.team.noty.event.models.Act;
import com.team.noty.event.mvp.presenters.CreateEventCommPresenter;
import com.team.noty.event.mvp.views.CreateEventCommView;
import com.team.noty.event.ui.activities.EventActivity;
import com.team.noty.event.ui.activities.OpenPhotoActivity;
import com.team.noty.event.ui.activities.ProfileUpdater;
import com.team.noty.event.ui.activities.SelectAddressActivity;
import com.team.noty.event.ui.activities.TabsActivity;
import com.team.noty.event.ui.dialogs.DialogProvider;
import com.team.noty.event.ui.fragments.BaseFragment;
import com.team.noty.event.utils.RealPathUtil;
import com.team.noty.event.utils.callbacks.CallBack;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseCreateFragment extends BaseFragment implements CreateEventCommView {

    @InjectPresenter
    CreateEventCommPresenter presenter;
    protected Act act;

    protected String categoryId;
    protected String imagePath;
    protected boolean add = false;
    protected List<Category> categories;
    protected int age_selected = 0;

    public static BaseCreateFragment create(Act eventComm) {
        BaseCreateFragment fragment = eventComm instanceof Event ? new CreateEventFragment() : new CreateCommunityFragment();
        fragment.act = eventComm;
        return fragment;
    }

    protected String categoryTitle(String id) {
        for (Category category : categories)
            if (category.id.equals(id))
                return category.name;
        return getString(R.string.chose_category);
    }

    protected String getAgeLimit(int age_limit){
        return age_limit > 0 ? getString(R.string.age_limit_t, age_limit) : getResources().getStringArray(R.array.age_limits)[0];
    }

    protected String getPrice(long price){
        return price == 0?"":getString(R.string.currency_template, price);
    }

    @Override
    @CallSuper
    public void onComplete(){
        hideLoad();
        DialogProvider.infoOk(getContext(), R.string.accept, () -> {
            if (act.id!=null)
                EventActivity.open(getContext(),act);
            else
                TabsActivity.openSearch(getContext());
        }).show();
        clear();
    }

    public void clear(){
        if(getActivity() instanceof ProfileUpdater)
            ((ProfileUpdater) getActivity()).update();
    }

    @Override
    public void setCategories(List<Category> categories) {
        this.categories.addAll(categories);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        categories = new ArrayList<>();
        presenter.getCategory();
    }

    protected void hideAdditions(View image, View description, View video, View btn) {
        if (add) {
            image.setVisibility(View.GONE);
            video.setVisibility(View.GONE);
            description.setVisibility(View.GONE);
            btn.setVisibility(View.VISIBLE);
            add = !add;
        }
    }

    protected void showAdditions(View image, View description, View video, View btn) {
        if (!add) {
            image.setVisibility(View.VISIBLE);
            description.setVisibility(View.VISIBLE);
            video.setVisibility(View.VISIBLE);
            btn.setVisibility(View.GONE);
            add = !add;
        }
    }

    protected void getImage() {
        if (getActivity() != null)
            OpenPhotoActivity.open(getActivity(), true, OpenPhotoActivity.AspectFor.LOGO)
                    .map(uri -> RealPathUtil.getRealPath(getContext(), uri))
                    .subscribe(path -> {
                            imagePath = path;
                            setImage(path);
                    });
    }

    protected abstract void setImage(String path);

    @Override
    public void completeEdit() {
        onComplete();
        if (getActivity() != null)
            getActivity().onBackPressed();
        DialogProvider.infoOk(getContext(), R.string.sent, () -> getActivity().finish()).show();
    }

    public void onResult(int requestCode, int resultCode, Intent data, TextView location) {
        Address address = SelectAddressActivity.result(requestCode, resultCode, data);
        if (address == null)
            super.onActivityResult(requestCode, resultCode, data);
        else {
            act.latitude = address.location.latitude;
            act.longitude = address.location.longitude;
            location.setText(address.title);
        }
    }
}

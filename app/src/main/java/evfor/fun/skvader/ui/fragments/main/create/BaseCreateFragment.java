package evfor.fun.skvader.ui.fragments.main.create;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.view.View;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import evfor.fun.skvader.R;
import evfor.fun.skvader.models.Address;
import evfor.fun.skvader.models.Category;
import evfor.fun.skvader.models.Event;
import evfor.fun.skvader.models.Act;
import evfor.fun.skvader.mvp.presenters.CreateEventCommPresenter;
import evfor.fun.skvader.mvp.views.CreateEventCommView;
import evfor.fun.skvader.ui.activities.EventActivity;
import evfor.fun.skvader.ui.activities.OpenPhotoActivity;
import evfor.fun.skvader.ui.activities.ProfileUpdater;
import evfor.fun.skvader.ui.activities.SelectAddressActivity;
import evfor.fun.skvader.ui.activities.TabsActivity;
import evfor.fun.skvader.ui.dialogs.DialogProvider;
import evfor.fun.skvader.ui.fragments.BaseFragment;
import evfor.fun.skvader.utils.RealPathUtil;

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

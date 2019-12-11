package com.team.noty.event.ui.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.pixplicity.multiviewpager.MultiViewPager;
import com.team.noty.event.R;
import com.team.noty.event.models.Category;
import com.team.noty.event.models.User;
import com.team.noty.event.mvp.presenters.RegPrewPresenter;
import com.team.noty.event.mvp.views.RegPrewView;
import com.team.noty.event.ui.dialogs.DialogProvider;
import com.team.noty.event.ui.dialogs.Toaster;
import com.team.noty.event.ui.utils.reg_step_view.BaseStep;
import com.team.noty.event.ui.utils.reg_step_view.Step1;
import com.team.noty.event.ui.utils.reg_step_view.Step2;
import com.team.noty.event.ui.utils.reg_step_view.Step3;
import com.team.noty.event.ui.utils.reg_step_view.Step4;
import com.team.noty.event.utils.RealPathUtil;
import com.team.noty.event.utils.callbacks.CallBack;

import java.util.List;

import butterknife.BindView;
import io.reactivex.Maybe;

public class RegistrationPrewActivity extends BaseActivity implements RegPrewView {

    public static void open(Context context) {
        context.startActivity(new Intent(context, RegistrationPrewActivity.class));
    }

    @InjectPresenter
    RegPrewPresenter prewPresenter;

    @BindView(R.id.reg_prew_counter)
    MultiViewPager counterPager;
    @BindView(R.id.reg_prew_pager)
    MultiViewPager pager;
    private int[] views = {R.layout.reg_step1, R.layout.reg_step2, R.layout.reg_step3, R.layout.reg_step4};
    private Step1 step1;
    private Step2 step2;
    private Step3 step3;
    private Step4 step4;
    private MenuItem nextBtn;
    private CallBack callBack;

    @Override
    protected int layout() {
        return R.layout.activity_registration_prew;
    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Москва");
        }
        counterPager.setPageTransformer(true, new FadePageTransformer());
        counterPager.setAdapter(new CounterAdapter());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            counterPager.setNestedScrollingEnabled(false);
        }
        pager.setAdapter(new PageAdapter());
        counterPager.addOnPageChangeListener(new SelectPageListener() {
            @Override
            public void onPageSelected(int position) {
                pager.setCurrentItem(position);
                setNextBtn(position);
            }
        });
        pager.addOnPageChangeListener(new SelectPageListener() {
            @Override
            public void onPageSelected(int position) {
                counterPager.setCurrentItem(position);
                setNextBtn(position);
            }
        });
        pager.setCurrentItem(0);
        prewPresenter.getCity();
        createSteps();
    }

    private void createSteps() {
        step1 = new Step1();
        step2 = new Step2();
        step3 = new Step3();
        step4 = new Step4();
    }

    private void setNextBtn(int pos) {
        if (nextBtn != null) {
            nextBtn.setVisible(!(pos == views.length - 1));
        }
    }

    @Override
    public void onBackPressed() {
        if (counterPager.getCurrentItem() > 0) {
            counterPager.setCurrentItem(getItem(-1), true);
        } else
            DialogProvider.exit(this, super::onBackPressed).show();
    }

    @Override
    protected void onCreateMenu(Menu menu) {
        nextBtn = menu.findItem(R.id.reg_next_button);
        nextBtn.setTitle(R.string.next);
    }

    @Override
    protected int menuLayout() {
        return R.menu.reg_next_button;
    }

    @Override
    public void showInterests(List<Category> list) {
        if (step1 != null)
            step1.setContent(list);
    }

    @Override
    public void onComplete() {
        if (callBack != null)
            callBack.call();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.reg_next_button) {
            counterPager.setCurrentItem(getItem(+1), true);
            return true;
        } else if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private int getItem(int i) {
        if (counterPager.getAdapter() != null
                && i > 0
                && counterPager.getCurrentItem() + 1 < counterPager.getAdapter().getCount()
                || i < 0 && counterPager.getCurrentItem() > 0)
            return counterPager.getCurrentItem() + i;
        else return counterPager.getCurrentItem();
    }

    public BaseStep getStep(View view, int pos) {
        switch (pos) {
            case 0:
                step1.setView(view);
                return step1;
            case 1:
                step2.setView(view);
                return step2;
            case 2:
                step3.setView(view);
                return step3;
            case 3:
                step4.setView(view);
                return step4;
        }
        return null;
    }

    private void setContent(View view, int pos) {
        BaseStep step = getStep(view, pos);
        step.setView(view);
        switch (pos) {
            case 0: {
                prewPresenter.getCategories();
                break;
            }
            case 1: {
                step2.setOpenPhoto(() -> prewPresenter.checkPhotoPermission());
                break;
            }
            case 3: {
                step4.setCallBacks(this::openCreate, this::openSearch);
                break;
            }
        }
    }

    @Override
    public void openPhoto() {
        DialogProvider.listPhoto(this,
                new String[]{
                        getString(R.string.load_from_gallery),
                        getString(R.string.take_picture)},
                this::takeFromGallery,
                this::takePicture
        ).show();
    }

    @Override
    public void setCity(String cityName) {
        hideLoad();
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(cityName);
    }

    private void takeFromGallery() {
        takeAndSendPhoto(OpenPhotoActivity.open(this, true, OpenPhotoActivity.AspectFor.AVATAR));
    }

    private void takePicture() {
        takeAndSendPhoto(OpenPhotoActivity.open(this, false, OpenPhotoActivity.AspectFor.AVATAR));
    }

    @SuppressLint("CheckResult")
    private void takeAndSendPhoto(Maybe<Uri> maybe) {
        maybe
                .map(uri -> RealPathUtil.getRealPath(this, uri))
                .subscribe(this::setPath,
                        throwable -> Toaster.duration(this, throwable.getMessage(), 5000));
    }

    private void setPath(String path) {
        if (step2 != null)
            step2.setPath(path);
    }

    private void openSearch() {
        callBack = () -> {
            TabsActivity.openSearch(this);
            finish();
        };
        setUser();
    }

    private void openCreate() {
        callBack = () -> {
            TabsActivity.openCreate(this);
            finish();
        };
        setUser();
    }

    private void setUser() {
        showLoad();
        User user = new User();
        user.imageUrl = step2.getPath();
        user.categories = step1.getCategories();
        user.info = step3.getText();
        prewPresenter.setProfile(user);
    }

    class PageAdapter extends PagerAdapter {
        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            LayoutInflater inflater = LayoutInflater.from(RegistrationPrewActivity.this);
            ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.reg_prew_page, container, false);
            FrameLayout frameLayout = layout.findViewById(R.id.reg_prew_container);
            TextView titleView = layout.findViewById(R.id.reg_prew_title);
            TextView messageView = layout.findViewById(R.id.reg_prew_message);
            View view = inflater.inflate(views[position], frameLayout, false);
            setContent(view, position);
            frameLayout.addView(view);
            container.addView(layout);
            titleView.requestFocus();
            if (position < getResources().getStringArray(R.array.reg_prew_title).length) {
                titleView.setText(getResources().getStringArray(R.array.reg_prew_title)[position]);
                messageView.setText(getResources().getStringArray(R.array.reg_prew_message)[position]);
            }
            return layout;
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view.equals(object);
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    }

    class CounterAdapter extends PagerAdapter {
        int w = 0;
        DisplayMetrics dm;

        public CounterAdapter() {
            dm = getResources().getDisplayMetrics();
            w = (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, dm.widthPixels / 3, dm));
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            LayoutInflater inflater = LayoutInflater.from(RegistrationPrewActivity.this);
            ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.counter_item, container, false);
            ((TextView) layout.findViewById(R.id.counter_text)).setText(String.valueOf(position + 1));
            View item = layout.findViewById(R.id.counter_item);
            item.setLayoutParams(new FrameLayout.LayoutParams(w, item.getLayoutParams().height));
            container.addView(layout);
            return layout;
        }

        @Override

        public int getCount() {
            return 4;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view.equals(object);
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    }

    public class FadePageTransformer implements ViewPager.PageTransformer {

        public void transformPage(@NonNull View view, float position) {
            if (position < -1 || position > 1) {
                view.setAlpha(0);
            } else if (position >= -1 && position <= 1) {
                position = (position <= 0) ? position + 1 : 1 - position;
                view.findViewById(R.id.counter_back_bg).animate().scaleX(position).scaleY(position).setDuration(0).start();
                float globalSize = 0.5f + 0.5f * position;
                view.animate().alpha(position).scaleX(globalSize).scaleY(globalSize).setDuration(0).start();
                view.setAlpha(0.5f);
            } else if (position == 0) {
                view.setAlpha(1);
            }
        }

    }

    private abstract class SelectPageListener implements ViewPager.OnPageChangeListener {
        @Override
        public final void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public abstract void onPageSelected(int position);

        @Override
        public final void onPageScrollStateChanged(int state) {
        }
    }
}

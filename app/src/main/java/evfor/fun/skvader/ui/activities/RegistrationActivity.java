package evfor.fun.skvader.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;

import com.arellomobile.mvp.presenter.InjectPresenter;
import evfor.fun.skvader.R;
import evfor.fun.skvader.mvp.presenters.RegistrationPresenter;
import evfor.fun.skvader.mvp.views.RegistrationView;
import evfor.fun.skvader.ui.dialogs.DialogProvider;
import evfor.fun.skvader.ui.fragments.BaseFragment;
import evfor.fun.skvader.ui.fragments.registration.BaseRegFragment;
import evfor.fun.skvader.ui.fragments.registration.MailPasswordFragment;
import evfor.fun.skvader.ui.fragments.registration.NumberPhoneFragment;
import evfor.fun.skvader.ui.fragments.registration.RegistrationFragment;

import java.util.LinkedList;
import java.util.List;

public class RegistrationActivity extends BaseActivity implements TiteledActivity, RegistrationView {

    public static void open(Context context) {
        context.startActivity(new Intent(context, RegistrationActivity.class));
    }

    @InjectPresenter
    RegistrationPresenter presenter;

    @IdRes
    int container = R.id.container;
    ActionBar actionBar;
    List<BaseRegFragment> fragmentList;
    private int currentFragment = 0;

    @Override
    protected int layout() {
        return R.layout.activity_registration;
    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
        createFragments();
        setFragment(fragmentList.get(0));
    }

    @Override
    protected void actionBar(@NonNull ActionBar bar) {
        actionBar = bar;
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void createFragments() {
        fragmentList = new LinkedList<>();
        fragmentList.add(RegistrationFragment.create(this::setFirstPage));
        fragmentList.add(NumberPhoneFragment.create(this::setSecondPage));
        fragmentList.add(MailPasswordFragment.create(this::setLastPage));
    }

    private void setFirstPage(RegistrationFragment.Model model) {
        presenter.setGenderFullNameDate(getGender(model.isGender()), model.getFirstName(), model.getLastName(), model.getDate(), model.getCity());
    }

    private int getGender(boolean g) {
        return g ? 1 : 0;
    }

    private void setSecondPage(String phone) {
        presenter.setPhone(phone);
    }

    private void setLastPage(MailPasswordFragment.Model model) {
        presenter.setEmailPassword(model.getEmail(), model.getPassword());
    }

    @Override
    public void setTitle(String title) {
        if (actionBar != null)
            actionBar.setTitle(title);
    }

    @Override
    protected int menuLayout() {
        return R.menu.reg_next_button;
    }

    @Override
    protected void onCreateMenu(Menu menu) {
        menu.getItem(0).setTitle(R.string.next);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.reg_next_button) {
            nextStep();
            return true;
        } else if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (currentFragment > 0)
            setFragment(fragmentList.get(--currentFragment));
        else
            super.onBackPressed();
    }

    private void setFragment(BaseFragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(container, fragment)
                .commit();
    }

    @Override
    public void nextStep() {
        if (fragmentList.get(currentFragment).canNext())
            if (currentFragment + 1 < fragmentList.size())
                setFragment(fragmentList.get(++currentFragment));
            else {
                showLoad();
                presenter.sendRegistration();
            }
    }

    @Override
    public void onComplete() {
        hideLoad();
        RegistrationPrewActivity.open(this);
        finish();
    }

    @Override
    public void error() {
        DialogProvider.infoOk(this, R.string.reg_error).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        fragmentList.get(currentFragment).onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}

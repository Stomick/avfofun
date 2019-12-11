package evfor.fun.skvader.ui.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.widget.EditText;

import com.arellomobile.mvp.presenter.InjectPresenter;
import evfor.fun.skvader.R;
import evfor.fun.skvader.mvp.presenters.ChangePasswordPresenter;
import evfor.fun.skvader.mvp.views.CompletableView;
import evfor.fun.skvader.utils.ObjUtils;

import butterknife.BindView;

public class ChangePasswordActivity extends BaseActivity implements CompletableView {

    @InjectPresenter
    ChangePasswordPresenter presenter;

    @BindView(R.id.change_password_old)
    EditText changePasswordOld;
    @BindView(R.id.change_password_new)
    EditText changePasswordNew;
    @BindView(R.id.change_password_new_again)
    EditText changePasswordNewAgain;

    @Override
    protected int layout() {
        return R.layout.activity_change_password;
    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
    }

    @Override
    protected void actionBar(@NonNull ActionBar bar) {
        bar.setTitle(R.string.do_change_password);
        setBackArrow(bar);
    }

    @Override
    protected int menuLayout() {
        return R.menu.save_menu_button;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String newPass = ObjUtils.nullNotEquals(changePasswordNew.getText().toString(), changePasswordNewAgain.getText().toString());
        if (item.getItemId() == R.id.menu_save && newPass != null) {
            presenter.changePassword(newPass, changePasswordOld.getText().toString());
            return true;
        } else if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onComplete() {
        finish();
    }
}

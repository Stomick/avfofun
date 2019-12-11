package evfor.fun.skvader.ui.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.facebook.login.LoginManager;
import evfor.fun.skvader.R;
import evfor.fun.skvader.models.User;
import evfor.fun.skvader.mvp.presenters.EditProfilePresenter;
import evfor.fun.skvader.mvp.views.EditProfileView;
import evfor.fun.skvader.ui.dialogs.DialogProvider;
import evfor.fun.skvader.ui.fragments.main.CabinetTabFragment;
import evfor.fun.skvader.ui.utils.DateTimePickerUtils;
import evfor.fun.skvader.utils.StringUtils;
import com.vk.sdk.VKSdk;

import butterknife.BindView;
import butterknife.OnClick;

import static evfor.fun.skvader.ui.fragments.main.CabinetTabFragment.about;
import static evfor.fun.skvader.ui.fragments.main.CabinetTabFragment.phoneNumber;
import static evfor.fun.skvader.ui.fragments.main.CabinetTabFragment.user_static;

public class EditProfileActivity extends BaseActivity implements EditProfileView {

    @InjectPresenter
    EditProfilePresenter presenter;

    @BindView(R.id.registration_rb_male)
    RadioButton registrationRbMale;
    @BindView(R.id.registration_rb_female)
    RadioButton registrationRbFemale;
    @BindView(R.id.edit_name)
    TextView editName;
    @BindView(R.id.edit_lastname)
    TextView editLastname;
    @BindView(R.id.edit_city)
    TextView editCity;
    @BindView(R.id.edit_phone)
    TextView editPhone;
    @BindView(R.id.edit_mail)
    TextView editMail;
    @BindView(R.id.edit_birthdate)
    TextView editBirthdate;
    @BindView(R.id.edit_my_soc)
    TextView editMySoc;
    @BindView(R.id.edit_send_notification)
    Switch editSendNotification;
    @BindView(R.id.edit_share_event)
    Switch editShareEvent;
    @BindView(R.id.edit_share_community)
    Switch editShareCommunity;
    private User editable;

    @Override
    protected int layout() {
        return R.layout.activity_edit_profile;
    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
        editable = new User();
        editMail.setOnClickListener(v -> Toast.makeText(EditProfileActivity.this, "Изменить email возможно на сайте evfor.fun", Toast.LENGTH_SHORT).show());
        presenter.loadProfile();
    }

    @Override
    protected void actionBar(@NonNull ActionBar bar) {
        bar.setTitle(R.string.edit);
        setBackArrow(bar);
    }

    @Override
    protected int menuLayout() {
        return R.menu.save_menu_button;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_save) {
            save();
            return true;
        } else if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.edit_birthdate)
    public void onEditBirthdateClicked() {
        DialogProvider.dateDialog(this,
                editBirthdate.getText().toString(),
                (datePicker, i, i1, i2) -> {
                    editable.date = DateTimePickerUtils.dateToString(datePicker);
                    editBirthdate.setText(editable.date);
                }
        ).show();
    }

    @OnClick(R.id.edit_my_soc)
    public void onEditMySocClicked() {
        startActivity(new Intent(this, MySocNetActivity.class));
    }

    @OnClick(R.id.edit_change_password)
    public void onEditChangePasswordClicked() {
        startActivity(new Intent(this, ChangePasswordActivity.class));
    }

    @OnClick(R.id.edit_exiting_account)
    public void onEditExitingAccountClicked() {
        LoginManager.getInstance().logOut();
        user_static = new User();
        VKSdk.logout();
        new AlertDialog.Builder(this)
                .setTitle(R.string.exit)
                .setMessage(R.string.question_exit_account)
                .setNegativeButton(R.string.exit, (dialogInterface, i) -> presenter.exitAccount())
                .setPositiveButton(R.string.cancel, (dialogInterface, i) -> dialogInterface.dismiss())
                .show();
    }

    @OnClick(R.id.edit_city)
    public void onClickCity() {
        SelectCityActivity.open(this);
    }

    private void save() {
        editable.gender = getCheckedGender();
        editable.firstname = editName.getText().toString();
        user_static.firstname = editName.getText().toString();
        user_static.lastname = editLastname.getText().toString();
        editable.lastname = editLastname.getText().toString();
        editable.date = editBirthdate.getText().toString().isEmpty()?" ":editBirthdate.getText().toString();
        editable.email = editMail.getText().toString().isEmpty()?" ":editMail.getText().toString();
        editable.city = editCity.getText().toString().isEmpty()?" ":editCity.getText().toString();
        editable.phone = editPhone.getText().toString().isEmpty()?" ":editPhone.getText().toString();
        phoneNumber = editPhone.getText().toString().isEmpty()?" ":editPhone.getText().toString();
        editable.sendMail = editSendNotification.isChecked()?0:1;
        editable.eventAccess = editShareEvent.isChecked()?0:1;
        editable.commAccess = editShareCommunity.isChecked()?0:1;
        presenter.edit(editable);
    }

    private String getCheckedGender() {
        if (registrationRbMale.isChecked())
            return registrationRbMale.getText().toString();
        else return registrationRbFemale.getText().toString();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //presenter.loadProfile();
    }

    @Override
    public void loadProfile(User user) {
        editName.setText(String.valueOf(user_static.firstname!=null?user_static.firstname:user.firstname));
        editLastname.setText(String.valueOf(user_static.lastname!=null?user_static.lastname:user.lastname));
        editBirthdate.setText(StringUtils.emptyIfNull(user.date));
        editMail.setText(String.valueOf(user.email));
        editCity.setText(StringUtils.emptyIfNull(user.city));
        editPhone.setText(StringUtils.emptyIfNull(user.phone));
        editSendNotification.setChecked(user.sendMail != 1);
        editShareEvent.setChecked(user.eventAccess != 1); //TODO maybe wrong checking
        editShareCommunity.setChecked(user.commAccess != 1);
        if (user.gender.equalsIgnoreCase(getString(R.string.male)))
            registrationRbMale.setChecked(true);
        else registrationRbFemale.setChecked(true);
    }

    @Override
    public void onComplete() {

                Log.e("my","Потому шо заебало суука нахуй это дерьмо собачье, шило гори в аду");
                finish();

    }

    @Override
    public void exit() {
        user_static = null;
        phoneNumber = null;
        CabinetTabFragment.photoPath = null;
        about = null;
        IntroActivity.openNewTaskClearTop(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String city = SelectCityActivity.getCity(data);
        if (city == null)
            super.onActivityResult(requestCode, resultCode, data);
        else
            editCity.setText(city);
    }

    @OnClick(R.id.edit_feedback)
    void onClick() {
        String appPackageName = getPackageName();
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }
}

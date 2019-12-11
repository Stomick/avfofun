package evfor.fun.skvader.ui.fragments.registration;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import evfor.fun.skvader.R;
import evfor.fun.skvader.ui.activities.SelectCityActivity;
import evfor.fun.skvader.ui.activities.TiteledActivity;
import evfor.fun.skvader.ui.dialogs.DialogProvider;
import evfor.fun.skvader.ui.utils.DateTimePickerUtils;
import evfor.fun.skvader.utils.social.FieldProfileTask;
import evfor.fun.skvader.utils.social.SocialProfileManager;
import evfor.fun.skvader.utils.StringUtils;
import evfor.fun.skvader.utils.callbacks.CallBack1;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;

public class RegistrationFragment extends BaseRegFragment {
    private CallBack1<Model> callBack1;
    @BindView(R.id.registration_rb_male)
    RadioButton registrationRbMale;
    @BindView(R.id.registration_rb_female)
    RadioButton registrationRbFeMale;
    @BindView(R.id.registration_first_name)
    EditText registrationFirstName;
    @BindView(R.id.registration_last_name)
    EditText registrationLastName;
    @BindView(R.id.registration_city)
    TextView registrationCity;
    @BindView(R.id.registration_birth_date_selector)
    TextView registrationBirthDateSelector;

    public static RegistrationFragment create(CallBack1<Model> callBack1) {
        RegistrationFragment fragment = new RegistrationFragment();
        fragment.callBack1 = callBack1;
        return fragment;
    }

    @Override
    protected int layout() {
        return R.layout.fragment_registration;
    }

    @SuppressLint("CheckResult")
    @Override
    protected void initView(Bundle savedInstanceState) {
        SocialProfileManager
                .request(FieldProfileTask.FNAME)
                .subscribe(registrationFirstName::setText);
        SocialProfileManager
                .request(FieldProfileTask.LNAME)
                .subscribe(registrationLastName::setText);
    }

    private void setGender(String gender) {
        if (gender.equalsIgnoreCase("female"))
            registrationRbFeMale.setChecked(true);
    }

    @Override
    public void onAttach(Context context) {
        if (context instanceof TiteledActivity)
            ((TiteledActivity) context).setTitle(getString(R.string.registration));
        super.onAttach(context);
    }

    @OnClick(R.id.registration_birth_date_selector)
    public void onViewClicked() {
        DialogProvider.dateDialog(getContext(), this::onDateSet).show();
    }

    void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, datePicker.getDayOfMonth());
        cal.set(Calendar.MONTH, datePicker.getMonth());
        cal.set(Calendar.YEAR, datePicker.getYear());
        if(System.currentTimeMillis()>cal.getTimeInMillis())
            registrationBirthDateSelector.setText(DateTimePickerUtils.dateToString(datePicker));
        else
            Snackbar.make(getView(),R.string.correct_date,Snackbar.LENGTH_LONG).show();
            //Toast.makeText(getContext(), R.string.correct_date, Toast.LENGTH_SHORT).show();
        datePicker.setMaxDate(System.currentTimeMillis());
    }

    @Override
    public boolean canNext() {
        boolean fn = !registrationFirstName.getText().toString().isEmpty(),
                ln = !registrationLastName.getText().toString().isEmpty(),
                d = StringUtils.containsNumber(registrationBirthDateSelector.getText().toString()),
                city = !registrationCity.getText().toString().isEmpty()
                        && !registrationCity.getText().toString().equalsIgnoreCase(getString(R.string.input_city));
        if (!fn)
            registrationFirstName.setError(getString(R.string.fill_field));
        else if (!ln)
            registrationLastName.setError(getString(R.string.fill_field));
        else if (!d)
            Toast.makeText(getContext(), R.string.select_date, Toast.LENGTH_SHORT).show();
        else if (!city)
            Toast.makeText(getContext(), R.string.input_city, Toast.LENGTH_SHORT).show();
        else {
            callBack1.call(
                    new Model(registrationFirstName.getText().toString(),
                            registrationLastName.getText().toString(),
                            registrationBirthDateSelector.getText().toString(),
                            registrationRbMale.isChecked(),
                            registrationCity.getText().toString())
            );
            return true;
        }
        return false;
    }

    @OnClick(R.id.registration_city)
    public void onClickCity() {
        SelectCityActivity.open(getContext());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        String city = SelectCityActivity.getCity(data);
        if (city != null)
            registrationCity.setText(city);
        else super.onActivityResult(requestCode, resultCode, data);
    }

    public class Model {
        private String firstName;
        private String lastName;
        private String date;
        private boolean gender;
        private String city;

        public Model(String firstName, String lastName, String date, boolean gender, String city) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.date = date;
            this.gender = gender;
            this.city = city;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getDate() {
            return date;
        }

        public boolean isGender() {
            return gender;
        }

        public String getCity() {
            return city;
        }
    }

}

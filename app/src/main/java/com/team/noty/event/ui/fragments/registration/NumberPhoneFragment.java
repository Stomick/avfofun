package com.team.noty.event.ui.fragments.registration;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.mukesh.countrypicker.Country;
import com.team.noty.event.R;
import com.team.noty.event.ui.activities.TiteledActivity;
import com.team.noty.event.ui.dialogs.DialogProvider;
import com.team.noty.event.ui.dialogs.Toaster;
import com.team.noty.event.utils.callbacks.CallBack1;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;

public class NumberPhoneFragment extends BaseRegFragment {

    @BindView(R.id.input_phone_index)
    TextView inputPhoneIndex;
    @BindView(R.id.phone_bot_title)
    TextView phoneBotTitle;
    @BindView(R.id.input_phone_country)
    TextView inputPhoneCountry;
    private CallBack1<String> callBack1;
    private ArrayAdapter<String> countryAdapter;
    @BindView(R.id.input_phone_number)
    EditText phoneNumberET;
    AlertDialog countryDialog;

    static public NumberPhoneFragment create(CallBack1<String> callBack1) {
        NumberPhoneFragment fragment = new NumberPhoneFragment();
        fragment.callBack1 = callBack1;
        return fragment;
    }

    @Override
    protected int layout() {
        return R.layout.fragment_input_phone;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        phoneBotTitle.setVisibility(View.GONE);
        countryAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner_country_item, create());
        inputPhoneCountry.setOnClickListener(view -> {
            countryDialog =
                    DialogProvider.singleList(
                            getString(R.string.chose_county),
                            Country.getAllCountries().indexOf(getCurrent()),
                            getContext(),
                            countryAdapter,
                            this::selectCountry);
            countryDialog.show();
        });
        selectCountry(Country.getAllCountries().indexOf(getCurrent()));
    }

    private void selectCountry(int pos) {
        if (countryDialog != null)
            countryDialog.dismiss();
        inputPhoneIndex.setText(Country.getAllCountries().get(pos).getDialCode());
        inputPhoneCountry.setText(Country.getAllCountries().get(pos).getName());
    }

    private Country getCurrent() {
        Country country = null;
        if (getContext() != null)
            country = Country.getCountryFromSIM(getContext());
        if (country == null)
            country = Country.getCountryByLocale(Locale.getDefault());
        return country;
    }

    private List<String> create() {
        List<String> countries = new ArrayList<>();
        for (Country c : Country.getAllCountries())
            countries.add(c.getName());
        return countries;
    }

    @Override
    public void onAttach(Context context) {
        if (context instanceof TiteledActivity)
            ((TiteledActivity) context).setTitle(getString(R.string.input_number_phone));
        super.onAttach(context);
    }

    @Override
    public boolean canNext() {
        if (phoneNumberET.getText().length() > 7) {
            callBack1.call(inputPhoneIndex.getText().toString() + phoneNumberET.getText().toString());
            return true;
        } else {
            Toaster.duration(getContext(), getString(R.string.incorrect_phone), 500);
            return false;
        }
    }
}

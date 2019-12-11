package com.team.noty.event.ui.fragments.registration;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.team.noty.event.R;
import com.team.noty.event.ui.activities.TiteledActivity;
import com.team.noty.event.utils.StringUtils;
import com.team.noty.event.utils.callbacks.CallBack1;
import com.team.noty.event.utils.social.FieldProfileTask;
import com.team.noty.event.utils.social.SocialProfileManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MailPasswordFragment extends BaseRegFragment {

    @BindView(R.id.mp_pass_input)
    TextInputLayout mpPassInput;
    @BindView(R.id.mp_pass_input_again)
    TextInputLayout mpPassInputAgain;
    private CallBack1<Model> callBack1;

    @BindView(R.id.mail_password_mail)
    EditText mailET;
    @BindView(R.id.mail_password_password)
    EditText passET;
    @BindView(R.id.mail_password_password_again)
    EditText passAgainET;

    static public MailPasswordFragment create(CallBack1<Model> callBack1) {
        MailPasswordFragment fragment = new MailPasswordFragment();
        fragment.callBack1 = callBack1;
        return fragment;
    }

    @Override
    protected int layout() {
        return R.layout.fragment_mail_password;
    }

    @SuppressLint("CheckResult")
    @Override
    protected void initView(Bundle savedInstanceState) {
        SocialProfileManager
                .request(FieldProfileTask.EMAIL)
                .subscribe(mailET::setText);
    }

    @Override
    public void onAttach(Context context) {
        if (context instanceof TiteledActivity)
            ((TiteledActivity) context).setTitle(getString(R.string.you_mail_and_password));
        super.onAttach(context);
    }

    @Override
    public boolean canNext() {
        if (!passAgainET.isFocused())
            passAgainET.getBackground().setColorFilter(getResources().getColor(R.color.under_line),
                    PorterDuff.Mode.SRC_ATOP);
        if (!passET.isFocused())
            passET.getBackground().setColorFilter(getResources().getColor(R.color.under_line),
                    PorterDuff.Mode.SRC_ATOP);
        boolean e = StringUtils.validEmail(mailET.getText().toString()),
                p = !passET.getText().toString().isEmpty()&&passET.getText().toString().length()>5 && !passAgainET.getText().toString().isEmpty()
                        && passET.getText().toString().equals(passAgainET.getText().toString())&&passAgainET.getText().toString().length()>5;

        if (!e) {
            mailET.setError(mailET.getText().toString().isEmpty() ?
                    getString(R.string.fill_field) :
                    getString(R.string.incorrect));
            return false;
        }
        if (!p) {
            if (passET.getText().toString().isEmpty()||passET.getText().toString().length()<6)
                passET.getBackground().setColorFilter(getResources().getColor(R.color.red_button_text),
                        PorterDuff.Mode.SRC_ATOP);
            else if (passAgainET.getText().toString().isEmpty()
                    || !passAgainET.getText().toString().equals(passET.getText().toString())||passAgainET.getText().toString().length()<6)
                passAgainET.getBackground().setColorFilter(getResources().getColor(R.color.red_button_text),
                        PorterDuff.Mode.SRC_ATOP);
            return false;
        } else {
            callBack1.call(new Model(mailET.getText().toString(), passET.getText().toString()));
            return true;
        }
    }

    public class Model {
        private String email;
        private String password;

        Model(String email, String password) {
            this.email = email;
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }
    }

}

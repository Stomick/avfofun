package com.team.noty.event.ui.fragments.intro;

import android.os.Bundle;
import android.view.View;

import com.team.noty.event.R;
import com.team.noty.event.ui.fragments.BaseFragment;

import butterknife.OnClick;

public class StartFragment extends BaseFragment {

    private View.OnClickListener onRegistration;
    private View.OnClickListener onEnter;
    private View.OnClickListener onWoutReg;

    public static StartFragment create(
            View.OnClickListener onRegistration,
            View.OnClickListener onEnter,
            View.OnClickListener onWoutReg) {
        StartFragment fragment = new StartFragment();
        fragment.onRegistration = onRegistration;
        fragment.onEnter = onEnter;
        fragment.onWoutReg = onWoutReg;
        return fragment;
    }

    @Override
    protected int layout() {
        return R.layout.fragment_start;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @OnClick({R.id.intro_reg_button, R.id.intro_enter_button, R.id.intro_woutregistration_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.intro_reg_button:
                if (onRegistration != null)
                    onRegistration.onClick(view);
                break;
            case R.id.intro_enter_button:
                if (onEnter != null)
                    onEnter.onClick(view);
                break;
            case R.id.intro_woutregistration_button:
                if (onWoutReg != null)
                    onWoutReg.onClick(view);
                break;
        }
    }
}

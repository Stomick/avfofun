package com.team.noty.event.ui.activities;


import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;

import com.team.noty.event.R;

public class EmptyBarActivity extends EmptyActivity {

    @Override
    protected void actionBar(@NonNull ActionBar bar) {
        setBackArrow(bar);
        bar.setTitle(R.string.edit);
    }
}

package evfor.fun.skvader.ui.activities;


import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;

import evfor.fun.skvader.R;

public class EmptyBarActivity extends EmptyActivity {

    @Override
    protected void actionBar(@NonNull ActionBar bar) {
        setBackArrow(bar);
        bar.setTitle(R.string.edit);
    }
}

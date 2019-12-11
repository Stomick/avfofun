package evfor.fun.skvader.ui.utils.reg_step_view;

import android.view.View;

import butterknife.ButterKnife;

public class BaseStep {
    protected View view;

    public void setView(View view){
        this.view = view;
        ButterKnife.bind(this, view);
    }
}

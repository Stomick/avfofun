package evfor.fun.skvader.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import evfor.fun.skvader.mvp.views.BaseView;
import evfor.fun.skvader.ui.activities.LoadIndicator;
import evfor.fun.skvader.ui.dialogs.DialogProvider;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends MvpAppCompatFragment implements BaseView, LoadIndicator {

    private Unbinder unbinder;
    protected LoadIndicator loadIndicator = LoadIndicator.empty;

    public BaseFragment() {
        // Required empty public constructor
    }

    @LayoutRes
    protected abstract int layout();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof LoadIndicator)
            loadIndicator = ((LoadIndicator) context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        hideLoad();
        loadIndicator = LoadIndicator.empty;
    }

    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container,
                                   Bundle savedInstanceState) {
        if (layout() != 0) {
            View view = inflater.inflate(layout(), container, false);
            unbinder = ButterKnife.bind(this, view);
            initView(savedInstanceState);
            return view;
        } else return super.onCreateView(inflater, container, savedInstanceState);
    }

    protected abstract void initView(Bundle savedInstanceState);

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null)
            unbinder.unbind();
    }

    @Override
    public void hideLoad() {
        loadIndicator.hideLoad();
    }

    @Override
    public void showLoad() {
        loadIndicator.showLoad();
    }

    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void showInfo(int messageId, Object... objects) {
        if (messageId != 0)
            DialogProvider.infoOk(getContext(), getString(messageId, objects)).show();
        else DialogProvider.infoOk(getContext(), String.valueOf(objects[0])).show();
    }
}

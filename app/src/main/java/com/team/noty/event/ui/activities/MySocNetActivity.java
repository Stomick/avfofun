package com.team.noty.event.ui.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.team.noty.event.R;
import com.team.noty.event.mvp.presenters.SocPresenter;
import com.team.noty.event.mvp.views.SocView;
import com.team.noty.event.ui.adapters.SocAdapter;
import com.team.noty.event.ui.dialogs.DialogProvider;
import com.team.noty.event.ui.models.UiSoc;
import com.team.noty.event.ui.utils.RecyclerUtils;
import com.team.noty.event.utils.callbacks.CallBack1;
import com.team.noty.event.utils.social.Social;

import java.util.List;

import butterknife.BindView;

public class MySocNetActivity extends BaseActivity implements SocView {

    @InjectPresenter
    SocPresenter presenter;
    @BindView(R.id.mysoc_list)
    RecyclerView mysocList;
    private SocAdapter adapter;
    DialogFragment dialog;
    CallBack1<String> request;
    CallBack1<String> setter;
    String vk, fb, tw, inst;

    @Override
    protected int layout() {
        return R.layout.activity_mysocnet;
    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
        adapter = SocAdapter.editable();
        adapter.setSetTextCallBack(this::input);
        RecyclerUtils.setVerticalAdapter(mysocList, adapter);
        presenter.load();
    }

    private void input(CallBack1<String> callBack, UiSoc start) {
        selector(start);
        setter = callBack;
        dialog = DialogProvider.editText(getString(R.string.set_url), request, start.url);
        dialog.show(getSupportFragmentManager(), "write");
    }

    private void selector(UiSoc soc) {
        switch (soc.image_res) {
            case R.drawable.ic_tw:
                request = text -> {
                    tw = text;
                    if (setter != null)
                        setter.call(text);
                };
                break;
            case R.drawable.ic_vk:
                request = text -> {
                    vk = text;
                    if (setter != null)
                        setter.call(text);
                };
                break;
            case R.drawable.ic_inst:
                request = text -> {
                    inst = text;
                    if (setter != null)
                        setter.call(text);
                };
                break;
            default:
                request = text -> {
                    fb = text;
                    if (setter != null)
                        setter.call(text);
                };
        }
    }

    @Override
    protected void actionBar(@NonNull ActionBar bar) {
        setBackArrow(bar);
        bar.setTitle(R.string.my_soc);
    }

    @Override
    public void onComplete() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }

    @Override
    public void loadList(List<UiSoc> list) {
        adapter.load(list);
    }

    @Override
    protected int menuLayout() {
        return R.menu.save_menu_button;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_save) {
            presenter.setSoc(fb, tw, vk, inst);
            finish();
            return true;
        } else if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

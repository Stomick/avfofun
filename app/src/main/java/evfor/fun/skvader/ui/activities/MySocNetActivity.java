package evfor.fun.skvader.ui.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.arellomobile.mvp.presenter.InjectPresenter;
import evfor.fun.skvader.R;
import evfor.fun.skvader.mvp.presenters.SocPresenter;
import evfor.fun.skvader.mvp.views.SocView;
import evfor.fun.skvader.ui.adapters.SocAdapter;
import evfor.fun.skvader.ui.dialogs.DialogProvider;
import evfor.fun.skvader.ui.models.UiSoc;
import evfor.fun.skvader.ui.utils.RecyclerUtils;
import evfor.fun.skvader.utils.callbacks.CallBack1;

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

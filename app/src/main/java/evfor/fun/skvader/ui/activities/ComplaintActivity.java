package evfor.fun.skvader.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.ActionBar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import evfor.fun.skvader.R;
import evfor.fun.skvader.models.ActId;
import evfor.fun.skvader.mvp.presenters.ComplaintPresenter;
import evfor.fun.skvader.mvp.views.CompletableView;
import evfor.fun.skvader.ui.dialogs.DialogProvider;
import evfor.fun.skvader.utils.callbacks.CallBack;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class ComplaintActivity extends BaseActivity implements CompletableView {

    private static final String ID = "id";
    private static final String MODE = "mode";
    private static final String USER = "u";
    private static final String DELETEUSER = "du";
    private static final String DELETE = "de";
    private static final String EVENT = "e";
    private static final String COMM = "c";

    @InjectPresenter
    ComplaintPresenter presenter;
    @BindView(R.id.comp_select)
    TextView compSelect;
    @BindView(R.id.comp_description)
    EditText compDescription;
    @BindView(R.id.comp_select_title)
    TextView compSelectTitle;
    @BindView(R.id.comp_button)
    Button compButton;
    private int checked = -1;
    private int checked_item = -1;
    private String id;
    private ActId eventComId;
    private CallBack sendRequest;
    private int listId = R.array.comp_list;
    String title;

    public static void openDeleteUser(Context context, String id, boolean isEvent, String eventComId) {
        context.startActivity(new Intent(context, ComplaintActivity.class)
                .putExtra(MODE, DELETEUSER).putExtra(ID, id)
                .putExtra(isEvent ? EVENT : COMM, eventComId)
        );
    }

    public static void openFromUser(Context context, String id) {
        context.startActivity(new Intent(context, ComplaintActivity.class).putExtra(MODE, USER).putExtra(ID, id));
    }

    public static void openFromEvent(Context context, String id) {
        context.startActivity(new Intent(context, ComplaintActivity.class).putExtra(MODE, EVENT).putExtra(ID, id));
    }

    public static void openFromComm(Context context, String id) {
        context.startActivity(new Intent(context, ComplaintActivity.class).putExtra(MODE, COMM).putExtra(ID, id));
    }

    public static void openFromDeleteEvent(Context context, String id) {
        context.startActivity(new Intent(context, ComplaintActivity.class).putExtra(MODE, DELETE).putExtra(EVENT, id));
    }

    public static void openFromDeleteComm(Context context, String id) {
        context.startActivity(new Intent(context, ComplaintActivity.class).putExtra(MODE, DELETE).putExtra(COMM, id));
    }

    @Override
    protected int layout() {
        return R.layout.activity_complaint;
    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
        choseMode(getIntent().getExtras());
        title = getString(R.string.chose_cause);
    }

    @Override
    protected void actionBar(@NonNull ActionBar bar) {
        setBackArrow(bar);
    }

    private void choseMode(Bundle bundle) {
        if (bundle != null) {
            if (bundle.containsKey(MODE)) {
                String mode = bundle.getString(MODE);
                assert mode != null;
                if (mode.equals(USER)) {
                    setComplainMode(R.string.on_user);
                    listId = R.array.comp_list;
                    if (bundle.containsKey(ID)) {
                        id = bundle.getString(ID);
                        sendRequest = () ->
                                presenter.sendOnUser(
                                        compDescription.getText().toString(),
                                        id,
                                        checked
                                );
                        return;
                    }
                }
                if (mode.equals(EVENT) || mode.equals(COMM)) {
                    setComplainMode(R.string.on_event);
                    if (bundle.containsKey(ID)) {
                        listId = R.array.comp_list_event;
                        id = bundle.getString(ID);
                        if (mode.equals(EVENT))
                            sendRequest = () -> presenter.sendOnEvent(
                                    compDescription.getText().toString(),
                                    id,
                                    checked);
                        else if (mode.equals(COMM))
                            sendRequest = () -> presenter.sendOnCommunity(
                                    compDescription.getText().toString(),
                                    id,
                                    checked);
                        return;
                    }
                }
                if (mode.equals(DELETE)) {
                    setDeleteMode();
                    listId = R.array.delete_event_community;
                    if (bundle.containsKey(EVENT)) {
                        eventComId = new ActId(bundle.getString(EVENT), true);
                        sendRequest = () -> presenter.deleteEvent(
                                compDescription.getText().toString(),
                                eventComId.id,
                                checked);
                        return;
                    } else if (bundle.containsKey(COMM)) {
                        eventComId = new ActId(bundle.getString(COMM), true);
                        sendRequest = () -> presenter.deleteCommunity(
                                compDescription.getText().toString(),
                                eventComId.id,
                                checked);
                        return;
                    }
                }
                if (mode.equals(DELETEUSER)) {
                    setDeleteUserMode();
                    listId = R.array.comp_list;
                    id = bundle.getString(ID);
                    if (bundle.containsKey(EVENT))
                        eventComId = new ActId(bundle.getString(EVENT), true);
                    else
                        eventComId = new ActId(bundle.getString(COMM), false);
                    sendRequest = () ->
                            presenter.deletePartipicant(id,
                                    new ActId(eventComId.id, eventComId.isEvent),
                                    checked,
                                    compDescription.getText().toString());
                    return;
                }
            }
        }

        Toast.makeText(this, R.string.error, Toast.LENGTH_SHORT).show();
        finish();
    }

    private void setDeleteUserMode() {
        setTitle(R.string.comp_title_delete_user);
        compSelectTitle.setText(getString(R.string.comp_select_title_delete_user));
        compDescription.setHint(getString(R.string.comp_description_hint_delete_user));
    }

    private void setDeleteMode() {
        setTitle(R.string.comp_select_title_delete);
        compSelectTitle.setText(getString(R.string.comp_select_title_delete));
        compDescription.setHint(getString(R.string.comp_description_hint_delete));
        title = getString(R.string.cause_delete);
    }

    private void setComplainMode(@StringRes int on) {
        setTitle(getString(R.string.comp_on_t, getString(on)));
        compSelectTitle.setText(getString(R.string.comp_select_title_t, getString(on)));
        compDescription.setHint(getString(R.string.comp_description_hint_t, getString(on)));
    }

    @Override
    public void setTitle(CharSequence title) {
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(title);
    }

    @Override
    public void setTitle(int titleId) {
        setTitle(getString(titleId));
    }

    @Override
    public void onComplete() {
        DialogProvider.infoOk(this, R.string.sent, this::finish).show();
    }

    @OnClick({R.id.comp_select, R.id.comp_select_title})
    public void onCompSelectClicked() {
        DialogProvider.radioList(title,
                this,
                checked_item,
                getResources().getStringArray(listId),
                this::selectCause)
                .show();
    }

    private void selectCause(int i) {
        checked_item = i;
        checked = getSelectId(i);
        compSelect.setText(getResources().getStringArray(listId)[i]);
        //compButton.setEnabled(checkEnableButton());
        compButton.setEnabled(true);
    }

    private int getSelectId(int i) {
        switch (listId) {
            case R.array.delete_event_community:
                return getListIdDeleteEvent(i);
            case R.array.comp_list_event:
                return getListIdComplainEvent(i);
            case R.array.comp_list:
                return getListIdComplainUser(i);
            default:
                return 1;
        }
    }

    private int getListIdDeleteEvent(int i) {
        switch (i) {
            case 0:
                return 11;
            case 1:
                return 12;
            case 2:
                return eventComId != null && eventComId.isEvent ? 10 : 9;
            default:
                return 1;
        }
    }

    private int getListIdComplainEvent(int i) {
        switch (i) {
            case 0:
                return 3;
            case 1:
                return 4;
            case 2:
                return 5;
            default:
                return 1;
        }
    }

    private int getListIdComplainUser(int i) {
        switch (i) {
            case 0:
                return 8;
            case 1:
                return 6;
            case 2:
                return 7;
            default:
                return 1;
        }
    }

    @OnTextChanged(R.id.comp_description)
    void onTextChanged() {
        compButton.setEnabled(checkEnableButton());
    }

    private boolean checkEnableButton() {
        return checked_item == 4 && !compDescription.getText().toString().isEmpty() || checked_item != 4;
    }

    @OnClick(R.id.comp_button)
    public void onCompButtonClicked() {
        if (sendRequest != null)
            sendRequest.call();
        TabsActivity.openSearch(this);
    }
}

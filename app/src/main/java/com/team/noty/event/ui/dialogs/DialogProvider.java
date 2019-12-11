package com.team.noty.event.ui.dialogs;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.StrictMode;
import android.support.annotation.StringRes;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ListAdapter;

import com.bogdwellers.pinchtozoom.ImageMatrixTouchHandler;
import com.team.noty.event.R;
import com.team.noty.event.ui.utils.ViewUtils;
import com.team.noty.event.ui.utils.listeners.SimpleDialogMultiListener;
import com.team.noty.event.ui.utils.listeners.SimpleDialogRadioListeners;
import com.team.noty.event.ui.utils.listeners.SimpleDismissListener;
import com.team.noty.event.utils.DateFormatter;
import com.team.noty.event.utils.ImageLoader;
import com.team.noty.event.utils.callbacks.CallBack;
import com.team.noty.event.utils.callbacks.CallBack1;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.security.auth.callback.Callback;

public class DialogProvider {

    public static DialogFragment editText(String title, CallBack1<String> callBack, String start){
        return EditTextDialog.create(title, callBack, start);
    }

    public static AlertDialog deleteUserDialog(Context context, String userName, String eventCommName, CallBack delete){
        return setStyle(builder(context)
        .setTitle(R.string.delete_dialog_title)
                .setMessage(context.getString(R.string.delete_dialog, userName, eventCommName))
                .setNegativeButton(R.string.delete, new SimpleDialogMultiListener(delete))
                .setPositiveButton(R.string.cancel, DialogProvider::dismiss)
                .create()
        );
    }

    public static AlertDialog dateDialog(Context context, DatePickerDialog.OnDateSetListener listener) {
        Calendar now = Calendar.getInstance();
        return new DatePickerDialog(context, R.style.MyDialogTheme, listener, now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));
    }
    public static AlertDialog dateDialog(Context context, String now, DatePickerDialog.OnDateSetListener listener) {
        Calendar current = DateFormatter.fromString(now);
        return new DatePickerDialog(context, R.style.MyDialogTheme, listener, current.get(Calendar.YEAR), current.get(Calendar.MONTH), current.get(Calendar.DAY_OF_MONTH));
    }

    public static AlertDialog timeDialog(Context context, TimePickerDialog.OnTimeSetListener listener) {
        Calendar calendar = Calendar.getInstance();
        return new TimePickerDialog(context, R.style.MyDialogTheme, listener,
                calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
    }

    public static AlertDialog listDialog(Context context, List<String> categories, DialogInterface.OnClickListener choseListener, DialogInterface.OnClickListener okListener) {
        return setStyle(builder(context)
                .setTitle(R.string.chose_category)
                .setSingleChoiceItems(categories.toArray(new String[categories.size()]), -1, choseListener)
                .setPositiveButton(R.string.ok, okListener)
                .setNegativeButton(R.string.cancel, DialogProvider::dismiss)
                .create());
    }

    public static AlertDialog daysDialog(Context context, boolean[] checked, DialogInterface.OnClickListener okListener) {
        return setStyle(builder(context)
                .setTitle(R.string.chose_category)
                .setMultiChoiceItems(R.array.days_of_week, checked, (dialogInterface, i, b) -> checked[i] = b)
                .setPositiveButton(R.string.ok, okListener)
                .setNegativeButton(R.string.cancel, DialogProvider::dismiss)
                .create());
    }

    private static void dismiss(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }

    public static AlertDialog needReg(Context context, CallBack regAction) {
        return setStyle(builder(context)
                .setPositiveButton(R.string.cancel, DialogProvider::dismiss)
                .setNegativeButton(R.string.registration, new SimpleDialogMultiListener(regAction))
                .setTitle(R.string.need_registration_title)
                .setMessage(R.string.need_registration)
                .create());
    }
    public static AlertDialog needReg(Context context, CallBack regAction,CallBack regFinish) {
        return setStyle(builder(context)
                .setPositiveButton(R.string.cancel, new SimpleDialogMultiListener(regFinish))
                .setNegativeButton(R.string.registration, new SimpleDialogMultiListener(regAction))
                .setTitle(R.string.need_registration_title)
                .setMessage(R.string.need_registration)
                .create());
    }

    public static AlertDialog exit(Context context, CallBack exit) {
        return setStyle(builder(context)
                .setPositiveButton(R.string.cancel, DialogProvider::dismiss)
                .setNegativeButton(R.string.exit, new SimpleDialogMultiListener(exit))
                .setTitle(R.string.exit_title)
                .setMessage(R.string.exit_massage)
                .create());
    }

    public static AlertDialog infoOk(Context context) {
        return setStyle(builder(context)
                .setPositiveButton(R.string.ok, DialogProvider::dismiss)
                .setTitle("")
                .setMessage("Empty")
                .create());
    }

    public static AlertDialog infoOk(Context context, String message) {
        return setStyle(builder(context)
                .setPositiveButton(R.string.ok, DialogProvider::dismiss)
                .setTitle("")
                .setMessage(message)
                .create());
    }
    public static AlertDialog infoOk(Context context, String title, String message) {
        return setStyle(builder(context)
                .setPositiveButton(R.string.ok, DialogProvider::dismiss)
                .setTitle(title)
                .setMessage(message)
                .create());
    }

    public static AlertDialog infoOk(Context context, String message, CallBack callBack) {
        return setStyle(builder(context)
                .setPositiveButton(R.string.ok, new SimpleDialogMultiListener(callBack))
                .setTitle("")
                .setMessage(message)
                .create());
    }

    public static AlertDialog infoOk(Context context, @StringRes int message) {
        return setStyle(builder(context)
                .setPositiveButton(R.string.ok, DialogProvider::dismiss)
                .setTitle("")
                .setMessage(message)
                .create());
    }

    public static AlertDialog infoOk(Context context, @StringRes int message, CallBack callBack) {

        AlertDialog dialog = setStyle(builder(context)
                .setPositiveButton(R.string.ok, new SimpleDialogMultiListener(callBack))
                .setTitle("")
                .setMessage(message)
                .create());
        dialog.setOnDismissListener(new SimpleDismissListener(callBack));
        return dialog;
    }

    public static AlertDialog cameraOrGallery(Context context, DialogInterface.OnClickListener select) {
        return setStyle(builder(context)
                .setTitle(R.string.photo)
                .setItems(new CharSequence[]{context.getString(R.string.load_from_gallery), context.getString(R.string.take_picture)}, select)
                .create());
    }

    public static AlertDialog avatarOptions(Context context, CallBack gallery, CallBack camera, CallBack open, CallBack delete) {
        return setStyle(builder(context)
                .setTitle(R.string.photo)
                .setItems(new CharSequence[]{
                        context.getString(R.string.load_from_gallery),
                        context.getString(R.string.take_picture),
                        context.getString(R.string.open),
                        context.getString(R.string.delete)
                }, new SimpleDialogMultiListener(gallery, camera, open, delete))
                .create());
    }

    public static AlertDialog radioList(String title, Context context, int checked, String[] items, CallBack1<Integer> choseItem) {
        return setStyle(builder(context)
                .setTitle(title)
                .setSingleChoiceItems(items, checked, (dialogInterface, i) -> {
                })
                .setPositiveButton(R.string.ok, new SimpleDialogRadioListeners(choseItem))
                .create());
    }

    public static AlertDialog listPhoto(Context context, String[] items, CallBack... callBacks) {
        return list(context.getString(R.string.photo), context, items, callBacks);
    }
    public static AlertDialog list(String title, Context context, String[] items, CallBack... callBacks) {
        return setStyle(builder(context)
                .setTitle(title)
                .setItems(items, new SimpleDialogMultiListener(callBacks))
                .create());
    }
    public static AlertDialog singleList(String title, Integer selected, Context context, String[] items, CallBack1<Integer> callBack) {
        return setStyle(builder(context)
                .setTitle(title)
                .setSingleChoiceItems(items, selected, new SimpleDialogRadioListeners(callBack))
                .create());
    }
    public static AlertDialog singleList(String title, Integer selected, Context context, ListAdapter items, CallBack1<Integer> callBack) {
        return setStyle(builder(context)
                .setTitle(title)
                .setSingleChoiceItems(items, selected, new SimpleDialogRadioListeners(callBack))
                .create());
    }

    private static AlertDialog.Builder builder(Context context) {
        return new AlertDialog.Builder(context, R.style.MyDialogTheme);
    }

    private static AlertDialog setStyle(AlertDialog dialog) {
        dialog.setOnShowListener(DialogProvider::setStyle);
        return dialog;
    }

    private static void setStyle(DialogInterface dialogInterface) {
        if (dialogInterface instanceof AlertDialog) {
            if (((AlertDialog) dialogInterface).getWindow() != null)
                ViewUtils.overrideFonts(((AlertDialog) dialogInterface).getContext(), ((AlertDialog) dialogInterface).getWindow().getDecorView());
            ((AlertDialog) dialogInterface).getButton(AlertDialog.BUTTON_NEGATIVE).setAllCaps(true);
            ((AlertDialog) dialogInterface).getButton(AlertDialog.BUTTON_POSITIVE).setAllCaps(true);
            ((AlertDialog) dialogInterface).getButton(AlertDialog.BUTTON_NEUTRAL).setAllCaps(true);
        }
    }
}

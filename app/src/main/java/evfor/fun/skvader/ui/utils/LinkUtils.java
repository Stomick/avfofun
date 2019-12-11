package evfor.fun.skvader.ui.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;

import evfor.fun.skvader.R;
import evfor.fun.skvader.models.ActId;
import evfor.fun.skvader.network.URLS;
import evfor.fun.skvader.ui.dialogs.Toaster;

public class LinkUtils {

    public static void share(Context context, String link) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, link);
        sendIntent.setType("text/plain");
        context.startActivity(sendIntent);
    }

    public static void copy(Context context, String link) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("", link);
        if (clipboard != null) {
            clipboard.setPrimaryClip(clip);
            Toaster.duration(context, context.getString(R.string.copied), 1000);
        }
    }

    public static String createUserLink(String id) {
        return URLS.domain + "account?id=" + id;
    }

    public static String createEventCommLink(ActId actId) {
        return URLS.domain + (actId.isEvent ? "events" : "communities") + "?id=" + actId.id;
    }
}

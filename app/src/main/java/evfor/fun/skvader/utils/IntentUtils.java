package evfor.fun.skvader.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

public class IntentUtils {
    public static void openImageView(Context context, String path) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse(path), "image/*");
        context.startActivity(intent);
    }

    public static void openVK(Context context, String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public static void openINST(Context context, String url) {
        Uri uri = Uri.parse(url);
        Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

        likeIng.setPackage("com.instagram.android");

        try {
            context.startActivity(likeIng);
        } catch (ActivityNotFoundException e) {
            context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(url)));
        }
    }

    public static void openTW(Context context, String url) {
        Intent intent;
        try {
            context.getApplicationContext().getPackageManager().getPackageInfo("com.twitter.android", 0);
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception e) {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        }
        context.startActivity(intent);
    }

    public static void openFB(Context context, String url) {
        try {
            context.getPackageManager().getPackageInfo("com.facebook.katana", 0);
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/" + url.substring(url.lastIndexOf('/')))));
        } catch (Exception e) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        }
    }
}

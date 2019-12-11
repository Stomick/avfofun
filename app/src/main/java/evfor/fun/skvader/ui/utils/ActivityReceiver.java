package evfor.fun.skvader.ui.utils;

import android.content.Intent;

public interface ActivityReceiver {
    void onActivityResult(int requestCode, int resultCode, Intent data);
}

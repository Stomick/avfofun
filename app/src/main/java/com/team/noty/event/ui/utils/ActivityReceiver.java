package com.team.noty.event.ui.utils;

import android.content.Intent;

public interface ActivityReceiver {
    void onActivityResult(int requestCode, int resultCode, Intent data);
}

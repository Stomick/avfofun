package com.team.noty.event.ui.models;

import android.content.Context;

import com.team.noty.event.R;
import com.team.noty.event.utils.IntentUtils;

public class UiSoc {
    public String url;
    public int image_res;
    public String title;
    public int hint_res;

    public UiSoc(String url, int image_res, String title, int hint_res) {
        this.url = url;
        this.image_res = image_res;
        this.title = title;
        this.hint_res = hint_res;
    }

    public void open(Context context) {
        switch (image_res) {
            case R.drawable.ic_tw:
                IntentUtils.openTW(context, url);
                break;
            case R.drawable.ic_vk:
                IntentUtils.openVK(context, url);
                break;
            case R.drawable.ic_inst:
                IntentUtils.openINST(context, url);
                break;
            default:
                IntentUtils.openFB(context, url);
        }
    }

    public UiSoc(int image_res, int hint_res) {
        this.image_res = image_res;
        this.hint_res = hint_res;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof UiSoc)
            return ((UiSoc) obj).image_res == image_res;
        return super.equals(obj);
    }
}

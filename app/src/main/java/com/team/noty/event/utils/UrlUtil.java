package com.team.noty.event.utils;

import com.team.noty.event.network.URLS;

public class UrlUtil {
    public static String correct(String url){
        if (url == null)
            return "";
        url = replaceSecondDoubleSlash(url);
        if (url.startsWith("/talk/"))
            return URLS.domain + url.replace("/talk/", "talk/");
        if (url.startsWith("img/") || url.startsWith("/img/")) {
            return URLS.imgdomain + url;
        } else return url;
    }

    private static String replaceSecondDoubleSlash(String url) {
        url = url.replace("://", "#");
        url = url.replaceAll("//", "/");
        url = url.replace("#", "://");
        return url;
    }
}

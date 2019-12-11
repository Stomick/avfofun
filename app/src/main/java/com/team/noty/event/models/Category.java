package com.team.noty.event.models;

import android.support.annotation.NonNull;

public class Category implements Comparable<Category>{
    public String id;
    public String logoUrl;
    public String name;

    public Category(String id, String logoUrl, String name) {
        this.id = id;
        this.logoUrl = logoUrl;
        this.name = name;
    }

    @Override
    public int compareTo(@NonNull Category category) {
        return -id.compareTo(category.id);
    }
}

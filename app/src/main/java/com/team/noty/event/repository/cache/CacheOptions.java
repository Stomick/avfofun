package com.team.noty.event.repository.cache;

public class CacheOptions {
    public int seconds;
    public int count = 0;

    public CacheOptions(int seconds) {
        this.seconds = seconds;
    }
}

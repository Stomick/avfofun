package com.team.noty.event.repository.cache;

import com.team.noty.event.exceptions.NotFoundException;
import com.team.noty.event.utils.Log;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

public class CacheMap<K, T> implements Cache<K, T> {

    private Map<K, TimeStampMillis<T>> map;
    private CacheOptions options;

    @Inject
    CacheMap(CacheOptions options) {
        this.options = options;
        this.map = new HashMap<>();
    }

    @Override
    public boolean contains(K key) {
        deleteIfOld(key);
        return map.containsKey(key);
    }

    @Override
    public void put(K key, T item) {
        if (canAdd()) {
            map.put(key, new TimeStampMillis<>(getTime(), item));
        }
    }

    private boolean canAdd() {
        return options.count == 0 || map.size() < options.count;
    }

    @Override
    public T get(K key) throws NotFoundException {
        deleteIfOld(key);
        if (map.containsKey(key))
            return map.get(key).item;
        throw new NotFoundException(String.valueOf(key));
    }

    private void deleteIfOld(K key) {
        if (map.containsKey(key) && itemPassed(map.get(key).timestamp)) {
            map.remove(key);
        }
    }

    private boolean itemPassed(long timestamp) {
        return (-timestamp + getTime()) / 1000 > options.seconds;
    }

    private long getTime() {
        return System.currentTimeMillis();
    }

    private class TimeStampMillis<V> {
        private long timestamp;
        private V item;

        TimeStampMillis(long timestamp, V item) {
            this.timestamp = timestamp;
            this.item = item;
        }

        @Override
        public boolean equals(Object obj) {
            try {
                if (obj instanceof TimeStampMillis) {
                    V a = item;
                    V b = ((TimeStampMillis<V>) obj).item;
                    return (a == b) || (a != null && a.equals(b));
                }
            } catch (Exception ignore) {
            }
            return super.equals(obj);
        }
    }

}

package com.team.noty.event.repository.cache;

import com.team.noty.event.exceptions.NotFoundException;

import io.reactivex.Single;

public interface Cache<K, T> {
    void put(K key, T item);
    T get(K key) throws NotFoundException;
    boolean contains(K key);
}

package evfor.fun.skvader.repository.cache;

import evfor.fun.skvader.exceptions.NotFoundException;

public interface Cache<K, T> {
    void put(K key, T item);
    T get(K key) throws NotFoundException;
    boolean contains(K key);
}

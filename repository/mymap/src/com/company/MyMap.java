package com.company;

import java.util.ArrayList;
import java.util.List;

public class MyMap<T, V> {
    public static class MapException extends Exception {
        public MapException(String message) {
            super(message);
        }
    }

    private class SimpleMap {
        public T key;
        public V value;
        public SimpleMap next;

        public SimpleMap(T key, V value, SimpleMap next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public SimpleMap(T key, V value) {
            this(key, value, null);
        }
    }

    private SimpleMap head = null;
    private SimpleMap tail = null;
    private int size = 0;


    public void addFirst(T key, V value) {
        head = new SimpleMap(key, value, head);
        if (size == 0) {
            tail = head;
        }
        size++;
    }

    public void addLast(T key, V value) {
        if (size == 0) {
            head = tail = new SimpleMap(key, value);
        } else {
            tail.next = new SimpleMap(key, value);
            tail = tail.next;
        }
        size++;
    }

    private void checkEmptyError() throws MapException {
        if (size == 0) {
            throw new MapException("Empty Map");
        }
    }

    private SimpleMap getNodeKey(T key) {
        SimpleMap curr = head;
        while ((curr.next != null) && (curr.key != key)) {
            curr = curr.next;
        }
        return curr;
    }

    private SimpleMap getNodeValue(V value) {
        SimpleMap curr = head;
        while ((curr.next != null) && (curr.value != value)) {
            curr = curr.next;
        }
        return curr;
    }

    private SimpleMap getNodePrevKey(T key) {
        SimpleMap curr = head;
        SimpleMap prev = head;
        while (curr.key != key) {
            prev = curr;
            curr = curr.next;
        }
        return prev;
    }

    private SimpleMap getNodePrevValue(V value) {
        SimpleMap curr = head;
        SimpleMap prev = head;
        while (curr.value != value) {
            prev = curr;
            curr = curr.next;
        }
        return prev;
    }

    public void removeFirst() throws MapException {
        checkEmptyError();
        head = head.next;
        if (size == 1) {
            head = null;
        }
        size--;

    }

    public void removeByKey(T key) throws MapException {
        if (getNodeKey(key) == null) {
            throw new MapException("incorrect key");
        }

        if (size == 1) {
            removeFirst();
        } else {

            SimpleMap prev = getNodePrevKey(key);
            prev.next = prev.next.next;
            if (prev.next == null) {
                tail = prev;
            }
        }
    }

    public void removeByValue(V value) throws MapException {
        if (getNodeValue(value) == null) {
            throw new MapException("incorrect key");
        }
        if (size == 1) {
            removeFirst();
        } else {
            SimpleMap prevKey = getNodePrevValue(value);
            SimpleMap prevValue = getNodePrevValue(value);

            prevValue.next = prevValue.next.next;

            if (prevValue.next == null) {
                tail = prevValue;
                size--;

            }
        }

    }
    public int size() {
        return size;
    }

    public T getKey(T key) throws MapException {
        return getNodeKey(key).key;
    }

    public V getValue(T key) throws MapException {
        return getNodeKey(key).value;
    }

    public T getFirstKey() throws MapException {
        checkEmptyError();
        return head.key;
    }

    public V getFirstValue() throws MapException {
        checkEmptyError();
        return head.value;
    }

    public T getLastKey() throws MapException {
        checkEmptyError();
        return tail.key;
    }

    public V getLastValue() throws MapException {
        checkEmptyError();
        return tail.value;
    }

    public List<T> getKeys() {
        List<T> keys = new ArrayList<>();
        SimpleMap curr = head;
        keys.add(head.key);
        while (curr.next != null) {
            curr = curr.next;
            keys.add(curr.key);
        }
        return keys;
    }
}
